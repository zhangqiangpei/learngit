package com.yirong.iis.user.service;

import java.util.Map;

import com.yirong.awaken.core.service.IBaseService;
import com.yirong.iis.user.entity.IisLawsRegulations;
import com.yirong.iis.user.userentity.IisLawsRegulationsUserEntity;

 /**
 * 功能描述：法律法规表service接口
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-27 16:13:26
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
public interface IisLawsRegulationsService extends IBaseService<IisLawsRegulations, String> {

	/**
	 * 功能描述：新增法律法规表
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-27 16:13:26
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisLawsRegulations
	 * @return
	 */
	Map saveIisLawsRegulations(IisLawsRegulations iisLawsRegulations);

	/**
	 * 功能描述：修改法律法规表
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-27 16:13:26
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisLawsRegulations
	 * @return
	 */
	Map updateIisLawsRegulations(IisLawsRegulations iisLawsRegulations);

	/**
	 * 功能描述：删除法律法规表（批量）
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-27 16:13:26
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param idStrs
	 * @return
	 */
	Map delIisLawsRegulations(String idStrs);

	/**
	 * 功能描述：根据ID查询法律法规表信息
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-27 16:13:26
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param id
	 * @return
	 */
	Map queryIisLawsRegulationsById(String id);

	 /**
	 * 功能描述：查询法律法规表列表信息
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-27 16:13:26
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param ue
	 * @return
	 */
	Map queryIisLawsRegulationsList(IisLawsRegulationsUserEntity ue);

}
