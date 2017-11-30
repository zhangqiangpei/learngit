package com.yirong.iis.mm.dao;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.iis.mm.entity.IisCountrySurvey;
import com.yirong.iis.mm.entity.IisPoliticalEnvironment;

/**
 * 功能描述：政治环境表dao接口
 * 
 * @author 陈清沣
 *         <p>
 *         创建时间 ：2017-11-30 10:42:31
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public interface IisPoliticalEnvironmentDao extends IBaseDao<IisPoliticalEnvironment, String> {
    /**
     * 功能描述：根据英文名和字段名查询国家政治环境表
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
    IisPoliticalEnvironment getByCountryEngNameAndFieldName(String engName, String fieldName);
}
