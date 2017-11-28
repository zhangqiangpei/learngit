package com.yirong.iis.tp.tslt.trkd.service.impl.fundamentals;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.yirong.iis.tp.common.entity.LtTrkdCompanyFinanceReport;
import com.yirong.iis.tp.tslt.trkd.service.LtHttpService;
import com.yirong.iis.tp.tslt.trkd.service.LtTrkdCompanyService;
import com.yirong.iis.tp.tslt.trkd.service.fundamentals.ILtTrkdCompanyFinanceReportService;

/**
 * 获取财务报表
 * @author lijp
 *
 */
@SuppressWarnings("unchecked")
@Service("ltGetFinancialStatementsReportsServiceImpl")
public class LtGetFinancialStatementsReportsServiceImpl extends LtHttpService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ILtTrkdCompanyFinanceReportService ltTrkdCompanyFinanceReportService;
	
	@Autowired
	private LtTrkdCompanyService ltTrkdCompanyService;
	
	@Override
	public Map<String, Object> exec(Map<String, Object> param) {
		JSONObject content = new JSONObject();
		JSONObject GetFinancialStatementsReports_Request_1 = new JSONObject();
		GetFinancialStatementsReports_Request_1.put("companyId", param.get("companyId").toString());//"IBM.N"
		GetFinancialStatementsReports_Request_1.put("companyIdType", param.get("companyIdType").toString());//"RIC"
		if(param.containsKey("countryCode")){
			GetFinancialStatementsReports_Request_1.put("countryCode", param.get("countryCode").toString());
		}
		
		if(param.containsKey("includeMedians")){
			GetFinancialStatementsReports_Request_1.put("includeMedians", param.get("includeMedians"));
		}
		
		content.put("GetFinancialStatementsReports_Request_1", GetFinancialStatementsReports_Request_1);
		String result = HttpRequestUtils.httpPostHead(
				getHttpUrl("GetFinancialStatementsReports"), 
				content,
				getHeadMap());
		logger.info("获取财务报表接口返回："+result);
		if(StringUtil.isNotNullOrEmpty(result)){
			JSONObject data = JSONObject.fromObject(result);
			if(data.has("Fault")){
				String error = data.getJSONObject("Fault").getJSONObject("Reason").getJSONObject("Text").getString("Value");
				logger.error("获取财务报表接口失败："+error);
				
				addRequestLog("GetFinancialStatementsReports",content.toString(),result,0);
				return ResultUtil.newError("获取财务报表接口失败："+error).toMap();
			}
			
			//返回成功 处理数据
			if(data.has("GetFinancialStatementsReports_Response_1")){
				//获取报表json
				JSONObject financialStatements = data.getJSONObject("GetFinancialStatementsReports_Response_1")
						.getJSONObject("FundamentalReports").getJSONObject("ReportFinancialStatements").getJSONObject("FinancialStatements");
				
				//组装报表字段  全称和简称  对应map
				Map<String,String> coaMap = new HashMap<String,String>();
				JSONArray mapItem = financialStatements.getJSONObject("COAMap").getJSONArray("mapItem");
				for(int i=0;i<mapItem.size();i++){
					JSONObject json = mapItem.getJSONObject(i);
					coaMap.put(json.getString("coaItem"), json.getString("Value"));
				}
				
				//获取对应公司
				LtTrkdCompany company = ltTrkdCompanyService.findByProperty("companyId", param.get("companyId").toString());
				if(null == company){
					addRequestLog("GetFinancialStatementsReports",content.toString(),result,2);
					return ResultUtil.newError("新增财务报表失败，公司不存在："+param.get("companyId").toString()).toMap();
				}
				
				//拼装财务数据
				List<LtTrkdCompanyFinanceReport> frList = new ArrayList<LtTrkdCompanyFinanceReport>();
				//type Annual全年，Interim季度
				if(financialStatements.has("AnnualPeriods")){
					JSONArray fiscalPeriod = financialStatements.getJSONObject("AnnualPeriods").getJSONArray("FiscalPeriod");
					
					for(int i = 0;i < fiscalPeriod.size();i++){
						JSONObject yearJson = fiscalPeriod.getJSONObject(i);
						JSONArray statementArr = yearJson.getJSONArray("Statement");
						
						for(int j = 0;j < statementArr.size() ; j++){
							JSONObject json =  statementArr.getJSONObject(j);
							String type = json.getString("Type");//类型（INC=损益表 ，BAL=资产负债表，CAS=现金流表）
							JSONObject fPHeader = json.getJSONObject("FPHeader");
							JSONArray lineItem = json.getJSONArray("lineItem");
							
							for(int n = 0;n < lineItem.size() ; n++){
								JSONObject line = lineItem.getJSONObject(n);
								LtTrkdCompanyFinanceReport fp = new LtTrkdCompanyFinanceReport();
								fp.setCompanyId(company.getId());
								
								if(json.has("AuditorName")){
									fp.setAuditorName(json.getJSONObject("AuditorName").getString("Value"));
								}
								
								if(json.has("AuditorOpinion")){
									fp.setAuditorOpinion(json.getJSONObject("AuditorOpinion").getString("Value"));
								}
								
								fp.setCoaCode(line.getString("coaCode"));
								fp.setCoaType(type);
								fp.setCoaValue(line.getString("Value"));
								fp.setEndDate(yearJson.getString("EndDate"));
								if(yearJson.has("FiscalPeriodNumber")){
									fp.setFiscalPeriodNumber(yearJson.getInt("FiscalPeriodNumber"));
								}
								fp.setFiscalYear(yearJson.getString("FiscalYear"));
								fp.setName(coaMap.get(line.getString("coaCode")));
								if(fPHeader.has("PeriodLength")){
									fp.setPeriodLength(fPHeader.getInt("PeriodLength"));
								}
								
								if(fPHeader.has("periodType")){
									fp.setPeriodType(fPHeader.getJSONObject("periodType").getString("Value"));
								}
								
								fp.setSource(fPHeader.getJSONObject("Source").getString("Value"));
								fp.setStatementDate(fPHeader.getString("StatementDate"));
								fp.setType(yearJson.getString("Type"));
								fp.setUpdateType(fPHeader.getJSONObject("UpdateType").getString("Value"));
								
								frList.add(fp);
							}
							
						}
						
					}
					
				}
				
				if(financialStatements.has("InterimPeriods")){
					JSONArray fiscalPeriod = financialStatements.getJSONObject("InterimPeriods").getJSONArray("FiscalPeriod");
					
					for(int i = 0;i < fiscalPeriod.size();i++){
						JSONObject yearJson = fiscalPeriod.getJSONObject(i);
						JSONArray statementArr = yearJson.getJSONArray("Statement");
						
						for(int j = 0;j < statementArr.size() ; j++){
							JSONObject json =  statementArr.getJSONObject(j);
							String type = json.getString("Type");//类型（INC=损益表 ，BAL=资产负债表，CAS=现金流表）
							JSONObject fPHeader = json.getJSONObject("FPHeader");
							JSONArray lineItem = json.getJSONArray("lineItem");
							
							for(int n = 0;n < lineItem.size() ; n++){
								JSONObject line = lineItem.getJSONObject(n);
								LtTrkdCompanyFinanceReport fp = new LtTrkdCompanyFinanceReport();
								fp.setCompanyId(company.getId());
								if(json.has("AuditorName")){
									fp.setAuditorName(json.getJSONObject("AuditorName").getString("Value"));
								}
								
								if(json.has("AuditorOpinion")){
									fp.setAuditorOpinion(json.getJSONObject("AuditorOpinion").getString("Value"));
								}
								
								fp.setCoaCode(line.getString("coaCode"));
								fp.setCoaType(type);
								fp.setCoaValue(line.getString("Value"));
								fp.setEndDate(yearJson.getString("EndDate"));
								if(yearJson.has("FiscalPeriodNumber")){
									fp.setFiscalPeriodNumber(yearJson.getInt("FiscalPeriodNumber"));
								}
								fp.setFiscalYear(yearJson.getString("FiscalYear"));
								fp.setName(coaMap.get(line.getString("coaCode")));
								if(fPHeader.has("PeriodLength")){
									fp.setPeriodLength(fPHeader.getInt("PeriodLength"));
								}
								
								if(fPHeader.has("periodType")){
									fp.setPeriodType(fPHeader.getJSONObject("periodType").getString("Value"));
								}
								
								fp.setSource(fPHeader.getJSONObject("Source").getString("Value"));
								fp.setStatementDate(fPHeader.getString("StatementDate"));
								fp.setType(yearJson.getString("Type"));
								fp.setUpdateType(fPHeader.getJSONObject("UpdateType").getString("Value"));
								
								frList.add(fp);
							}
							
						}
						
					}
				}
				
				//先删除旧有数据，然后新增新数据
				List<LtTrkdCompanyFinanceReport> oldList = ltTrkdCompanyFinanceReportService.findByProperty("companyId", param.get("companyIdType").toString(), Order.basicOrder());
				ltTrkdCompanyFinanceReportService.deleteAllByEntities(oldList);
				if(null != frList && frList.size() > 0){
					ltTrkdCompanyFinanceReportService.saveAll(frList);
				}
				
				addRequestLog("GetFinancialStatementsReports",content.toString(),result,1);
				return ResultUtil.newOk("获取财务报表成功！").toMap();
			}
		}
		
		addRequestLog("GetFinancialStatementsReports",content.toString(),result,0);
		return ResultUtil.newError("获取财务报表失败!").toMap();
	}

}
