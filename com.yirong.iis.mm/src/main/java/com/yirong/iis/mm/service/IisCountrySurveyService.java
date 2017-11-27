package com.yirong.iis.mm.service;

import java.util.Map;

import com.yirong.awaken.core.service.IBaseService;
import com.yirong.iis.mm.entity.IisCountrySurvey;
import com.yirong.iis.mm.userentity.IisCountrySurveyUserEntity;

/**
 * 功能描述：国家概括表service接口
 * 
 * @author 陈清沣
 *         <p>
 *         创建时间 ：2017-11-24 18:17:00
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
	 * @author 陈清沣
	 *         <p>
	 *         创建时间 ：2017-11-24 18:17:00
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
	 * @author 陈清沣
	 *         <p>
	 *         创建时间 ：2017-11-24 18:17:00
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
	 * @author 陈清沣
	 *         <p>
	 *         创建时间 ：2017-11-24 18:17:00
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
	 * @author 陈清沣
	 *         <p>
	 *         创建时间 ：2017-11-24 18:17:00
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
	 * @author 陈清沣
	 *         <p>
	 *         创建时间 ：2017-11-24 18:17:00
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param ue
	 * @return
	 */
	Map queryIisCountrySurveyList(IisCountrySurveyUserEntity ue);

}
