package com.yirong.iis.tp.tslt.et.util;

import com.reuters.rfa.common.Context;
import com.reuters.rfa.common.EventQueue;
import com.reuters.rfa.common.EventSource;
import com.reuters.rfa.common.Handle;
import com.reuters.rfa.dictionary.FieldDictionary;
import com.reuters.rfa.omm.OMMEncoder;
import com.reuters.rfa.omm.OMMPool;
import com.reuters.rfa.omm.OMMTypes;
import com.reuters.rfa.session.Session;
import com.reuters.rfa.session.omm.OMMConnectionIntSpec;
import com.reuters.rfa.session.omm.OMMConsumer;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;

/**
 * 功能描述：客户端工具类
 *
 * @author 刘捷(liujie)
 *         <p>
 *         创建时间 ：2017年11月21日 下午3:44:21
 *         </p>
 *
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public class StarterConsumer {

	/**
	 * 日志操作类
	 */
	private final static Logger logger = LoggerFactory.getLogger(StarterConsumer.class);

	/**
	 * 参数实体类
	 */
	private CommandLine commondLine;

	/**
	 * 会话信息
	 */
	private Session session;

	/**
	 * 事件队列
	 */
	private EventQueue eventQueue;

	/**
	 * OMM池
	 */
	private OMMPool ommPool;

	/**
	 * OMM编码器
	 */
	private OMMEncoder ommEncoder;

	/**
	 * 登录客户端
	 */
	private LoginClient loginClient;

	/**
	 * 数据客户端
	 */
	private DataClient dataClient;

	/**
	 * omm客户端
	 */
	private OMMConsumer ommConsumer;

	/**
	 * 链接操作类
	 */
	private Handle connIntSpecHandle;

	/**
	 * 字段字典操作类
	 */
	private FieldDictionary dictionary;

	/**
	 * 是否继续获取数据
	 */
	private boolean Continue = true;

	/**
	 * 功能描述：构造函数
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年11月22日 上午9:33:09
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public StarterConsumer(CommandLine commondLine) {
		this.commondLine = commondLine;
	}

	/**
	 * 功能描述：初始化
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年11月21日 下午3:44:39
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @return
	 *
	 */
	public boolean init() {
		logger.info("==================路透elektron产品初始化开始======================= ");
		Context.initialize();
		// 处理会话
		String sessionName = commondLine.getVariable("session");
		session = Session.acquire(sessionName);
		if (null == session) {
			logger.error("session创建异常！");
			Context.uninitialize();
			return false;
		}
		logger.info("RFA Version: " + Context.getRFAVersionInfo().getProductVersion());
		// 处理事件队列
		eventQueue = EventQueue.create("myEventQueue");
		// 处理omm池
		ommPool = OMMPool.create();
		// 创建omm编码器
		ommEncoder = ommPool.acquireEncoder();
		ommEncoder.initialize(OMMTypes.MSG, 5000);
		// 初始化登录客户端
		loginClient = new LoginClient(this);
		// 初始化数据客户端
		dataClient = new DataClient(this);
		// 创建omm客户端
		ommConsumer = (OMMConsumer) session.createEventSource(EventSource.OMM_CONSUMER, "myOMMConsumer", true);
		// 创建omm链接
		OMMConnectionIntSpec connIntSpec = new OMMConnectionIntSpec();
		connIntSpecHandle = ommConsumer.registerClient(eventQueue, connIntSpec, loginClient, null);
		// 初始化代码表文件
		dictionary = FieldDictionary.create();
		FieldDictionary.readRDMFieldDictionary(dictionary, commondLine.getVariable("rdmFieldDictionary"));
		FieldDictionary.readEnumTypeDef(dictionary, commondLine.getVariable("enumType"));
		// 发送登录请求
		loginClient.sendRequest();
		logger.info("==================路透elektron产品初始化结束（成功）======================= ");
		return true;
	}

	/**
	 * 功能描述：线程运行
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年11月21日 下午3:45:04
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @return
	 *
	 */
	public void run() {
		while (Continue) {
			try {
				eventQueue.dispatch(1000);
			} catch (Exception e) {
				logger.error("调度异常", e);
			}
		}
	}

	/**
	 * 功能描述：线程停止
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年11月21日 下午7:53:06
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @param type
	 *
	 */
	public void stop() {
		logger.info("==============路透elektron产品关闭线程开始=================");
		Continue = false;
		if (null != connIntSpecHandle) {
			ommConsumer.unregisterClient(connIntSpecHandle);
		}
		if (null != dataClient) {
			dataClient.closeRequest();
		}
		if (null != loginClient) {
			loginClient.closeRequest();
		}
		if (null != ommConsumer) {
			ommConsumer.destroy();
		}
		eventQueue.deactivate();
		session.release();
		Context.uninitialize();
		logger.info("==============路透elektron产品关闭线程结束=================");
	}

	public CommandLine getCommondLine() {
		return commondLine;
	}

	public OMMPool getOmmPool() {
		return ommPool;
	}

	public OMMEncoder getOmmEncoder() {
		return ommEncoder;
	}

	public EventQueue getEventQueue() {
		return eventQueue;
	}

	public OMMConsumer getOmmConsumer() {
		return ommConsumer;
	}

	public LoginClient getLoginClient() {
		return loginClient;
	}

	public DataClient getDataClient() {
		return dataClient;
	}

	public FieldDictionary getDictionary() {
		return dictionary;
	}
}
