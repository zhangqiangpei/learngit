package com.yirong.iis.user.service;

import java.util.Map;

import com.yirong.awaken.core.service.IBaseService;
import com.yirong.iis.user.entity.VeIisCompanyFinanceReport;
import com.yirong.iis.user.userentity.VeIisCompanyReportUserEntity;
 
/**
 * 
 * @ClassName: IisCompanyReportService  
 * @Description: TODO(企业财务报告视图service接口) 
 * @author liny
 * @date 2017年11月28日 下午7:52:04 
 * @version V0.1
 */
@SuppressWarnings("rawtypes")
public interface IisCompanyReportService extends IBaseService<VeIisCompanyFinanceReport, String> {
  
  
	/**
	 * 
	 * @Title: queryIisCompanyReportList 
	 * @Description: TODO(查询财务报告高管列表) 
	 * @param companyId
	 * @return Map
	 */
	Map queryIisCompanyReportList(VeIisCompanyReportUserEntity ue);

}
