package com.yirong.iis.tp.tslt.trkd.service.impl.fundamentals.oracle;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.iis.tp.common.dao.LtTrkdCompanyOffAffDao;
import com.yirong.iis.tp.common.entity.LtTrkdCompanyOffAff;
import com.yirong.iis.tp.tslt.trkd.service.fundamentals.LtTrkdCompanyOffAffService;

 /**
 * 功能描述：人员企业联盟表service实现类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-24 19:11:57
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@Service("LtTrkdCompanyOffAffServiceImpl")
public class LtTrkdCompanyOffAffServiceImpl extends BaseService<LtTrkdCompanyOffAff, String>
		implements LtTrkdCompanyOffAffService {

	/**
	 * 日志操作类
	 */
	private Logger logger = LoggerFactory
			.getLogger(LtTrkdCompanyOffAffServiceImpl.class);

	/**
	 * dao注入
	 */
	@Autowired
	private LtTrkdCompanyOffAffDao ltTrkdCompanyOffAffDao;

	 /**
	 * 功能描述：获取dao操作类
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-24 19:11:57
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	@Override
	public IBaseDao<LtTrkdCompanyOffAff, String> getBaseDao() {
		return ltTrkdCompanyOffAffDao;
	}

}
