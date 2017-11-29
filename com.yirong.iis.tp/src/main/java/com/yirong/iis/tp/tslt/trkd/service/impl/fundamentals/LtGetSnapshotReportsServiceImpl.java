package com.yirong.iis.tp.tslt.trkd.service.impl.fundamentals;

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
import com.yirong.iis.tp.common.entity.LtTrkdCompanyOfficer;
import com.yirong.iis.tp.common.entity.LtTrkdCompanyRatios;
import com.yirong.iis.tp.tslt.trkd.service.LtHttpService;
import com.yirong.iis.tp.tslt.trkd.service.LtTrkdCompanyService;
import com.yirong.iis.tp.tslt.trkd.service.fundamentals.LtTrkdCompanyOfficerService;
import com.yirong.iis.tp.tslt.trkd.service.fundamentals.LtTrkdCompanyRatiosService;

/**
 * 获取快照报告
 * @author lijp
 *
 */
@SuppressWarnings("unchecked")
@Service("ltGetSnapshotReportsServiceImpl")
public class LtGetSnapshotReportsServiceImpl extends LtHttpService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LtTrkdCompanyService ltTrkdCompanyService;
	
	@Autowired
	private LtTrkdCompanyOfficerService ltTrkdCompanyOfficerService;
	
	@Autowired
	private LtTrkdCompanyRatiosService ltTrkdCompanyRatiosService;

	@Override
	public Map<String, Object> exec(Map<String, Object> param) {
		JSONObject content = new JSONObject();
		JSONObject GetSnapshotReports_Request_1 = new JSONObject();
		GetSnapshotReports_Request_1.put("companyId", param.get("companyId").toString());//"IBM.N"
		GetSnapshotReports_Request_1.put("companyIdType", param.get("companyIdType").toString());//"RIC"
		if(param.containsKey("countryCode")){
			GetSnapshotReports_Request_1.put("countryCode", param.get("countryCode").toString());
		}
		
		if(param.containsKey("includeMedians")){
			GetSnapshotReports_Request_1.put("includeMedians", param.get("includeMedians"));
		}
		
		content.put("GetSnapshotReports_Request_1", GetSnapshotReports_Request_1);
		String result = HttpRequestUtils.httpPostHead(
				getHttpUrl("GetSnapshotReports"), 
				content,
				getHeadMap());
		logger.info("获取快照报告接口返回："+result);
		if(StringUtil.isNotNullOrEmpty(result)){
			JSONObject data = JSONObject.fromObject(result);
			if(data.has("Fault")){
				String error = data.getJSONObject("Fault").getJSONObject("Reason").getJSONObject("Text").getString("Value");
				addRequestLog("GetSnapshotReports",content.toString(),result,0,param.get("companyId").toString());
				logger.error("获取快照报告接口失败："+error);
				return ResultUtil.newError("获取快照报告接口失败："+error).toMap();
			}
			
			//返回成功 处理数据
			if(data.has("GetSnapshotReports_Response_1")){
				//获取对应公司
				LtTrkdCompany company = ltTrkdCompanyService.findByProperty("companyId", param.get("companyId").toString());
				if(null == company){
					addRequestLog("GetSnapshotReports",content.toString(),result,2,param.get("companyId").toString());
					return ResultUtil.newError("新增高管信息失败，公司不存在："+param.get("companyId").toString()).toMap();
				}
				
				JSONObject reportSnapshot = data.getJSONObject("GetSnapshotReports_Response_1").getJSONObject("FundamentalReports")
						.getJSONObject("ReportSnapshot");
				
				//公司表基础信息更新
				company.setEmployees(reportSnapshot.getJSONObject("CoGeneralInfo").getJSONObject("Employees").getString("Value"));
				company.setWebLinks(reportSnapshot.getJSONObject("webLinks").getJSONArray("webSite").getJSONObject(0).getString("Value"));
				
				JSONObject contactInfo = reportSnapshot.getJSONObject("contactInfo");
				company.setPostalCode(contactInfo.getString("postalCode"));
				company.setStateOrRegion(contactInfo.getString("state-region"));
				company.setStreetAddress(contactInfo.getJSONArray("streetAddress").getJSONObject(0).getString("Value"));
				company.setCity(contactInfo.getString("city"));
				
				JSONArray textArr = reportSnapshot.getJSONObject("TextInfo").getJSONArray("Text");
				for(int i = 0;i < textArr.size() ; i++){
					JSONObject json = textArr.getJSONObject(i);
					if("Business Summary".equals(json.getString("Type"))){
						company.setTextInfo(json.getString("Value"));
						break;
					}
				}
				
				ltTrkdCompanyService.save(company);
				
				//高管表更新
				
				List<LtTrkdCompanyOfficer> officerList = new ArrayList<LtTrkdCompanyOfficer>();
				JSONArray officerArr = reportSnapshot.getJSONObject("officers").getJSONArray("officer");
				for(int i = 0; i < officerArr.size(); i++){
					JSONObject officerJson = officerArr.getJSONObject(i);
					LtTrkdCompanyOfficer officer = new LtTrkdCompanyOfficer();
					officer.setAge(officerJson.getInt("age"));
					officer.setCompanyId(company.getCompanyId());
					officer.setFirstName(officerJson.getString("firstName"));
					officer.setLastName(officerJson.getString("lastName"));
					officer.setMi(officerJson.getString("mI"));
					officer.setRank(officerJson.getInt("rank"));
					officer.setSince(officerJson.getString("since"));
					
					JSONObject titleJson = officerJson.getJSONObject("title");
					officer.setTitle(titleJson.getString("Value"));
					String titleStart = titleJson.getString("startYear");
					if(StringUtil.isNotNullOrEmpty(titleJson.getString("startMonth"))){
						titleStart += "-" + titleJson.getString("startMonth");
					}
					
					if(StringUtil.isNotNullOrEmpty(titleJson.getString("startDay"))){
						titleStart += "-" + titleJson.getString("startDay");
					}
					officer.setTitleStart(titleStart);
					officerList.add(officer);
				}
				
				List<LtTrkdCompanyOfficer> oldOfficerList = ltTrkdCompanyOfficerService.findByProperty("companyId", company.getCompanyId(), Order.basicOrder());
				if(null != oldOfficerList && oldOfficerList.size() > 0){
					ltTrkdCompanyOfficerService.deleteAllByEntities(oldOfficerList);
				}
				
				if(officerList.size() > 0){
					ltTrkdCompanyOfficerService.saveAll(officerList);
				}
				
				//比率表更新
				JSONObject radioJson = reportSnapshot.getJSONObject("Ratios");
				JSONArray groupArr = radioJson.getJSONArray("Group");
				List<LtTrkdCompanyRatios> ratiosList = new ArrayList<LtTrkdCompanyRatios>();
				for(int i = 0 ; i < groupArr.size() ; i++){
					JSONObject groupJson = groupArr.getJSONObject(i);
					
					JSONArray ratioArr = groupJson.getJSONArray("Ratio");
					for(int j = 0; j < ratioArr.size() ; j++){
						JSONObject ratioJson = ratioArr.getJSONObject(j);
						
						LtTrkdCompanyRatios ratios = new LtTrkdCompanyRatios();
						ratios.setCompanyId(company.getCompanyId());
						ratios.setExchangeRate(radioJson.getDouble("ExchangeRate"));
						ratios.setPriceCurrency(radioJson.getString("PriceCurrency"));
						ratios.setReportingCurrency(radioJson.getString("ReportingCurrency"));
						ratios.setLatestAvailableDate(radioJson.getString("LatestAvailableDate"));
						
						ratios.setGroupId(groupJson.getString("ID"));
						
						ratios.setFieldName(ratioJson.getString("FieldName"));
						ratios.setType(ratioJson.getString("Type"));
						ratios.setFieldValue(ratioJson.getString("Value"));
						
						ratiosList.add(ratios);
					}
				}
				
				List<LtTrkdCompanyRatios> oldList = ltTrkdCompanyRatiosService.findByProperty("companyId", company.getCompanyId(), Order.basicOrder());
				if(null != oldList && oldList.size() > 0){
					ltTrkdCompanyRatiosService.deleteAllByEntities(oldList);
				}
				
				if(ratiosList.size() > 0){
					ltTrkdCompanyRatiosService.saveAll(ratiosList);
				}
				
				addRequestLog("GetSnapshotReports",content.toString(),result,1,param.get("companyId").toString());
				return ResultUtil.newOk("获取快照报告成功！").toMap();
			}
		}
		
		addRequestLog("GetSnapshotReports",content.toString(),result,0,param.get("companyId").toString());
		return ResultUtil.newError("获取快照报告失败!").toMap();
	}

}
