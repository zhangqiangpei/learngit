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
import com.yirong.iis.user.entity.IisLawsRegulationsCy;
import com.yirong.iis.user.service.IisLawsRegulationsCyService;
import com.yirong.iis.user.userentity.IisLawsRegulationsCyUserEntity;
import com.yirong.iis.user.userentity.IisLawsRegulationsCyUserEntity;

 /**
 * 功能描述：法律法规分类api接口
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
@RequestMapping("IisLawsRegulationsCyApi")
@ResponseBody
public class IisLawsRegulationsCyApi {

	/**
	 * 标准service注入
	 */
	@Autowired
	private IisLawsRegulationsCyService iisLawsRegulationsCyService;
	
	/**
	 * 功能描述：新增法律法规分类
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
		IisLawsRegulationsCy iisLawsRegulationsCy = (IisLawsRegulationsCy) JsonUtil.StringToObject(param,
				IisLawsRegulationsCy.class);
		// 业务处理
		Map map = this.iisLawsRegulationsCyService.saveIisLawsRegulationsCy(iisLawsRegulationsCy);
		return JsonUtil.ObjectToString(map);
	}

	/**
	 * 功能描述：修改法律法规分类
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
		IisLawsRegulationsCy iisLawsRegulationsCy = (IisLawsRegulationsCy) JsonUtil.StringToObject(param,
				IisLawsRegulationsCy.class);
		// 业务处理
		Map map = this.iisLawsRegulationsCyService.updateIisLawsRegulationsCy(iisLawsRegulationsCy);
		return JsonUtil.ObjectToString(map);
	}

	/**
	 * 功能描述：根据ID查询法律法规分类信息
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
		Map map = iisLawsRegulationsCyService.queryIisLawsRegulationsCyById(id);
		return JsonUtil.ObjectToString(map);
	}

	/**
	 * 功能描述：删除法律法规分类（批量）
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
		Map map = iisLawsRegulationsCyService.delIisLawsRegulationsCy(ids);
		return JsonUtil.ObjectToString(map);
	}

	 /**
	 * 功能描述：查询法律法规分类列表信息
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
		IisLawsRegulationsCyUserEntity psue = (IisLawsRegulationsCyUserEntity) JsonUtil
				.StringToObject(param, IisLawsRegulationsCyUserEntity.class,calssMap);
		// 处理业务
		Map map = iisLawsRegulationsCyService.queryIisLawsRegulationsCyList(psue);
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
		IisLawsRegulationsCyUserEntity para = this.getUserEntity(context);
		
		Map map = this.iisLawsRegulationsCyService.queryList(para);
 
		return JsonUtil.ObjectToStringClob(map);
	}
	
	@SuppressWarnings("rawtypes")
	private IisLawsRegulationsCyUserEntity getUserEntity(String context){
		// 获取参数信息
		String param =  JsonUtil.getJsonStrByAttrName(context,UserConstants.API_PARAM_NAME);
		
		 // 定义转换对象属性类
	    Map<String, Class> calssMap = new HashMap<String, Class>();
	    calssMap.put("orders", Order.class);
	  
	    IisLawsRegulationsCyUserEntity para = null;
		if(StringUtil.isNotNullOrEmpty(param)){
			para  = (IisLawsRegulationsCyUserEntity) JsonUtil.StringToObject(param, IisLawsRegulationsCyUserEntity.class,calssMap);
		}else{
			para = new IisLawsRegulationsCyUserEntity();
		}
		return para;
	}
}
