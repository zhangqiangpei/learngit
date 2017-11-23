package com.yirong.iis.user.service;

import java.util.Map;

import com.yirong.awaken.core.service.IBaseService;
import com.yirong.iis.user.entity.IisReportType;
import com.yirong.iis.user.userentity.IisReportTypeUserEntity;

/**
* 功能描述：报告分类表service接口
* 
* @author 林明铁
*         <p>
*         创建时间 ：2017-11-09 10:00:09
*         </p>
* 
*         <p>
*         修改历史：(修改人，修改时间，修改原因/内容)
*         </p>
*/
@SuppressWarnings("rawtypes")
public interface IisReportTypeService extends IBaseService<IisReportType, String> {

	 /**
	 * 功能描述：查询报告分类表列表信息
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-09 10:00:09
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param ue
	 * @return
	 */
	Map queryIisReportTypeList(IisReportTypeUserEntity ue);

	/**
	 * 功能描述：获取每个分类的五条记录
	 *
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-09 10:00:09
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @return
	 */
    Map queryIisReportTypeListFiveRecord();

	/**
	 * 功能描述：获取每个分类的五条记录
	 *
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-09 10:00:09
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @return
	 */
    Map queryIisReportTypeListThreeRecord();
}
