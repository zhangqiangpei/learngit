package com.yirong.iis.tp.tslt.et.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.commons.cache.eif.RedisCacheEif;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.iis.tp.common.dao.LtEtDataDao;
import com.yirong.iis.tp.common.entity.LtEtData;
import com.yirong.iis.tp.tslt.et.service.LtEtDataService;
import com.yirong.iis.tp.tslt.et.userentity.LtEtDataUserEntity;

/**
 * 功能描述：elektron数据表service实现类
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
@Service("LtEtDataServiceImpl")
public class LtEtDataServiceImpl extends BaseService<LtEtData, String> implements LtEtDataService {

	/**
	 * 日志操作类
	 */
	private Logger logger = LoggerFactory.getLogger(LtEtDataServiceImpl.class);

	/**
	 * dao注入
	 */
	@Autowired
	private LtEtDataDao ltEtDataDao;

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
	public IBaseDao<LtEtData, String> getBaseDao() {
		return ltEtDataDao;
	}

	/**
	 * 功能描述：新增elektron数据表
	 * 
	 * @author
	 *         <p>
	 *         创建时间 ：2017-11-20 15:53:48
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param ltEtData
	 * @return
	 */
	@Override
	public void doLtEtData(List<LtEtDataUserEntity> ueList) {
		if (null != ueList && ueList.size() > 0) {
			for (LtEtDataUserEntity ue : ueList) {
				// 从缓存获取编码信息
				String codeId = RedisCacheEif.hget(ue.getRicCode() + ue.getCodeType(), "id");
				String fieldId = RedisCacheEif.hget(ue.getFleldId(), "id");
				String fieldType = RedisCacheEif.hget(ue.getFleldId(), "type");
				// 从数据库获取数据
				LtEtData ltEtData = this.ltEtDataDao.findByCodeIdAndFieldId(codeId, fieldId);
				if (null == ltEtData) {
					ltEtData = new LtEtData();
					ltEtData.setCodeId(codeId);
					ltEtData.setFieldId(fieldId);
				}
				// 判断类型
				switch (fieldType) {
				default:
					ltEtData.setStringValue(ue.getValue());
					break;
				}
				this.save(ltEtData);
			}
		}
	}

}
