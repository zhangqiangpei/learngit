package com.yirong.iis.user.service;

import com.yirong.iis.user.userentity.IisNewsUserEntity;
import com.yirong.iis.user.userentity.IisReportUserEntity;

import java.util.Map;

/**
 * @author MingTie
 * CreateDate：2017/11/24
 * Description：
 */
public interface IisEsSearchService {
    /**
     * 功能描述：搜索新闻
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
    Map esSearchNews(IisNewsUserEntity ue, String tokenId);

    /**
     * 功能描述：搜索报告
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
    Map esSearchReport(IisReportUserEntity ue, String tokenId);
}
