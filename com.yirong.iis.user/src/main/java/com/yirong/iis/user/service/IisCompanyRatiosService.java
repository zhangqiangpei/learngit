package com.yirong.iis.user.service;

import java.util.Map;

import com.yirong.awaken.core.service.IBaseService;
import com.yirong.iis.user.entity.VeIisCompanyRatios;
import com.yirong.iis.user.userentity.VeIisCompanyRatiosUserEntity;
 
/**
 * 
 * @ClassName: IisCompanyRatiosService  
 * @Description: TODO(企业股市概括视图service接口) 
 * @author liny
 * @date 2017年11月28日 下午7:52:04 
 * @version V0.1
 */
@SuppressWarnings("rawtypes")
public interface IisCompanyRatiosService extends IBaseService<VeIisCompanyRatios, String> {
  
  
	/**
	 * 
	 * @Title: queryIisCompanyRatiosList 
	 * @Description: TODO(查询企业股市概括列表) 
	 * @param companyId
	 * @return Map
	 */
	Map queryIisCompanyRatiosList(VeIisCompanyRatiosUserEntity ue);

}
