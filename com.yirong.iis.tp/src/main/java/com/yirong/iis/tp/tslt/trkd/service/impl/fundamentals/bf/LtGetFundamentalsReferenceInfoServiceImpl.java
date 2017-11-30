package com.yirong.iis.tp.tslt.trkd.service.impl.fundamentals.bf;

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
 * 获取基础知识参考信息
 * @author lijp
 *
 */
@SuppressWarnings("unchecked")
@Service("ltGetFundamentalsReferenceInfoServiceImpl")
public class LtGetFundamentalsReferenceInfoServiceImpl extends LtHttpService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Map<String, Object> exec(Map<String, Object> param) {
		JSONObject content = new JSONObject();
		JSONObject GetFundamentalsReferenceInfo_Request_1 = new JSONObject();
		GetFundamentalsReferenceInfo_Request_1.put("companyId", param.get("companyId").toString());//"IBM.N"
		GetFundamentalsReferenceInfo_Request_1.put("companyIdType", param.get("companyIdType").toString());//"RIC"
		if(param.containsKey("countryCode")){
			GetFundamentalsReferenceInfo_Request_1.put("countryCode", param.get("countryCode").toString());
		}
		
		if(param.containsKey("ShowReferenceInformation")){
			GetFundamentalsReferenceInfo_Request_1.put("ShowReferenceInformation", param.get("ShowReferenceInformation"));
		}
		
		content.put("GetFundamentalsReferenceInfo_Request_1", GetFundamentalsReferenceInfo_Request_1);
		String result = HttpRequestUtils.httpPostHead(
				getHttpUrl("GetFundamentalsReferenceInfo"), 
				content,
				getHeadMap());
		logger.info("获取基础知识参考信息接口返回："+result);
		if(StringUtil.isNotNullOrEmpty(result)){
			JSONObject data = JSONObject.fromObject(result);
			if(data.has("Fault")){
				String error = data.getJSONObject("Fault").getJSONObject("Reason").getJSONObject("Text").getString("Value");
				logger.error("获取基础知识参考信息接口失败："+error);
				return ResultUtil.newError("获取基础知识参考信息接口失败："+error).toMap();
			}
			
			//返回成功 处理数据
			if(data.has("GetFundamentalsReferenceInfo_Response_1")){
				
				return ResultUtil.newOk("获取基础知识参考信息成功！").toMap();
			}
		}
		
		return ResultUtil.newError("获取基础知识参考信息失败!").toMap();
	}
}
