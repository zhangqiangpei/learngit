package com.yirong.iis.user.service.impl;

import com.yirong.commons.akclient.Eif.AkClient;
import com.yirong.commons.cache.eif.RedisCacheEif;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.commons.util.datatype.StringUtil;
import com.yirong.iis.user.service.KmUserWebAwakenService;
import com.yirong.iis.user.userentity.KmUserAwakenAddFileUserEntity;
import com.yirong.iis.user.util.IisUserWebManageMentUtil;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述：知识管理平台调用awaken平台service实现类
 *
 * @author 刘捷(liujie)
 *         <p>
 *         创建时间 ：2017年9月7日 上午10:21:32
 *         </p>
 *
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@Service("KmUserWebAwakenServiceImpl")
public class KmUserWebAwakenServiceImpl implements KmUserWebAwakenService {

	/**
	 * 日志操作类
	 */
	private Logger logger = LoggerFactory.getLogger(KmUserWebAwakenServiceImpl.class);

	/**
	 * 功能描述：上传文件
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年9月25日 下午3:29:45
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @param ue
	 * @return
	 *
	 */
	@Override
	public String httpUploadFile(KmUserAwakenAddFileUserEntity ue, File file) {
		// 拼装参数
		Map<String, Object> param = IisUserWebManageMentUtil.beanToMap(ue);
		// 调用接口
		Map<String, Object> resultMap = AkClient.addFile(param, file);
		if (!check(resultMap)) {// 验证未通过
			return null;
		}
		String code = resultMap.get("code").toString();
		String msg = resultMap.get("msg").toString();
		if ("0".equals(code)) { // 成功
			return msg;
		} else {
			logger.error("服务端返回失败，失败消息{0}", msg);
			return null;
		}
	}

	/**
	 * 功能描述：下载文件
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年9月26日 上午10:13:27
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @param ue
	 * @return
	 *
	 */
	@Override
	public Object[] httpDownFile(String eosId, String tokenId) {
		// 拼装参数
		Map<String, Object> param = operGetParam(tokenId);
		param.put("id", eosId);
		// 调用接口
		Map<String, Object> resultMap = AkClient.downloadFile(param);
		if (!check(resultMap)) {// 验证未通过
			return null;
		}
		String code = resultMap.get("code").toString();
		Object msg = resultMap.get("msg");
		if ("0".equals(code)) { // 成功
			Object[] result = new Object[2];
			result[0] = (InputStream) msg;
			result[1] = resultMap.get("fileName").toString();
			return result;
		} else {
			logger.error("服务端返回失败，失败消息：{0}", msg.toString());
			return null;
		}
	}

	/**
	 * 功能描述：统一验证
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年9月26日 上午10:22:52
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @param resultMap
	 * @return
	 *
	 */
	private boolean check(Map<String, Object> resultMap) {
		if (null == resultMap || resultMap.size() == 0) {
			logger.error("请求异常，返回map为空");
			return false;
		}
		// 处理返回值
		Object codeObj = resultMap.get("code");
		if (null == codeObj) {
			logger.error("codeObj为空");
			return false;
		}
		return true;
	}

	/**
	 * 功能描述：获取操作人公用方法
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年9月27日 上午11:35:03
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @return
	 *
	 */
	private Map<String, Object> operGetParam(String tokenId) {
		if (StringUtil.isNullOrEmpty(tokenId)) {
			return null;
		}
		String userId = RedisCacheEif.hget(tokenId, "id");
		String userName = RedisCacheEif.hget(tokenId, "userDisplayName");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("operationtor", userId);
		map.put("operationtorName", userName);
		return map;
	}

}
