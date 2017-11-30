package com.yirong.iis.user.dao;


import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.iis.user.entity.IisProjectTrack;

/**
 * 功能描述：追踪表dao接口
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
public interface IisProjectTrackDao extends IBaseDao<IisProjectTrack, String> {

    /**
     * 功能描述：追踪表dao接口
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
    IisProjectTrack findByCreatorAndTypeCodeAndProjectName(String creator, String typeCode, String projectName);

}
