package com.yirong.iis.mm.service;

import java.util.Map;

import com.yirong.awaken.core.service.IBaseService;
import com.yirong.iis.mm.entity.IisReportTemplate;
import com.yirong.iis.mm.userentity.IisReportTemplateUserEntity;

/**
* 功能描述：报告模版表service接口
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
public interface IisReportTemplateService extends IBaseService<IisReportTemplate, String> {

	 /**
	 * 功能描述：查询报告模版表列表信息
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
	Map queryIisReportTemplateList(IisReportTemplateUserEntity ue);
}
