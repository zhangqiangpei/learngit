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
import com.yirong.iis.user.service.IisCompanyInformationService;
import com.yirong.iis.user.userentity.IisCompanyInformationUserEntity;

 

/**
 * 
 * @ClassName: IisCompanyInformationApi  
 * @Description: TODO(企业资讯表api接口) 
 * @author liny
 * @date 2017年11月22日 下午3:48:59 
 * @version V0.1
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("IisCompanyInformationApi")
@ResponseBody
public class IisCompanyInformationApi {

	/**
	 * 标准service注入
	 */
	@Autowired
	private IisCompanyInformationService iisCompanyInformationService;
	 
	/**
	 * 
	 * @Title: list 
	 * @Description: TODO(查询企业资讯表列表信息) 
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
		IisCompanyInformationUserEntity psue = (IisCompanyInformationUserEntity) JsonUtil
				.StringToObject(param, IisCompanyInformationUserEntity.class,calssMap);
		// 处理业务
		Map map = iisCompanyInformationService.queryIisCompanyInformationList(psue);
		return JsonUtil.ObjectToString(map);
	}

}
