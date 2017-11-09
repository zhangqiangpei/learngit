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
import com.yirong.iis.user.dao.IisReportShareDao;
import com.yirong.iis.user.entity.IisReportShare;
import com.yirong.iis.user.service.IisReportShareService;

import java.util.Map;

/**
 * 功能描述：报告共享表service实现类
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
@Service("IisReportShareServiceImpl")
public class IisReportShareServiceImpl extends BaseService<IisReportShare, String>
		implements IisReportShareService {

	/**
	 * 日志操作类
	 */
	private Logger logger = LoggerFactory
			.getLogger(IisReportShareServiceImpl.class);

	/**
	 * dao注入
	 */
	@Autowired
	private IisReportShareDao iisReportShareDao;

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
	public IBaseDao<IisReportShare, String> getBaseDao() {
		return iisReportShareDao;
	}

     /**
      * 功能描述：新增报告共享表
      *
      * @author 林明铁
      *         <p>
      *         创建时间 ：2017-11-09 10:00:09
      *         </p>
      *
      *         <p>
      *         修改历史：(修改人，修改时间，修改原因/内容)
      *         </p>
      * @param iisReportShare
      * @return
      */
     @Override
     public Map saveIisReportShare(IisReportShare iisReportShare){
         // 根据编码及分类ID获取数据（唯一键）
         IisReportShare iisReportShareTemp = this.iisReportShareDao
                 .findByReportIdAndShareType(iisReportShare.getReportId(), iisReportShare.getShareType());
         String id = iisReportShare.getId();
         if (null == iisReportShareTemp
                 || (StringUtil.isNotNullOrEmpty(id) && id.equals(iisReportShareTemp
                 .getId()))) {// 该唯一键不存在 或者为“修改操作且修改本身数据”
             id = iisReportShare.getId();
             if (StringUtil.isNotNullOrEmpty(id)) {// 修改
                 // 获取数据库对象
                 iisReportShareTemp = this.iisReportShareDao.findOne(id);
                 // 复制属性
                 BeanUtil.copyPropertiesIgnoreNull(iisReportShare, iisReportShareTemp);
                 this.save(iisReportShareTemp);
             } else {
                 this.save(iisReportShare);
             }
             return ResultUtil.newOk("操作成功").toMap();
         } else {// 该名称及父类ID已存在
             String result = ErrorPromptInfoUtil.getErrorInfo("00301");
             logger.warn(result);
             return ResultUtil.newError(result).toMap();
         }
     }
}
