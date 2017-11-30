package com.yirong.iis.user.api;

import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yirong.commons.util.datatype.JsonUtil;
import com.yirong.iis.user.service.IisDataClassifyService;

 /**
 * 功能描述：数据分类表api接口
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-22 09:43:03
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("iisDataClassifyApi")
@ResponseBody
public class IisDataClassifyApi {

	/**
	 * 标准service注入
	 */
	@Autowired
	private IisDataClassifyService iisDataClassifyService;
	
	/**
	 * 获取全部一级分类
	 * @param jsonstr
	 * @return
	 */
	@RequestMapping(value = "/getFirstClassify", method = RequestMethod.POST)
	public String getFirstClassify(@RequestBody String jsonstr) {
		Map map = this.iisDataClassifyService.getFirstClassify();
		return JsonUtil.ObjectToString(map);
	}
	
	/**
	 * 获取全部的二级节点与所属的指标信息
	 * @param jsonstr
	 * @return
	 */
	@RequestMapping(value = "/getSecondClassifyAndInfo", method = RequestMethod.POST)
	public String getSecondClassifyAndInfo(@RequestBody String jsonstr) {
		String infoName = null;
		JSONObject json = JSONObject.fromObject(JSONObject.fromObject(jsonstr).getString("context"));
		if(json.containsKey("infoName")){
			infoName = json.getString("infoName");
		}
				
		Map map = this.iisDataClassifyService.getSecondClassifyAndInfo(infoName);
		return JsonUtil.ObjectToString(map);
	}
	

}
