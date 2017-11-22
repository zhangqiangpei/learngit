package com.yirong.iis.user.service;

import java.util.Map;

import com.yirong.awaken.core.service.IBaseService;
import com.yirong.iis.user.entity.IisCompanyInformation;
import com.yirong.iis.user.userentity.IisCompanyInformationUserEntity;

/**
 * 
 * @ClassName: IisCompanyInformationService  
 * @Description: TODO(企业资讯表service接口) 
 * @author liny
 * @date 2017年11月22日 上午11:19:30 
 * @version V0.1
 */
@SuppressWarnings("rawtypes")
public interface IisCompanyInformationService extends IBaseService<IisCompanyInformation, String> {
 
	 /**
	  * 
	  * @Title: queryIisCompanyInformationList 
	  * @Description: TODO(查询企业资讯表列表信息) 
	  * @param ue
	  * @return Map
	  */
	Map queryIisCompanyInformationList(IisCompanyInformationUserEntity ue);

}
