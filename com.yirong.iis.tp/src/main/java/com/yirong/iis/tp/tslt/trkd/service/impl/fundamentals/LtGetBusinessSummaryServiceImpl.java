package com.yirong.iis.tp.tslt.trkd.service.impl.fundamentals;

import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.yirong.awaken.core.util.ResultUtil;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.commons.util.datatype.StringUtil;
import com.yirong.commons.util.server.HttpRequestUtils;
import com.yirong.iis.tp.tslt.trkd.service.LtHttpService;

/**
 * 获取业务摘要
 * @author lijp
 *
 */
@SuppressWarnings("unchecked")
@Service("ltGetBusinessSummaryServiceImpl")
public class LtGetBusinessSummaryServiceImpl extends LtHttpService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Map<String, Object> exec(Map<String, Object> param) {
		JSONObject content = new JSONObject();
		JSONObject GetBusinessSummary_Request_1 = new JSONObject();
		GetBusinessSummary_Request_1.put("companyId", param.get("companyId").toString());
		GetBusinessSummary_Request_1.put("companyIdType", param.get("companyIdType").toString());
		if(param.containsKey("countryCode")){
			GetBusinessSummary_Request_1.put("countryCode", param.get("countryCode").toString());
		}
		
		if(param.containsKey("lang")){
			GetBusinessSummary_Request_1.put("lang", param.get("lang").toString());
		}
		
		content.put("GetBusinessSummary_Request_1", GetBusinessSummary_Request_1);
		String result = HttpRequestUtils.httpPostHead(
				getHttpUrl("GetBusinessSummary"), 
				content,
				getHeadMap());
		logger.info("获取业务摘要接口返回："+result);
		if(StringUtil.isNotNullOrEmpty(result)){
			JSONObject data = JSONObject.fromObject(result);
			if(data.has("Fault")){
				String error = data.getJSONObject("Fault").getJSONObject("Reason").getJSONObject("Text").getString("Value");
				logger.error("获取业务摘要接口失败："+error);
				return ResultUtil.newError("获取业务摘要接口失败："+error).toMap();
			}
			
			//返回成功 处理数据
			if(data.has("GetBusinessSummary_Response_1")){
				
				return ResultUtil.newOk("获取业务摘要成功！").toMap();
			}
		}
		
		return ResultUtil.newError("获取业务摘要失败!").toMap();
	}

}
