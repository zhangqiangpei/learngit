package com.yirong.iis.user.service;

import java.util.Map;

import com.yirong.awaken.core.service.IBaseService;
import com.yirong.iis.user.entity.IisRegulators;
import com.yirong.iis.user.userentity.IisRegulatorsUserEntity;

 /**
 * 功能描述：监管机构表service接口
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-27 11:23:46
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
public interface IisRegulatorsService extends IBaseService<IisRegulators, String> {

	/**
	 * 功能描述：新增监管机构表
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-27 11:23:46
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisRegulators
	 * @return
	 */
	Map saveIisRegulators(IisRegulators iisRegulators);

	/**
	 * 功能描述：修改监管机构表
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-27 11:23:46
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisRegulators
	 * @return
	 */
	Map updateIisRegulators(IisRegulators iisRegulators);

	/**
	 * 功能描述：删除监管机构表（批量）
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-27 11:23:46
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param idStrs
	 * @return
	 */
	Map delIisRegulators(String idStrs);

	/**
	 * 功能描述：根据ID查询监管机构表信息
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-27 11:23:46
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param id
	 * @return
	 */
	Map queryIisRegulatorsById(String id);

	 /**
	 * 功能描述：查询监管机构表列表信息
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-27 11:23:46
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param ue
	 * @return
	 */
	Map queryIisRegulatorsList(IisRegulatorsUserEntity ue);

	/**
	 * 不分页查询
	 * @param para
	 * @return
	 */
	Map queryList(IisRegulatorsUserEntity para);

}
