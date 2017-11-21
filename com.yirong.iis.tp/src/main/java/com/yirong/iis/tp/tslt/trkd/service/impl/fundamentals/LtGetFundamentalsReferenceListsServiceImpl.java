package com.yirong.iis.tp.tslt.trkd.service.impl.fundamentals;

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
 * 获取基础知识参考列表
 * @author lijp
 *
 */
@SuppressWarnings("unchecked")
@Service("ltGetFundamentalsReferenceListsServiceImpl")
public class LtGetFundamentalsReferenceListsServiceImpl extends LtHttpService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Map<String, Object> exec(Map<String, Object> param) {
		JSONObject content = new JSONObject();
		JSONObject GetFundamentalsReferenceLists_Request_1 = new JSONObject();
		GetFundamentalsReferenceLists_Request_1.put("Category", param.get("Category").toString());
		if(param.containsKey("lang")){
			GetFundamentalsReferenceLists_Request_1.put("lang", param.get("lang").toString());
		}
		
		content.put("GetFundamentalsReferenceLists_Request_1", GetFundamentalsReferenceLists_Request_1);
		String result = HttpRequestUtils.httpPostHead(
				getHttpUrl("GetFundamentalsReferenceLists"), 
				content,
				getHeadMap());
		logger.info("获取基础知识参考列表接口返回："+result);
		if(StringUtil.isNotNullOrEmpty(result)){
			JSONObject data = JSONObject.fromObject(result);
			if(data.has("Fault")){
				String error = data.getJSONObject("Fault").getJSONObject("Reason").getJSONObject("Text").getString("Value");
				logger.error("获取基础知识参考列表接口失败："+error);
				return ResultUtil.newError("获取基础知识参考列表接口失败："+error).toMap();
			}
			
			//返回成功 处理数据
			if(data.has("GetFundamentalsReferenceLists_Response_1")){
				
				return ResultUtil.newOk("获取基础知识参考列表成功！").toMap();
			}
		}
		
		return ResultUtil.newError("获取基础知识参考列表失败!").toMap();
	}

}
