package com.yirong.iis.user.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yirong.commons.util.datatype.JsonUtil;
import com.yirong.commons.util.order.Order;
import com.yirong.iis.user.entity.IisThematicItem;
import com.yirong.iis.user.service.IisThematicItemService;
import com.yirong.iis.user.userentity.IisThematicItemUserEntity;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

 /**
 * 功能描述：专题模块表api接口
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-24 15:49:14
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("IisThematicItemApi")
@ResponseBody
public class IisThematicItemApi {

	/**
	 * 标准service注入
	 */
	@Autowired
	private IisThematicItemService iisThematicItemService;
	
	/**
	 * 功能描述：新增专题模块表
	 * 
	 * @author howie lee
	 *         <p>
	 *         创建时间 ：2017-11-24 15:49:14
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
		IisThematicItem iisThematicItem = (IisThematicItem) JsonUtil.StringToObject(param,
				IisThematicItem.class);
		// 业务处理
		Map map = this.iisThematicItemService.saveIisThematicItem(iisThematicItem);
		return JsonUtil.ObjectToString(map);
	}

	/**
	 * 功能描述：批量新增专题模块表
	 * 
	 * @author howie lee
	 *         <p>
	 *         创建时间 ：2017-11-24 15:49:14
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param paramAll
	 * @return
	 */
	@RequestMapping(value = "saveBatch", method = RequestMethod.POST)
	public String saveBatch(@RequestBody String paramAll) {
		// 获取参数信息
		String param = JsonUtil.getJsonStrByAttrName(paramAll,
				"context");
		String items = JsonUtil.getJsonStrByAttrName(param,"items");
		// 实体转换
		JSONArray arr = JSONArray.fromObject(items);
		List<IisThematicItem> iisThematicItems = new ArrayList<IisThematicItem>();
		for(int i=0;i<arr.size();i++){
			IisThematicItem iisThematicItem = new IisThematicItem();
			iisThematicItem.setThematicItemData(JsonUtil.getJsonStrByAttrName(arr.getString(i),"thematicItemData"));
			iisThematicItem.setThematicItemId(JsonUtil.getJsonStrByAttrName(arr.getString(i),"thematicItemId"));
			iisThematicItem.setThematicId(JsonUtil.getJsonStrByAttrName(arr.getString(i),"thematicId"));
			iisThematicItem.setType(JsonUtil.getJsonStrByAttrName(arr.getString(i),"type"));
			iisThematicItems.add(iisThematicItem);
		}
		
		// 业务处理
		Map map = this.iisThematicItemService.saveBatchIisThematicItem(iisThematicItems);
		return JsonUtil.ObjectToString(map);
	}
	
	/**
	 * 功能描述：修改专题模块表
	 * 
	 * @author howie lee
	 *         <p>
	 *         创建时间 ：2017-11-24 15:49:14
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
		IisThematicItem iisThematicItem = (IisThematicItem) JsonUtil.StringToObject(param,
				IisThematicItem.class);
		// 业务处理
		Map map = this.iisThematicItemService.updateIisThematicItem(iisThematicItem);
		return JsonUtil.ObjectToString(map);
	}

	/**
	 * 功能描述：根据ID查询专题模块表信息
	 * 
	 * @author howie lee
	 *         <p>
	 *         创建时间 ：2017-11-24 15:49:14
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
		Map map = iisThematicItemService.queryIisThematicItemById(id);
		return JsonUtil.ObjectToString(map);
	}

	/**
	 * 功能描述：删除专题模块表（批量）
	 * 
	 * @author howie lee
	 *         <p>
	 *         创建时间 ：2017-11-24 15:49:14
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
		Map map = iisThematicItemService.delIisThematicItem(ids);
		return JsonUtil.ObjectToString(map);
	}

	 /**
	 * 功能描述：查询专题模块表列表信息
	 * 
	 * @author howie lee
	 *         <p>
	 *         创建时间 ：2017-11-24 15:49:14
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
		IisThematicItemUserEntity psue = (IisThematicItemUserEntity) JsonUtil
				.StringToObject(param, IisThematicItemUserEntity.class,calssMap);
		// 处理业务
		Map map = iisThematicItemService.queryIisThematicItemList(psue);
		return JsonUtil.ObjectToString(map);
	}

}
