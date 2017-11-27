package com.yirong.iis.tp.tslt.trkd.service.impl.fundamentals.oracle;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.iis.tp.common.dao.LtTrkdCompanyIndexDao;
import com.yirong.iis.tp.common.entity.LtTrkdCompanyIndex;
import com.yirong.iis.tp.tslt.trkd.service.fundamentals.LtTrkdCompanyIndexService;

 /**
 * 功能描述：公司IndexMemberships表 service实现类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-24 18:51:42
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@Service("LtTrkdCompanyIndexServiceImpl")
public class LtTrkdCompanyIndexServiceImpl extends BaseService<LtTrkdCompanyIndex, String>
		implements LtTrkdCompanyIndexService {

	/**
	 * 日志操作类
	 */
	private Logger logger = LoggerFactory
			.getLogger(LtTrkdCompanyIndexServiceImpl.class);

	/**
	 * dao注入
	 */
	@Autowired
	private LtTrkdCompanyIndexDao ltTrkdCompanyIndexDao;

	 /**
	 * 功能描述：获取dao操作类
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-24 18:51:42
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	@Override
	public IBaseDao<LtTrkdCompanyIndex, String> getBaseDao() {
		return ltTrkdCompanyIndexDao;
	}
	
}
