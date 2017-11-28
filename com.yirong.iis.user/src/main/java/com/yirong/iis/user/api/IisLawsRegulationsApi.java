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
import com.yirong.iis.user.entity.IisLawsRegulations;
import com.yirong.iis.user.service.IisLawsRegulationsService;
import com.yirong.iis.user.userentity.IisLawsRegulationsUserEntity;

 /**
 * 功能描述：法律法规表api接口
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-27 16:13:26
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("IisLawsRegulationsApi")
@ResponseBody
public class IisLawsRegulationsApi {

	/**
	 * 标准service注入
	 */
	@Autowired
	private IisLawsRegulationsService iisLawsRegulationsService;
	
	/**
	 * 功能描述：新增法律法规表
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-27 16:13:26
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
		IisLawsRegulations iisLawsRegulations = (IisLawsRegulations) JsonUtil.StringToObject(param,
				IisLawsRegulations.class);
		// 业务处理
		Map map = this.iisLawsRegulationsService.saveIisLawsRegulations(iisLawsRegulations);
		return JsonUtil.ObjectToString(map);
	}

	/**
	 * 功能描述：修改法律法规表
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-27 16:13:26
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
		IisLawsRegulations iisLawsRegulations = (IisLawsRegulations) JsonUtil.StringToObject(param,
				IisLawsRegulations.class);
		// 业务处理
		Map map = this.iisLawsRegulationsService.updateIisLawsRegulations(iisLawsRegulations);
		return JsonUtil.ObjectToString(map);
	}

	/**
	 * 功能描述：根据ID查询法律法规表信息
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-27 16:13:26
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
		Map map = iisLawsRegulationsService.queryIisLawsRegulationsById(id);
		return JsonUtil.ObjectToStringClob(map);
	}

	/**
	 * 功能描述：删除法律法规表（批量）
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-27 16:13:26
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
		Map map = iisLawsRegulationsService.delIisLawsRegulations(ids);
		return JsonUtil.ObjectToString(map);
	}

	 /**
	 * 功能描述：查询法律法规表列表信息
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-27 16:13:26
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
		IisLawsRegulationsUserEntity psue = (IisLawsRegulationsUserEntity) JsonUtil
				.StringToObject(param, IisLawsRegulationsUserEntity.class,calssMap);
		// 处理业务
		Map map = iisLawsRegulationsService.queryIisLawsRegulationsList(psue);
		return JsonUtil.ObjectToString(map);
	}

}
