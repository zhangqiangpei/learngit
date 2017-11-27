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
import com.yirong.iis.user.entity.IisPoliticalEnvironment;
import com.yirong.iis.user.service.IisPoliticalEnvironmentService;
import com.yirong.iis.user.userentity.IisPoliticalEnvironmentUserEntity;

 /**
 * 功能描述：国家概括表api接口
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-22 14:07:44
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("IisPoliticalEnvironmentApi")
@ResponseBody
public class IisPoliticalEnvironmentApi {

	/**
	 * 标准service注入
	 */
	@Autowired
	private IisPoliticalEnvironmentService iisPoliticalEnvironmentService;
	
	
	/**
	 * 满足条件的所有,不分页
	 * @param paramAll
	 * @return
	 */
	@RequestMapping(value = "queryList", method = RequestMethod.POST)
	public String queryList(@RequestBody String paramAll) {
		
		IisPoliticalEnvironmentUserEntity psue = this.getUserEntity(paramAll);
		
		// 处理业务
		Map map = iisPoliticalEnvironmentService.queryList(psue);
		return JsonUtil.ObjectToStringClob(map);
	}
	
	 @SuppressWarnings("rawtypes")
	private IisPoliticalEnvironmentUserEntity getUserEntity(String context){
		// 获取参数信息
		String param =  JsonUtil.getJsonStrByAttrName(context,UserConstants.API_PARAM_NAME);
		
		 // 定义转换对象属性类
	    Map<String, Class> calssMap = new HashMap<String, Class>();
	    calssMap.put("orders", Order.class);
	  
	    IisPoliticalEnvironmentUserEntity para = null;
		if(StringUtil.isNotNullOrEmpty(param)){
			para  = (IisPoliticalEnvironmentUserEntity) JsonUtil.StringToObject(param, IisPoliticalEnvironmentUserEntity.class,calssMap);
		}else{
			para = new IisPoliticalEnvironmentUserEntity();
		}
		return para;
	}

}
