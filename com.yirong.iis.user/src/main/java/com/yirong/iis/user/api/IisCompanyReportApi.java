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
import com.yirong.iis.user.service.IisCompanyReportService;
import com.yirong.iis.user.userentity.VeIisCompanyReportUserEntity;

 
/**
 * 
 * @ClassName: IisCompanyReportApi  
 * @Description: TODO(企业财务报告信息api接口) 
 * @author liny
 * @date 2017年11月28日 下午3:48:48 
 * @version V0.1
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("IisCompanyReportApi")
@ResponseBody
public class IisCompanyReportApi {

	/**
	 * 标准service注入
	 */
	@Autowired
	private IisCompanyReportService iisCompanyReortService;
 

	 
	 
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
		VeIisCompanyReportUserEntity ue = (VeIisCompanyReportUserEntity) JsonUtil
				.StringToObject(param, VeIisCompanyReportUserEntity.class,calssMap);
		// 处理业务
		Map map = iisCompanyReortService.queryIisCompanyReportList(ue);
		return JsonUtil.ObjectToString(map);
	}
 
}
