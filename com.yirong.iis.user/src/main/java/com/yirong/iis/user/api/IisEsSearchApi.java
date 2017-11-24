package com.yirong.iis.user.api;

import com.yirong.commons.util.datatype.JsonUtil;
import com.yirong.commons.util.order.Order;
import com.yirong.iis.user.service.IisEsSearchService;
import com.yirong.iis.user.userentity.IisNewsUserEntity;
import com.yirong.iis.user.userentity.IisReportUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述：搜索api
 *
 * @author
 *         <p>
 *         创建时间 ：2017年9月29日 下午3:35:37
 *         </p>
 *
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("IisEsSearchApi")
@ResponseBody
public class IisEsSearchApi {

    @Autowired
    private IisEsSearchService iisEsSearchService;

    /**
     * 功能描述：搜索新闻
     *
     * @author
     *         <p>
     *         创建时间 ：2017年9月29日 下午3:43:30
     *         </p>
     *
     *         <p>
     *         修改历史：(修改人，修改时间，修改原因/内容)
     *         </p>
     *
     * @param paramAll
     * @return
     *
     */
    @RequestMapping(value = "searchReports", method = RequestMethod.POST)
    public String searchReports(@RequestBody String paramAll) {
        // 获取参数信息
        String param = JsonUtil.getJsonStrByAttrName(paramAll, "context");
        String tokenId = JsonUtil.getJsonStrByAttrName(paramAll, "sessionId");
        // 定义转换对象属性类
        Map<String, Class> calssMap = new HashMap<String, Class>();
        calssMap.put("orders", Order.class);
        // 实体转换
        IisReportUserEntity ue = (IisReportUserEntity) JsonUtil.StringToObject(param,
                IisReportUserEntity.class, calssMap);
        // 处理业务
        Map map = iisEsSearchService.esSearchReport(ue, tokenId);
        return JsonUtil.ObjectToString(map);
    }

    /**
     * 功能描述：搜索新闻
     *
     * @author
     *         <p>
     *         创建时间 ：2017年9月29日 下午3:43:30
     *         </p>
     *
     *         <p>
     *         修改历史：(修改人，修改时间，修改原因/内容)
     *         </p>
     *
     * @param paramAll
     * @return
     *
     */
    @RequestMapping(value = "searchNews", method = RequestMethod.POST)
    public String searchNews(@RequestBody String paramAll) {
        // 获取参数信息
        String param = JsonUtil.getJsonStrByAttrName(paramAll, "context");
        String tokenId = JsonUtil.getJsonStrByAttrName(paramAll, "sessionId");
        // 定义转换对象属性类
        Map<String, Class> calssMap = new HashMap<String, Class>();
        calssMap.put("orders", Order.class);
        // 实体转换
        IisNewsUserEntity ue = (IisNewsUserEntity) JsonUtil.StringToObject(param,
                IisNewsUserEntity.class, calssMap);
        // 处理业务
        Map map = iisEsSearchService.esSearchNews(ue, tokenId);
        return JsonUtil.ObjectToString(map);
    }
}
