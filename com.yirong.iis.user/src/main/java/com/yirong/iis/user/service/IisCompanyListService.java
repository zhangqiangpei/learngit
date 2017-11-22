package com.yirong.iis.user.service;

import java.util.Map;

import com.yirong.awaken.core.service.IBaseService;
import com.yirong.iis.user.entity.IisCompanyList;
import com.yirong.iis.user.userentity.IisCompanyListUserEntity;
 
/**
 * 
 * @ClassName: IisCompanyListService  
 * @Description: TODO(企业列表service接口) 
 * @author liny
 * @date 2017年11月22日 上午11:19:48 
 * @version V0.1
 */
@SuppressWarnings("rawtypes")
public interface IisCompanyListService extends IBaseService<IisCompanyList, String> {

	/**
	 * 
	 * @Title: saveIisCompanyList 
	 * @Description: TODO(新增企业列表) 
	 * @param iisCompanyList
	 * @return Map
	 */
	Map saveIisCompanyList(IisCompanyList iisCompanyList);

	/**
	 * 
	 * @Title: updateIisCompanyList 
	 * @Description: TODO(修改企业列表) 
	 * @param iisCompanyList
	 * @return Map
	 */
	Map updateIisCompanyList(IisCompanyList iisCompanyList);

	/**
	 * 
	 * @Title: delIisCompanyList 
	 * @Description: TODO(删除企业列表（批量）) 
	 * @param idStrs
	 * @return Map
	 */
	Map delIisCompanyList(String idStrs);

	/**
	 * 
	 * @Title: queryIisCompanyListById 
	 * @Description: TODO(根据ID查询企业列表信息) 
	 * @param id
	 * @return Map
	 */
	Map queryIisCompanyListById(String id);

	/**
	 * 
	 * @Title: queryIisCompanyListList 
	 * @Description: TODO(查询企业列表列表信息) 
	 * @param ue
	 * @return Map
	 */
	Map queryIisCompanyListList(IisCompanyListUserEntity ue);

}
