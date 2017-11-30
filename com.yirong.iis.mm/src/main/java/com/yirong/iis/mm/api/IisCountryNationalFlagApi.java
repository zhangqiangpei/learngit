package com.yirong.iis.mm.api;

import java.util.HashMap;
import java.util.Map;

import com.yirong.iis.mm.entity.IisCountryNationalFlag;
import com.yirong.iis.mm.service.IisCountryNationalFlagService;
import com.yirong.iis.mm.userentity.IisCountryNationalFlagUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yirong.commons.util.order.Order;
import com.yirong.commons.util.datatype.JsonUtil;

 /**
 * 功能描述：国家国旗表api接口
 * 
 * @author 陈清沣
 *         <p>
 *         创建时间 ：2017-11-29 10:40:39
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("IisCountryNationalFlagApi")
@ResponseBody
public class IisCountryNationalFlagApi {

	/**
	 * 标准service注入
	 */
	@Autowired
	private IisCountryNationalFlagService iisCountryNationalFlagService;
	
	/**
	 * 功能描述：新增国家国旗表
	 * 
	 * @author 陈清沣
	 *         <p>
	 *         创建时间 ：2017-11-29 10:40:39
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
		String pathName = "context/engName";
		String engName = JsonUtil.getJsonStrByAttrName(paramAll, pathName);
		// 业务处理
		Map map = this.iisCountryNationalFlagService.saveIisCountryNationalFlag(engName,false);
		return JsonUtil.ObjectToString(map);
	}

	/**
	 * 功能描述：修改国家国旗表
	 * 
	 * @author 陈清沣
	 *         <p>
	 *         创建时间 ：2017-11-29 10:40:39
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
		String pathName = "context/engName";
		String engName = JsonUtil.getJsonStrByAttrName(paramAll, pathName);
		// 业务处理
		Map map = this.iisCountryNationalFlagService.updateIisCountryNationalFlag(engName);
		return JsonUtil.ObjectToString(map);
	}

	/**
	 * 功能描述：根据ID查询国家国旗表信息
	 * 
	 * @author 陈清沣
	 *         <p>
	 *         创建时间 ：2017-11-29 10:40:39
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
		String pathName = "context/engName";
		String engName = JsonUtil.getJsonStrByAttrName(paramAll, pathName);
		// 处理业务
		Map map = iisCountryNationalFlagService.queryIisCountryNationalFlagByEngName(engName);
		return JsonUtil.ObjectToStringClob(map);
	}

	/**
	 * 功能描述：删除国家国旗表
	 * 
	 * @author 陈清沣
	 *         <p>
	 *         创建时间 ：2017-11-29 10:40:39
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
		Map map = iisCountryNationalFlagService.delIisCountryNationalFlag(ids);
		return JsonUtil.ObjectToString(map);
	}


}
