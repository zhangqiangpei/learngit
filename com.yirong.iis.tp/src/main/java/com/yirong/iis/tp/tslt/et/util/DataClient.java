package com.yirong.iis.tp.tslt.et.util;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

import com.reuters.rfa.common.Client;
import com.reuters.rfa.common.Event;
import com.reuters.rfa.common.Handle;
import com.reuters.rfa.omm.OMMMsg;
import com.reuters.rfa.omm.OMMPool;
import com.reuters.rfa.rdm.RDMInstrument;
import com.reuters.rfa.rdm.RDMMsgTypes;
import com.reuters.rfa.session.omm.OMMItemEvent;
import com.reuters.rfa.session.omm.OMMItemIntSpec;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;

/**
 * 功能描述：数据客户端
 *
 * @author 刘捷(liujie)
 *         <p>
 *         创建时间 ：2017年11月21日 下午8:28:43
 *         </p>
 *
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public class DataClient implements Client {

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
	 * 处理操作类集合
	 */
	private LinkedList<Handle> dataHandles;

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
	public DataClient(StarterConsumer starterConsumer) {
		this.starterConsumer = starterConsumer;
	}

	/**
	 * 功能描述：发送消息
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年11月22日 上午8:52:29
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @return
	 *
	 */
	public boolean sendRequest() {
		/** 获取参数信息 **/
		String serviceName = commandLine.getVariable("serviceName");
		String itemNames = commandLine.getVariable("itemName");
		String mmt = commandLine.getVariable("mmt");
		short capability = RDMMsgTypes.msgModelType(mmt);
		// 多个ric code用逗号隔开
		StringTokenizer st = new StringTokenizer(itemNames, ",");
		LinkedList<String> itemNamesList = new LinkedList<String>();
		while (st.hasMoreTokens()) {
			itemNamesList.add(st.nextToken().trim());
		}
		Iterator<String> iter = itemNamesList.iterator();
		OMMItemIntSpec ommItemIntSpec = new OMMItemIntSpec();
		/** 处理请求参数 **/
		OMMPool pool = starterConsumer.getOmmPool();
		OMMMsg ommmsg = pool.acquireMsg();
		ommmsg.setMsgType(OMMMsg.MsgType.REQUEST);
		ommmsg.setMsgModelType(capability);
		ommmsg.setPriority((byte) 1, 1);
		Handle loginHandle = starterConsumer.getLoginClient().getLoginHandle();
		if (null != loginHandle) {
			ommmsg.setAssociatedMetaInfo(loginHandle);
		}
		if (Boolean.valueOf(commandLine.getVariable("attribInfoInUpdates"))) {
			ommmsg.setIndicationFlags(OMMMsg.Indication.REFRESH | OMMMsg.Indication.ATTRIB_INFO_IN_UPDATES);
		} else {
			ommmsg.setIndicationFlags(OMMMsg.Indication.REFRESH);
		}
		while (iter.hasNext()) {
			String itemName = (String) iter.next();
			ommmsg.setAttribInfo(serviceName, itemName, RDMInstrument.NameType.RIC);
			ommItemIntSpec.setMsg(ommmsg);
			Handle itemHandle = starterConsumer.getOmmConsumer().registerClient(starterConsumer.getEventQueue(),
					ommItemIntSpec, this, null);
			dataHandles.add(itemHandle);
		}
		pool.releaseMsg(ommmsg);
		return true;
	}

	/**
	 * 功能描述：进程事件
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年11月21日 下午8:29:12
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
		if (type == Event.COMPLETION_EVENT) {
			logger.info("事件已完成");
			return;
		}
		logger.info("收到事件信息");
		if (type == Event.OMM_ITEM_EVENT) {// 非项目事件
			logger.error("非项目事件类型");
			starterConsumer.stop();
			return;
		}
		/** 处理业务 **/
		OMMItemEvent ie = (OMMItemEvent) event;
		OMMMsg respMsg = ie.getMsg();
		System.out.println(respMsg);
	}

	/**
	 * 功能描述：关闭请求
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年11月22日 上午9:10:16
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 *
	 */
	public void closeRequest() {
		Iterator<Handle> iter = dataHandles.iterator();
		Handle dataHandle = null;
		while (iter.hasNext()) {
			dataHandle = (Handle) iter.next();
			starterConsumer.getOmmConsumer().unregisterClient(dataHandle);
		}
		dataHandles.clear();
	}

}
