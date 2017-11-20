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
 * 获取竞争对手
 * @author lijp
 *
 */
@SuppressWarnings("unchecked")
@Service("ltGetCompetitorsServiceImpl")
public class LtGetCompetitorsServiceImpl extends LtHttpService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Map<String, Object> exec(Map<String, Object> param) {
		JSONObject content = new JSONObject();
		JSONObject GetCompetitors_Request_1 = new JSONObject();
		GetCompetitors_Request_1.put("companyId", param.get("companyId").toString());
		GetCompetitors_Request_1.put("companyIdType", param.get("companyIdType").toString());
		if(param.containsKey("countryCode")){
			GetCompetitors_Request_1.put("countryCode", param.get("countryCode").toString());
		}
		
		content.put("GetCompetitors_Request_1", GetCompetitors_Request_1);
		String result = HttpRequestUtils.httpPostHead(
				getHttpUrl("GetCompetitors"), 
				content,
				getHeadMap());
		logger.info("获取竞争对手接口返回："+result);
		if(StringUtil.isNotNullOrEmpty(result)){
			JSONObject data = JSONObject.fromObject(result);
			if(data.has("Fault")){
				String error = data.getJSONObject("Fault").getJSONObject("Reason").getJSONObject("Text").getString("Value");
				logger.error("获取竞争对手接口失败："+error);
				return ResultUtil.newError("获取竞争对手接口失败："+error).toMap();
			}
			
			//返回成功 处理数据
			if(data.has("GetCompetitors_Response_1")){
				
				return ResultUtil.newOk("获取竞争对手成功！").toMap();
			}
		}
		
		return ResultUtil.newError("获取竞争对手失败!").toMap();
	}

}
