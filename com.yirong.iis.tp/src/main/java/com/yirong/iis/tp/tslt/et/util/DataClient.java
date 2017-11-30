package com.yirong.iis.tp.tslt.et.util;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.reuters.rfa.ansipage.Page;
import com.reuters.rfa.ansipage.PageUpdate;
import com.reuters.rfa.dictionary.FidDef;
import com.reuters.rfa.omm.OMMData;
import com.reuters.rfa.omm.OMMDataBuffer;
import com.reuters.rfa.omm.OMMEntry;
import com.reuters.rfa.omm.OMMFieldEntry;
import com.reuters.rfa.omm.OMMFilterEntry;
import com.reuters.rfa.omm.OMMIterable;
import com.reuters.rfa.omm.OMMMap;
import com.reuters.rfa.omm.OMMMapEntry;
import com.reuters.rfa.omm.OMMSeries;
import com.reuters.rfa.omm.OMMTypes;
import com.reuters.rfa.omm.OMMVector;
import com.reuters.rfa.omm.OMMVectorEntry;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.commons.util.datatype.StringUtil;
import com.yirong.iis.tp.common.entity.LtEtCode;
import com.yirong.iis.tp.tslt.et.ief.LtEtIef;
import com.yirong.iis.tp.tslt.et.userentity.LtEtDataUserEntity;

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
public class DataClient {

	/**
	 * 日志操作类
	 */
	private final static Logger logger = LoggerFactory.getLogger(DataClient.class);

	/**
	 * 客户端操作类
	 */
	private ConsumerClient starterConsumer;

	/**
	 * 消息操作客户端
	 */
	private MsgClient msgClient;

