package com.yirong.iis.tp.tslt.trkd.service.impl;

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
 * 查找行业分类架构
 * @author lijp
 *
 */
@SuppressWarnings("unchecked")
@Service("ltFindIndustryClassificationSchemaServiceImpl")
public class LtFindIndustryClassificationSchemaServiceImpl extends LtHttpService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Map<String, Object> exec(Map<String, Object> param) {
		JSONObject content = new JSONObject();
		JSONObject FindIndustryClassificationSchema_Request_1 = new JSONObject();
		FindIndustryClassificationSchema_Request_1.put("TaxonomyCode", param.get("TaxonomyCode").toString());
		FindIndustryClassificationSchema_Request_1.put("IndustryCode", param.get("IndustryCode").toString());
		if(param.containsKey("LanguageCode")){
			FindIndustryClassificationSchema_Request_1.put("LanguageCode", param.get("LanguageCode").toString());
		}
		
		if(param.containsKey("WantToSeeCompanies")){
			FindIndustryClassificationSchema_Request_1.put("WantToSeeCompanies", param.get("WantToSeeCompanies"));
		}
		
		if(param.containsKey("WantToSeeDescriptions")){
			FindIndustryClassificationSchema_Request_1.put("WantToSeeDescriptions", param.get("WantToSeeDescriptions"));
		}
		
		content.put("FindIndustryClassificationSchema_Request_1", FindIndustryClassificationSchema_Request_1);
		String result = HttpRequestUtils.httpPostHead(
				getHttpUrl("FindIndustryClassificationSchema"), 
				content,
				getHeadMap());
		logger.info("查找行业分类架构接口返回："+result);
		if(StringUtil.isNotNullOrEmpty(result)){
			JSONObject data = JSONObject.fromObject(result);
			if(data.has("Fault")){
				String error = data.getJSONObject("Fault").getJSONObject("Reason").getJSONObject("Text").getString("Value");
				logger.error("查找行业分类架构接口失败："+error);
				return ResultUtil.newError("查找行业分类架构接口失败："+error).toMap();
			}
			
			//返回成功 处理数据
			if(data.has("FindIndustryClassificationSchema_Response_1")){
				
				return ResultUtil.newOk("查找行业分类架构成功！").toMap();
			}
		}
		
		return ResultUtil.newError("查找行业分类架构失败!").toMap();
	}

}
