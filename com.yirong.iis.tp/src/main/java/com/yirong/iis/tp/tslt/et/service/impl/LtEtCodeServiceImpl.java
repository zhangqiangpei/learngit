package com.yirong.iis.tp.tslt.et.service.impl;

import java.util.List;
import java.util.Map;

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
			Map<String, String> map = RedisCacheEif.hgetall(redisId);
			ltEtCode.setRicCode(map.get("code"));
			ltEtCode.setParentRicCode(map.get("pcode"));
			ltEtCode.setCodeDesc(map.get("desc"));
			ltEtCode.setCodeName(map.get("name"));
			ltEtCode.setCodeType(map.get("codeType"));
			ltEtCode.setCountryEnglishName(map.get("countryEnglishName"));
		} else {// 缓存没有数据，需查询数据库
			synchronized (ricCode) {// 防止并发
				ltEtCode = this.ltEtCodeDao.findByRicCode(ricCode);
				if (null != ltEtCode) {// 数据库有数据，同时保存一份至缓存
					RedisCacheEif.hset(redisId, "code", ricCode);
					String parentRicCode = ltEtCode.getParentRicCode();
					if (StringUtil.isNotNullOrEmpty(parentRicCode)) {
						RedisCacheEif.hset(redisId, "pcode", ltEtCode.getParentRicCode());
					}
					String desc = ltEtCode.getCodeDesc();
					if (StringUtil.isNotNullOrEmpty(desc)) {
						RedisCacheEif.hset(redisId, "desc", desc);
					}
					RedisCacheEif.hset(redisId, "name", ltEtCode.getCodeName());
					RedisCacheEif.hset(redisId, "codeType", ltEtCode.getCodeType());
					String countryEnglishName = ltEtCode.getCountryEnglishName();
					if (StringUtil.isNotNullOrEmpty(countryEnglishName)) {
						RedisCacheEif.hset(redisId, "countryEnglishName", countryEnglishName);
					}
					RedisCacheEif.hset(redisId, "id", ltEtCode.getId());// 由于是识别标志，必须放最后SET（防止并发）
				}
			}
		}
		return ltEtCode;
	}

	/**
	 * 功能描述：根据代码类型获取代码信息
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
	public List<LtEtCode> findByCodeType(String codeType) {
		return this.ltEtCodeDao.findByCodeType(codeType);
	}

	/**
	 * 功能描述：新增路透编码
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年11月23日 下午8:58:48
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @param ltEtCode
	 * @return
	 *
	 */
	@Override
	public LtEtCode doLtEtCode(String code, String pcode, boolean isLink) {
		LtEtCode ltEtCode = cacheFindByRicCode(code);
		if (null == ltEtCode) {// 不存在需新增
			ltEtCode = new LtEtCode();
			LtEtCode pLtEtCode = cacheFindByRicCode(pcode);
			ltEtCode.setCodeName(pLtEtCode.getCodeName() + "-" + code);
			ltEtCode.setCodeType(pLtEtCode.getCodeType());
			ltEtCode.setCountryEnglishName(pLtEtCode.getCountryEnglishName());
			ltEtCode.setRicCode(code);
			ltEtCode.setParentRicCode(pcode);
			this.save(ltEtCode);
		}
		return ltEtCode;
	}

}
