package com.yirong.iis.tp.tslt.et.util;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

import com.reuters.rfa.common.Client;
import com.reuters.rfa.common.Event;
import com.reuters.rfa.common.Handle;
import com.reuters.rfa.common.PublisherPrincipalIdentity;
import com.reuters.rfa.dictionary.FidDef;
import com.reuters.rfa.omm.OMMAttribInfo;
import com.reuters.rfa.omm.OMMData;
import com.reuters.rfa.omm.OMMDataBuffer;
import com.reuters.rfa.omm.OMMEntry;
import com.reuters.rfa.omm.OMMFieldEntry;
import com.reuters.rfa.omm.OMMIterable;
import com.reuters.rfa.omm.OMMMsg;
import com.reuters.rfa.omm.OMMPool;
import com.reuters.rfa.omm.OMMPriority;
import com.reuters.rfa.omm.OMMTypes;
import com.reuters.rfa.rdm.RDMDictionary;
import com.reuters.rfa.rdm.RDMInstrument;
import com.reuters.rfa.rdm.RDMMsgTypes;
import com.reuters.rfa.rdm.RDMService;
import com.reuters.rfa.rdm.RDMUser;
import com.reuters.rfa.session.omm.OMMItemEvent;
import com.reuters.rfa.session.omm.OMMItemIntSpec;
import com.reuters.rfa.utility.HexDump;
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
	private final static Logger logger = LoggerFactory.getLogger(DataClient.class);

	/**
	 * 客户端操作类
	 */
	private StarterConsumer starterConsumer;

	/**
	 * 配置信息
	 */
	private CommandLine commandLine;

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
		commandLine = starterConsumer.getCommondLine();
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
	public void sendRequest() {
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
		dataHandles = new LinkedList<Handle>();
		while (iter.hasNext()) {
			String itemName = (String) iter.next();
			ommmsg.setAttribInfo(serviceName, itemName, RDMInstrument.NameType.RIC);
			ommItemIntSpec.setMsg(ommmsg);
			Handle itemHandle = starterConsumer.getOmmConsumer().registerClient(starterConsumer.getEventQueue(),
					ommItemIntSpec, this, null);
			dataHandles.add(itemHandle);
		}
		pool.releaseMsg(ommmsg);
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
		if (type != Event.OMM_ITEM_EVENT) {// 非项目事件
			logger.error("非项目事件类型");
			starterConsumer.stop();
			return;
		}
		/** 处理业务 **/
		OMMItemEvent ie = (OMMItemEvent) event;
		OMMMsg respMsg = ie.getMsg();
		doMsg(respMsg);
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

	/**
	 * 功能描述：处理数据
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年11月22日 上午10:46:21
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @param respMsg
	 *
	 */
	private void doMsg(OMMMsg msg) {
		/** 提示相关信息 **/
		logger.info("Msg Type: " + OMMMsg.MsgType.toString(msg.getMsgType()));
		logger.info("Msg Model Type: " + RDMMsgTypes.toString(msg.getMsgModelType()));
		logger.info("Indication Flags: " + OMMMsg.Indication.indicationString(msg));
		logger.info("Hint Flags: " + hintString(msg));
		if (msg.has(OMMMsg.HAS_STATE)) {
			logger.info("State: " + msg.getState());
		}
		if (msg.has(OMMMsg.HAS_PRIORITY)) {
			OMMPriority p = msg.getPriority();
			if (p != null) {
				logger.info("Priority: " + p.getPriorityClass() + "," + p.getCount());
			} else {
				logger.info("Priority: Error flag recieved but there is not priority present");
			}

		}
		if (msg.has(OMMMsg.HAS_QOS)) {
			logger.info("Qos: " + msg.getQos());
		}
		if (msg.has(OMMMsg.HAS_QOS_REQ)) {
			logger.info("QosReq: " + msg.getQosReq());
		}
		if (msg.has(OMMMsg.HAS_ITEM_GROUP)) {
			logger.info("Group: " + msg.getItemGroup());
		}
		if (msg.has(OMMMsg.HAS_PERMISSION_DATA)) {
			byte[] permdata = msg.getPermissionData();
			logger.info("PermissionData: " + HexDump.toHexString(permdata, false));
			logger.info(" ( " + HexDump.formatHexString(permdata) + " ) ");
		}
		if (msg.has(OMMMsg.HAS_SEQ_NUM)) {
			logger.info("SeqNum: " + msg.getSeqNum());
		}
		if (msg.has(OMMMsg.HAS_CONFLATION_INFO)) {
			logger.info("Conflation Count: " + msg.getConflationCount());
			logger.info("Conflation Time: " + msg.getConflationTime());
		}
		if (msg.has(OMMMsg.HAS_RESP_TYPE_NUM)) {
			logger.info("RespTypeNum: " + msg.getRespTypeNum());
			if (msg.getMsgType() == OMMMsg.MsgType.REFRESH_RESP) {
				logger.info(" (" + OMMMsg.RespType.toString(msg.getRespTypeNum()) + ")");
			} else {
				if ((msg.getMsgModelType() >= RDMMsgTypes.MARKET_PRICE)
						&& (msg.getMsgModelType() <= RDMMsgTypes.HISTORY)) {
					logger.info(" (" + RDMInstrument.Update.toString(msg.getRespTypeNum()) + ")");
				}
			}
		}
		if (msg.has(OMMMsg.HAS_ID)) {
			logger.info("Id: " + msg.getId());
		}

		if ((msg.has(OMMMsg.HAS_PUBLISHER_INFO)) || (msg.getMsgType() == OMMMsg.MsgType.POST)) {
			PublisherPrincipalIdentity pi = (PublisherPrincipalIdentity) msg.getPrincipalIdentity();
			if (pi != null) {
				logger.info("Publisher Address: 0x" + Long.toHexString(pi.getPublisherAddress()));
				logger.info("Publisher Id: " + pi.getPublisherId());
			}
		}
		if (msg.has(OMMMsg.HAS_USER_RIGHTS)) {
			logger.info("User Rights Mask: " + OMMMsg.UserRights.userRightsString(msg.getUserRightsMask()));
		}
		if (msg.has(OMMMsg.HAS_ATTRIB_INFO)) {
			OMMAttribInfo ai = msg.getAttribInfo();
			if (ai.has(OMMAttribInfo.HAS_SERVICE_NAME)) {
				logger.info("ServiceName: " + ai.getServiceName());
			}
			if (ai.has(OMMAttribInfo.HAS_SERVICE_ID)) {
				logger.info("ServiceId: " + ai.getServiceID());
			}
			if (ai.has(OMMAttribInfo.HAS_NAME)) {
				logger.info("Name: " + ai.getName());
			}
			if (ai.has(OMMAttribInfo.HAS_NAME_TYPE)) {
				logger.info("NameType: " + ai.getNameType());
				if (msg.getMsgModelType() == RDMMsgTypes.LOGIN) {
					logger.info(" (" + RDMUser.NameType.toString(ai.getNameType()) + ")");
				} else if (RDMInstrument.isInstrumentMsgModelType(msg.getMsgModelType())) {
					logger.info(" (" + RDMInstrument.NameType.toString(ai.getNameType()) + ")");
				}
			}
			if (ai.has(OMMAttribInfo.HAS_FILTER)) {
				logger.info("Filter: " + ai.getFilter());
				if (msg.getMsgModelType() == RDMMsgTypes.DIRECTORY) {
					logger.info(" (" + RDMService.Filter.toString(ai.getFilter()) + ")");
				} else if (msg.getMsgModelType() == RDMMsgTypes.DICTIONARY) {
					logger.info(" (" + RDMDictionary.Filter.toString(ai.getFilter()) + ")");
				}
			}
			if (ai.has(OMMAttribInfo.HAS_ID)) {
				logger.info("ID: " + ai.getId());
			}
			if (ai.has(OMMAttribInfo.HAS_ATTRIB)) {
				logger.info("Attrib");
				doData(ai.getAttrib());
			}
		}
		if (msg.getDataType() != OMMTypes.NO_DATA) {
			logger.info(msg.getPayload().getEncodedLength() + " bytes");
			doData(msg.getPayload());
		} else {
			logger.info("None");
		}
	}

	/**
	 * 功能描述：处理数据
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年11月22日 上午10:54:39
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @param data
	 *
	 */
	@SuppressWarnings("rawtypes")
	private void doData(OMMData data) {
		short type = data.getType();
		System.out.println("==========" + type);
		if (data.isBlank()) {
			logger.info("无数据");
		} else if (OMMTypes.isAggregate(type)) {// 获取到字段ID及字段名称
			for (Iterator iter = ((OMMIterable) data).iterator(); iter.hasNext();) {
				OMMEntry entry = (OMMEntry) iter.next();
				switch (entry.getType()) {
				case OMMTypes.FIELD_ENTRY:// 字段信息
					OMMFieldEntry fe = (OMMFieldEntry) entry;
					FidDef fiddef = starterConsumer.getDictionary().getFidDef(fe.getFieldId());
					if (null == fiddef) {
						logger.error("无字段信息");
					} else {
						System.out.println(fiddef.getFieldId());
						System.out.println(fiddef.getName());
					}
					break;
				default:// 其他
					logger.error("非字段信息");
					break;
				}
			}
		} else if ((type == OMMTypes.RMTES_STRING) && ((OMMDataBuffer) data).hasPartialUpdates()) {
			logger.error("数据异常,type:" + data.getType());
		} else if (type == OMMTypes.ANSI_PAGE) {
			logger.error("数据异常,type:" + data.getType());
		} else if (type == OMMTypes.BUFFER || data.getType() == OMMTypes.OPAQUE_BUFFER) {
			logger.error("数据异常,type:" + data.getType());
		} else if (type == OMMTypes.MSG) {
			logger.error("数据异常,type:" + data.getType());
		} else {// 获取到数据值
			System.out.println(data);
		}
	}

	/**
	 * 功能描述：处理hint字符串
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年11月22日 上午10:49:43
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @param msg
	 * @return
	 *
	 */
	private static final String hintString(OMMMsg msg) {
		StringBuilder buf = new StringBuilder(60);
		boolean bAppend = true;
		if (msg.has(OMMMsg.HAS_ATTRIB_INFO)) {
			bAppend = append(buf, "HAS_ATTRIB_INFO", bAppend);
		}
		if (msg.has(OMMMsg.HAS_CONFLATION_INFO)) {
			bAppend = append(buf, "HAS_CONFLATION_INFO", bAppend);
		}
		if (msg.has(OMMMsg.HAS_HEADER)) {
			bAppend = append(buf, "HAS_HEADER", bAppend);
		}
		if (msg.has(OMMMsg.HAS_ITEM_GROUP)) {
			bAppend = append(buf, "HAS_ITEM_GROUP", bAppend);
		}
		if (msg.has(OMMMsg.HAS_PERMISSION_DATA)) {
			bAppend = append(buf, "HAS_PERMISSION_DATA", bAppend);
		}
		if (msg.has(OMMMsg.HAS_PRIORITY)) {
			bAppend = append(buf, "HAS_PRIORITY", bAppend);
		}
		if (msg.has(OMMMsg.HAS_QOS)) {
			bAppend = append(buf, "HAS_QOS", bAppend);
		}
		if (msg.has(OMMMsg.HAS_QOS_REQ)) {
			bAppend = append(buf, "HAS_QOS_REQ", bAppend);
		}
		if (msg.has(OMMMsg.HAS_RESP_TYPE_NUM)) {
			bAppend = append(buf, "HAS_RESP_TYPE_NUM", bAppend);
		}
		if (msg.has(OMMMsg.HAS_SEQ_NUM)) {
			bAppend = append(buf, "HAS_SEQ_NUM", bAppend);
		}
		if (msg.has(OMMMsg.HAS_ID)) {
			bAppend = append(buf, "HAS_ID", bAppend);
		}
		if (msg.has(OMMMsg.HAS_PUBLISHER_INFO)) {
			bAppend = append(buf, "HAS_PUBLISHER_INFO", bAppend);
		}
		if (msg.has(OMMMsg.HAS_STATE)) {
			bAppend = append(buf, "HAS_STATE", bAppend);
		}
		if (msg.has(OMMMsg.HAS_USER_RIGHTS)) {
			bAppend = append(buf, "HAS_USER_RIGHTS", bAppend);
		}

		return buf.toString();
	}

	/**
	 * 功能描述：处理字符串
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年11月22日 上午10:49:18
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @param buf
	 * @param str
	 * @param first
	 * @return
	 *
	 */
	private static boolean append(StringBuilder buf, String str, boolean first) {
		if (!first) {
			buf.append(" | ");
			first = false;
		} else {
			first = false;
		}
		buf.append(str);
		return first;
	}

}
