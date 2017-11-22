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
import com.yirong.iis.user.service.IisCountryInfoService;
import com.yirong.iis.user.userentity.IisCountryInfoUserEntity;

/**
 * 功能描述：国家信息表api接口
 *
 * @author 刘慧卿
 *         <p>
 *         创建时间 ：2017-11-21 15:44:24
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
	 * 根据国家的州的码表,以及国家的相关的中英文名称,进行模糊查询
	 * @param context
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/queryList", method = RequestMethod.POST)
	public String queryList(@RequestBody String context) {
		// 获取参数信息
		IisCountryInfoUserEntity para = this.getUserEntity(context);
		
		Map map = this.iisCountryInfoService.queryList(para);
 
		return JsonUtil.ObjectToString(map);
	}
    
    @SuppressWarnings("rawtypes")
	private IisCountryInfoUserEntity getUserEntity(String context){
		// 获取参数信息
		String param =  JsonUtil.getJsonStrByAttrName(context,UserConstants.API_PARAM_NAME);
		
		 // 定义转换对象属性类
	    Map<String, Class> calssMap = new HashMap<String, Class>();
	    calssMap.put("orders", Order.class);
	  
	    IisCountryInfoUserEntity para = null;
		if(StringUtil.isNotNullOrEmpty(param)){
			para  = (IisCountryInfoUserEntity) JsonUtil.StringToObject(param, IisCountryInfoUserEntity.class,calssMap);
		}else{
			para = new IisCountryInfoUserEntity();
		}
		return para;
	}
}
