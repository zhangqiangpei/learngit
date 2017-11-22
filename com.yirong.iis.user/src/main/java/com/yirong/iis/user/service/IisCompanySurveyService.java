package com.yirong.iis.user.service;

import java.util.Map;

import com.yirong.awaken.core.service.IBaseService;
import com.yirong.iis.user.entity.IisCompanySurvey;
import com.yirong.iis.user.userentity.IisCompanySurveyUserEntity;

/**
 * 
 * @ClassName: IisCompanySurveyService  
 * @Description: TODO(企业概况表service接口) 
 * @author liny
 * @date 2017年11月22日 上午11:20:07 
 * @version V0.1
 */
@SuppressWarnings("rawtypes")
public interface IisCompanySurveyService extends IBaseService<IisCompanySurvey, String> {

	/**
	 * 
	 * @Title: saveIisCompanySurvey 
	 * @Description: TODO(新增企业概况表) 
	 * @param iisCompanySurvey
	 * @return Map
	 */
	Map saveIisCompanySurvey(IisCompanySurvey iisCompanySurvey);

	/**
	 * 
	 * @Title: updateIisCompanySurvey 
	 * @Description: TODO(修改企业概况表) 
	 * @param iisCompanySurvey
	 * @return Map
	 */
	Map updateIisCompanySurvey(IisCompanySurvey iisCompanySurvey);

	/**
	 * 
	 * @Title: delIisCompanySurvey 
	 * @Description: TODO(删除企业概况表（批量）) 
	 * @param idStrs
	 * @return Map
	 */
	Map delIisCompanySurvey(String idStrs);

	/**
	 * 
	 * @Title: queryIisCompanySurveyById 
	 * @Description: TODO(根据ID查询企业概况表信息) 
	 * @param id
	 * @return Map
	 */
	Map queryIisCompanySurveyById(String id);

	/**
	 * 
	 * @Title: queryIisCompanySurveyList 
	 * @Description: TODO(查询企业概况表列表信息) 
	 * @param ue
	 * @return Map
	 */
	Map queryIisCompanySurveyList(IisCompanySurveyUserEntity ue);

}