	/**
	 * 分页信息
	 */
	private Page currentPage;

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
	public DataClient(ConsumerClient starterConsumer, MsgClient msgClient) {
		this.starterConsumer = starterConsumer;
		this.msgClient = msgClient;
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
	public Object doData(OMMData data, String itemName) {
		short type = data.getType();
		if (data.isBlank()) {
			logger.info("无数据");
		} else if (OMMTypes.isAggregate(type)) {// 获取到字段ID及字段名称
			return doDataList(data, itemName);
		} else if ((type == OMMTypes.RMTES_STRING) && ((OMMDataBuffer) data).hasPartialUpdates()) {
			logger.error("数据异常,type:" + data.getType());
		} else if (type == OMMTypes.ANSI_PAGE) {
			return doDataAnsiPage(data, itemName);
		} else if (type == OMMTypes.BUFFER || data.getType() == OMMTypes.OPAQUE_BUFFER) {
			logger.error("数据异常,type:" + data.getType());
		} else if (type == OMMTypes.MSG) {
			logger.error("数据异常,type:" + data.getType());
		} else {// 获取到数据值
			return data;
		}
		return null;
	}

	/**
	 * 功能描述：处理集合
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年11月22日 下午6:59:20
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @param data
	 *
	 */
	private Object doDataList(OMMData data, String itemName) {
		doDataListHeader(data, itemName);
		List<Object> result = new ArrayList<Object>();
		for (Iterator<?> iter = ((OMMIterable) data).iterator(); iter.hasNext();) {
			OMMEntry entry = (OMMEntry) iter.next();
			Object obj = doDataEntry(entry, itemName);
			if (null != obj) {
				result.add(obj);
			}
		}
		return result;
	}

	/**
	 * 功能描述：处理集合头部
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年11月22日 下午6:59:37
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @param data
	 * @param ps
	 * @param tabLevel
	 *
	 */
	private Object doDataListHeader(OMMData data, String itemName) {
		short dataType = data.getType();
		switch (dataType) {
		case OMMTypes.FIELD_LIST:
			return null;
		case OMMTypes.SERIES:
			OMMSeries series = (OMMSeries) data;
			if (series.has(OMMSeries.HAS_SUMMARY_DATA)) {
				return doData(series.getSummaryData(), itemName);
			}
		case OMMTypes.MAP:
			OMMMap map = (OMMMap) data;
			if (map.has(OMMMap.HAS_SUMMARY_DATA)) {
				return doData(map.getSummaryData(), itemName);
			}
		case OMMTypes.VECTOR:
			OMMVector vector = (OMMVector) data;
			if (vector.has(OMMVector.HAS_SUMMARY_DATA)) {
				return doData(vector.getSummaryData(), itemName);
			}
		}
		return null;
	}

	/**
	 * 功能描述：处理实体对象
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年11月22日 下午7:01:25
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @param entry
	 *
	 */
	private Object doDataEntry(OMMEntry entry, String itemName) {
		switch (entry.getType()) {
		case OMMTypes.FIELD_ENTRY: // 字段信息
			OMMFieldEntry fe = (OMMFieldEntry) entry;
			FidDef fiddef = starterConsumer.getDictionary().getFidDef(fe.getFieldId());
			if (null != fiddef) {
				OMMData data = null;
				if (fe.getDataType() == OMMTypes.UNKNOWN) {
					data = fe.getData(fiddef.getOMMType());
				} else {
					data = fe.getData();
				}
				if (data.getType() != OMMTypes.ENUM) {
					LtEtDataUserEntity ue = new LtEtDataUserEntity();
					short fieldId = fiddef.getFieldId();
					ue.setFleldId(fieldId);
					ue.setRicCode(itemName);
					Object obj = doData(data, itemName);
					if (null != obj) {
						String value = doData(data, itemName).toString();
						ue.setValue(value);
						if (fieldId == 815 || fieldId == 238) {// 下一页链代码
							LtEtIef.doLtEtCode(value, itemName, true);
							msgClient.sendRequest(value);
						} else if ((fieldId >= 800 && fieldId < 815 && fieldId != 814)
								|| (fieldId > 238 && fieldId <= 253 && fieldId != 239)) {// 代码下还存在子数据，每次返回14条且有下一页链代码标识，需将14条子数据及链代码发送请求获取数据
							// 1-14子集合
							LtEtCode ltEtCode = LtEtIef.getLtEtCode(itemName);
							if (1 == ltEtCode.getIsGetSon().intValue()
									&& StringUtil.isNullOrEmpty(ltEtCode.getParentRicCode())) {// 需要获取子代码，且本code为第二级，允许继续订阅（目前根据业务需求仅获取到第二级代码）
								LtEtIef.doLtEtCode(value, itemName, false);
								msgClient.sendRequest(value);
							}
						}
					}
					return ue;
				}
			} else {
				logger.error("字段在字典表中不存在，请确认字典表");
			}
			break;
		case OMMTypes.ELEMENT_ENTRY:
			return doData(entry.getData(), itemName);
		case OMMTypes.MAP_ENTRY:
			if ((((OMMMapEntry) entry).getAction() != OMMMapEntry.Action.DELETE)
					&& entry.getDataType() != OMMTypes.NO_DATA) {
				return doData(entry.getData(), itemName);
			}
		case OMMTypes.VECTOR_ENTRY:
			if ((((OMMVectorEntry) entry).getAction() != OMMVectorEntry.Action.DELETE)
					&& (((OMMVectorEntry) entry).getAction() != OMMVectorEntry.Action.CLEAR)) {
				return doData(entry.getData(), itemName);
			}
		case OMMTypes.FILTER_ENTRY:
			if (((OMMFilterEntry) entry).getAction() != OMMFilterEntry.Action.CLEAR) {
				return doData(entry.getData(), itemName);
			}
		default:
			return doData(entry.getData(), itemName);
		}
		return null;
	}

	/**
	 * 功能描述：处理ansipage数据
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年11月22日 下午7:10:14
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @param data
	 *
	 */
	private Object doDataAnsiPage(OMMData data, String itemName) {
		boolean newPage = false;
		if (null == currentPage) {
			currentPage = new Page();
			newPage = true;
		}
		Vector<PageUpdate> pageUpdates = new Vector<PageUpdate>();
		ByteArrayInputStream bais = new ByteArrayInputStream(data.getBytes());
		currentPage.decode(bais, pageUpdates);
		if (!newPage) {
			// print the update string
			Iterator<PageUpdate> iter = pageUpdates.iterator();
			while (iter.hasNext()) {
				PageUpdate u = (PageUpdate) iter.next();
				StringBuffer buf = new StringBuffer(80);
				for (short k = u.getBeginningColumn(); k < u.getEndingColumn(); k++) {
					buf.append(currentPage.getChar(u.getRow(), k));
				}
				if (!(buf.toString()).equalsIgnoreCase("")) {
					return buf.toString();
				}
			}
		}
		return null;
	}

	public void setCurrentPage(Page currentPage) {
		this.currentPage = currentPage;
	}

}
