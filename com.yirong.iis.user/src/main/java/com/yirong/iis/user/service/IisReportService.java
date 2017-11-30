package com.yirong.iis.user.service;

import java.io.File;
import java.util.Map;

import com.yirong.awaken.core.service.IBaseService;
import com.yirong.iis.user.entity.IisReport;
import com.yirong.iis.user.userentity.IisReportShareUserEntity;
import com.yirong.iis.user.userentity.IisReportUserEntity;

 /**
 * 功能描述：报告表service接口
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
public interface IisReportService extends IBaseService<IisReport, String> {
	 /**
	  * 功能描述：新增报告表
	  *
	  * @author 林明铁
	  *         <p>
	  *         创建时间 ：2017-11-09 10:00:09
	  *         </p>
	  *
	  *         <p>
	  *         修改历史：(修改人，修改时间，修改原因/内容)
	  *         </p>
	  * @param iisReport
	  * @param tokenId
      * @return
	  */
	 Map saveIisReport(IisReport iisReport, String tokenId);

	 /**
	  * 功能描述：修改报告表
	  *
	  * @author 林明铁
	  *         <p>
	  *         创建时间 ：2017-11-09 10:00:09
	  *         </p>
	  *
	  *         <p>
	  *         修改历史：(修改人，修改时间，修改原因/内容)
	  *         </p>
	  * @param iisReport
	  * @return
	  */
	 Map updateIisReport(IisReport iisReport, String tokenId);

	 /**
	  * 功能描述：删除报告表（批量）
	  *
	  * @author 林明铁
	  *         <p>
	  *         创建时间 ：2017-11-09 10:00:09
	  *         </p>
	  *
	  *         <p>
	  *         修改历史：(修改人，修改时间，修改原因/内容)
	  *         </p>
	  * @param idStrs
	  * @param tokenId
      * @return
	  */
	 Map delIisReport(String idStrs, String tokenId);

	 /**
	  * 功能描述：根据ID查询报告表信息
	  *
	  * @author 林明铁
	  *         <p>
	  *         创建时间 ：2017-11-09 10:00:09
	  *         </p>
	  *
	  *         <p>
	  *         修改历史：(修改人，修改时间，修改原因/内容)
	  *         </p>
	  * @param id
	  * @return
	  */
	 Map queryIisReportById(String id);

	 /**
	  * 功能描述：查询报告表列表信息
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
	 Map queryIisReportList(IisReportUserEntity ue);

	 /**
	  * 功能描述：报告转换成docs，返回转换后文件
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
	 File reportToDocs(IisReport iisReport);
 }
