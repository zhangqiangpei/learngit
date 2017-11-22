package com.yirong.iis.tp.tslt.et.service.impl;

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
		String redisId = "lt" + fieldId;
		String id = RedisCacheEif.hget(redisId, "id");
		LtEtField ltEtField = new LtEtField();
		if (StringUtil.isNotNullOrEmpty(id)) {// 缓存有数据，直接使用缓存
			ltEtField.setId(id);
			ltEtField.setFieldCode(RedisCacheEif.hget(redisId, "code"));
			ltEtField.setFieldDesc(RedisCacheEif.hget(redisId, "desc"));
			ltEtField.setFieldEnglishDesc(RedisCacheEif.hget(redisId, "englishDesc"));
			ltEtField.setFieldEnglishName(RedisCacheEif.hget(redisId, "englishName"));
			ltEtField.setFieldId(RedisCacheEif.hget(redisId, "fieldId"));
			ltEtField.setFieldName(RedisCacheEif.hget(redisId, "name"));
			ltEtField.setFieldType(RedisCacheEif.hget(redisId, "type"));
		} else {
			ltEtField = this.ltEtFieldDao.findByFieldId(fieldId);
			if (null != ltEtField) {// 有数据，保存一份至缓存
				RedisCacheEif.hset(redisId, "id", ltEtField.getId());
				RedisCacheEif.hset(redisId, "code", ltEtField.getFieldCode());
				RedisCacheEif.hset(redisId, "englishDesc", ltEtField.getFieldEnglishDesc());
				RedisCacheEif.hset(redisId, "englishName", ltEtField.getFieldEnglishName());
				RedisCacheEif.hset(redisId, "fieldId", fieldId);
				RedisCacheEif.hset(redisId, "name", ltEtField.getFieldName());
				RedisCacheEif.hset(redisId, "type", ltEtField.getFieldType());
			}
		}
		return ltEtField;
	}

}
