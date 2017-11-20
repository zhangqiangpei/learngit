package com.yirong.iis.tp.et.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.iis.tp.dao.LtEtFieldDao;
import com.yirong.iis.tp.entity.LtEtField;
import com.yirong.iis.tp.et.service.LtEtFieldService;

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
@SuppressWarnings("rawtypes")
@Service("LtEtFieldServiceImpl")
public class LtEtFieldServiceImpl extends BaseService<LtEtField, String> implements LtEtFieldService {

	/**
	 * 日志操作类
	 */
	private Logger logger = LoggerFactory.getLogger(LtEtFieldServiceImpl.class);

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

}
