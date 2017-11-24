package com.yirong.iis.tp.tslt.et.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.commons.cache.eif.RedisCacheEif;
import com.yirong.commons.util.datatype.StringUtil;
import com.yirong.iis.tp.common.dao.LtEtFieldDao;
import com.yirong.iis.tp.common.entity.LtEtField;
import com.yirong.iis.tp.tslt.et.service.LtEtFieldService;

/**
 * 功能描述：elektron字段表service实现类
 * 
 * @author
 *         <p>
 *         创建时间 ：2017-11-20 15:53:48
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@Service("LtEtFieldServiceImpl")
public class LtEtFieldServiceImpl extends BaseService<LtEtField, String> implements LtEtFieldService {

	/**
	 * dao注入
	 */
	@Autowired
	private LtEtFieldDao ltEtFieldDao;

	/**
	 * 功能描述：获取dao操作类
	 * 
	 * @author
	 *         <p>
	 *         创建时间 ：2017-11-20 15:53:48
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	@Override
	public IBaseDao<LtEtField, String> getBaseDao() {
		return ltEtFieldDao;
	}

	/**
	 * 功能描述：根据字段ID获取字段信息（缓存）
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年11月22日 下午8:29:02
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @param fieldId
	 * @return
	 *
	 */
	@Override
	public LtEtField cacheFindByFieldId(String fieldId) {
		synchronized (fieldId) {// 防止多线程并发
			String redisId = "lt" + fieldId;
			String id = RedisCacheEif.hget(redisId, "id");
			LtEtField ltEtField = new LtEtField();
			if (StringUtil.isNotNullOrEmpty(id)) {// 缓存有数据，直接使用缓存
				ltEtField.setId(id);
				Map<String, String> map = RedisCacheEif.hgetall(redisId);
				ltEtField.setFieldCode(map.get("code"));
				ltEtField.setFieldDesc(map.get("desc"));
				ltEtField.setFieldEnglishDesc(map.get("englishDesc"));
				ltEtField.setFieldEnglishName(map.get("englishName"));
				ltEtField.setFieldId(map.get("fieldId"));
				ltEtField.setFieldName(map.get("name"));
				ltEtField.setFieldType(map.get("type"));
			} else {
				ltEtField = this.ltEtFieldDao.findByFieldId(fieldId);
				if (null != ltEtField) {// 有数据，保存一份至缓存
					RedisCacheEif.hset(redisId, "code", ltEtField.getFieldCode());
					RedisCacheEif.hset(redisId, "englishDesc", ltEtField.getFieldEnglishDesc());
					RedisCacheEif.hset(redisId, "englishName", ltEtField.getFieldEnglishName());
					RedisCacheEif.hset(redisId, "fieldId", fieldId);
					RedisCacheEif.hset(redisId, "name", ltEtField.getFieldName());
					RedisCacheEif.hset(redisId, "type", ltEtField.getFieldType());
					RedisCacheEif.hset(redisId, "id", ltEtField.getId());// 由于是识别标志，必须放最后SET（防止并发）
				}
			}
			return ltEtField;
		}
	}

}
