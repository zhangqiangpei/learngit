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
import com.yirong.commons.util.datatype.StringUtil;
import com.yirong.commons.util.order.Order;
import com.yirong.iis.user.constant.UserConstants;
import com.yirong.iis.user.entity.IisRegulatoryPolicy;
import com.yirong.iis.user.service.IisRegulatoryPolicyService;
import com.yirong.iis.user.userentity.IisRegulatoryPolicyUserEntity;

 /**
 * 功能描述：监管政策表api接口
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-27 09:56:48
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("IisRegulatoryPolicyApi")
@ResponseBody
public class IisRegulatoryPolicyApi {

	/**
	 * 标准service注入
	 */
	@Autowired
	private IisRegulatoryPolicyService iisRegulatoryPolicyService;
	
	/**
	 * 功能描述：新增监管政策表
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-27 09:56:48
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
		IisRegulatoryPolicy iisRegulatoryPolicy = (IisRegulatoryPolicy) JsonUtil.StringToObject(param,
				IisRegulatoryPolicy.class);
		// 业务处理
		Map map = this.iisRegulatoryPolicyService.saveIisRegulatoryPolicy(iisRegulatoryPolicy);
		return JsonUtil.ObjectToString(map);
	}

	/**
	 * 功能描述：修改监管政策表
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-27 09:56:48
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
		IisRegulatoryPolicy iisRegulatoryPolicy = (IisRegulatoryPolicy) JsonUtil.StringToObject(param,
				IisRegulatoryPolicy.class);
		// 业务处理
		Map map = this.iisRegulatoryPolicyService.updateIisRegulatoryPolicy(iisRegulatoryPolicy);
		return JsonUtil.ObjectToString(map);
	}

	/**
	 * 功能描述：根据ID查询监管政策表信息
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-27 09:56:48
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
		Map map = iisRegulatoryPolicyService.queryIisRegulatoryPolicyById(id);
		return JsonUtil.ObjectToString(map);
	}

	/**
	 * 功能描述：删除监管政策表（批量）
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-27 09:56:48
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
		Map map = iisRegulatoryPolicyService.delIisRegulatoryPolicy(ids);
		return JsonUtil.ObjectToString(map);
	}

	 /**
	 * 功能描述：查询监管政策表列表信息
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-27 09:56:48
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
		IisRegulatoryPolicyUserEntity psue = (IisRegulatoryPolicyUserEntity) JsonUtil
				.StringToObject(param, IisRegulatoryPolicyUserEntity.class,calssMap);
		// 处理业务
		Map map = iisRegulatoryPolicyService.queryIisRegulatoryPolicyList(psue);
		return JsonUtil.ObjectToString(map);
	}
	
	/**
	 * 不分页查询信息
	 * @param context
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/queryList", method = RequestMethod.POST)
	public String queryList(@RequestBody String context) {
		// 获取参数信息
		IisRegulatoryPolicyUserEntity para = this.getUserEntity(context);
		
		Map map = this.iisRegulatoryPolicyService.queryList(para);
 
		return JsonUtil.ObjectToString(map);
	}
	
	@SuppressWarnings("rawtypes")
	private IisRegulatoryPolicyUserEntity getUserEntity(String context){
		// 获取参数信息
		String param =  JsonUtil.getJsonStrByAttrName(context,UserConstants.API_PARAM_NAME);
		
		 // 定义转换对象属性类
	    Map<String, Class> calssMap = new HashMap<String, Class>();
	    calssMap.put("orders", Order.class);
	  
	    IisRegulatoryPolicyUserEntity para = null;
		if(StringUtil.isNotNullOrEmpty(param)){
			para  = (IisRegulatoryPolicyUserEntity) JsonUtil.StringToObject(param, IisRegulatoryPolicyUserEntity.class,calssMap);
		}else{
			para = new IisRegulatoryPolicyUserEntity();
		}
		return para;
	}

}
