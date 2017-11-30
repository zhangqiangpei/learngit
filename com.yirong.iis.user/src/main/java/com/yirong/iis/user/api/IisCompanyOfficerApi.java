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
import com.yirong.iis.user.service.IisCompanyOfficerService;
import com.yirong.iis.user.userentity.VeIisCompanyOfficerUserEntity;

 
/**
 * 
 * @ClassName: IisCompanyOfficerApi  
 * @Description: TODO(企业高管信息api接口) 
 * @author liny
 * @date 2017年11月28日 下午3:48:48 
 * @version V0.1
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("IisCompanyOfficerApi")
@ResponseBody
public class IisCompanyOfficerApi {

	/**
	 * 标准service注入
	 */
	@Autowired
	private IisCompanyOfficerService iisCompanyOfficerService;
 

	 
	 
	 /**
	  * 
	  * @Title: list 
	  * @Description: TODO(查询企业高管列表信息) 
	  * @param paramAll
	  * @return String
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
		VeIisCompanyOfficerUserEntity ue = (VeIisCompanyOfficerUserEntity) JsonUtil
				.StringToObject(param, VeIisCompanyOfficerUserEntity.class,calssMap);
		// 处理业务
		Map map = iisCompanyOfficerService.queryIisCompanyOfficerList(ue);
		return JsonUtil.ObjectToString(map);
	}
 
}
