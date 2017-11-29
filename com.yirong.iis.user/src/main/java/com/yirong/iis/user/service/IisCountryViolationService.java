package com.yirong.iis.user.service;

import java.util.Map;

import com.yirong.awaken.core.service.IBaseService;
import com.yirong.iis.user.entity.IisCountryViolation;
import com.yirong.iis.user.userentity.IisCountryViolationUserEntity;

 /**
 * 功能描述：国家违约概率表service接口
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
public interface IisCountryViolationService extends IBaseService<IisCountryViolation, String> {

	/**
	 * 功能描述：新增国家违约概率表
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-29 09:44:05
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisCountryViolation
	 * @return
	 */
	Map saveIisCountryViolation(IisCountryViolation iisCountryViolation);

	/**
	 * 功能描述：修改国家违约概率表
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-29 09:44:05
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisCountryViolation
	 * @return
	 */
	Map updateIisCountryViolation(IisCountryViolation iisCountryViolation);

	/**
	 * 功能描述：删除国家违约概率表（批量）
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
	Map delIisCountryViolation(String idStrs);

	/**
	 * 功能描述：根据ID查询国家违约概率表信息
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
	Map queryIisCountryViolationById(String id);

	 /**
	 * 功能描述：查询国家违约概率表列表信息
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
	Map queryIisCountryViolationList(IisCountryViolationUserEntity ue);

	/**
	 * 不分页查询对应国家下的所有信息
	 * @param psue
	 * @return
	 */
	Map querylist(IisCountryViolationUserEntity psue);

}
