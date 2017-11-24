package com.yirong.iis.tp.tslt.trkd.service.impl.fundamentals.oracle;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.iis.tp.common.dao.LtTrkdCompanyOfficerPostDao;
import com.yirong.iis.tp.common.entity.LtTrkdCompanyOfficerPost;
import com.yirong.iis.tp.tslt.trkd.service.fundamentals.LtTrkdCompanyOfficerPostService;

 /**
 * 功能描述：公司高管履历表service实现类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-24 19:36:42
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
@Service("LtTrkdCompanyOfficerPostServiceImpl")
public class LtTrkdCompanyOfficerPostServiceImpl extends BaseService<LtTrkdCompanyOfficerPost, String>
		implements LtTrkdCompanyOfficerPostService {

	/**
	 * 日志操作类
	 */
	private Logger logger = LoggerFactory
			.getLogger(LtTrkdCompanyOfficerPostServiceImpl.class);

	/**
	 * dao注入
	 */
	@Autowired
	private LtTrkdCompanyOfficerPostDao ltTrkdCompanyOfficerPostDao;

	 /**
	 * 功能描述：获取dao操作类
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-24 19:36:42
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	@Override
	public IBaseDao<LtTrkdCompanyOfficerPost, String> getBaseDao() {
		return ltTrkdCompanyOfficerPostDao;
	}

}
