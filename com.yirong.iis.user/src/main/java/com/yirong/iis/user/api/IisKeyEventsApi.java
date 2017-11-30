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
import com.yirong.iis.user.entity.IisKeyEvents;
import com.yirong.iis.user.service.IisKeyEventsService;
import com.yirong.iis.user.userentity.IisKeyEventsUserEntity;

 /**
 * 功能描述：重大事件表api接口
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-28 10:58:00
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("IisKeyEventsApi")
@ResponseBody
public class IisKeyEventsApi {

	/**
	 * 标准service注入
	 */
	@Autowired
	private IisKeyEventsService iisKeyEventsService;
	
	/**
	 * 功能描述：新增重大事件表
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-28 10:58:00
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param paramAll
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(@RequestBody String paramAll) {
		// 获取参数信息
		String param = JsonUtil.getJsonStrByAttrName(paramAll,
				"context");
		// 实体转换
		IisKeyEvents iisKeyEvents = (IisKeyEvents) JsonUtil.StringToObject(param,
				IisKeyEvents.class);
		// 业务处理
		Map map = this.iisKeyEventsService.saveIisKeyEvents(iisKeyEvents);
		return JsonUtil.ObjectToString(map);
	}

	/**
	 * 功能描述：修改重大事件表
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-28 10:58:00
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param paramAll
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@RequestBody String paramAll) {
		// 获取参数信息
		String param = JsonUtil.getJsonStrByAttrName(paramAll,
				"context");
		// 实体转换
		IisKeyEvents iisKeyEvents = (IisKeyEvents) JsonUtil.StringToObject(param,
				IisKeyEvents.class);
		// 业务处理
		Map map = this.iisKeyEventsService.updateIisKeyEvents(iisKeyEvents);
		return JsonUtil.ObjectToString(map);
	}

	/**
	 * 功能描述：根据ID查询重大事件表信息
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-28 10:58:00
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param paramAll
	 * @return
	 */
	@RequestMapping(value = "get", method = RequestMethod.POST)
	public String get(@RequestBody String paramAll) {
		// 获取参数信息
		String pathName = "context/id";
		String id = JsonUtil.getJsonStrByAttrName(paramAll, pathName);
		// 处理业务
		Map map = iisKeyEventsService.queryIisKeyEventsById(id);
		return JsonUtil.ObjectToStringClob(map);
	}

	/**
	 * 功能描述：删除重大事件表（批量）
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-28 10:58:00
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param idStrs
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String delete(@RequestBody String paramAll) {
		// 获取参数信息
		String pathName = "context/ids";
		String ids = JsonUtil.getJsonStrByAttrName(paramAll, pathName);
		// 处理业务
		Map map = iisKeyEventsService.delIisKeyEvents(ids);
		return JsonUtil.ObjectToString(map);
	}

	 /**
	 * 功能描述：查询重大事件表列表信息
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-28 10:58:00
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param ue
	 * @return
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
		IisKeyEventsUserEntity psue = (IisKeyEventsUserEntity) JsonUtil
				.StringToObject(param, IisKeyEventsUserEntity.class,calssMap);
		// 处理业务
		Map map = iisKeyEventsService.queryIisKeyEventsList(psue);
		return JsonUtil.ObjectToStringClob(map);
	}

}
