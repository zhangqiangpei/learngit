package com.yirong.iis.mm.service;

import java.util.Map;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.awaken.core.service.IBaseService;
import com.yirong.iis.mm.entity.IisCompanyList;
import com.yirong.iis.mm.userentity.IisCompanyListUserEntity;

/**
 * 功能描述：企业列表Service接口
 * 
 * @author 薛雅芳
 *         <p>
 *         修改时间:2017年11月28日下午1:41:27
 *         </p>
 *         
 *         <p>
 *         修改历史:(修改人,修改时间,修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
public interface IisCompanyListService extends IBaseService<IisCompanyList, String>{

	/**
	 * 
	 * 功能描述：删除企业列表(批量)
	 *
	 * @author 薛雅芳
	 *         <p>
	 *         创建时间 ：2017年11月28日下午3:44:17
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param paramAll
	 * @return
	 */
	Map delIisCompanyList(String idStrs);

	/**
	 * 
	 * 功能描述：获取dao操作类
	 *
	 * @author 薛雅芳
	 *         <p>
	 *         创建时间 ：2017年11月28日下午3:54:39
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param paramAll
	 * @return
	 */
	IBaseDao<IisCompanyList, String> getBaseDao();

	/**
	 * 
	 * 功能描述：查询企业列表的列表信息
	 *
	 * @author 薛雅芳
	 *         <p>
	 *         创建时间 ：2017年11月28日下午4:19:33
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param paramAll
	 * @return
	 */
	Map queryIisCompanyListList(IisCompanyListUserEntity ue);

	/**
	 * 
	 * 功能描述：根据国家类型排除去查找国家
	 *
	 * @author 薛雅芳
	 *         <p>
	 *         创建时间 ：2017年11月30日上午11:51:51
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param paramAll
	 * @return
	 */
	Map queryIisCountryListByTypeId(String typeId);

	

}
