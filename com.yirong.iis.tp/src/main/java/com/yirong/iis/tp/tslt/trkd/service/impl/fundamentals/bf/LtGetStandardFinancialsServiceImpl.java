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
 * 获得标准财务
 * @author lijp
 *
 */
@SuppressWarnings("unchecked")
@Service("ltGetStandardFinancialsServiceImpl")
public class LtGetStandardFinancialsServiceImpl extends LtHttpService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Map<String, Object> exec(Map<String, Object> param) {
		JSONObject content = new JSONObject();
		JSONObject GetStandardFinancials_Request_1 = new JSONObject();
		GetStandardFinancials_Request_1.put("companyId", param.get("companyId").toString());//"IBM.N"
		GetStandardFinancials_Request_1.put("companyIdType", param.get("companyIdType").toString());//"RIC"
		GetStandardFinancials_Request_1.put("finStatement", param.get("finStatement").toString());
		
		if(param.containsKey("numPeriods")){
			GetStandardFinancials_Request_1.put("numPeriods", param.get("numPeriods").toString());
		}
		
		if(param.containsKey("startDate")){
			GetStandardFinancials_Request_1.put("startDate", param.get("startDate"));
		}
		
		if(param.containsKey("endDate")){
			GetStandardFinancials_Request_1.put("endDate", param.get("endDate"));
		}
		
		if(param.containsKey("startFY")){
			GetStandardFinancials_Request_1.put("startFY", param.get("startFY"));
		}
		
		if(param.containsKey("endFY")){
			GetStandardFinancials_Request_1.put("endFY", param.get("endFY"));
		}
		
		if(param.containsKey("fpNumber")){
			GetStandardFinancials_Request_1.put("fpNumber", param.get("fpNumber"));
		}
		
		if(param.containsKey("fpNumber")){
			GetStandardFinancials_Request_1.put("fpNumber", param.get("fpNumber"));
		}
		
		if(param.containsKey("coaCodes")){
			GetStandardFinancials_Request_1.put("coaCodes", param.get("coaCodes").toString());
		}
		
		if(param.containsKey("displayTypes")){
			GetStandardFinancials_Request_1.put("displayTypes", param.get("displayTypes").toString());
		}
		
		if(param.containsKey("showCompanyInfo")){
			GetStandardFinancials_Request_1.put("showCompanyInfo", param.get("showCompanyInfo"));
		}
		
		if(param.containsKey("showStatementInfo")){
			GetStandardFinancials_Request_1.put("showStatementInfo", param.get("showStatementInfo"));
		}
		
		if(param.containsKey("showIssues")){
			GetStandardFinancials_Request_1.put("showIssues", param.get("showIssues"));
		}
		
		if(param.containsKey("showAvailability")){
			GetStandardFinancials_Request_1.put("showAvailability", param.get("showAvailability"));
		}
		
		if(param.containsKey("updateType")){
			GetStandardFinancials_Request_1.put("updateType", param.get("updateType").toString());
		}
		
		if(param.containsKey("completeStatement")){
			GetStandardFinancials_Request_1.put("completeStatement", param.get("completeStatement").toString());
		}
		
		if(param.containsKey("finalFiling")){
			GetStandardFinancials_Request_1.put("finalFiling", param.get("finalFiling").toString());
		}
		
		if(param.containsKey("lang")){
			GetStandardFinancials_Request_1.put("lang", param.get("lang").toString());
		}
		content.put("GetStandardFinancials_Request_1", GetStandardFinancials_Request_1);
		String result = HttpRequestUtils.httpPostHead(
				getHttpUrl("GetStandardFinancials"), 
				content,
				getHeadMap());
		logger.info("获得标准财务接口返回："+result);
		if(StringUtil.isNotNullOrEmpty(result)){
			JSONObject data = JSONObject.fromObject(result);
			if(data.has("Fault")){
				String error = data.getJSONObject("Fault").getJSONObject("Reason").getJSONObject("Text").getString("Value");
				logger.error("获得标准财务接口失败："+error);
				return ResultUtil.newError("获得标准财务接口失败："+error).toMap();
			}
			
			//返回成功 处理数据
			if(data.has("GetStandardFinancials_Response_1")){
				
				return ResultUtil.newOk("获得标准财务成功！").toMap();
			}
		}
		
		return ResultUtil.newError("获得标准财务失败!").toMap();
	}

}
