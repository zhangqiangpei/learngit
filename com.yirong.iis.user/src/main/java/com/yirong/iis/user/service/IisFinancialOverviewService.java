package com.yirong.iis.user.service;

import java.util.Map;

import com.yirong.awaken.core.service.IBaseService;
import com.yirong.iis.user.entity.IisFinancialOverview;
import com.yirong.iis.user.userentity.IisFinancialOverviewUserEntity;

 /**
 * 功能描述：企业财务总览service接口
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-22 17:21:53
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
public interface IisFinancialOverviewService extends IBaseService<IisFinancialOverview, String> {
 
	 /**
	  * 
	  * @Title: queryIisFinancialOverviewList 
	  * @Description: TODO(查询企业财务总览列表信息) 
	  * @param ue
	  * @return Map
	  */
	Map queryIisFinancialOverviewList(IisFinancialOverviewUserEntity ue);

}
