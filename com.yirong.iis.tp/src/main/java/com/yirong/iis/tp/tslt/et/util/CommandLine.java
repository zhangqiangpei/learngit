package com.yirong.iis.tp.tslt.et.util;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

import com.reuters.rfa.config.ConfigDb;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;

/**
 * 功能描述：命令行参数实体
 *
 * @author 刘捷(liujie)
 *         <p>
 *         创建时间 ：2017年11月21日 下午2:52:09
 *         </p>
 *
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public class CommandLine {

	/**
	 * 日志操作类
	 */
	private final static Logger logger = LoggerFactory.getLogger(CommandLine.class);

	/**
	 * 默认的选项配置
	 */
	private static Map<String, Object> defaultOptions = null;

	/**
	 * 选项配置
	 */
	private static Map<String, Object> options = null;

	/**
	 * 数据库配置
	 */
	private ConfigDb ConfigDb;

	// 处理默认配置
	static {
		defaultOptions = new HashMap<String, Object>();
		// enable debug tracing
		defaultOptions.put("debug", false);
		// Session name to use
		defaultOptions.put("session", "myNamespace::mySession");
		// service to request
		defaultOptions.put("serviceName", "DIRECT_FEED");
		// List of items to open separated by ','.
		defaultOptions.put("itemName", "TRI.N");
		// Message Model Type
		defaultOptions.put("mmt", "MARKET_PRICE");
		// Ask provider to send OMMAttribInfo with update and status messages
		defaultOptions.put("attribInfoInUpdates", false);
		// DACS username for login
		try {
			defaultOptions.put("user", System.getProperty("user.name"));
		} catch (Exception e) {
			logger.info("初始化用户名异常!", e);
			defaultOptions.put("user", "guest");
		}
		// DACS position for login
		try {
			defaultOptions.put("position",
					InetAddress.getLocalHost().getHostAddress() + "/" + InetAddress.getLocalHost().getHostName());
		} catch (Exception e) {
			logger.info("初始化IP信息异常!", e);
			defaultOptions.put("position", "1.1.1.1/net");
		}
		// DACS application ID for login
		defaultOptions.put("application", "256");
		// RDMFieldDictionary filename
		defaultOptions.put("rdmFieldDictionary", "/var/triarch/RDMFieldDictionary");
		// enumtype.def filename
		defaultOptions.put("enumType", "/var/triarch/enumtype.def");
		// How long application should run before exiting (in seconds)
		defaultOptions.put("runTime", 600);
	}

	/**
	 * 功能描述：构造函数
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年11月21日 下午3:05:33
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public CommandLine(String session, String serviceName, String user, String iteamName) {
		/** 初始化默认参数 **/
		options = new HashMap<String, Object>();
		for (String key : defaultOptions.keySet()) {
			Object obj = defaultOptions.get(key);
			options.put(key, obj);
		}
		/** 处理新参数 **/
		// 处理会话
		addVariable("session", session);
		// 处理服务名
		addVariable("serviceName", serviceName);
		// 处理编码名称（多个用,号隔开）
		addVariable("itemName", iteamName);
		// 处理用户
		addVariable("user", user);
	}

	/**
	 * 功能描述：添加变量
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年11月21日 下午3:17:10
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @param key
	 * @param value
	 *
	 */
	private void addVariable(String key, String value) {
		options.put(key, value);
		ConfigDb.addVariable(key, value);
	}

	/**
	 * 功能描述：获取变量
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年11月21日 下午3:25:48
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @param key
	 * @return
	 *
	 */
	public String getVariable(String key) {
		return ConfigDb.variable(null, key, options.get(key).toString());
	}
}
