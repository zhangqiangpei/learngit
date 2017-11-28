package com.yirong.iis.tp.tslt.trkd.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.iis.tp.common.dao.LtTrkdCompanyDao;
import com.yirong.iis.tp.common.entity.LtTrkdCompany;
import com.yirong.iis.tp.tslt.trkd.service.LtTrkdCompanyService;

 /**
 * 功能描述：trkd公司表service实现类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-28 10:06:54
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@Service("ltTrkdCompanyServiceImpl")
public class LtTrkdCompanyServiceImpl extends BaseService<LtTrkdCompany, String>
		implements LtTrkdCompanyService {

	/**
	 * 日志操作类
	 */
	private Logger logger = LoggerFactory
			.getLogger(LtTrkdCompanyServiceImpl.class);

	/**
	 * dao注入
	 */
	@Autowired
	private LtTrkdCompanyDao ltTrkdCompanyDao;

	 /**
	 * 功能描述：获取dao操作类
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-28 10:06:54
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	@Override
	public IBaseDao<LtTrkdCompany, String> getBaseDao() {
		return ltTrkdCompanyDao;
	}

}
