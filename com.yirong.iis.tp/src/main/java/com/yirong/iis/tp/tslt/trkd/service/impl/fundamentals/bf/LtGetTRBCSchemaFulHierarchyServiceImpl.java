package com.yirong.iis.tp.tslt.trkd.service.impl.fundamentals.bf;

import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.yirong.awaken.core.util.ResultUtil;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.commons.util.datatype.StringUtil;
import com.yirong.commons.util.server.HttpRequestUtils;
import com.yirong.iis.tp.tslt.trkd.service.LtHttpService;

/**
 * 获取TRBC架构完整层次结构
 * @author lijp
 *
 */
@SuppressWarnings("unchecked")
@Service("ltGetTRBCSchemaFulHierarchyServiceImpl")
public class LtGetTRBCSchemaFulHierarchyServiceImpl extends LtHttpService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Map<String, Object> exec(Map<String, Object> param) {
		JSONObject content = new JSONObject();
		JSONObject GetTRBCSchemaFullHierarchy_Request_1 = new JSONObject();
		if(param.containsKey("LanguageCode")){
			GetTRBCSchemaFullHierarchy_Request_1.put("LanguageCode", param.get("LanguageCode").toString());
		}
		
		if(param.containsKey("WantToSeeDescriptions")){
			GetTRBCSchemaFullHierarchy_Request_1.put("WantToSeeDescriptions", param.get("WantToSeeDescriptions"));
		}
		
		content.put("GetTRBCSchemaFullHierarchy_Request_1", GetTRBCSchemaFullHierarchy_Request_1);
		String result = HttpRequestUtils.httpPostHead(
				getHttpUrl("GetTRBCSchemaFulHierarchy"), 
				content,
				getHeadMap());
		logger.info("获取TRBC架构完整层次结构接口返回："+result);
		if(StringUtil.isNotNullOrEmpty(result)){
			JSONObject data = JSONObject.fromObject(result);
			if(data.has("Fault")){
				String error = data.getJSONObject("Fault").getJSONObject("Reason").getJSONObject("Text").getString("Value");
				logger.error("获取TRBC架构完整层次结构接口失败："+error);
				return ResultUtil.newError("获取TRBC架构完整层次结构接口失败："+error).toMap();
			}
			
			//返回成功 处理数据
			if(data.has("GetTRBCSchemaFullHierarchy_Response_1")){
				
				return ResultUtil.newOk("获取TRBC架构完整层次结构成功！").toMap();
			}
		}
		
		return ResultUtil.newError("获取TRBC架构完整层次结构失败!").toMap();
	}
}
