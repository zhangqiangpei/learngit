package com.yirong.iis.tp.tslt.trkd.service;

import com.yirong.awaken.core.service.IBaseService;
import com.yirong.iis.tp.common.entity.LtTrkdRequestLog;

public interface ILtTrkdRequestLogService  extends IBaseService<LtTrkdRequestLog,String>{
	
	/**
	 * 添加请求日志
	 * @param name
	 * @param param
	 * @param result
	 * @param status
	 * @param companyId 
	 */
	void addRequestLog(String name, String param, String result,Integer status, String companyId);

}
