package com.yirong.iis.tp.tslt.trkd.service.impl.fundamentals.oracle;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.iis.tp.common.dao.LtTrkdCompanyForecastDataDao;
import com.yirong.iis.tp.common.entity.LtTrkdCompanyForecastData;
import com.yirong.iis.tp.tslt.trkd.service.fundamentals.LtTrkdCompanyForecastDataService;

 /**
 * 功能描述：公司预测数据表service实现类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-24 18:43:28
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@Service("LtTrkdCompanyForecastDataServiceImpl")
public class LtTrkdCompanyForecastDataServiceImpl extends BaseService<LtTrkdCompanyForecastData, String>
		implements LtTrkdCompanyForecastDataService {

	/**
	 * 日志操作类
	 */
	private Logger logger = LoggerFactory
			.getLogger(LtTrkdCompanyForecastDataServiceImpl.class);

	/**
	 * dao注入
	 */
	@Autowired
	private LtTrkdCompanyForecastDataDao ltTrkdCompanyForecastDataDao;

	 /**
	 * 功能描述：获取dao操作类
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-24 18:43:28
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	@Override
	public IBaseDao<LtTrkdCompanyForecastData, String> getBaseDao() {
		return ltTrkdCompanyForecastDataDao;
	}


}
