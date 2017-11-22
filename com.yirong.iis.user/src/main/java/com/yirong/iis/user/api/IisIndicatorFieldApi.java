package com.yirong.iis.user.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yirong.commons.util.datatype.JsonUtil;
import com.yirong.commons.util.order.Order;
import com.yirong.iis.user.entity.IisIndicatorField;
import com.yirong.iis.user.service.IisIndicatorFieldService;
import com.yirong.iis.user.userentity.IisIndicatorFieldUserEntity;

 /**
 * 功能描述：指标字段表api接口
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
@RequestMapping("IisIndicatorFieldApi")
@ResponseBody
public class IisIndicatorFieldApi {

	/**
	 * 标准service注入
	 */
	@Autowired
	private IisIndicatorFieldService iisIndicatorFieldService;
	
}
