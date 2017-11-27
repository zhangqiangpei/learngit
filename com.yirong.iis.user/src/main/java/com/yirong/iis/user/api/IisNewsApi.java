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
import com.yirong.iis.user.service.IisNewsService;
import com.yirong.iis.user.userentity.IisNewsUserEntity;

 
/**
 * 
 * @ClassName: IisNewsApi  
 * @Description: TODO(新闻表api接口) 
 * @author liny
 * @date 2017年11月24日 上午10:35:00 
 * @version V0.1
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("IisNewsApi")
@ResponseBody
public class IisNewsApi {

    /**
     * 标准service注入
     */
    @Autowired
    private IisNewsService iisNewsService;
    
    /**
     * 
     * @Title: list 
     * @Description: TODO(查询新闻表列表信息) 
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
		IisNewsUserEntity psue = (IisNewsUserEntity) JsonUtil.StringToObject(param, IisNewsUserEntity.class,calssMap);
		// 处理业务
		Map map = iisNewsService.queryIisNewsList(psue);
		return JsonUtil.ObjectToString(map);
	}
	
	/**
	 * 
	 * @Title: get 
	 * @Description: TODO(根据ID查询新闻表信息) 
	 * @param paramAll
	 * @return String
	 */
	@RequestMapping(value = "get", method = RequestMethod.POST)
	public String get(@RequestBody String paramAll) {
		// 获取参数信息
		String pathName = "context/id";
		String id = JsonUtil.getJsonStrByAttrName(paramAll, pathName);
		// 处理业务
		Map map = iisNewsService.queryIisNewsById(id);
		return JsonUtil.ObjectToString(map);
	}
	
	/**
	 * 
	 * @Title: companyNews 
	 * @Description: TODO(查询关注企业新闻列表信息) 
	 * @param paramAll
	 * @return String
	 */
	@RequestMapping(value = "companyNews", method = RequestMethod.POST)
	public String companyNews(@RequestBody String paramAll) {
		// 获取参数信息
		String param = JsonUtil.getJsonStrByAttrName(paramAll,
				"context");
		// 定义转换对象属性类
		Map<String, Class> calssMap = new HashMap<String, Class>();
		calssMap.put("orders", Order.class);
		// 实体转换
		IisNewsUserEntity psue = (IisNewsUserEntity) JsonUtil.StringToObject(param, IisNewsUserEntity.class,calssMap);
		// 处理业务
		Map map = iisNewsService.queryIisNewsCompanyList(psue);
		return JsonUtil.ObjectToString(map);
	}
}
