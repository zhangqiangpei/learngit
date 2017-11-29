package com.yirong.iis.user.service;


import java.util.Map;

import com.yirong.awaken.core.service.IBaseService;
import com.yirong.iis.user.entity.IisProjectTrackDetail;

/**
 * 功能描述：跟踪明细表service接口
 *
 * @author
 *         <p>
 *         创建时间 ：2017-11-28 15:32:14
 *         </p>
 *
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
public interface IisProjectTrackDetailService extends IBaseService<IisProjectTrackDetail, String> {

    /**
     * 功能描述：新增跟踪明细表
     *
     * @author 林明铁
     *         <p>
     *         创建时间 ：2017-11-28 15:32:14
     *         </p>
     *
     *         <p>
     *         修改历史：(修改人，修改时间，修改原因/内容)
     *         </p>
     * @param iisProjectTrackDetail
     * @return
     */
    Map saveIisProjectTrackDetail(IisProjectTrackDetail iisProjectTrackDetail);
}
