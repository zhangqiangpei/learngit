package com.yirong.iis.tp.tslt.trkd.service.impl.significantDevelopments.oracle;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.iis.tp.common.dao.LtTrkdCompanySignificantDao;
import com.yirong.iis.tp.common.entity.LtTrkdCompanySignificant;
import com.yirong.iis.tp.tslt.trkd.service.significantDevelopments.LtTrkdCompanySignificantService;

 /**
 * 功能描述：公司重大事件表service实现类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-28 08:51:07
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@Service("LtTrkdCompanySignificantServiceImpl")
public class LtTrkdCompanySignificantServiceImpl extends BaseService<LtTrkdCompanySignificant, String>
		implements LtTrkdCompanySignificantService {

	/**
	 * dao注入
	 */
	@Autowired
	private LtTrkdCompanySignificantDao ltTrkdCompanySignificantDao;

	 /**
	 * 功能描述：获取dao操作类
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-28 08:51:07
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	@Override
	public IBaseDao<LtTrkdCompanySignificant, String> getBaseDao() {
		return ltTrkdCompanySignificantDao;
	}

}
