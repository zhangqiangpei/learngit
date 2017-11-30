package com.yirong.iis.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.iis.user.dao.IisIndicatorInfoDao;
import com.yirong.iis.user.entity.IisIndicatorInfo;
import com.yirong.iis.user.service.IisIndicatorInfoService;

 /**
 * 功能描述：指标信息表service实现类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-22 09:43:03
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
@Service("IisIndicatorInfoServiceImpl")
public class IisIndicatorInfoServiceImpl extends BaseService<IisIndicatorInfo, String>
		implements IisIndicatorInfoService {

	/**
	 * 日志操作类
	 */
	private Logger logger = LoggerFactory
			.getLogger(IisIndicatorInfoServiceImpl.class);

	/**
	 * dao注入
	 */
	@Autowired
	private IisIndicatorInfoDao iisIndicatorInfoDao;

	 /**
	 * 功能描述：获取dao操作类
	 * 
	 * @author 
	 *         <p>
	 *         创建时间 ：2017-11-22 09:43:03
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	@Override
	public IBaseDao<IisIndicatorInfo, String> getBaseDao() {
		return iisIndicatorInfoDao;
	}

}
