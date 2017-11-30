package com.yirong.iis.tp.tslt.et.util;

import com.reuters.rfa.common.Client;
import com.reuters.rfa.common.Event;
import com.reuters.rfa.common.EventQueue;
import com.reuters.rfa.common.EventSource;
import com.reuters.rfa.common.Handle;
import com.reuters.rfa.omm.OMMElementList;
import com.reuters.rfa.omm.OMMEncoder;
import com.reuters.rfa.omm.OMMMsg;
import com.reuters.rfa.omm.OMMPool;
import com.reuters.rfa.omm.OMMState;
import com.reuters.rfa.omm.OMMTypes;
import com.reuters.rfa.rdm.RDMMsgTypes;
import com.reuters.rfa.rdm.RDMUser;
import com.reuters.rfa.session.Session;
import com.reuters.rfa.session.omm.OMMConnectionEvent;
import com.reuters.rfa.session.omm.OMMConsumer;
import com.reuters.rfa.session.omm.OMMItemEvent;
import com.reuters.rfa.session.omm.OMMItemIntSpec;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.iis.tp.tslt.et.ief.LtEtIef;

/**
 * 功能描述：登录实体类
 *
 * @author 刘捷(liujie)
 *         <p>
 *         创建时间 ：2017年11月21日 下午7:39:58
 *         </p>
 *
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public class LoginClient implements Client {

	/**
	 * 日志操作类
	 */
	private final static Logger logger = LoggerFactory.getLogger(LoginClient.class);

	/**
	 * 配置信息
	 */
	private CommandLine commandLine;

	/**
	 * 登录操作类
	 */
	private Handle loginHandle;

	/**
	 * omm客户端
	 */
	private OMMConsumer ommConsumer;

	/**
	 * 事件队列
	 */
	private EventQueue eventQueue;

	/**
	 * 处理omm池
	 */
	private OMMPool ommPool;

	/**
	 * 功能描述：构造函数
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年11月21日 下午7:55:29
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public LoginClient(CommandLine commandLine) {
		// 配置文件
		this.commandLine = commandLine;
		// 处理事件队列
		eventQueue = EventQueue.create(commandLine.getOption("eventQueueName").toString());
		// 处理omm池
		ommPool = OMMPool.create();
		// 处理会话
		String sessionName = commandLine.getVariable("session");
		Session session = Session.acquire(sessionName);
		// 创建omm客户端
		ommConsumer = (OMMConsumer) session.createEventSource(EventSource.OMM_CONSUMER, "myOMMConsumer", true);
	}

	/**
	 * 功能描述：发送登录信息
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年11月21日 下午8:46:26
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @return
	 *
	 */
	public void sendRequest() {
		logger.info("================发送登录请求开始==================");
		OMMMsg ommmsg = encodeLoginReqMsg();
		OMMItemIntSpec ommItemIntSpec = new OMMItemIntSpec();
		ommItemIntSpec.setMsg(ommmsg);
		loginHandle = ommConsumer.registerClient(eventQueue, ommItemIntSpec, this, null);
		logger.info("================发送登录请求结束==================");
	}

	/**
	 * 功能描述：将请求信息进行编码
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年11月21日 下午8:43:53
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @return
	 *
	 */
	private OMMMsg encodeLoginReqMsg() {
		OMMEncoder encoder = ommPool.acquireEncoder();
		encoder.initialize(OMMTypes.MSG, 500);
		OMMMsg msg = ommPool.acquireMsg();
		msg.setMsgType(OMMMsg.MsgType.REQUEST);
		msg.setMsgModelType(RDMMsgTypes.LOGIN);
		msg.setIndicationFlags(OMMMsg.Indication.REFRESH);
		msg.setAttribInfo(null, commandLine.getVariable("user"), RDMUser.NameType.USER_NAME);
		encoder.encodeMsgInit(msg, OMMTypes.ELEMENT_LIST, OMMTypes.NO_DATA);
		encoder.encodeElementListInit(OMMElementList.HAS_STANDARD_DATA, (short) 0, (short) 0);
		encoder.encodeElementEntryInit(RDMUser.Attrib.ApplicationId, OMMTypes.ASCII_STRING);
		encoder.encodeString(commandLine.getVariable("application"), OMMTypes.ASCII_STRING);
		encoder.encodeElementEntryInit(RDMUser.Attrib.Position, OMMTypes.ASCII_STRING);
		encoder.encodeString(commandLine.getVariable("position"), OMMTypes.ASCII_STRING);
		encoder.encodeElementEntryInit(RDMUser.Attrib.Role, OMMTypes.UINT);
		encoder.encodeUInt((long) RDMUser.Role.CONSUMER);
		encoder.encodeAggregateComplete();
		OMMMsg encMsg = (OMMMsg) encoder.getEncodedObject();
		ommPool.releaseMsg(msg);
		return encMsg;
	}

	/**
	 * 功能描述：处理线程信息
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年11月28日 下午5:51:15
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 *
	 */
	public void run() {
		while (true) {
			try {
				eventQueue.dispatch(1000);
				Thread.sleep(1000);
			} catch (Exception e) {
				logger.error("处理异常", e);
			}
		}
	}

	/**
	 * 功能描述：进程事件
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年11月21日 下午7:41:37
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @param arg0
	 *
	 */
	@Override
	public void processEvent(Event event) {
		/** 验证 **/
		int type = event.getType();
		if (type == Event.COMPLETION_EVENT) {// 完成
			logger.info("事件已完成");
			return;
		}
		if (type == Event.OMM_CONNECTION_EVENT) {// 连接
			OMMConnectionEvent connectionEvent = ((OMMConnectionEvent) event);
			logger.info("Name: " + connectionEvent.getConnectionName());
			logger.info("Status: " + connectionEvent.getConnectionStatus().toString());
			logger.info("Host: " + connectionEvent.getConnectedHostName());
			logger.info("Port: " + connectionEvent.getConnectedPort());
			logger.info("ComponentVersion: " + connectionEvent.getConnectedComponentVersion());
			return;
		}
		logger.info("收到登录响应");
		if (type != Event.OMM_ITEM_EVENT) {// 非项目事件
			logger.error("非项目事件类型");
			return;
		}
		/** 处理业务 **/
		OMMItemEvent ie = (OMMItemEvent) event;
		OMMMsg respMsg = ie.getMsg();
		if (respMsg.getMsgModelType() != RDMMsgTypes.LOGIN) {
			logger.error("非正常登录状态");
			return;
		}

		if (respMsg.isFinal()) {
			logger.info("收到登录返回消息（未知成功与否）");
			return;
		}

		if (respMsg.has(OMMMsg.HAS_STATE) && (respMsg.getState().getStreamState() == OMMState.Stream.OPEN)
				&& (respMsg.getState().getDataState() == OMMState.Data.OK)) {
			logger.info("收到登录成功信息");
			LtEtIef.runData();// 开始获取数据
		} else {
			logger.error("登录失败，提示:" + OMMMsg.MsgType.toString(respMsg.getMsgType()));
		}

	}

	/**
	 * 功能描述：关闭请求
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年11月22日 上午9:12:00
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 *
	 */
	public void closeRequest() {
		if (null != loginHandle) {
			ommConsumer.unregisterClient(loginHandle);
			loginHandle = null;
		}
	}

	public Handle getLoginHandle() {
		return loginHandle;
	}

	public OMMConsumer getOmmConsumer() {
		return ommConsumer;
	}

}
