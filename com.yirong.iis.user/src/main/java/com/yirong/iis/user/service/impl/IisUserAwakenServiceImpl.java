package com.yirong.iis.user.service.impl;

import com.yirong.commons.akclient.Eif.AkClient;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.iis.user.service.IisUserAwakenService;
import com.yirong.iis.user.userentity.IisUserAwakenFileUserEntity;
import com.yirong.iis.user.userentity.IisUserAwakenOperUserEntity;
import com.yirong.iis.user.util.IisUserWebManageMentUtil;
import org.springframework.stereotype.Service;

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
@Service("KmUserAwakenServiceImpl")
public class IisUserAwakenServiceImpl implements IisUserAwakenService {

	/**
	 * 日志操作类
	 */
	private Logger logger = LoggerFactory.getLogger(IisUserAwakenServiceImpl.class);

	/**
	 * 功能描述：删除文件
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年9月26日 上午10:21:05
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
	public boolean httpDeleteFile(IisUserAwakenOperUserEntity ue) {
		// 拼装参数
		Map<String, Object> param = IisUserWebManageMentUtil.beanToMap(ue);
		// 调用接口
		Map<String, Object> resultMap = AkClient.deleteFile(param);
		if (!check(resultMap)) {// 验证未通过
			return false;
		}
		String code = resultMap.get("code").toString();
		String msg = resultMap.get("msg").toString();
		if ("0".equals(code)) { // 成功
			return true;
		} else {
			logger.error("服务端返回失败，失败消息{0}", msg);
			return false;
		}
	}

	/**
	 * 功能描述：新增知识信息
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年10月9日 下午3:59:48
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
	public String httpAddInfo(IisUserAwakenFileUserEntity ue) {
		// 拼装参数
		Map<String, Object> param = IisUserWebManageMentUtil.beanToMap(ue);
		// 调用接口
		Map<String, Object> resultMap = AkClient.addFileInfo(param);
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
	 * 功能描述：删除知识信息
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年10月9日 下午4:00:51
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
	public boolean httpDeleteInfo(IisUserAwakenOperUserEntity ue) {
		// 拼装参数
		Map<String, Object> param = IisUserWebManageMentUtil.beanToMap(ue);
		// 调用接口
		Map<String, Object> resultMap = AkClient.deleteFileInfo(param);
		if (!check(resultMap)) {// 验证未通过
			return false;
		}
		String code = resultMap.get("code").toString();
		String msg = resultMap.get("msg").toString();
		if ("0".equals(code)) { // 成功
			return true;
		} else {
			logger.error("服务端返回失败，失败消息{0}", msg);
			return false;
		}
	}

	/**
	 * 功能描述：更新知识信息
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年10月9日 下午4:01:45
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
	public boolean httpUpdateInfo(IisUserAwakenFileUserEntity ue) {
		// 拼装参数
		Map<String, Object> param = IisUserWebManageMentUtil.beanToMap(ue);
		// 调用接口
		Map<String, Object> resultMap = AkClient.updateFileInfo(param);
		if (!check(resultMap)) {// 验证未通过
			return false;
		}
		String code = resultMap.get("code").toString();
		if ("0".equals(code)) { // 成功
			return true;
		} else {
			logger.error("服务端返回失败，失败消息{0}", resultMap.get("msg").toString());
			return false;
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

}
