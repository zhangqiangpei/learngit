package com.yirong.iis.user.service;

import java.util.Map;

import com.yirong.awaken.core.service.IBaseService;
import com.yirong.iis.user.entity.IisCountryNationalFlag;
import com.yirong.iis.user.userentity.IisCountryNationalFlagUserEntity;

 /**
 * 功能描述：国家国旗表service接口
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-28 17:12:26
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
public interface IisCountryNationalFlagService extends IBaseService<IisCountryNationalFlag, String> {

	/**
	 * 功能描述：新增国家国旗表
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-28 17:12:26
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisCountryNationalFlag
	 * @return
	 */
	Map saveIisCountryNationalFlag(IisCountryNationalFlag iisCountryNationalFlag);

	/**
	 * 功能描述：修改国家国旗表
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-28 17:12:26
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisCountryNationalFlag
	 * @return
	 */
	Map updateIisCountryNationalFlag(IisCountryNationalFlag iisCountryNationalFlag);

	/**
	 * 功能描述：删除国家国旗表（批量）
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-28 17:12:26
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param idStrs
	 * @return
	 */
	Map delIisCountryNationalFlag(String idStrs);

	/**
	 * 功能描述：根据ID查询国家国旗表信息
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-28 17:12:26
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param id
	 * @return
	 */
	Map queryIisCountryNationalFlagById(String id);

	 /**
	 * 功能描述：查询国家国旗表列表信息
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-28 17:12:26
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param ue
	 * @return
	 */
	Map queryIisCountryNationalFlagList(IisCountryNationalFlagUserEntity ue);

}
