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
import com.yirong.iis.user.entity.IisRegulators;
import com.yirong.iis.user.service.IisRegulatorsService;
import com.yirong.iis.user.userentity.IisRegulatorsUserEntity;
import com.yirong.iis.user.userentity.IisRegulatoryPolicyUserEntity;
import com.yirong.iis.user.userentity.IisRegulatorsUserEntity;

 /**
 * 功能描述：监管机构表api接口
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-27 11:23:46
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("IisRegulatorsApi")
@ResponseBody
public class IisRegulatorsApi {

	/**
	 * 标准service注入
	 */
	@Autowired
	private IisRegulatorsService iisRegulatorsService;
	
	/**
	 * 功能描述：新增监管机构表
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-27 11:23:46
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
		IisRegulators iisRegulators = (IisRegulators) JsonUtil.StringToObject(param,
				IisRegulators.class);
		// 业务处理
		Map map = this.iisRegulatorsService.saveIisRegulators(iisRegulators);
		return JsonUtil.ObjectToString(map);
	}

	/**
	 * 功能描述：修改监管机构表
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-27 11:23:46
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
		IisRegulators iisRegulators = (IisRegulators) JsonUtil.StringToObject(param,
				IisRegulators.class);
		// 业务处理
		Map map = this.iisRegulatorsService.updateIisRegulators(iisRegulators);
		return JsonUtil.ObjectToString(map);
	}

	/**
	 * 功能描述：根据ID查询监管机构表信息
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-27 11:23:46
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
		Map map = iisRegulatorsService.queryIisRegulatorsById(id);
		return JsonUtil.ObjectToString(map);
	}

	/**
	 * 功能描述：删除监管机构表（批量）
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-27 11:23:46
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
		Map map = iisRegulatorsService.delIisRegulators(ids);
		return JsonUtil.ObjectToString(map);
	}

	 /**
	 * 功能描述：查询监管机构表列表信息
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-27 11:23:46
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
		IisRegulatorsUserEntity psue = (IisRegulatorsUserEntity) JsonUtil
				.StringToObject(param, IisRegulatorsUserEntity.class,calssMap);
		// 处理业务
		Map map = iisRegulatorsService.queryIisRegulatorsList(psue);
		return JsonUtil.ObjectToString(map);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/queryList", method = RequestMethod.POST)
	public String queryList(@RequestBody String context) {
		// 获取参数信息
		IisRegulatorsUserEntity para = this.getUserEntity(context);
		
		Map map = this.iisRegulatorsService.queryList(para);
 
		return JsonUtil.ObjectToStringClob(map);
	}
	
	@SuppressWarnings("rawtypes")
	private IisRegulatorsUserEntity getUserEntity(String context){
		// 获取参数信息
		String param =  JsonUtil.getJsonStrByAttrName(context,UserConstants.API_PARAM_NAME);
		
		 // 定义转换对象属性类
	    Map<String, Class> calssMap = new HashMap<String, Class>();
	    calssMap.put("orders", Order.class);
	  
	    IisRegulatorsUserEntity para = null;
		if(StringUtil.isNotNullOrEmpty(param)){
			para  = (IisRegulatorsUserEntity) JsonUtil.StringToObject(param, IisRegulatorsUserEntity.class,calssMap);
		}else{
			para = new IisRegulatorsUserEntity();
		}
		return para;
	}

}
