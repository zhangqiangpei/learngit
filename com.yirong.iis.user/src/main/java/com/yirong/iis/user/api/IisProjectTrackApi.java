package com.yirong.iis.user.api;


import java.util.HashMap;
import java.util.Map;

import com.yirong.iis.user.entity.IisProjectTrack;
import com.yirong.iis.user.service.IisProjectTrackService;
import com.yirong.iis.user.userentity.IisProjectTrackUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yirong.commons.util.order.Order;
import com.yirong.commons.util.datatype.JsonUtil;

/**
 * 功能描述：追踪表api接口
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
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("IisProjectTrackApi")
@ResponseBody
public class IisProjectTrackApi {

    /**
     * 标准service注入
     */
    @Autowired
    private IisProjectTrackService iisProjectTrackService;

    /**
     * 功能描述：新增追踪表
     *
     * @author 林明铁
     *         <p>
     *         创建时间 ：2017-11-28 15:31:27
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
        IisProjectTrack iisProjectTrack = (IisProjectTrack) JsonUtil.StringToObject(param,
                IisProjectTrack.class);
        iisProjectTrack.setKeyWord(iisProjectTrack.getKeyWord().replace("，",","));
        // 业务处理
        Map map = this.iisProjectTrackService.saveIisProjectTrack(iisProjectTrack);
        return JsonUtil.ObjectToString(map);
    }


    /**
     * 功能描述：查询追踪表列表信息
     *
     * @author 林明铁
     *         <p>
     *         创建时间 ：2017-11-28 15:31:27
     *         </p>
     *
     *         <p>
     *         修改历史：(修改人，修改时间，修改原因/内容)
     *         </p>
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public String list(@RequestBody String paramAll) {
        // 获取参数信息
        String param = JsonUtil.getJsonStrByAttrName(paramAll,
                "context");
        // 定义转换对象属性类
        Map<String, Class> calssMap = new HashMap<String, Class>();
        calssMap.put("orders", Order.class);
        // 实体转换
        IisProjectTrackUserEntity psue = (IisProjectTrackUserEntity) JsonUtil
                .StringToObject(param, IisProjectTrackUserEntity.class,calssMap);
        // 处理业务
        Map map = iisProjectTrackService.queryIisProjectTrackList(psue);
        return JsonUtil.ObjectToString(map);
    }
}
