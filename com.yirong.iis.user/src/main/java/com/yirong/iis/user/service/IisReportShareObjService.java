package com.yirong.iis.user.service;

import com.yirong.awaken.core.service.IBaseService;
import com.yirong.iis.user.entity.IisReportShareObj;

import java.util.Map;

/**
* 功能描述：报告共享对象表service接口
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
public interface IisReportShareObjService extends IBaseService<IisReportShareObj, String> {

    /**
     * 功能描述：新增报告共享对象表
     *
     * @author 林明铁
     *         <p>
     *         创建时间 ：2017-11-09 10:00:09
     *         </p>
     *
     *         <p>
     *         修改历史：(修改人，修改时间，修改原因/内容)
     *         </p>
     * @param iisReportShareObj
     * @return
     */
    Map saveIisReportShareObj(IisReportShareObj iisReportShareObj);
}
