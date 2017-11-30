package com.yirong.iis.tp.tslt.trkd.service.impl.fundamentals.bf;

import java.util.Map;

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
import com.yirong.iis.tp.tslt.trkd.service.fundamentals.LtTrkdCompanyOfficerService;

/**
 * Get Officers & Directors
 * 获得官员和董事
 * @athor lijp
 *
 */
@SuppressWarnings("unchecked")
@Service("ltGetOfficersAndDirectorsServiceImpl")
public class LtGetOfficersAndDirectorsServiceImpl extends LtHttpService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LtTrkdCompanyService ltTrkdCompanyService;
	
	@Autowired
	private LtTrkdCompanyOfficerService ltTrkdCompanyOfficerService;
	
	@Override
	public Map<String, Object> exec(Map<String, Object> param) {
		JSONObject content = new JSONObject();
		JSONObject GetOfficersAndDirectors_Request_1 = new JSONObject();
		GetOfficersAndDirectors_Request_1.put("companyId", param.get("companyId").toString());//"IBM.N"
		GetOfficersAndDirectors_Request_1.put("companyIdType", param.get("companyIdType").toString());//"RIC"
		if(param.containsKey("countryCode")){
			GetOfficersAndDirectors_Request_1.put("countryCode", param.get("countryCode").toString());
		}
		
		if(param.containsKey("lang")){
			GetOfficersAndDirectors_Request_1.put("lang", param.get("lang").toString());
		}
		
		GetOfficersAndDirectors_Request_1.put("ShowReferenceInformation", param.get("ShowReferenceInformation"));
		content.put("GetOfficersAndDirectors_Request_1", GetOfficersAndDirectors_Request_1);
		String result = HttpRequestUtils.httpPostHead(
				getHttpUrl("GetOfficersAndDirectors"), 
				content,
				getHeadMap());
		logger.info("获取官员和董事接口返回："+result);
		if(StringUtil.isNotNullOrEmpty(result)){
			JSONObject data = JSONObject.fromObject(result);
			if(data.has("Fault")){
				String error = data.getJSONObject("Fault").getJSONObject("Reason").getJSONObject("Text").getString("Value");
				logger.error("获取官员和董事接口失败："+error);
				
				//addRequestLog("GetOfficersAndDirectors",content.toString(),result,0);
				return ResultUtil.newError("获取官员和董事接口失败："+error).toMap();
			}
			
			//返回成功 处理数据
			if(data.has("GetOfficersAndDirectors_Response_1")){
				//获取公司
				LtTrkdCompany company = ltTrkdCompanyService.findByProperty("companyId", param.get("companyId").toString());
				if(null == company){
					//addRequestLog("GetOfficersAndDirectors",content.toString(),result,2);
					return ResultUtil.newError("保存公司高管信息失败，公司不存在："+ param.get("companyId").toString()).toMap();
				}
				
				
				
				
				//addRequestLog("GetOfficersAndDirectors",content.toString(),result,1);
				return ResultUtil.newOk("获取获得官员和董事成功！").toMap();
			}
		}
		
		//addRequestLog("GetOfficersAndDirectors",content.toString(),result,0);
		return ResultUtil.newError("获取获得官员和董事失败!").toMap();
	}

}
