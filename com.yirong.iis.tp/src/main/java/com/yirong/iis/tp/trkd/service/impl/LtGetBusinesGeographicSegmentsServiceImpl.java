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
 * 获取商业和地理细分
 * @author lijp
 *
 */
@SuppressWarnings("unchecked")
@Service("ltGetBusinesGeographicSegmentsServiceImpl")
public class LtGetBusinesGeographicSegmentsServiceImpl extends LtHttpService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Map<String, Object> exec(Map<String, Object> param) {
		JSONObject content = new JSONObject();
		JSONObject GetBusinessAndGeographicSegments_Request_1 = new JSONObject();
		GetBusinessAndGeographicSegments_Request_1.put("companyId", param.get("companyId").toString());
		GetBusinessAndGeographicSegments_Request_1.put("companyIdType", param.get("companyIdType").toString());
		GetBusinessAndGeographicSegments_Request_1.put("segmentType", param.get("segmentType").toString());
		if(param.containsKey("countryCode")){
			GetBusinessAndGeographicSegments_Request_1.put("countryCode", param.get("countryCode").toString());
		}
		
		if(param.containsKey("numPeriods")){
			GetBusinessAndGeographicSegments_Request_1.put("numPeriods", param.get("numPeriods").toString());
		}
		
		if(param.containsKey("startDate")){
			GetBusinessAndGeographicSegments_Request_1.put("startDate", param.get("startDate"));
		}
		
		if(param.containsKey("endDate")){
			GetBusinessAndGeographicSegments_Request_1.put("endDate", param.get("endDate"));
		}
		
		if(param.containsKey("startFY")){
			GetBusinessAndGeographicSegments_Request_1.put("startFY", param.get("startFY"));
		}
		
		if(param.containsKey("endFY")){
			GetBusinessAndGeographicSegments_Request_1.put("endFY", param.get("endFY"));
		}
		
		if(param.containsKey("fpNumber")){
			GetBusinessAndGeographicSegments_Request_1.put("fpNumber", param.get("fpNumber"));
		}
		
		if(param.containsKey("coaCodes")){
			GetBusinessAndGeographicSegments_Request_1.put("coaCodes", param.get("coaCodes").toString());
		}
		
		if(param.containsKey("displayTypes")){
			GetBusinessAndGeographicSegments_Request_1.put("displayTypes", param.get("displayTypes").toString());
		}
		
		if(param.containsKey("showCompanyInfo")){
			GetBusinessAndGeographicSegments_Request_1.put("showCompanyInfo", param.get("showCompanyInfo"));
		}
		
		if(param.containsKey("showStatementInfo")){
			GetBusinessAndGeographicSegments_Request_1.put("showStatementInfo", param.get("showStatementInfo"));
		}
		
		if(param.containsKey("showIssues")){
			GetBusinessAndGeographicSegments_Request_1.put("showIssues", param.get("showIssues"));
		}
		
		if(param.containsKey("showAvailability")){
			GetBusinessAndGeographicSegments_Request_1.put("showAvailability", param.get("showAvailability"));
		}
		
		if(param.containsKey("updateType")){
			GetBusinessAndGeographicSegments_Request_1.put("updateType", param.get("updateType").toString());
		}
		
		if(param.containsKey("periodType")){
			GetBusinessAndGeographicSegments_Request_1.put("periodType", param.get("periodType").toString());
		}
		
		if(param.containsKey("completeStatement")){
			GetBusinessAndGeographicSegments_Request_1.put("completeStatement", param.get("completeStatement").toString());
		}
		
		if(param.containsKey("finalFiling")){
			GetBusinessAndGeographicSegments_Request_1.put("finalFiling", param.get("finalFiling").toString());
		}
		
		content.put("GetBusinessAndGeographicSegments_Request_1", GetBusinessAndGeographicSegments_Request_1);
		String result = HttpRequestUtils.httpPostHead(
				getHttpUrl("GetBusinesGeographicSegments"), 
				content,
				getHeadMap());
		logger.info("获取商业和地理细分接口返回："+result);
		if(StringUtil.isNotNullOrEmpty(result)){
			JSONObject data = JSONObject.fromObject(result);
			if(data.has("Fault")){
				String error = data.getJSONObject("Fault").getJSONObject("Reason").getJSONObject("Text").getString("Value");
				logger.error("获取商业和地理细分接口失败："+error);
				return ResultUtil.newError("获取商业和地理细分接口失败："+error).toMap();
			}
			
			//返回成功 处理数据
			if(data.has("GetBusinessAndGeographicSegments_Response_1")){
				
				return ResultUtil.newOk("获取商业和地理细分成功！").toMap();
			}
		}
		
		return ResultUtil.newError("获取商业和地理细分失败!").toMap();
	}

}
