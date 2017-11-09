package com.yirong.iis.user.service.impl;


import com.yirong.awaken.core.util.BeanUtil;
import com.yirong.awaken.core.util.ResultUtil;
import com.yirong.commons.util.datatype.StringUtil;
import com.yirong.commons.util.error.ErrorPromptInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.iis.user.dao.IisReportShareObjDao;
import com.yirong.iis.user.entity.IisReportShareObj;
import com.yirong.iis.user.service.IisReportShareObjService;

import java.util.Map;

/**
 * 功能描述：报告共享对象表service实现类
 * 
 * @author 林明铁
 *         <p>
 *         创建时间 ：2017-11-09 10:00:09
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
@Service("IisReportShareObjServiceImpl")
public class IisReportShareObjServiceImpl extends BaseService<IisReportShareObj, String>
		implements IisReportShareObjService {

	/**
	 * 日志操作类
	 */
	private Logger logger = LoggerFactory
			.getLogger(IisReportShareObjServiceImpl.class);

	/**
	 * dao注入
	 */
	@Autowired
	private IisReportShareObjDao iisReportShareObjDao;

	 /**
	 * 功能描述：获取dao操作类
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-09 10:00:09
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	@Override
	public IBaseDao<IisReportShareObj, String> getBaseDao() {
		return iisReportShareObjDao;
	}

	 /**
	  * 功能描述：新增报告共享对象表
	  *
	  * @author 林明铁
	  *         <p>
	  *         创建时间 ：2017-11-09 10:00:09
	  *         </p>
	  *
	  *         <p>
	  *         修改历史：(修改人，修改时间，修改原因/内容)
	  *         </p>
	  * @param iisReportShareObj
	  * @return
	  */
	 @Override
	 public Map saveIisReportShareObj(IisReportShareObj iisReportShareObj){
		 // 根据编码及分类ID获取数据（唯一键）
		 IisReportShareObj iisReportShareObjTemp = this.iisReportShareObjDao
				 .findByReportIdAndObjId(iisReportShareObj.getReportId(), iisReportShareObj.getObjId());
		 String id = iisReportShareObj.getId();
		 if (null == iisReportShareObjTemp
				 || (StringUtil.isNotNullOrEmpty(id) && id.equals(iisReportShareObjTemp
				 .getId()))) {// 该唯一键不存在 或者为“修改操作且修改本身数据”
			 id = iisReportShareObj.getId();
			 if (StringUtil.isNotNullOrEmpty(id)) {// 修改
				 // 获取数据库对象
				 iisReportShareObjTemp = this.iisReportShareObjDao.findOne(id);
				 // 复制属性
				 BeanUtil.copyPropertiesIgnoreNull(iisReportShareObj, iisReportShareObjTemp);
				 this.save(iisReportShareObjTemp);
			 } else {
				 this.save(iisReportShareObj);
			 }
			 return ResultUtil.newOk("操作成功").toMap();
		 } else {// 该名称及父类ID已存在
			 String result = ErrorPromptInfoUtil.getErrorInfo("00201");
			 logger.warn(result);
			 return ResultUtil.newError(result).toMap();
		 }
	 }
}
