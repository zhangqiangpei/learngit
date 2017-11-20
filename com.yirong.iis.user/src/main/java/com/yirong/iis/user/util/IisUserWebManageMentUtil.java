package com.yirong.iis.user.util;

import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.microservices.client.MsClient;
import com.yirong.microservices.client.entity.MsRpcResult;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.PropertyUtilsBean;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述：资源整合工具类
 *
 * @author 刘捷(liujie)
 *         <p>
 *         创建时间 ：2017年9月4日 下午2:15:53
 *         </p>
 *
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public class IisUserWebManageMentUtil {

	/**
	 * 日志操作类
	 */
	private final static Logger logger = LoggerFactory.getLogger(IisUserWebManageMentUtil.class);

	/**
	 * 功能描述：调用微服务
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年9月4日 下午5:13:06
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @param serviceName
	 * @param interPath
	 * @param paramMap
	 * @return
	 *
	 */
	public static String msInvoke(String serviceName, String interPath, Map<String, Object> contextMap) {
		return msInvoke(serviceName, interPath, contextMap, null);
	}

	/**
	 * 功能描述：调用微服务
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年9月27日 下午3:49:20
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @param serviceName
	 * @param interPath
	 * @param contextMap
	 * @param tokenId
	 * @return
	 *
	 */
	public static String msInvoke(String serviceName, String interPath, Map<String, Object> contextMap,
			String tokenId) {
		// 处理参数
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("_ms_http_req_type", "POST");
		paramMap.put("sessionId", tokenId);
		paramMap.put("context", contextMap);
		MsRpcResult pmResult = MsClient.invoke(serviceName, interPath, paramMap);
		if (null != pmResult && pmResult.isSuc()) {// 成功
			JSONObject result = JSONObject.fromObject(pmResult.getRespStr());
			String code = result.getString("code");
			if ("0".equals(code)) {// 成功
				return result.getJSONObject("data").toString();
			} else {// 失败
				logger.error("微服务请求返回异常，服务名:{0}，方法名:{1}", serviceName, interPath);
				return null;
			}
		} else {// 异常
			logger.error("微服务请求异常，服务名:{0}，方法名:{1}", serviceName, interPath);
			return null;
		}
	}

	/**
	 * 功能描述：将对象转成map
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年9月25日 下午3:46:48
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @param obj
	 * @return
	 *
	 */
	public static Map<String, Object> beanToMap(Object obj) {
		Map<String, Object> params = new HashMap<String, Object>(0);
		try {
			PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
			PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);
			for (int i = 0; i < descriptors.length; i++) {
				String name = descriptors[i].getName();
				if (!"class".equals(name)) {
					params.put(name, propertyUtilsBean.getNestedProperty(obj, name));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}

}
