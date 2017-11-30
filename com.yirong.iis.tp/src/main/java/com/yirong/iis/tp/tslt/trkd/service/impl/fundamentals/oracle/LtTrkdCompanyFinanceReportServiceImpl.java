package com.yirong.iis.tp.tslt.trkd.service.impl.fundamentals.oracle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.iis.tp.common.dao.LtTrkdCompanyFinanceReportDao;
import com.yirong.iis.tp.common.entity.LtTrkdCompanyFinanceReport;
import com.yirong.iis.tp.tslt.trkd.service.fundamentals.ILtTrkdCompanyFinanceReportService;

@Service("ltTrkdCompanyFinanceReportServiceImpl")
public class LtTrkdCompanyFinanceReportServiceImpl extends BaseService<LtTrkdCompanyFinanceReport,String> implements ILtTrkdCompanyFinanceReportService{
	
	@Autowired
	private LtTrkdCompanyFinanceReportDao ltTrkdCompanyFinanceReportDao;
	
	@Override
	public IBaseDao<LtTrkdCompanyFinanceReport, String> getBaseDao() {
		return ltTrkdCompanyFinanceReportDao;
	}

}
