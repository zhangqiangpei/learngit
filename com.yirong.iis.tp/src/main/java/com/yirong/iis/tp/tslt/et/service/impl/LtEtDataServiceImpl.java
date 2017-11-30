package com.yirong.iis.tp.tslt.et.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.commons.util.datatype.StringUtil;
import com.yirong.iis.tp.common.dao.LtEtDataDao;
import com.yirong.iis.tp.common.entity.LtEtCode;
import com.yirong.iis.tp.common.entity.LtEtData;
import com.yirong.iis.tp.common.entity.LtEtField;
import com.yirong.iis.tp.tslt.et.constant.LtEtConstant;
import com.yirong.iis.tp.tslt.et.service.LtEtCodeService;
import com.yirong.iis.tp.tslt.et.service.LtEtDataService;
import com.yirong.iis.tp.tslt.et.service.LtEtFieldService;
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
	 * dao注入
	 */
	@Autowired
	private LtEtDataDao ltEtDataDao;

	/**
	 * 代码service注入
	 */
	@Autowired
	private LtEtCodeService ltEtCodeService;

	/**
	 * 字段service注入
	 */
	@Autowired
	private LtEtFieldService ltEtFieldService;

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
				// 获取基本信息
				LtEtCode ltEtCode = ltEtCodeService.cacheFindByRicCode(ue.getRicCode());
				LtEtField ltEtField = ltEtFieldService.cacheFindByFieldId(String.valueOf(ue.getFleldId()));
				if (null == ltEtField) {// 多出来的字段为冗余字段，跟本系统无关
					continue;
				}
				// 从数据库获取数据
				LtEtData ltEtData = this.ltEtDataDao.findByCodeIdAndFieldId(ltEtCode.getId(), ltEtField.getId());
				if (null == ltEtData) {// 无数据需新增
					ltEtData = new LtEtData();
					ltEtData.setCodeId(ltEtCode.getId());
					ltEtData.setFieldId(ltEtField.getId());
				}
				// 处理值
				String value = ue.getValue();
				if (StringUtil.isNotNullOrEmpty(value) && StringUtil.isNotNullOrEmpty(value.trim())
						&& !"null".equals(value)) {// 对方程序有时候回放回NULL字符串，需特殊处理（有值的字段才保存）
					// 判断类型
					String code = LtEtConstant.FIELD_TYPE_MAP.get(ltEtField.getFieldType());
					ltEtData.setFieldType(code);
					ltEtData.setDataValue(value);
					this.save(ltEtData);
				}
			}
		}
	}

}
