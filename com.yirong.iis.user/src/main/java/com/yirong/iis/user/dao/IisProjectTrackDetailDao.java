package com.yirong.iis.user.dao;


import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.iis.user.entity.IisProjectTrackDetail;

/**
 * 功能描述：跟踪明细表dao接口
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
public interface IisProjectTrackDetailDao extends IBaseDao<IisProjectTrackDetail, String> {

    /**
     * 功能描述：根据项目ID和资讯ID查询
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
    IisProjectTrackDetail findByProIdAndObjId(String proId, String objId);

}

