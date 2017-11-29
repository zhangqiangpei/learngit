package com.yirong.iis.user.service;

import java.util.Map;

import com.yirong.awaken.core.service.IBaseService;
import com.yirong.iis.user.entity.IisCountryCredit;
import com.yirong.iis.user.userentity.IisCountryCreditUserEntity;

 /**
 * 功能描述：国家信用表service接口
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-29 09:44:05
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
public interface IisCountryCreditService extends IBaseService<IisCountryCredit, String> {

	/**
	 * 功能描述：新增国家信用表
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-29 09:44:05
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisCountryCredit
	 * @return
	 */
	Map saveIisCountryCredit(IisCountryCredit iisCountryCredit);

	/**
	 * 功能描述：修改国家信用表
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-29 09:44:05
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisCountryCredit
	 * @return
	 */
	Map updateIisCountryCredit(IisCountryCredit iisCountryCredit);

	/**
	 * 功能描述：删除国家信用表（批量）
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-29 09:44:05
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param idStrs
	 * @return
	 */
	Map delIisCountryCredit(String idStrs);

	/**
	 * 功能描述：根据ID查询国家信用表信息
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-29 09:44:05
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param id
	 * @return
	 */
	Map queryIisCountryCreditById(String id);

	 /**
	 * 功能描述：查询国家信用表列表信息
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-29 09:44:05
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param ue
	 * @return
	 */
	Map queryIisCountryCreditList(IisCountryCreditUserEntity ue);

	/**
	 * 根据国家名称查询相关信息
	 * @param id
	 * @return
	 */
	Map queryIisCountryCreditByName(String englishName);

}
