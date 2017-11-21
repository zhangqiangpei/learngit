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
 * 获得主要客户
 * @author lijp
 *
 */
@SuppressWarnings("unchecked")
@Service("ltGetMajorCustomersServiceImpl")
public class LtGetMajorCustomersServiceImpl extends LtHttpService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Map<String, Object> exec(Map<String, Object> param) {
		JSONObject content = new JSONObject();
		JSONObject GetMajorCustomers_Request_1 = new JSONObject();
		GetMajorCustomers_Request_1.put("companyId", param.get("companyId").toString());
		GetMajorCustomers_Request_1.put("companyIdType", param.get("companyIdType").toString());
		if(param.containsKey("countryCode")){
			GetMajorCustomers_Request_1.put("countryCode", param.get("countryCode").toString());
		}
		
		content.put("GetMajorCustomers_Request_1", GetMajorCustomers_Request_1);
		String result = HttpRequestUtils.httpPostHead(
				getHttpUrl("GetMajorCustomers"), 
				content,
				getHeadMap());
		logger.info("获得主要客户接口返回："+result);
		if(StringUtil.isNotNullOrEmpty(result)){
			JSONObject data = JSONObject.fromObject(result);
			if(data.has("Fault")){
				String error = data.getJSONObject("Fault").getJSONObject("Reason").getJSONObject("Text").getString("Value");
				logger.error("获得主要客户接口失败："+error);
				return ResultUtil.newError("获得主要客户接口失败："+error).toMap();
			}
			
			//返回成功 处理数据
			if(data.has("GetMajorCustomers_Response_1")){
				
				return ResultUtil.newOk("获得主要客户成功！").toMap();
			}
		}
		
		return ResultUtil.newError("获得主要客户失败!").toMap();
	}
	
}
