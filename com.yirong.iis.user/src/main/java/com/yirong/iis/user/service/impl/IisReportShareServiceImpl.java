package com.yirong.iis.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.iis.user.dao.IisReportShareDao;
import com.yirong.iis.user.entity.IisReportShare;
import com.yirong.iis.user.service.IisReportShareService;

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


}
