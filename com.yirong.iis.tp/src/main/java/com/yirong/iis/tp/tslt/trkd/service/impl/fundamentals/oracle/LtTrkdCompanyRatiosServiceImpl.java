package com.yirong.iis.tp.tslt.trkd.service.impl.fundamentals.oracle;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.iis.tp.common.dao.LtTrkdCompanyRatiosDao;
import com.yirong.iis.tp.common.entity.LtTrkdCompanyRatios;
import com.yirong.iis.tp.tslt.trkd.service.fundamentals.LtTrkdCompanyRatiosService;

 /**
 * 功能描述：公司比率表service实现类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-24 19:43:48
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@Service("LtTrkdCompanyRatiosServiceImpl")
public class LtTrkdCompanyRatiosServiceImpl extends BaseService<LtTrkdCompanyRatios, String>
		implements LtTrkdCompanyRatiosService {

	/**
	 * dao注入
	 */
	@Autowired
	private LtTrkdCompanyRatiosDao ltTrkdCompanyRatiosDao;

	 /**
	 * 功能描述：获取dao操作类
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-24 19:43:48
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	@Override
	public IBaseDao<LtTrkdCompanyRatios, String> getBaseDao() {
		return ltTrkdCompanyRatiosDao;
	}

}
