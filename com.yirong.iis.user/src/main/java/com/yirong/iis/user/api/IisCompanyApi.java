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
import com.yirong.iis.user.service.IisCompanyService;
import com.yirong.iis.user.userentity.VeIisCompanyUserEntity;

 
/**
 * 
 * @ClassName: IisCompanyApi  
 * @Description: TODO(企业信息表api接口) 
 * @author liny
 * @date 2017年11月28日 下午3:48:48 
 * @version V0.1
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("IisCompanyApi")
@ResponseBody
public class IisCompanyApi {

	/**
	 * 标准service注入
	 */
	@Autowired
	private IisCompanyService iisCompanyService;
 

	/**
	 * 
	 * @Title: get 
	 * @Description: TODO(根据ID查询企业信息) 
	 * @param paramAll
	 * @return String
	 */
	@RequestMapping(value = "get", method = RequestMethod.POST)
	public String get(@RequestBody String paramAll) {
		// 获取参数信息
		String pathName = "context/id";
		String id = JsonUtil.getJsonStrByAttrName(paramAll, pathName);
		// 处理业务
		Map map = iisCompanyService.queryIisCompanyById(id);
		return JsonUtil.ObjectToString(map);
	}

	 
	 /**
	  * 
	  * @Title: list 
	  * @Description: TODO(查询企业概况表列表信息) 
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
		VeIisCompanyUserEntity ue = (VeIisCompanyUserEntity) JsonUtil
				.StringToObject(param, VeIisCompanyUserEntity.class,calssMap);
		// 处理业务
		Map map = iisCompanyService.queryIisCompanyList(ue);
		return JsonUtil.ObjectToString(map);
	}
	
	/**
	 * 
	 * @Title: get 
	 * @Description: TODO(根据companyId查询企业信息) 
	 * @param paramAll
	 * @return String
	 */
	@RequestMapping(value = "getByCompanyId", method = RequestMethod.POST)
	public String getByCompanyId(@RequestBody String paramAll) {
		// 获取参数信息
		String pathName = "context/companyId";
		String companyId = JsonUtil.getJsonStrByAttrName(paramAll, pathName);
		// 处理业务
		Map map = iisCompanyService.queryIisCompanyByCompanyId(companyId);
		return JsonUtil.ObjectToString(map);
	}

}
