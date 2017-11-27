package com.yirong.iis.mm.service;

import java.util.Map;

import com.yirong.awaken.core.service.IBaseService;
import com.yirong.iis.mm.entity.IisCountryInfo;
import com.yirong.iis.mm.userentity.IisCountryInfoUserEntity;

/**
 * 功能描述：国家信息表service接口
 * 
 * @author 陈清沣
 *         <p>
 *         创建时间 ：2017-11-24 18:16:59
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
public interface IisCountryInfoService extends IBaseService<IisCountryInfo, String> {

	/**
	 * 功能描述：新增国家信息表
	 * 
	 * @author 陈清沣
	 *         <p>
	 *         创建时间 ：2017-11-24 18:16:59
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisCountryInfo
	 * @return
	 */
	Map saveIisCountryInfo(IisCountryInfo iisCountryInfo);

	/**
	 * 功能描述：修改国家信息表
	 * 
	 * @author 陈清沣
	 *         <p>
	 *         创建时间 ：2017-11-24 18:16:59
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisCountryInfo
	 * @return
	 */
	Map updateIisCountryInfo(IisCountryInfo iisCountryInfo);

	/**
	 * 功能描述：删除国家信息表（批量）
	 * 
	 * @author 陈清沣
	 *         <p>
	 *         创建时间 ：2017-11-24 18:16:59
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param idStrs
	 * @return
	 */
	Map delIisCountryInfo(String idStrs);

	/**
	 * 功能描述：根据ID查询国家信息表信息
	 * 
	 * @author 陈清沣
	 *         <p>
	 *         创建时间 ：2017-11-24 18:16:59
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param id
	 * @return
	 */
	Map queryIisCountryInfoById(String id);

	 /**
	 * 功能描述：查询国家信息表列表信息
	 * 
	 * @author 陈清沣
	 *         <p>
	 *         创建时间 ：2017-11-24 18:16:59
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param ue
	 * @return
	 */
	Map queryIisCountryInfoList(IisCountryInfoUserEntity ue);

}
