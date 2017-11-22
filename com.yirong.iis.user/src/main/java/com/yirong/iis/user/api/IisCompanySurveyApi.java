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
import com.yirong.iis.user.entity.IisCompanySurvey;
import com.yirong.iis.user.service.IisCompanySurveyService;
import com.yirong.iis.user.userentity.IisCompanySurveyUserEntity;

 
/**
 * 
 * @ClassName: IisCompanySurveyApi  
 * @Description: TODO(企业概况表api接口) 
 * @author liny
 * @date 2017年11月22日 下午3:48:48 
 * @version V0.1
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("IisCompanySurveyApi")
@ResponseBody
public class IisCompanySurveyApi {

	/**
	 * 标准service注入
	 */
	@Autowired
	private IisCompanySurveyService iisCompanySurveyService;
	
 
	/**
	 * 
	 * @Title: save 
	 * @Description: TODO(新增企业概况表) 
	 * @param paramAll
	 * @return String
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(@RequestBody String paramAll) {
		// 获取参数信息
		String param = JsonUtil.getJsonStrByAttrName(paramAll,"context");
		// 实体转换
		IisCompanySurvey iisCompanySurvey = (IisCompanySurvey) JsonUtil.StringToObject(param,
				IisCompanySurvey.class);
		// 业务处理
		Map map = this.iisCompanySurveyService.saveIisCompanySurvey(iisCompanySurvey);
		return JsonUtil.ObjectToString(map);
	}

	/**
	 * 
	 * @Title: update 
	 * @Description: TODO(修改企业概况表) 
	 * @param paramAll
	 * @return String
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@RequestBody String paramAll) {
		// 获取参数信息
		String param = JsonUtil.getJsonStrByAttrName(paramAll,
				"context");
		// 实体转换
		IisCompanySurvey iisCompanySurvey = (IisCompanySurvey) JsonUtil.StringToObject(param,
				IisCompanySurvey.class);
		// 业务处理
		Map map = this.iisCompanySurveyService.updateIisCompanySurvey(iisCompanySurvey);
		return JsonUtil.ObjectToString(map);
	}

	/**
	 * 
	 * @Title: get 
	 * @Description: TODO(根据ID查询企业概况表信息) 
	 * @param paramAll
	 * @return String
	 */
	@RequestMapping(value = "get", method = RequestMethod.POST)
	public String get(@RequestBody String paramAll) {
		// 获取参数信息
		String pathName = "context/id";
		String id = JsonUtil.getJsonStrByAttrName(paramAll, pathName);
		// 处理业务
		Map map = iisCompanySurveyService.queryIisCompanySurveyById(id);
		return JsonUtil.ObjectToString(map);
	}

	/**
	 * 
	 * @Title: delete 
	 * @Description: TODO(删除企业概况表（批量）) 
	 * @param paramAll
	 * @return String
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String delete(@RequestBody String paramAll) {
		// 获取参数信息
		String pathName = "context/ids";
		String ids = JsonUtil.getJsonStrByAttrName(paramAll, pathName);
		// 处理业务
		Map map = iisCompanySurveyService.delIisCompanySurvey(ids);
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
		IisCompanySurveyUserEntity psue = (IisCompanySurveyUserEntity) JsonUtil
				.StringToObject(param, IisCompanySurveyUserEntity.class,calssMap);
		// 处理业务
		Map map = iisCompanySurveyService.queryIisCompanySurveyList(psue);
		return JsonUtil.ObjectToString(map);
	}

}
