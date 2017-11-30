package com.yirong.iis.user.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.awaken.core.util.BeanUtil;
import com.yirong.awaken.core.util.ResultUtil;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.commons.util.datatype.StringUtil;
import com.yirong.commons.util.entity.PageEntiry;
import com.yirong.commons.util.error.ErrorPromptInfoUtil;
import com.yirong.iis.user.dao.IisIndicatorFieldDao;
import com.yirong.iis.user.entity.IisIndicatorField;
import com.yirong.iis.user.service.IisIndicatorFieldService;
import com.yirong.iis.user.userentity.IisIndicatorFieldUserEntity;

 /**
 * 功能描述：指标字段表service实现类
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
@Service("IisIndicatorFieldServiceImpl")
public class IisIndicatorFieldServiceImpl extends BaseService<IisIndicatorField, String>
		implements IisIndicatorFieldService {

	/**
	 * 日志操作类
	 */
	private Logger logger = LoggerFactory
			.getLogger(IisIndicatorFieldServiceImpl.class);

	/**
	 * dao注入
	 */
	@Autowired
	private IisIndicatorFieldDao iisIndicatorFieldDao;

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
	public IBaseDao<IisIndicatorField, String> getBaseDao() {
		return iisIndicatorFieldDao;
	}

}
