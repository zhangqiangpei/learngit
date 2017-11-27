package com.yirong.iis.tp.tslt.trkd.service.impl.fundamentals.oracle;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.iis.tp.common.dao.LtTrkdCompanyIssuedDao;
import com.yirong.iis.tp.common.entity.LtTrkdCompanyIssued;
import com.yirong.iis.tp.tslt.trkd.service.fundamentals.LtTrkdCompanyIssuedService;

 /**
 * 功能描述：公司股票信息表service实现类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-24 19:07:02
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@Service("LtTrkdCompanyIssuedServiceImpl")
public class LtTrkdCompanyIssuedServiceImpl extends BaseService<LtTrkdCompanyIssued, String>
		implements LtTrkdCompanyIssuedService {

	/**
	 * 日志操作类
	 */
	private Logger logger = LoggerFactory
			.getLogger(LtTrkdCompanyIssuedServiceImpl.class);

	/**
	 * dao注入
	 */
	@Autowired
	private LtTrkdCompanyIssuedDao ltTrkdCompanyIssuedDao;

	 /**
	 * 功能描述：获取dao操作类
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-24 19:07:02
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	@Override
	public IBaseDao<LtTrkdCompanyIssued, String> getBaseDao() {
		return ltTrkdCompanyIssuedDao;
	}

}
