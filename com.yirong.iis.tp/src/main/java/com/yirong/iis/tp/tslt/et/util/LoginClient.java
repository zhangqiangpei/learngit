package com.yirong.iis.tp.tslt.et.util;

import com.reuters.rfa.common.Client;
import com.reuters.rfa.common.Event;
import com.reuters.rfa.common.Handle;
import com.reuters.rfa.omm.OMMElementList;
import com.reuters.rfa.omm.OMMEncoder;
import com.reuters.rfa.omm.OMMMsg;
import com.reuters.rfa.omm.OMMPool;
import com.reuters.rfa.omm.OMMState;
import com.reuters.rfa.omm.OMMTypes;
import com.reuters.rfa.rdm.RDMMsgTypes;
import com.reuters.rfa.rdm.RDMUser;
import com.reuters.rfa.session.omm.OMMConnectionEvent;
import com.reuters.rfa.session.omm.OMMItemEvent;
import com.reuters.rfa.session.omm.OMMItemIntSpec;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;

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
	 * 客户端操作类
	 */
	private StarterConsumer starterConsumer;

	/**
	 * 配置信息
	 */
	private CommandLine commandLine = starterConsumer.getCommondLine();

	/**
	 * 登录操作类
	 */
	private Handle loginHandle;

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
	public LoginClient(StarterConsumer starterConsumer) {
		this.starterConsumer = starterConsumer;
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
	public boolean sendLogin() {
		OMMMsg ommmsg = encodeLoginReqMsg();
		OMMItemIntSpec ommItemIntSpec = new OMMItemIntSpec();
		ommItemIntSpec.setMsg(ommmsg);
		loginHandle = starterConsumer.getOmmConsumer().registerClient(starterConsumer.getEventQueue(), ommItemIntSpec,
				this, null);
		return true;
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
		OMMEncoder encoder = starterConsumer.getOmmEncoder();
		OMMPool pool = starterConsumer.getOmmPool();
		encoder.initialize(OMMTypes.MSG, 500);
		OMMMsg msg = pool.acquireMsg();
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
		pool.releaseMsg(msg);
		return encMsg;
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
		if (type == Event.OMM_ITEM_EVENT) {// 非项目事件
			logger.error("非项目事件类型");
			starterConsumer.stop();
			return;
		}

		OMMItemEvent ie = (OMMItemEvent) event;
		OMMMsg respMsg = ie.getMsg();
		if (respMsg.getMsgModelType() != RDMMsgTypes.LOGIN) {
			logger.error("非正常登录状态");
			starterConsumer.stop();
			return;
		}

		if (respMsg.isFinal()) {
			logger.error("收到接收返回消息成功");
			return;
		}

		if (respMsg.has(OMMMsg.HAS_STATE) && (respMsg.getState().getStreamState() == OMMState.Stream.OPEN)
				&& (respMsg.getState().getDataState() == OMMState.Data.OK)) {
			logger.error("收到登录成功信息");
			starterConsumer.send();
			;
		} else {
			logger.error("登录失败，提示:" + OMMMsg.MsgType.toString(respMsg.getMsgType()));
		}

	}

}
