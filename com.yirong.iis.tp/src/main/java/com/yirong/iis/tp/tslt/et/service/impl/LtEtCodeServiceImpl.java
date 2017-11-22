package com.yirong.iis.tp.tslt.et.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.commons.cache.eif.RedisCacheEif;
import com.yirong.commons.util.datatype.StringUtil;
import com.yirong.iis.tp.common.dao.LtEtCodeDao;
import com.yirong.iis.tp.common.entity.LtEtCode;
import com.yirong.iis.tp.tslt.et.service.LtEtCodeService;

/**
 * 功能描述：elektron代码表service实现类
 * 
 * @author
 *         <p>
 *         创建时间 ：2017-11-20 15:49:46
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@Service("LtEtCodeServiceImpl")
public class LtEtCodeServiceImpl extends BaseService<LtEtCode, String> implements LtEtCodeService {

	/**
	 * dao注入
	 */
	@Autowired
	private LtEtCodeDao ltEtCodeDao;

	/**
	 * 功能描述：获取dao操作类
	 * 
	 * @author
	 *         <p>
	 *         创建时间 ：2017-11-20 15:49:46
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	@Override
	public IBaseDao<LtEtCode, String> getBaseDao() {
		return ltEtCodeDao;
	}

	/**
	 * 功能描述：获取所有代码信息（缓存）
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年11月22日 下午8:07:23
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @return
	 *
	 */
	@Override
	public LtEtCode cacheFindByRicCode(String ricCode) {
		String redisId = "lt" + ricCode;
		String id = RedisCacheEif.hget(redisId, "id");
		LtEtCode ltEtCode = null;
		if (StringUtil.isNotNullOrEmpty(id)) {// 缓存有数据，直接使用
			ltEtCode = new LtEtCode();
			ltEtCode.setId(id);
			ltEtCode.setRicCode(RedisCacheEif.hget(redisId, "code"));
		} else {// 缓存没有数据，需查询数据库
			ltEtCode = this.ltEtCodeDao.findByRicCode(ricCode);
			if (null != ltEtCode) {// 数据库有数据，同时保存一份至缓存
				RedisCacheEif.hset(redisId, "id", ltEtCode.getId());
				RedisCacheEif.hset(redisId, "code", ricCode);
			}
		}
		return ltEtCode;
	}

	/**
	 * 功能描述：查询所有编码信息
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年11月22日 下午8:41:34
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @return
	 *
	 */
	@Override
	public List<LtEtCode> findAll() {
		return this.ltEtCodeDao.findAll();
	}

}
