package com.yirong.iis.user.service;

import java.util.Map;

import com.yirong.awaken.core.service.IBaseService;
import com.yirong.iis.user.entity.IisLawsRegulationsCy;
import com.yirong.iis.user.userentity.IisLawsRegulationsCyUserEntity;

 /**
 * 功能描述：法律法规分类service接口
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
public interface IisLawsRegulationsCyService extends IBaseService<IisLawsRegulationsCy, String> {

	/**
	 * 功能描述：新增法律法规分类
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-27 16:13:26
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisLawsRegulationsCy
	 * @return
	 */
	Map saveIisLawsRegulationsCy(IisLawsRegulationsCy iisLawsRegulationsCy);

	/**
	 * 功能描述：修改法律法规分类
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-27 16:13:26
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisLawsRegulationsCy
	 * @return
	 */
	Map updateIisLawsRegulationsCy(IisLawsRegulationsCy iisLawsRegulationsCy);

	/**
	 * 功能描述：删除法律法规分类（批量）
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
	Map delIisLawsRegulationsCy(String idStrs);

	/**
	 * 功能描述：根据ID查询法律法规分类信息
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
	Map queryIisLawsRegulationsCyById(String id);

	 /**
	 * 功能描述：查询法律法规分类列表信息
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
	Map queryIisLawsRegulationsCyList(IisLawsRegulationsCyUserEntity ue);

	/**
	 * 查询分类 以及分类下的法律
	 * @param para
	 * @return
	 */
	Map queryList(IisLawsRegulationsCyUserEntity para);

}
