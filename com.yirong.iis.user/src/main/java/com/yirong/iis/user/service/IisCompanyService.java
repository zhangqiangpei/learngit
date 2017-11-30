package com.yirong.iis.user.service;

import java.util.Map;

import com.yirong.awaken.core.service.IBaseService;
import com.yirong.iis.user.entity.VeIisCompany;
import com.yirong.iis.user.userentity.VeIisCompanyUserEntity;
 
/**
 * 
 * @ClassName: IisCompanyService  
 * @Description: TODO(企业视图service接口) 
 * @author liny
 * @date 2017年11月28日 下午7:52:04 
 * @version V0.1
 */
@SuppressWarnings("rawtypes")
public interface IisCompanyService extends IBaseService<VeIisCompany, String> {
  
	/**
	 * 
	 * @Title: queryIisCompanyById 
	 * @Description: TODO(根据ID查询企业信息) 
	 * @param id
	 * @return Map
	 */
	Map queryIisCompanyById(String id);
	
	/**
	 * 
	 * @Title: queryIisCompanyByCompanyId 
	 * @Description: TODO(根据CompanyId查询企业列信息) 
	 * @param id
	 * @return Map
	 */
	Map queryIisCompanyByCompanyId(String id);

	/**
	 * 
	 * @Title: queryIisCompanyList 
	 * @Description: TODO(查询企业列表列表信息) 
	 * @param ue
	 * @return Map
	 */
	Map queryIisCompanyList(VeIisCompanyUserEntity ue);

}
