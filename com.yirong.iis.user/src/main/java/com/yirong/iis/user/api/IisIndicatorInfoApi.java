package com.yirong.iis.user.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yirong.iis.user.service.IisIndicatorInfoService;

 /**
 * 功能描述：指标信息表api接口
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-22 09:43:03
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("IisIndicatorInfoApi")
@ResponseBody
public class IisIndicatorInfoApi {

	/**
	 * 标准service注入
	 */
	@Autowired
	private IisIndicatorInfoService iisIndicatorInfoService;

}
