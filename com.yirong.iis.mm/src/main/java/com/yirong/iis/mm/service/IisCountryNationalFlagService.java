package com.yirong.iis.mm.service;

import java.util.Map;

import com.yirong.awaken.core.service.IBaseService;
import com.yirong.iis.mm.entity.IisCountryNationalFlag;
import com.yirong.iis.mm.userentity.IisCountryNationalFlagUserEntity;

/**
 * 功能描述：国家国旗表service接口
 * 
 * @author 陈清沣
 *         <p>
 *         创建时间 ：2017-11-29 10:40:39
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
	 * @author 陈清沣
	 *         <p>
	 *         创建时间 ：2017-11-29 10:40:39
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param engName
	 * @return
	 */
	Map saveIisCountryNationalFlag(String engName,boolean isUpdate);

	/**
	 * 功能描述：修改国家国旗表
	 * 
	 * @author 陈清沣
	 *         <p>
	 *         创建时间 ：2017-11-29 10:40:39
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param engName
	 * @return
	 */
	Map updateIisCountryNationalFlag(String engName);

	/**
	 * 功能描述：删除国家国旗表（批量）
	 * 
	 * @author 陈清沣
	 *         <p>
	 *         创建时间 ：2017-11-29 10:40:39
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
	 * 功能描述：根据英文名查询国家国旗表信息
	 * 
	 * @author 陈清沣
	 *         <p>
	 *         创建时间 ：2017-11-29 10:40:39
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param id
	 * @return
	 */
	Map queryIisCountryNationalFlagByEngName(String engName);

}
