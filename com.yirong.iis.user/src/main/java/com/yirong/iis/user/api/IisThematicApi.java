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
import com.yirong.iis.user.entity.IisThematic;
import com.yirong.iis.user.service.IisThematicService;
import com.yirong.iis.user.userentity.IisThematicUserEntity;

 /**
 * 功能描述：专题表api接口
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
@RequestMapping("IisThematicApi")
@ResponseBody
public class IisThematicApi {

	/**
	 * 标准service注入
	 */
	@Autowired
	private IisThematicService iisThematicService;
	
	/**
	 * 功能描述：新增专题表
	 * 
	 * @author 林明铁
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
		IisThematic iisThematic = (IisThematic) JsonUtil.StringToObject(param,
				IisThematic.class);
		// 业务处理
		Map map = this.iisThematicService.saveIisThematic(iisThematic);
		return JsonUtil.ObjectToString(map);
	}

	/**
	 * 功能描述：修改专题表
	 * 
	 * @author 林明铁
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
		IisThematic iisThematic = (IisThematic) JsonUtil.StringToObject(param,
				IisThematic.class);
		// 业务处理
		Map map = this.iisThematicService.updateIisThematic(iisThematic);
		return JsonUtil.ObjectToString(map);
	}

	/**
	 * 功能描述：根据ID查询专题表信息
	 * 
	 * @author 林明铁
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
		Map map = iisThematicService.queryIisThematicById(id);
		return JsonUtil.ObjectToString(map);
	}

	/**
	 * 功能描述：删除专题表（批量）
	 * 
	 * @author 林明铁
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
		Map map = iisThematicService.delIisThematic(ids);
		return JsonUtil.ObjectToString(map);
	}

	 /**
	 * 功能描述：查询专题表列表信息
	 * 
	 * @author 林明铁
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
		IisThematicUserEntity psue = (IisThematicUserEntity) JsonUtil
				.StringToObject(param, IisThematicUserEntity.class,calssMap);
		// 处理业务
		Map map = iisThematicService.queryIisThematicList(psue);
		return JsonUtil.ObjectToString(map);
	}

}
