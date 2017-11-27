package com.yirong.iis.user.api;


import java.util.HashMap;
import java.util.Map;

import com.yirong.commons.cache.eif.RedisCacheEif;
import com.yirong.iis.user.entity.IisMyReport;
import com.yirong.iis.user.service.IisMyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yirong.commons.util.order.Order;
import com.yirong.commons.util.datatype.JsonUtil;

/**
 * 功能描述：个人报告表api接口
 *
 * @author
 *         <p>
 *         创建时间 ：2017-11-27 15:20:20
 *         </p>
 *
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("IisMyReportApi")
@ResponseBody
public class IisMyReportApi {

    /**
     * 标准service注入
     */
    @Autowired
    private IisMyReportService iisMyReportService;

    /**
     * 功能描述：新增个人报告表
     *
     * @author 林明铁
     *         <p>
     *         创建时间 ：2017-11-27 15:20:20
     *         </p>
     *
     *         <p>
     *         修改历史：(修改人，修改时间，修改原因/内容)
     *         </p>
     * @param paramAll
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@RequestBody String paramAll) {
        // 获取参数信息
        String param = JsonUtil.getJsonStrByAttrName(paramAll,
                "context");
        // 实体转换
        IisMyReport iisMyReport = (IisMyReport) JsonUtil.StringToObject(param,
                IisMyReport.class);
        String tokenId = JsonUtil.getJsonStrByAttrName(paramAll, "sessionId");
        String creator = RedisCacheEif.hget(tokenId, "id");
        iisMyReport.setCreator(creator);
        // 业务处理
        Map map = this.iisMyReportService.saveIisMyReport(iisMyReport);
        return JsonUtil.ObjectToString(map);
    }

    /**
     * 功能描述：删除个人报告表（批量）
     *
     * @author 林明铁
     *         <p>
     *         创建时间 ：2017-11-27 15:20:20
     *         </p>
     *
     *         <p>
     *         修改历史：(修改人，修改时间，修改原因/内容)
     *         </p>
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestBody String paramAll) {
        // 获取参数信息
        String pathName = "context/id";
        String objId = JsonUtil.getJsonStrByAttrName(paramAll, pathName);
        String tokenId = JsonUtil.getJsonStrByAttrName(paramAll, "sessionId");
        // 处理业务
        Map map = iisMyReportService.delIisMyReport(objId.replace("[","").replace("]","").replace("\"",""), tokenId);
        return JsonUtil.ObjectToString(map);
    }

    /**
     * 功能描述：根据ObjID和创建者查询我的收藏
     *
     * @author 林明铁
     *         <p>
     *         创建时间 ：2017-11-09 10:00:09
     *         </p>
     *
     *         <p>
     *         修改历史：(修改人，修改时间，修改原因/内容)
     *         </p>
     * @param paramAll
     * @return
     */
    @RequestMapping(value = "getByObjIdAndCreator", method = RequestMethod.POST)
    public String getByObjIdAndCreator(@RequestBody String paramAll) {
        // 获取参数信息
        String pathName = "context/id";
        String objId = JsonUtil.getJsonStrByAttrName(paramAll, pathName);
        String tokenId = JsonUtil.getJsonStrByAttrName(paramAll, "sessionId");
        String creator = RedisCacheEif.hget(tokenId, "id");
        // 处理业务
        Map map = iisMyReportService.queryIisMyReportByCreatorAndObjId(creator, objId);
        return JsonUtil.ObjectToStringClob(map);
    }
}
