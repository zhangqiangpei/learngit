package com.yirong.iis.user.service;

import java.util.Map;

import com.yirong.awaken.core.service.IBaseService;
import com.yirong.iis.user.entity.VeIisCompanyOfficer;
import com.yirong.iis.user.userentity.VeIisCompanyOfficerUserEntity;
 
/**
 * 
 * @ClassName: IisCompanyOfficerService  
 * @Description: TODO(企业高管视图service接口) 
 * @author liny
 * @date 2017年11月28日 下午7:52:04 
 * @version V0.1
 */
@SuppressWarnings("rawtypes")
public interface IisCompanyOfficerService extends IBaseService<VeIisCompanyOfficer, String> {
  
  
	/**
	 * 
	 * @Title: queryIisCompanyOfficerList 
	 * @Description: TODO(查询某个企业高管列表) 
	 * @param companyId
	 * @return Map
	 */
	Map queryIisCompanyOfficerList(VeIisCompanyOfficerUserEntity ue);

}
