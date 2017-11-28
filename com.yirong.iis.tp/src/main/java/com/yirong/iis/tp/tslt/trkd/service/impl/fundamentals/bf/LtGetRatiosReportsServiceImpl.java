package com.yirong.iis.tp.tslt.trkd.service.impl.fundamentals.bf;

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
import com.yirong.iis.tp.common.entity.LtTrkdCompanyRatios;
import com.yirong.iis.tp.tslt.trkd.service.LtHttpService;
import com.yirong.iis.tp.tslt.trkd.service.LtTrkdCompanyService;
import com.yirong.iis.tp.tslt.trkd.service.fundamentals.LtTrkdCompanyRatiosService;

/**
 * 获取比率报告 Get Ratios Reports
 * @author lijp
 *
 */
@SuppressWarnings("unchecked")
@Service("ltGetRatiosReportsServiceImpl")
public class LtGetRatiosReportsServiceImpl extends LtHttpService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LtTrkdCompanyService ltTrkdCompanyService;
	
	@Autowired
	private LtTrkdCompanyRatiosService ltTrkdCompanyRatiosService;
	
	@Override
	public Map<String, Object> exec(Map<String, Object> param) {
		JSONObject content = new JSONObject();
		JSONObject GetRatiosReports_Request_1 = new JSONObject();
		GetRatiosReports_Request_1.put("companyId", param.get("companyId").toString());//"IBM.N"
		GetRatiosReports_Request_1.put("companyIdType", param.get("companyIdType").toString());//"RIC"
		if(param.containsKey("countryCode")){
			GetRatiosReports_Request_1.put("countryCode", param.get("countryCode").toString());
		}
		
		if(param.containsKey("includeMedians")){
			GetRatiosReports_Request_1.put("includeMedians", param.get("includeMedians"));
		}
		
		content.put("GetRatiosReports_Request_1", GetRatiosReports_Request_1);
		String result = HttpRequestUtils.httpPostHead(
				getHttpUrl("GetRatiosReports"), 
				content,
				getHeadMap());
		logger.info("获取比率报告接口返回："+result);
		if(StringUtil.isNotNullOrEmpty(result)){
			JSONObject data = JSONObject.fromObject(result);
			if(data.has("Fault")){
				String error = data.getJSONObject("Fault").getJSONObject("Reason").getJSONObject("Text").getString("Value");
				logger.error("获取比率报告接口失败："+error);
				
				addRequestLog("GetRatiosReports",content.toString(),result,0);
				return ResultUtil.newError("获取比率报告接口失败："+error).toMap();
			}
			
			//返回成功 处理数据
			if(data.has("GetRatiosReports_Response_1")){
				
				//获取对应公司
				LtTrkdCompany company = ltTrkdCompanyService.findByProperty("companyId", param.get("companyId").toString());
				if(null == company){
					addRequestLog("GetRatiosReports",content.toString(),result,2);
					return ResultUtil.newError("新增比率报告失败，公司不存在："+param.get("companyId").toString()).toMap();
				}
				
				JSONObject radioJson = data.getJSONObject("GetRatiosReports_Response_1").getJSONObject("FundamentalReports")
						.getJSONObject("ReportRatios").getJSONObject("Ratios");
				JSONArray groupArr = radioJson.getJSONArray("Group");
				
				List<LtTrkdCompanyRatios> ratiosList = new ArrayList<LtTrkdCompanyRatios>();
				for(int i = 0 ; i < groupArr.size() ; i++){
					JSONObject groupJson = groupArr.getJSONObject(i);
					
					JSONArray ratioArr = groupJson.getJSONArray("Ratio");
					for(int j = 0; j < ratioArr.size() ; j++){
						JSONObject ratioJson = ratioArr.getJSONObject(j);
						
						LtTrkdCompanyRatios ratios = new LtTrkdCompanyRatios();
						ratios.setCompanyId(company.getId());
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
				
				List<LtTrkdCompanyRatios> oldList = ltTrkdCompanyRatiosService.findByProperty("companyId", company.getId(), Order.basicOrder());
				if(null != oldList && oldList.size() > 0){
					ltTrkdCompanyRatiosService.deleteAllByEntities(oldList);
				}
				
				if(ratiosList.size() > 0){
					ltTrkdCompanyRatiosService.saveAll(ratiosList);
				}
				
				addRequestLog("GetRatiosReports",content.toString(),result,1);
				return ResultUtil.newOk("获取比率报告成功！").toMap();
			}
		}
		
		addRequestLog("GetRatiosReports",content.toString(),result,0);
		return ResultUtil.newError("获取比率报告失败!").toMap();
	}

}
