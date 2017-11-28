package com.yirong.iis.tp.tslt.trkd.service.impl.fundamentals;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.util.ResultUtil;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.commons.util.datatype.StringUtil;
import com.yirong.commons.util.server.HttpRequestUtils;
import com.yirong.iis.tp.common.entity.LtTrkdCompany;
import com.yirong.iis.tp.tslt.trkd.service.LtHttpService;
import com.yirong.iis.tp.tslt.trkd.service.LtTrkdCompanyService;

/**
 * 获取一般信息
 * @author lijp
 *
 */
@SuppressWarnings("unchecked")
@Service("ltGetGeneralInformationServiceImpl")
public class LtGetGeneralInformationServiceImpl extends LtHttpService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LtTrkdCompanyService ltTrkdCompanyService;
	
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
				
				addRequestLog("GetGeneralInformation",content.toString(),result,0);
				return ResultUtil.newError("获取一般信息接口失败："+error).toMap();
			}
			
			//返回成功 处理数据
			if(data.has("GetGeneralInformation_Response_1")){
				LtTrkdCompany company = ltTrkdCompanyService.findByProperty("companyId", param.get("companyId").toString());
				if(null == company){
					addRequestLog("GetGeneralInformation",content.toString(),result,2);
					return ResultUtil.newError("保存公司基础信息失败，公司不存在："+ param.get("companyId").toString()).toMap();
				}
				
				JSONObject  generalInformation = data.getJSONObject("GetGeneralInformation_Response_1").getJSONObject("GeneralInformation");
				//保存公司基础信息
				company.setRepNo(generalInformation.getString("RepNo"));
				company.setProductionDate(generalInformation.getJSONObject("Production").getString("Date").replace("T", " "));
				
				JSONObject companyGeneralInfo = generalInformation.getJSONObject("CompanyGeneralInfo");
				company.setEmployees(companyGeneralInfo.getJSONObject("Employees").getString("Value"));
				company.setTotalSharesOut(companyGeneralInfo.getJSONObject("TotalSharesOut").getString("Value"));
				company.setCommonShareholders(companyGeneralInfo.getJSONObject("CommonShareholders").getString("Value"));
				company.setIncorporatedIn(companyGeneralInfo.getJSONObject("IncorporatedIn").getString("Date"));
				company.setPublicSince(companyGeneralInfo.getString("PublicSince"));
				
				company.setAuditor(generalInformation.getJSONObject("Advisors").getJSONObject("Auditor").getString("Name"));
				company.setWebLinks(generalInformation.getJSONObject("WebLinksInfo").getJSONArray("WebSite").getJSONObject(0).getString("Value"));
				
				JSONObject address = generalInformation.getJSONObject("ContactInfo").getJSONObject("Address");
				company.setStreetAddress(address.getJSONArray("StreetAddress").getJSONObject(0).getString("Value"));
				company.setCity(address.getString("City"));
				company.setPostalCode(address.getString("PostalCode"));
				company.setStateOrRegion(address.getString("StateOrRegion"));
				
				JSONArray textArr = generalInformation.getJSONObject("TextInfo").getJSONArray("Text");
				for(int i = 0;i < textArr.size() ; i++){
					JSONObject json = textArr.getJSONObject(i);
					if("Business Summary".equals(json.getString("Type"))){
						company.setTextInfo(json.getString("Value"));
						break;
					}
				}
				
				ltTrkdCompanyService.save(company);
				
				addRequestLog("GetGeneralInformation",content.toString(),result,1);
				return ResultUtil.newOk("获取一般信息成功！").toMap();
			}
		}
		
		addRequestLog("GetGeneralInformation",content.toString(),result,0);
		return ResultUtil.newError("获取一般信息失败!").toMap();
	}
}
