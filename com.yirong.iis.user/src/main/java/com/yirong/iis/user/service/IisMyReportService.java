package com.yirong.iis.user.service;


import java.util.Map;

import com.yirong.awaken.core.service.IBaseService;
import com.yirong.iis.user.entity.IisMyReport;

/**
 * 功能描述：个人报告表service接口
 *
 * @author
 *         <p>
 *         创建时间 ：2017-11-27 15:20:20
 *         </p>
 *
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
public interface IisMyReportService extends IBaseService<IisMyReport, String> {

    /**
     * 功能描述：新增个人报告表
     *
     * @author 林明铁
     *         <p>
     *         创建时间 ：2017-11-27 15:20:20
     *         </p>
     *
     *         <p>
     *         修改历史：(修改人，修改时间，修改原因/内容)
     *         </p>
     * @param iisMyReport
     * @return
     */
    Map saveIisMyReport(IisMyReport iisMyReport);

    /**
     * 功能描述：删除个人报告表（批量）
     *
     * @author 林明铁
     *         <p>
     *         创建时间 ：2017-11-27 15:20:20
     *         </p>
     *
     *         <p>
     *         修改历史：(修改人，修改时间，修改原因/内容)
     *         </p>
     * @param idStrs
     * @param tokenId
     * @return
     */
    Map delIisMyReport(String idStrs, String tokenId);

    /**
     * 功能描述：根据创建者和对象ID查询
     *
     * @author 林明铁
     *         <p>
     *         创建时间 ：2017-11-27 15:20:20
     *         </p>
     *
     *         <p>
     *         修改历史：(修改人，修改时间，修改原因/内容)
     *         </p>
     * @return
     */
    Map queryIisMyReportByCreatorAndObjId(String creator, String objId);
}
