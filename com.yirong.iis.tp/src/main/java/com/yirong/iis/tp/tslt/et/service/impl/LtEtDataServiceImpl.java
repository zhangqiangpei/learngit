package com.yirong.iis.tp.tslt.et.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
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
	 * 日志操作类
	 */
	private final static Logger logger = LoggerFactory.getLogger(LtEtDataServiceImpl.class);

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
				if (null == ltEtCode || null == ltEtField) {
					logger.error("ltEtCode或者ltEtField为空，ricCode:" + ue.getRicCode() + ",fleldId:" + ue.getFleldId());
					continue;
				}
				// 从数据库获取数据
				LtEtData ltEtData = this.ltEtDataDao.findByCodeIdAndFieldId(ltEtCode.getId(), ltEtField.getId());
				if (null == ltEtData) {// 无数据需新增
					ltEtData = new LtEtData();
					ltEtData.setCodeId(ltEtCode.getId());
					ltEtData.setFieldId(ltEtField.getId());
				}
				// 处理数据值
				if (StringUtil.isNotNullOrEmpty(ue.getValue())) {
					// 判断类型
					String code = LtEtConstant.FIELD_TYPE_MAP.get(ltEtField.getFieldType());
					ltEtData.setFieldType(code);
					// 处理数据
					switch (code) {
					case "017001":// 字符型
						ltEtData.setStringValue(ue.getValue());
						break;
					case "017002":// 整型
						ltEtData.setIntgerValue(Integer.valueOf(ue.getValue()));
						break;
					case "017004":// date型
						SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy", Locale.ENGLISH);
						try {
							ltEtData.setDateValue(sdf.parse(ue.getValue()));
						} catch (ParseException e) {
							logger.error("时间转换异常", e);
						}
						break;
					case "017005":// 浮点型
						ltEtData.setFloatValue(new BigDecimal(ue.getValue()));
						break;
					default:// 无任何匹配，直接存入String
						ltEtData.setStringValue(ue.getValue());
						break;
					}
				}
				this.save(ltEtData);
			}
		}
	}

}
