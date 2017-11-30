package com.yirong.iis.user.api;


import java.util.HashMap;
import java.util.Map;

import com.yirong.iis.user.entity.IisProjectTrackDetail;
import com.yirong.iis.user.service.IisProjectTrackDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yirong.commons.util.order.Order;
import com.yirong.commons.util.datatype.JsonUtil;

/**
 * 功能描述：跟踪明细表api接口
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
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("IisProjectTrackDetailApi")
@ResponseBody
public class IisProjectTrackDetailApi {

    /**
     * 标准service注入
     */
    @Autowired
    private IisProjectTrackDetailService iisProjectTrackDetailService;

    /**
     * 功能描述：新增跟踪明细表
     *
     * @author 林明铁
     *         <p>
     *         创建时间 ：2017-11-28 15:32:14
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
        IisProjectTrackDetail iisProjectTrackDetail = (IisProjectTrackDetail) JsonUtil.StringToObject(param,
                IisProjectTrackDetail.class);
        // 业务处理
        Map map = this.iisProjectTrackDetailService.saveIisProjectTrackDetail(iisProjectTrackDetail);
        return JsonUtil.ObjectToString(map);
    }

}
