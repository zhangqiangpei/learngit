package com.yirong.iis.user.api;

import com.yirong.commons.cache.eif.RedisCacheEif;
import com.yirong.commons.util.datatype.JsonUtil;
import com.yirong.iis.user.entity.IisMyCare;
import com.yirong.iis.user.service.IisMyCareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 功能描述：报告表api接口
 *
 * @author 林明铁
 *         <p>
 *         创建时间 ：2017-11-09 10:00:09
 *         </p>
 *
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("IisMyCareApi")
@ResponseBody
public class IisMyCareApi {

    @Autowired
    private IisMyCareService iisMyCareService;

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
     * @param paramAll
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@RequestBody String paramAll) {
        // 获取参数信息
        String param = JsonUtil.getJsonStrByAttrName(paramAll,
                "context");
        // 实体转换
        IisMyCare iisMyCare = (IisMyCare) JsonUtil.StringToObject(param,
                IisMyCare.class);
        String tokenId = JsonUtil.getJsonStrByAttrName(paramAll, "sessionId");
        String creator = RedisCacheEif.hget(tokenId, "id");
        iisMyCare.setCreator(creator);
        // 业务处理
        Map map = this.iisMyCareService.saveIisMyCare(iisMyCare, tokenId);
        return JsonUtil.ObjectToStringClob(map);
    }

    /**
     * 功能描述：根据ObjID和创建者查询我的关注
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
        Map map = iisMyCareService.queryIisMyCareByCreatorAndObjId(creator, objId);
        return JsonUtil.ObjectToStringClob(map);
    }

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
     * @param paramAll
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestBody String paramAll) {
        // 获取参数信息
        String pathName = "context/id";
        String objIid = JsonUtil.getJsonStrByAttrName(paramAll, pathName);
        String tokenId = JsonUtil.getJsonStrByAttrName(paramAll, "sessionId");
        // 处理业务
        Map map = iisMyCareService.delIisMyCare(objIid.replace("[","").replace("]","").replace("\"",""), tokenId);
        return JsonUtil.ObjectToStringClob(map);
    }
}
