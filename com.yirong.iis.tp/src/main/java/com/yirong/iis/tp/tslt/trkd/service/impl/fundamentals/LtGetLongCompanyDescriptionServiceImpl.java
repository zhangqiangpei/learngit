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
 * 获取公司描述
 * @author lijp
 *
 */
@SuppressWarnings("unchecked")
@Service("ltGetLongCompanyDescriptionServiceImpl")
public class LtGetLongCompanyDescriptionServiceImpl extends LtHttpService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Map<String, Object> exec(Map<String, Object> param) {
		JSONObject content = new JSONObject();
		JSONObject GetLongCompanyDescription_Request_1 = new JSONObject();
		GetLongCompanyDescription_Request_1.put("companyId", param.get("companyId").toString());
		GetLongCompanyDescription_Request_1.put("companyIdType", param.get("companyIdType").toString());
		if(param.containsKey("countryCode")){
			GetLongCompanyDescription_Request_1.put("countryCode", param.get("countryCode").toString());
		}
		
		content.put("GetLongCompanyDescription_Request_1", GetLongCompanyDescription_Request_1);
		String result = HttpRequestUtils.httpPostHead(
				getHttpUrl("GetLongCompanyDescription"), 
				content,
				getHeadMap());
		logger.info("获取公司描述接口返回："+result);
		if(StringUtil.isNotNullOrEmpty(result)){
			JSONObject data = JSONObject.fromObject(result);
			if(data.has("Fault")){
				String error = data.getJSONObject("Fault").getJSONObject("Reason").getJSONObject("Text").getString("Value");
				logger.error("获取公司描述接口失败："+error);
				return ResultUtil.newError("获取公司描述接口失败："+error).toMap();
			}
			
			//返回成功 处理数据
			if(data.has("GetLongCompanyDescription_Response_1")){
				
				return ResultUtil.newOk("获取公司描述成功！").toMap();
			}
		}
		
		return ResultUtil.newError("获取公司描述失败!").toMap();
	}

}
