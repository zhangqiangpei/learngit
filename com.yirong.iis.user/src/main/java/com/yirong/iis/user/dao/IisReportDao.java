package com.yirong.iis.user.dao;


import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.iis.user.entity.IisReport;

 /**
 * 功能描述：报告表dao接口
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
 public interface IisReportDao extends IBaseDao<IisReport, String> {

     /**
      * 功能描述：根据报告名称查询
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
     IisReport findByReportName(String reportName);
}
