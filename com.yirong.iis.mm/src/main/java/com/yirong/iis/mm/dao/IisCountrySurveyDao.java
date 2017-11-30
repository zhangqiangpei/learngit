package com.yirong.iis.mm.dao;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.iis.mm.entity.IisCountrySurvey;

/**
 * 功能描述：国家概括表dao接口
 * 
 * @author 陈清沣
 *         <p>
 *         创建时间 ：2017-11-27 19:29:29
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public interface IisCountrySurveyDao extends IBaseDao<IisCountrySurvey, String> {

    /**
     * 功能描述：根据英文名和字段名查询国家概况表
     *
     * @author 陈清沣
     *         <p>
     *         创建时间 ：2017-11-29 8:58
     *         </p>
     *
     *         <p>
     *         修改历史：(修改人，修改时间，修改原因/内容)
     *         </p>
     */
    IisCountrySurvey getByCountryEngNameAndFieldName(String engName,String fieldName);

}
