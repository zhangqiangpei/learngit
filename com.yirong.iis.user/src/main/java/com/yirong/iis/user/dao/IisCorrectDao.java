package com.yirong.iis.user.dao;


import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.iis.user.entity.IisCorrect;

/**
 * 功能描述：修正表dao接口
 *
 * @author
 *         <p>
 *         创建时间 ：2017-11-28 14:52:54
 *         </p>
 *
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public interface IisCorrectDao extends IBaseDao<IisCorrect, String> {

    /**
     * 功能描述：修正表dao接口
     *
     * @author
     *         <p>
     *         创建时间 ：2017-11-28 14:52:54
     *         </p>
     *
     *         <p>
     *         修改历史：(修改人，修改时间，修改原因/内容)
     *         </p>
     */
    IisCorrect findBySourceContentAndSourceLanguage(String sourceContent, String sourceLanguage);

}
