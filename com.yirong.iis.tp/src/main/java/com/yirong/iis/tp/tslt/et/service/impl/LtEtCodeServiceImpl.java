package com.yirong.iis.tp.et.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.iis.tp.dao.LtEtCodeDao;
import com.yirong.iis.tp.entity.LtEtCode;
import com.yirong.iis.tp.et.service.LtEtCodeService;

/**
 * 功能描述：elektron代码表service实现类
 * 
 * @author
 *         <p>
 *         创建时间 ：2017-11-20 15:49:46
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@Service("LtEtCodeServiceImpl")
public class LtEtCodeServiceImpl extends BaseService<LtEtCode, String> implements LtEtCodeService {

	/**
	 * 日志操作类
	 */
	private Logger logger = LoggerFactory.getLogger(LtEtCodeServiceImpl.class);

	/**
	 * dao注入
	 */
	@Autowired
	private LtEtCodeDao ltEtCodeDao;

	/**
	 * 功能描述：获取dao操作类
	 * 
	 * @author
	 *         <p>
	 *         创建时间 ：2017-11-20 15:49:46
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	@Override
	public IBaseDao<LtEtCode, String> getBaseDao() {
		return ltEtCodeDao;
	}

}
