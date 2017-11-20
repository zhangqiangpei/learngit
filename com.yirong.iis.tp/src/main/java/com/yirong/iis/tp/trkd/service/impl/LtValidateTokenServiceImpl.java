package com.yirong.iis.tp.trkd.service.impl;

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
import com.yirong.iis.tp.trkd.constant.LtConstant;
import com.yirong.iis.tp.trkd.service.LtHttpService;

/**
 * 验证token
 * @author lijp
 *
 */
@SuppressWarnings("unchecked")
@Service("ltValidateTokenServiceImpl")
public class LtValidateTokenServiceImpl extends LtHttpService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Map<String, Object> exec(Map<String, Object> param) {
		JSONObject content = new JSONObject();
		JSONObject ValidateToken_Request_1 = new JSONObject();
		ValidateToken_Request_1.put("ApplicationID", SysParameterEif.getValueByCode("Trkd-ApplicationID"));
		ValidateToken_Request_1.put("Token", LtConstant.ltToken);
		
		content.put("ValidateToken_Request_1", ValidateToken_Request_1);
		String result = HttpRequestUtils.httpPostHead(
				getHttpUrl("ValidateToken"), 
				content,
				getHeadMap());
		logger.info("验证token返回："+result);
		if(StringUtil.isNotNullOrEmpty(result)){
			JSONObject data = JSONObject.fromObject(result);
			if(data.has("Fault")){
				String error = data.getJSONObject("Fault").getJSONObject("Reason").getJSONObject("Text").getString("Value");
				logger.error("验证token失败："+error);
				return ResultUtil.newError("验证token失败："+error).toMap();
			}
			
			//返回成功 处理数据
			if(data.has("ValidateToken_Response_1")){
				String dateStr = data.getJSONObject("ValidateToken_Response_1").getString("Expiration");
				dateStr = dateStr.replace("T", " ").replace("Z", "");
				Date date = DateUtil.dateAdd(DateUtil.str2Date(dateStr,DateUtil.FORMAT_SECOND), DateUtil.HOUR, 8);
				LtConstant.expiration = date;
				
				return ResultUtil.newOk("验证token成功！").toMap();
			}
		}
		
		return ResultUtil.newError("验证token失败!").toMap();
	}

}
