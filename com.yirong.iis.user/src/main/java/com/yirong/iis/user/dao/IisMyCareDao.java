package com.yirong.iis.user.dao;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.iis.user.entity.IisMyCare;

/**
 * 功能描述：我的关注dao接口
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
public interface IisMyCareDao extends IBaseDao<IisMyCare, String> {

    /**
     * 功能描述：根据创建者和对象ID
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
    IisMyCare findByCreatorAndObjId(String creator, String objId);
}
