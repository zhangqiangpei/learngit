package com.yirong.iis.tp.tslt.trkd.service.impl.fundamentals.oracle;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.iis.tp.common.dao.LtTrkdCompanyTrendDao;
import com.yirong.iis.tp.common.entity.LtTrkdCompanyTrend;
import com.yirong.iis.tp.tslt.trkd.service.fundamentals.LtTrkdCompanyTrendService;

 /**
 * 功能描述：公司推荐趋势表service实现类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-24 18:33:19
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
@Service("LtTrkdCompanyTrendServiceImpl")
public class LtTrkdCompanyTrendServiceImpl extends BaseService<LtTrkdCompanyTrend, String>
		implements LtTrkdCompanyTrendService {

	/**
	 * 日志操作类
	 */
	private Logger logger = LoggerFactory
			.getLogger(LtTrkdCompanyTrendServiceImpl.class);

	/**
	 * dao注入
	 */
	@Autowired
	private LtTrkdCompanyTrendDao ltTrkdCompanyTrendDao;

	 /**
	 * 功能描述：获取dao操作类
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-24 18:33:19
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	@Override
	public IBaseDao<LtTrkdCompanyTrend, String> getBaseDao() {
		return ltTrkdCompanyTrendDao;
	}

}
