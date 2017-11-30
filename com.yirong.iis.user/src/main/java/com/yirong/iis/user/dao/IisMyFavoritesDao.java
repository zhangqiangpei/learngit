package com.yirong.iis.user.dao;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.iis.user.entity.IisMyFavorites;

/**
 * 功能描述：我的收藏dao接口
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
public interface IisMyFavoritesDao extends IBaseDao<IisMyFavorites, String> {

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
    IisMyFavorites findByCreatorAndObjId(String creator, String objId);
}
