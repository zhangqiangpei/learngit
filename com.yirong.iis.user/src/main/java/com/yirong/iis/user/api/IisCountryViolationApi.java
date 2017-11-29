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
import com.yirong.iis.user.entity.IisCountryViolation;
import com.yirong.iis.user.service.IisCountryViolationService;
import com.yirong.iis.user.userentity.IisCountryViolationUserEntity;

 /**
 * 功能描述：国家违约概率表api接口
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-29 09:44:05
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("IisCountryViolationApi")
@ResponseBody
public class IisCountryViolationApi {

	/**
	 * 标准service注入
	 */
	@Autowired
	private IisCountryViolationService iisCountryViolationService;
	
	/**
	 * 功能描述：新增国家违约概率表
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-29 09:44:05
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
		IisCountryViolation iisCountryViolation = (IisCountryViolation) JsonUtil.StringToObject(param,
				IisCountryViolation.class);
		// 业务处理
		Map map = this.iisCountryViolationService.saveIisCountryViolation(iisCountryViolation);
		return JsonUtil.ObjectToString(map);
	}

	/**
	 * 功能描述：修改国家违约概率表
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-29 09:44:05
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
		IisCountryViolation iisCountryViolation = (IisCountryViolation) JsonUtil.StringToObject(param,
				IisCountryViolation.class);
		// 业务处理
		Map map = this.iisCountryViolationService.updateIisCountryViolation(iisCountryViolation);
		return JsonUtil.ObjectToString(map);
	}

	/**
	 * 功能描述：根据ID查询国家违约概率表信息
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-29 09:44:05
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
		Map map = iisCountryViolationService.queryIisCountryViolationById(id);
		return JsonUtil.ObjectToString(map);
	}

	/**
	 * 功能描述：删除国家违约概率表（批量）
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-29 09:44:05
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
		Map map = iisCountryViolationService.delIisCountryViolation(ids);
		return JsonUtil.ObjectToString(map);
	}

	 /**
	 * 功能描述：查询国家违约概率表列表信息
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-29 09:44:05
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
		IisCountryViolationUserEntity psue = (IisCountryViolationUserEntity) JsonUtil
				.StringToObject(param, IisCountryViolationUserEntity.class,calssMap);
		// 处理业务
		Map map = iisCountryViolationService.queryIisCountryViolationList(psue);
		return JsonUtil.ObjectToString(map);
	}
	 /**
		 * 功能描述：不分页查询国家的所有信息
		 * 
		 * @author 刘慧卿
		 *         <p>
		 *         创建时间 ：2017-11-29 09:44:05
		 *         </p>
		 * 
		 *         <p>
		 *         修改历史：(修改人，修改时间，修改原因/内容)
		 *         </p>
		 * @param ue
		 * @return
		 */
		@RequestMapping(value = "querylist", method = RequestMethod.POST)
		public String querylist(@RequestBody String paramAll) {
			// 获取参数信息
			String param = JsonUtil.getJsonStrByAttrName(paramAll,
					"context");
			// 定义转换对象属性类
			Map<String, Class> calssMap = new HashMap<String, Class>();
			calssMap.put("orders", Order.class);
			// 实体转换
			IisCountryViolationUserEntity psue = (IisCountryViolationUserEntity) JsonUtil
					.StringToObject(param, IisCountryViolationUserEntity.class,calssMap);
			// 处理业务
			Map map = iisCountryViolationService.querylist(psue);
			return JsonUtil.ObjectToString(map);
		}
}
