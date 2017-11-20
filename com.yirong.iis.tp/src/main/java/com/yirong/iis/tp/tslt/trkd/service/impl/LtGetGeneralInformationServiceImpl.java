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
 * 获取一般信息
 * @author lijp
 *
 */
@SuppressWarnings("unchecked")
@Service("ltGetGeneralInformationServiceImpl")
public class LtGetGeneralInformationServiceImpl extends LtHttpService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Map<String, Object> exec(Map<String, Object> param) {
		JSONObject content = new JSONObject();
		JSONObject GetGeneralInformation_Request_1 = new JSONObject();
		GetGeneralInformation_Request_1.put("companyId", param.get("companyId").toString());
		GetGeneralInformation_Request_1.put("companyIdType", param.get("companyIdType").toString());
		if(param.containsKey("lang")){
			GetGeneralInformation_Request_1.put("lang", param.get("lang").toString());
		}
		
		if(param.containsKey("countryCode")){
			GetGeneralInformation_Request_1.put("countryCode", param.get("countryCode").toString());
		}
		
		if(param.containsKey("ShowReferenceInformation")){
			GetGeneralInformation_Request_1.put("ShowReferenceInformation", param.get("ShowReferenceInformation"));
		}
		
		content.put("GetGeneralInformation_Request_1", GetGeneralInformation_Request_1);
		String result = HttpRequestUtils.httpPostHead(
				getHttpUrl("GetGeneralInformation"), 
				content,
				getHeadMap());
		logger.info("获取一般信息接口返回："+result);
		if(StringUtil.isNotNullOrEmpty(result)){
			JSONObject data = JSONObject.fromObject(result);
			if(data.has("Fault")){
				String error = data.getJSONObject("Fault").getJSONObject("Reason").getJSONObject("Text").getString("Value");
				logger.error("获取一般信息接口失败："+error);
				return ResultUtil.newError("获取一般信息接口失败："+error).toMap();
			}
			
			//返回成功 处理数据
			if(data.has("GetGeneralInformation_Response_1")){
				
				return ResultUtil.newOk("获取一般信息成功！").toMap();
			}
		}
		
		return ResultUtil.newError("获取一般信息失败!").toMap();
	}
}
