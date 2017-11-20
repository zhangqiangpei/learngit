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
		if(param.containsKey("LanguageCode")){
			GetBusinessAndGeographicSegments_Request_1.put("LanguageCode", param.get("LanguageCode").toString());
		}
		
		if(param.containsKey("WantToSeeCompanies")){
			GetBusinessAndGeographicSegments_Request_1.put("WantToSeeCompanies", param.get("WantToSeeCompanies"));
		}
		
		if(param.containsKey("WantToSeeDescriptions")){
			GetBusinessAndGeographicSegments_Request_1.put("WantToSeeDescriptions", param.get("WantToSeeDescriptions"));
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
