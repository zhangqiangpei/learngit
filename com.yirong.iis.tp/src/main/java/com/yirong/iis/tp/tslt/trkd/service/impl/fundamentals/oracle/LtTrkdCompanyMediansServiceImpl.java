package com.yirong.iis.tp.tslt.trkd.service.impl.fundamentals.oracle;

import org.springframework.beans.factory.annotation.Autowired;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.iis.tp.common.dao.LtTrkdCompanyMediansDao;
import com.yirong.iis.tp.common.entity.LtTrkdCompanyMedians;
import com.yirong.iis.tp.tslt.trkd.service.fundamentals.ILtTrkdCompanyMediansService;

public class LtTrkdCompanyMediansServiceImpl extends BaseService<LtTrkdCompanyMedians,String> implements ILtTrkdCompanyMediansService{
	
	@Autowired
	private LtTrkdCompanyMediansDao ltTrkdCompanyMediansDao;
	
	@Override
	public IBaseDao<LtTrkdCompanyMedians, String> getBaseDao() {
		return ltTrkdCompanyMediansDao;
	}

}