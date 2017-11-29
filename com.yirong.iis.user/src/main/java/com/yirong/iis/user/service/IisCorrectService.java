package com.yirong.iis.user.service;


import java.util.Map;

import com.yirong.awaken.core.service.IBaseService;
import com.yirong.iis.user.entity.IisCorrect;

/**
 * 功能描述：修正表service接口
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
@SuppressWarnings("rawtypes")
public interface IisCorrectService extends IBaseService<IisCorrect, String> {

    /**
     * 功能描述：新增修正表
     *
     * @author 林明铁
     *         <p>
     *         创建时间 ：2017-11-28 14:52:54
     *         </p>
     *
     *         <p>
     *         修改历史：(修改人，修改时间，修改原因/内容)
     *         </p>
     * @param iisCorrect
     * @return
     */
    Map saveIisCorrect(IisCorrect iisCorrect);
}
