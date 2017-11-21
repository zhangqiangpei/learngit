package com.yirong.iis.user.api;

import java.util.HashMap;
import java.util.Map;

import com.yirong.iis.user.service.IisCountryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yirong.commons.util.order.Order;
import com.yirong.commons.util.datatype.JsonUtil;

/**
 * 功能描述：国家信息表api接口
 *
 * @author 林明铁
 *         <p>
 *         创建时间 ：2017-11-21 15:44:24
 *         </p>
 *
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("IisCountryInfoApi")
@ResponseBody
public class IisCountryInfoApi {

    /**
     * 标准service注入
     */
    @Autowired
    private IisCountryInfoService iisCountryInfoService;
}
