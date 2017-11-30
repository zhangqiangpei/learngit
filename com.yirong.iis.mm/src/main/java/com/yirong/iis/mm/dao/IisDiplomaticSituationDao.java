package com.yirong.iis.mm.dao;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.iis.mm.entity.IisDiplomaticSituation;
import com.yirong.iis.mm.entity.IisPoliticalEnvironment;

/**
 * 功能描述：外交情况表(与中国)dao接口
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
public interface IisDiplomaticSituationDao extends IBaseDao<IisDiplomaticSituation, String> {

    /**
     * 功能描述：根据英文名查询国家外交情况表
     *
     * @author 陈清沣
     *         <p>
     *         创建时间 ：2017-11-30 16:19
     *         </p>
     *
     *         <p>
     *         修改历史：(修改人，修改时间，修改原因/内容)
     *         </p>
     */
    IisDiplomaticSituation getByEnglishName(String englishName);
}
