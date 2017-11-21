package com.yirong.iis.user.service;

import com.yirong.iis.user.entity.IisMyFavorites;

import java.util.Map;

/**
 * 功能描述：我的收藏Service
 *
 * @author 林明铁
 *         <p>
 *         创建时间 ：2017-11-09 10:00:09
 *         </p>
 *
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 * @return
 */
public interface IisMyFavoritesService {

    /**
     * 功能描述：新增我的收藏
     *
     * @author 林明铁
     *         <p>
     *         创建时间 ：2017-11-09 10:00:09
     *         </p>
     *
     *         <p>
     *         修改历史：(修改人，修改时间，修改原因/内容)
     *         </p>
     * @return
     */
    Map saveIisMyFavorites(IisMyFavorites iisMyFavorites, String tokenId);

    /**
     * 功能描述：根据ID查询我的收藏
     *
     * @author 林明铁
     *         <p>
     *         创建时间 ：2017-11-09 10:00:09
     *         </p>
     *
     *         <p>
     *         修改历史：(修改人，修改时间，修改原因/内容)
     *         </p>
     * @return
     */
    Map queryIisMyFavoritesByCreatorAndObjId(String creator, String id);

    /**
     * 功能描述：删除我的收藏
     *
     * @author 林明铁
     *         <p>
     *         创建时间 ：2017-11-09 10:00:09
     *         </p>
     *
     *         <p>
     *         修改历史：(修改人，修改时间，修改原因/内容)
     *         </p>
     * @return
     */
    Map delIisMyFavorite(String replace, String tokenId);
}
