package com.yirong.iis.user.dao;


import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.iis.user.entity.IisReportShare;

 /**
 * 功能描述：报告共享表dao接口
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
public interface IisReportShareDao extends IBaseDao<IisReportShare, String> {

     /**
      * 功能描述：根据报告ID和共享类型查询
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
    IisReportShare findByReportIdAndShareType(String reportId, String shareType);
}

