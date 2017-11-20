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
 * 获取快照报告
 * @author lijp
 *
 */
@SuppressWarnings("unchecked")
@Service("ltGetSnapshotReportsServiceImpl")
public class LtGetSnapshotReportsServiceImpl extends LtHttpService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

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
				logger.error("获取快照报告接口失败："+error);
				return ResultUtil.newError("获取快照报告接口失败："+error).toMap();
			}
			
			//返回成功 处理数据
			if(data.has("GetSnapshotReports_Response_1")){
				
				return ResultUtil.newOk("获取快照报告成功！").toMap();
			}
		}
		
		return ResultUtil.newError("获取快照报告失败!").toMap();
	}

}
