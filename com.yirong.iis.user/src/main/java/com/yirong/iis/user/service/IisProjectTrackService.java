package com.yirong.iis.user.service;


import java.util.Map;

import com.yirong.awaken.core.service.IBaseService;
import com.yirong.iis.user.entity.IisProjectTrack;
import com.yirong.iis.user.userentity.IisProjectTrackUserEntity;

/**
 * 功能描述：追踪表service接口
 *
 * @author
 *         <p>
 *         创建时间 ：2017-11-28 15:31:27
 *         </p>
 *
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
public interface IisProjectTrackService extends IBaseService<IisProjectTrack, String> {

    /**
     * 功能描述：新增追踪表
     *
     * @author 林明铁
     *         <p>
     *         创建时间 ：2017-11-28 15:31:27
     *         </p>
     *
     *         <p>
     *         修改历史：(修改人，修改时间，修改原因/内容)
     *         </p>
     * @param iisProjectTrack
     * @return
     */
    Map saveIisProjectTrack(IisProjectTrack iisProjectTrack);

    /**
     * 功能描述：查询追踪表列表信息
     *
     * @author 林明铁
     *         <p>
     *         创建时间 ：2017-11-28 15:31:27
     *         </p>
     *
     *         <p>
     *         修改历史：(修改人，修改时间，修改原因/内容)
     *         </p>
     * @param ue
     * @return
     */
    Map queryIisProjectTrackList(IisProjectTrackUserEntity ue);
}
