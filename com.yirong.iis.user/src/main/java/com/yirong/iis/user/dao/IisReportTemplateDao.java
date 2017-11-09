package com.yirong.iis.user.dao;


import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.iis.user.entity.IisReportTemplate;

 /**
 * 功能描述：报告模版表dao接口
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
public interface IisReportTemplateDao extends IBaseDao<IisReportTemplate, String> {

     /**
      * 功能描述：根据创建人和模版名称
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
    IisReportTemplate findByCreatorAndTemplateName(String creator, String templateName);
}

