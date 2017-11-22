package com.yirong.iis.user.api;

import com.yirong.commons.cache.eif.RedisCacheEif;
import com.yirong.commons.util.datatype.JsonUtil;
import com.yirong.iis.user.entity.IisMyFavorites;
import com.yirong.iis.user.service.IisMyFavoritesService;
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
@RequestMapping("IisMyFavoritesApi")
@ResponseBody
public class IisMyFavoritesApi {

    @Autowired
    private IisMyFavoritesService iisMyFavoritesService;

    /**
     * 功能描述：新增我的收藏
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
        IisMyFavorites iisMyFavorites = (IisMyFavorites) JsonUtil.StringToObject(param,
                IisMyFavorites.class);
        String tokenId = JsonUtil.getJsonStrByAttrName(paramAll, "sessionId");
        String creator = RedisCacheEif.hget(tokenId, "id");
        iisMyFavorites.setCreator(creator);
        // 业务处理
        Map map = this.iisMyFavoritesService.saveIisMyFavorites(iisMyFavorites, tokenId);
        return JsonUtil.ObjectToStringClob(map);
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
        Map map = iisMyFavoritesService.queryIisMyFavoritesByCreatorAndObjId(creator, objId);
        return JsonUtil.ObjectToStringClob(map);
    }

    /**
     * 功能描述：删除我的收藏
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
        Map map = iisMyFavoritesService.delIisMyFavorite(objIid.replace("[","").replace("]","").replace("\"",""), tokenId);
        return JsonUtil.ObjectToStringClob(map);
    }
}
