package com.yirong.iis.tp.tslt.trkd.service.impl.token;

import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.yirong.awaken.core.util.ResultUtil;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.commons.sys.eif.SysParameterEif;
import com.yirong.commons.util.datatype.StringUtil;
import com.yirong.commons.util.server.HttpRequestUtils;
import com.yirong.iis.tp.common.constant.LtConstant;
import com.yirong.iis.tp.tslt.trkd.service.LtHttpService;

/**
 * 创建模拟令牌
 * @author lijp
 *
 */
@SuppressWarnings("unchecked")
@Service("ltCreateImpersonationTokenServiceImpl")
public class LtCreateImpersonationTokenServiceImpl extends LtHttpService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Map<String, Object> exec(Map<String, Object> param) {
		JSONObject content = new JSONObject();
		JSONObject CreateImpersonationToken_Request_3 = new JSONObject();
		CreateImpersonationToken_Request_3.put("ApplicationID", SysParameterEif.getValueByCode("Trkd-ApplicationID"));
		CreateImpersonationToken_Request_3.put("Token", LtConstant.ltToken);
		
		content.put("CreateImpersonationToken_Request_3", CreateImpersonationToken_Request_3);
		String result = HttpRequestUtils.httpPostHead(
				getHttpUrl("CreateImpersonationToken"), 
				content,
				getHeadMap());
		logger.info("创建模拟令牌返回："+result);
		if(StringUtil.isNotNullOrEmpty(result)){
			JSONObject data = JSONObject.fromObject(result);
			if(data.has("Fault")){
				String error = data.getJSONObject("Fault").getJSONObject("Reason").getJSONObject("Text").getString("Value");
				logger.error("创建模拟令牌失败："+error);
				return ResultUtil.newError("创建模拟令牌失败："+error).toMap();
			}
			
			//返回成功 处理数据
			if(data.has("CreateImpersonationToken_Response_3")){
				
				return ResultUtil.newOk("创建模拟令牌成功！").toMap();
			}
		}
		
		return ResultUtil.newError("创建模拟令牌失败!").toMap();
	}
	
}
