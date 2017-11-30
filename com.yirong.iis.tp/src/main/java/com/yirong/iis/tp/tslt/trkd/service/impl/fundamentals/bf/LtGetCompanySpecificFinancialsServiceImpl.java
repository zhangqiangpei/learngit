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
 * 获取公司特定的财务
 * @author lijp
 *
 */
@SuppressWarnings("unchecked")
@Service("ltGetCompanySpecificFinancialsServiceImpl")
public class LtGetCompanySpecificFinancialsServiceImpl extends LtHttpService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Map<String, Object> exec(Map<String, Object> param) {
		JSONObject content = new JSONObject();
		JSONObject GetCompanySpecificFinancials_Request_1 = new JSONObject();
		GetCompanySpecificFinancials_Request_1.put("companyId", param.get("companyId").toString());
		GetCompanySpecificFinancials_Request_1.put("companyIdType", param.get("companyIdType").toString());
		GetCompanySpecificFinancials_Request_1.put("finStatement", param.get("finStatement").toString());
		if(param.containsKey("countryCode")){
			GetCompanySpecificFinancials_Request_1.put("countryCode", param.get("countryCode").toString());
		}
		
		if(param.containsKey("numPeriods")){
			GetCompanySpecificFinancials_Request_1.put("numPeriods", param.get("numPeriods").toString());
		}
		
		if(param.containsKey("startDate")){
			GetCompanySpecificFinancials_Request_1.put("startDate", param.get("startDate"));
		}
		
		if(param.containsKey("endDate")){
			GetCompanySpecificFinancials_Request_1.put("endDate", param.get("endDate"));
		}
		
		if(param.containsKey("startFY")){
			GetCompanySpecificFinancials_Request_1.put("startFY", param.get("startFY"));
		}
		
		if(param.containsKey("endFY")){
			GetCompanySpecificFinancials_Request_1.put("endFY", param.get("endFY"));
		}
		
		if(param.containsKey("fpNumber")){
			GetCompanySpecificFinancials_Request_1.put("fpNumber", param.get("fpNumber"));
		}
		
		if(param.containsKey("coaCodes")){
			GetCompanySpecificFinancials_Request_1.put("coaCodes", param.get("coaCodes").toString());
		}
		
		if(param.containsKey("displayTypes")){
			GetCompanySpecificFinancials_Request_1.put("displayTypes", param.get("displayTypes").toString());
		}
		
		if(param.containsKey("showCompanyInfo")){
			GetCompanySpecificFinancials_Request_1.put("showCompanyInfo", param.get("showCompanyInfo"));
		}
		
		if(param.containsKey("showStatementInfo")){
			GetCompanySpecificFinancials_Request_1.put("showStatementInfo", param.get("showStatementInfo"));
		}
		
		if(param.containsKey("showIssues")){
			GetCompanySpecificFinancials_Request_1.put("showIssues", param.get("showIssues"));
		}
		
		if(param.containsKey("showAvailability")){
			GetCompanySpecificFinancials_Request_1.put("showAvailability", param.get("showAvailability"));
		}
		
		if(param.containsKey("updateType")){
			GetCompanySpecificFinancials_Request_1.put("updateType", param.get("updateType").toString());
		}
		
		if(param.containsKey("completeStatement")){
			GetCompanySpecificFinancials_Request_1.put("completeStatement", param.get("completeStatement").toString());
		}
		
		if(param.containsKey("finalFiling")){
			GetCompanySpecificFinancials_Request_1.put("finalFiling", param.get("finalFiling").toString());
		}
		
		content.put("GetCompanySpecificFinancials_Request_1", GetCompanySpecificFinancials_Request_1);
		String result = HttpRequestUtils.httpPostHead(
				getHttpUrl("GetCompanySpecificFinancials"), 
				content,
				getHeadMap());
		logger.info("获取公司特定的财务接口返回："+result);
		if(StringUtil.isNotNullOrEmpty(result)){
			JSONObject data = JSONObject.fromObject(result);
			if(data.has("Fault")){
				String error = data.getJSONObject("Fault").getJSONObject("Reason").getJSONObject("Text").getString("Value");
				logger.error("获取公司特定的财务接口失败："+error);
				return ResultUtil.newError("获取公司特定的财务接口失败："+error).toMap();
			}
			
			//返回成功 处理数据
			if(data.has("GetCompanySpecificFinancials_Response_1")){
				
				return ResultUtil.newOk("获取公司特定的财务成功！").toMap();
			}
		}
		
		return ResultUtil.newError("获取公司特定的财务失败!").toMap();
	}

}
