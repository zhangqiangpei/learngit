package com.yirong.iis.tp.tslt.trkd.api;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yirong.commons.util.datatype.JsonUtil;
import com.yirong.iis.tp.tslt.trkd.service.LtHttpService;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("test")
public class TestApi {
	
	@Resource(name="ltGetSignificantDevelopmentsServiceImpl")
	private LtHttpService ltHttpService;
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		 // 定义转换对象属性类
	    Map<String, Object> param = new HashMap<String, Object>();
	    param.put("companyId", "IBM.N");
	    param.put("companyIdType", "RIC");
		Map map = this.ltHttpService.request(param);
		return JsonUtil.ObjectToString(map);
	}
}
