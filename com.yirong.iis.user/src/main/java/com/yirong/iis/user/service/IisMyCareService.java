package com.yirong.iis.user.service;

import com.yirong.iis.user.entity.IisMyCare;

import java.util.Map;

/**
 * 功能描述：我的关注
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
public interface IisMyCareService {

    /**
     * 功能描述：新增我的关注
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
    Map saveIisMyCare(IisMyCare iisMyCare, String tokenId);

    /**
     * 功能描述：根据ID查询我的关注
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
    Map queryIisMyCareByCreatorAndObjId(String creator, String id);

    /**
     * 功能描述：删除我的关注
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
    Map delIisMyCare(String replace, String tokenId);
}
