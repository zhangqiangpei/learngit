package com.yirong.iis.mm.api;

import java.util.HashMap;
import java.util.Map;

import com.yirong.iis.mm.entity.IisCountryInfo;
import com.yirong.iis.mm.service.IisCountryInfoService;
import com.yirong.iis.mm.userentity.IisCountryInfoUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yirong.commons.util.order.Order;
import com.yirong.commons.util.datatype.JsonUtil;

 /**
 * 功能描述：国家信息表api接口
 *
 * @author 陈清沣
 *         <p>
 *         创建时间 ：2017-11-24 18:16:59
 *         </p>
 *
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("IisCountryInfoApi")
@ResponseBody
public class IisCountryInfoApi {

	/**
	 * 标准service注入
	 */
	@Autowired
	private IisCountryInfoService iisCountryInfoService;

	/**
	 * 功能描述：新增国家信息表
	 *
	 * @author 陈清沣
	 *         <p>
	 *         创建时间 ：2017-11-24 18:16:59
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
		IisCountryInfo iisCountryInfo = (IisCountryInfo) JsonUtil.StringToObject(param,
				IisCountryInfo.class);
		// 业务处理
		Map map = this.iisCountryInfoService.saveIisCountryInfo(iisCountryInfo);
		return JsonUtil.ObjectToString(map);
	}

	/**
	 * 功能描述：修改国家信息表
	 *
	 * @author 陈清沣
	 *         <p>
	 *         创建时间 ：2017-11-24 18:16:59
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
		String param = JsonUtil.getJsonStrByAttrName(paramAll, "context");
		// 实体转换
		IisCountryInfo iisCountryInfo = (IisCountryInfo) JsonUtil.StringToObject(param, IisCountryInfo.class);
		// 业务处理
		Map map = this.iisCountryInfoService.updateIisCountryInfo(iisCountryInfo);
		return JsonUtil.ObjectToString(map);
	}

	/**
	 * 功能描述：根据ID查询国家信息表信息
	 *
	 * @author 陈清沣
	 *         <p>
	 *         创建时间 ：2017-11-24 18:16:59
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
		Map map = iisCountryInfoService.queryIisCountryInfoById(id);
		return JsonUtil.ObjectToString(map);
	}

	/**
	 * 功能描述：删除国家信息表（批量）
	 *
	 * @author 陈清沣
	 *         <p>
	 *         创建时间 ：2017-11-24 18:16:59
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String delete(@RequestBody String paramAll) {
		// 获取参数信息
		String pathName = "context/ids";
		String ids = JsonUtil.getJsonStrByAttrName(paramAll, pathName);
		// 处理业务
		Map map = iisCountryInfoService.delIisCountryInfo(ids);
		return JsonUtil.ObjectToString(map);
	}

	 /**
	 * 功能描述：查询国家信息表列表信息
	 *
	 * @author 陈清沣
	 *         <p>
	 *         创建时间 ：2017-11-24 18:16:59
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
		IisCountryInfoUserEntity psue = (IisCountryInfoUserEntity) JsonUtil
				.StringToObject(param, IisCountryInfoUserEntity.class,calssMap);
		// 处理业务
		Map map = iisCountryInfoService.queryIisCountryInfoList(psue);
		return JsonUtil.ObjectToString(map);
	}

}
