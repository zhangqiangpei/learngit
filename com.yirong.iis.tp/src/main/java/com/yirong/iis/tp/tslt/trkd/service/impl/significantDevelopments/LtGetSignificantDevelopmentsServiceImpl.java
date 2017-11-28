package com.yirong.iis.tp.tslt.trkd.service.impl.significantDevelopments;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.util.ResultUtil;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.commons.util.datatype.StringUtil;
import com.yirong.commons.util.order.Order;
import com.yirong.commons.util.server.HttpRequestUtils;
import com.yirong.iis.tp.common.entity.LtTrkdCompany;
import com.yirong.iis.tp.common.entity.LtTrkdCompanySignificant;
import com.yirong.iis.tp.tslt.trkd.service.LtHttpService;
import com.yirong.iis.tp.tslt.trkd.service.LtTrkdCompanyService;
import com.yirong.iis.tp.tslt.trkd.service.significantDevelopments.LtTrkdCompanySignificantService;

/**
 * 获取公司重大事件接口
 * @author lijp
 *
 */
@SuppressWarnings("unchecked")
@Service("ltGetSignificantDevelopmentsServiceImpl")
public class LtGetSignificantDevelopmentsServiceImpl extends LtHttpService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LtTrkdCompanyService ltTrkdCompanyService;
	
	@Autowired
	private LtTrkdCompanySignificantService ltTrkdCompanySignificantService;

	@Override
	public Map<String, Object> exec(Map<String, Object> param) {
		JSONObject content = new JSONObject();
		JSONObject GetSignificantDevelopments_Request_1 = new JSONObject();
		JSONObject FindRequest = new JSONObject();
		JSONArray CompanyIdentifiers_typehint = new JSONArray();
		CompanyIdentifiers_typehint.add("CompanyIdentifiers");
		FindRequest.put("CompanyIdentifiers_typehint", CompanyIdentifiers_typehint);
		JSONArray CompanyIdentifiers = new JSONArray();
		JSONObject RIC = new JSONObject();
		RIC.put("Value", param.get("companyId").toString());
		
		JSONObject item = new JSONObject();
		item.put("RIC", RIC);
		CompanyIdentifiers.add(item);
		FindRequest.put("CompanyIdentifiers", CompanyIdentifiers);
		
		if(param.containsKey("StartDate")){
			FindRequest.put("StartDate", param.get("StartDate").toString());
		}
		
		if(param.containsKey("EndDate")){
			FindRequest.put("EndDate", param.get("EndDate").toString());
		}
		GetSignificantDevelopments_Request_1.put("FindRequest", FindRequest);
	
		
		content.put("GetSignificantDevelopments_Request_1", GetSignificantDevelopments_Request_1);
		String result = HttpRequestUtils.httpPostHead(
				getHttpUrl("GetSignificantDevelopment"), 
				content,
				getHeadMap());
		logger.info("获取快照报告接口返回："+result);
		
		if(StringUtil.isNotNullOrEmpty(result)){
			JSONObject data = JSONObject.fromObject(result);
			if(data.has("Fault")){
				String error = data.getJSONObject("Fault").getJSONObject("Reason").getJSONObject("Text").getString("Value");
				addRequestLog("GetSignificantDevelopment",content.toString(),result,0);
				logger.error("获取快照报告接口失败："+error);
				return ResultUtil.newError("获取快照报告接口失败："+error).toMap();
			}
			
			//返回成功 处理数据
			if(data.has("GetSignificantDevelopments_Response_1")){
				
				//获取对应公司
				LtTrkdCompany company = ltTrkdCompanyService.findByProperty("companyId", param.get("companyId").toString());
				if(null == company){
					addRequestLog("GetSignificantDevelopment",content.toString(),result,2);
					return ResultUtil.newError("新增公司重大事件失败，公司不存在："+param.get("companyId").toString()).toMap();
				}
				
				List<LtTrkdCompanySignificant> sigList = new ArrayList<LtTrkdCompanySignificant>();
				JSONArray developmentArr = data.getJSONObject("GetSignificantDevelopments_Response_1").getJSONObject("FindResponse")
						.getJSONArray("Development");
				for(int i = 0; i < developmentArr.size() ; i++){
					JSONObject developmentJson = developmentArr.getJSONObject(i);
					LtTrkdCompanySignificant sig = new LtTrkdCompanySignificant();
					sig.setCompanyId(company.getId());
					sig.setCountry(developmentJson.getJSONObject("Xrefs").getString("Country"));
					sig.setDescription(developmentJson.getString("Description"));
					sig.setHeadline(developmentJson.getString("Headline"));
					sig.setInitiation(developmentJson.getJSONObject("Dates").getString("Initiation").replace("T", " "));
					sig.setLastUpdate(developmentJson.getJSONObject("Dates").getString("LastUpdate").replace("T", " "));
					sig.setSignificance(developmentJson.getJSONObject("Flags").getInt("Significance"));
					sig.setTopic(developmentJson.getJSONObject("Topics").getJSONObject("Topic1").getString("Value"));
					sigList.add(sig);
				}
				
				List<LtTrkdCompanySignificant> oldList = ltTrkdCompanySignificantService.findByProperty("companyId", company.getId(), Order.basicOrder());
				if(null != oldList && oldList.size() > 0){
					ltTrkdCompanySignificantService.deleteAllByEntities(oldList);
				}
				
				if(sigList.size() > 0){
					ltTrkdCompanySignificantService.saveAll(sigList);
				}
				
				addRequestLog("GetSignificantDevelopment",content.toString(),result,1);
				return ResultUtil.newOk("获取快照报告成功！").toMap();
			}
		}
		
		addRequestLog("GetSignificantDevelopment",content.toString(),result,0);
		return ResultUtil.newError("获取快照报告失败!").toMap();
	}

}
