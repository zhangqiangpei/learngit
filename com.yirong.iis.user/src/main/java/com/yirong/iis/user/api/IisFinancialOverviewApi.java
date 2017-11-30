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
import com.yirong.iis.user.service.IisFinancialOverviewService;
import com.yirong.iis.user.userentity.IisFinancialOverviewUserEntity;

 /**
 * 功能描述：企业财务总览api接口
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-22 17:21:53
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("IisFinancialOverviewApi")
@ResponseBody
public class IisFinancialOverviewApi {

	/**
	 * 标准service注入
	 */
	@Autowired
	private IisFinancialOverviewService iisFinancialOverviewService;
  
	/**
	 * 
	 * @Title: list 
	 * @Description: TODO(查询企业财务总览列表信息) 
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
		IisFinancialOverviewUserEntity psue = (IisFinancialOverviewUserEntity) JsonUtil
				.StringToObject(param, IisFinancialOverviewUserEntity.class,calssMap);
		// 处理业务
		Map map = iisFinancialOverviewService.queryIisFinancialOverviewList(psue);
		return JsonUtil.ObjectToString(map);
	}

}
