package com.yirong.iis.tp.tslt.trkd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.iis.tp.common.dao.LtTrkdRequestLogDao;
import com.yirong.iis.tp.common.entity.LtTrkdRequestLog;
import com.yirong.iis.tp.tslt.trkd.service.ILtTrkdRequestLogService;

@Service("ltTrkdRequestLogServiceImpl")
public class LtTrkdRequestLogServiceImpl extends BaseService<LtTrkdRequestLog,String> implements ILtTrkdRequestLogService{
	
	@Autowired
	private LtTrkdRequestLogDao ltTrkdRequestLogDao;
	
	@Override
	public IBaseDao<LtTrkdRequestLog, String> getBaseDao() {
		return ltTrkdRequestLogDao;
	}

	@Override
	public void addRequestLog(String name, String param, String result,
			Integer status) {
		LtTrkdRequestLog log = new LtTrkdRequestLog();
		log.setName(name);
		log.setParam(param);
		log.setResult(result);
		log.setStatus(status);
		this.save(log);
	}

}
