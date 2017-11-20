package com.yirong.iis.lt.trkd.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.yirong.awaken.core.util.ResultUtil;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.commons.sys.eif.SysParameterEif;
import com.yirong.commons.util.datatype.DateUtil;
import com.yirong.commons.util.datatype.StringUtil;
import com.yirong.commons.util.server.HttpRequestUtils;
import com.yirong.iis.lt.constant.LtConstant;
import com.yirong.iis.lt.trkd.service.ILtTokenService;

/**
 * 获取token
 * @author lijp
 *
 */
@SuppressWarnings("unchecked")
@Service("ltTokenServiceImpl")
public class LtTokenServiceImpl implements ILtTokenService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Map<String, Object> getToken() {
		JSONObject content = new JSONObject();
		JSONObject CreateServiceToken_Request_1 = new JSONObject();
		CreateServiceToken_Request_1.put("ApplicationID", SysParameterEif.getValueByCode("Trkd-ApplicationID"));
		CreateServiceToken_Request_1.put("Username", SysParameterEif.getValueByCode("Trkd-Username"));
		CreateServiceToken_Request_1.put("Password", SysParameterEif.getValueByCode("Trkd-Password"));
		content.put("CreateServiceToken_Request_1", CreateServiceToken_Request_1);
		String result = HttpRequestUtils.httpsRequest("https://api.trkd.thomsonreuters.com/api/TokenManagement/TokenManagement.svc/REST/Anonymous/TokenManagement_1/CreateServiceToken_1"
				, content.toString());
		logger.info("路透获取token==="+result);
		
		if(StringUtil.isNotNullOrEmpty(result)){
			JSONObject data = JSONObject.fromObject(result);
			if(data.has("Fault")){
				String error = data.getJSONObject("Fault").getJSONObject("Reason").getJSONObject("Text").getString("Value");
				logger.error("请求路透token失败："+error);
				return ResultUtil.newError("请求路透token失败："+error).toMap();
			}
			
			if(data.has("CreateServiceToken_Response_1")){
				LtConstant.ltToken = data.getJSONObject("CreateServiceToken_Response_1").getString("Token");
				Date date = DateUtil.dateAdd(DateUtil.str2Date(data.getString("Expiration"),"yyyy-MM-dd HH:mm:ss"), Calendar.HOUR, 8);
				LtConstant.expiration = date;
				return ResultUtil.newOk("获取路透接口成功！").toMap();
			}
			
		}
		
		return ResultUtil.newError("获取路透token失败!").toMap();
	}

}
