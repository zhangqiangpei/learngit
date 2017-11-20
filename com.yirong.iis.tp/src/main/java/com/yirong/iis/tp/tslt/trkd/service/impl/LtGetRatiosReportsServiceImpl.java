package com.yirong.iis.tp.trkd.service.impl;

import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.yirong.awaken.core.util.ResultUtil;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.commons.util.datatype.StringUtil;
import com.yirong.commons.util.server.HttpRequestUtils;
import com.yirong.iis.tp.trkd.service.LtHttpService;

/**
 * 获取比率报告 Get Ratios Reports
 * @author lijp
 *
 */
@SuppressWarnings("unchecked")
@Service("ltGetRatiosReportsServiceImpl")
public class LtGetRatiosReportsServiceImpl extends LtHttpService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Map<String, Object> exec(Map<String, Object> param) {
		JSONObject content = new JSONObject();
		JSONObject GetRatiosReports_Request_1 = new JSONObject();
		GetRatiosReports_Request_1.put("companyId", param.get("companyId").toString());//"IBM.N"
		GetRatiosReports_Request_1.put("companyIdType", param.get("companyIdType").toString());//"RIC"
		if(param.containsKey("countryCode")){
			GetRatiosReports_Request_1.put("countryCode", param.get("countryCode").toString());
		}
		
		if(param.containsKey("includeMedians")){
			GetRatiosReports_Request_1.put("includeMedians", param.get("includeMedians"));
		}
		
		content.put("GetRatiosReports_Request_1", GetRatiosReports_Request_1);
		String result = HttpRequestUtils.httpPostHead(
				getHttpUrl("GetRatiosReports"), 
				content,
				getHeadMap());
		logger.info("获取比率报告接口返回："+result);
		if(StringUtil.isNotNullOrEmpty(result)){
			JSONObject data = JSONObject.fromObject(result);
			if(data.has("Fault")){
				String error = data.getJSONObject("Fault").getJSONObject("Reason").getJSONObject("Text").getString("Value");
				logger.error("获取比率报告接口失败："+error);
				return ResultUtil.newError("获取比率报告接口失败："+error).toMap();
			}
			
			//返回成功 处理数据
			if(data.has("GetRatiosReports_Response_1")){
				
				return ResultUtil.newOk("获取比率报告成功！").toMap();
			}
		}
		
		return ResultUtil.newError("获取比率报告失败!").toMap();
	}

}
