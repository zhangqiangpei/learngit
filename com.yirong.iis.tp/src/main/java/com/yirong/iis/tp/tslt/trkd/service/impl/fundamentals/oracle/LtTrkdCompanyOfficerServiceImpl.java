package com.yirong.iis.tp.tslt.trkd.service.impl.fundamentals.oracle;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.iis.tp.common.dao.LtTrkdCompanyOfficerDao;
import com.yirong.iis.tp.common.entity.LtTrkdCompanyOfficer;
import com.yirong.iis.tp.tslt.trkd.service.fundamentals.LtTrkdCompanyOfficerService;

 /**
 * 功能描述：公司高管表service实现类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-24 19:30:17
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@Service("LtTrkdCompanyOfficerServiceImpl")
public class LtTrkdCompanyOfficerServiceImpl extends BaseService<LtTrkdCompanyOfficer, String>
		implements LtTrkdCompanyOfficerService {

	/**
	 * dao注入
	 */
	@Autowired
	private LtTrkdCompanyOfficerDao ltTrkdCompanyOfficerDao;

	 /**
	 * 功能描述：获取dao操作类
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-24 19:30:17
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	@Override
	public IBaseDao<LtTrkdCompanyOfficer, String> getBaseDao() {
		return ltTrkdCompanyOfficerDao;
	}
	
}
