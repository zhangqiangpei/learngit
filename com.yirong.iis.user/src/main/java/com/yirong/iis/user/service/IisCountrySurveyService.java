package com.yirong.iis.user.service;

import java.util.Map;

import com.yirong.awaken.core.service.IBaseService;
import com.yirong.iis.user.entity.IisCountrySurvey;
import com.yirong.iis.user.userentity.IisCountrySurveyUserEntity;

 /**
 * 功能描述：国家概括表service接口
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-22 14:07:44
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
public interface IisCountrySurveyService extends IBaseService<IisCountrySurvey, String> {

	/**
	 * 功能描述：新增国家概括表
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-22 14:07:44
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisCountrySurvey
	 * @return
	 */
	Map saveIisCountrySurvey(IisCountrySurvey iisCountrySurvey);

	/**
	 * 功能描述：修改国家概括表
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-22 14:07:44
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisCountrySurvey
	 * @return
	 */
	Map updateIisCountrySurvey(IisCountrySurvey iisCountrySurvey);

	/**
	 * 功能描述：删除国家概括表（批量）
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-22 14:07:44
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param idStrs
	 * @return
	 */
	Map delIisCountrySurvey(String idStrs);

	/**
	 * 功能描述：根据ID查询国家概括表信息
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-22 14:07:44
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param id
	 * @return
	 */
	Map queryIisCountrySurveyById(String id);

	 /**
	 * 功能描述：查询国家概括表列表信息
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-22 14:07:44
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param ue
	 * @return
	 */
	Map queryIisCountrySurveyList(IisCountrySurveyUserEntity ue);

	Map queryList(IisCountrySurveyUserEntity psue);

}
