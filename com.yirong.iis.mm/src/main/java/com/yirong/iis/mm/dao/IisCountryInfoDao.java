package com.yirong.iis.mm.dao;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.iis.mm.entity.IisCountryInfo;
import com.yirong.iis.mm.entity.IisCountrySurvey;

/**
 * 功能描述：国家信息表dao接口
 * 
 * @author 陈清沣
 *         <p>
 *         创建时间 ：2017-11-24 18:16:59
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public interface IisCountryInfoDao extends IBaseDao<IisCountryInfo, String> {

    /**
     * 功能描述：根据英文名查询国家信息表
     *
     * @author 陈清沣
     *         <p>
     *         创建时间 ：2017-11-29 17:18
     *         </p>
     *
     *         <p>
     *         修改历史：(修改人，修改时间，修改原因/内容)
     *         </p>
     */
    IisCountryInfo getByEnglishName(String englishName);
}
