package com.yirong.iis.tp.tslt.et.util;

import java.io.ByteArrayInputStream;
import java.util.Iterator;
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
	private StarterConsumer starterConsumer;

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
	public DataClient(StarterConsumer starterConsumer) {
		this.starterConsumer = starterConsumer;
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
	public void doData(OMMData data) {
		short type = data.getType();
		if (data.isBlank()) {
			logger.info("无数据");
		} else if (OMMTypes.isAggregate(type)) {// 获取到字段ID及字段名称
			doDataList(data);
		} else if ((type == OMMTypes.RMTES_STRING) && ((OMMDataBuffer) data).hasPartialUpdates()) {
			logger.error("数据异常,type:" + data.getType());
		} else if (type == OMMTypes.ANSI_PAGE) {
			doDataAnsiPage(data);
		} else if (type == OMMTypes.BUFFER || data.getType() == OMMTypes.OPAQUE_BUFFER) {
			logger.error("数据异常,type:" + data.getType());
		} else if (type == OMMTypes.MSG) {
			logger.error("数据异常,type:" + data.getType());
		} else {// 获取到数据值
			System.out.println(data);
		}
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
	private void doDataList(OMMData data) {
		doDataListHeader(data);
		for (Iterator<?> iter = ((OMMIterable) data).iterator(); iter.hasNext();) {
			OMMEntry entry = (OMMEntry) iter.next();
			doDataEntry(entry);
		}
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
	private void doDataListHeader(OMMData data) {
		short dataType = data.getType();
		switch (dataType) {
		case OMMTypes.FIELD_LIST:
			break;
		case OMMTypes.SERIES:
			OMMSeries series = (OMMSeries) data;
			if (series.has(OMMSeries.HAS_SUMMARY_DATA)) {
				doData(series.getSummaryData());
			}
			break;
		case OMMTypes.MAP:
			OMMMap map = (OMMMap) data;
			if (map.has(OMMMap.HAS_SUMMARY_DATA)) {
				doData(map.getSummaryData());
			}
			break;
		case OMMTypes.VECTOR:
			OMMVector vector = (OMMVector) data;
			if (vector.has(OMMVector.HAS_SUMMARY_DATA)) {
				doData(vector.getSummaryData());
			}
			break;
		}
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
	private void doDataEntry(OMMEntry entry) {
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
					System.out.println(fiddef.getFieldId());
					System.out.println(fiddef.getName());
					doData(data);
				}
			} else {
				logger.error("字段在字典表中不存在，请确认字典表");
			}
			break;
		case OMMTypes.ELEMENT_ENTRY:
			doData(entry.getData());
			break;
		case OMMTypes.MAP_ENTRY:
			if ((((OMMMapEntry) entry).getAction() != OMMMapEntry.Action.DELETE)
					&& entry.getDataType() != OMMTypes.NO_DATA) {
				doData(entry.getData());
			}
			break;
		case OMMTypes.VECTOR_ENTRY:
			if ((((OMMVectorEntry) entry).getAction() != OMMVectorEntry.Action.DELETE)
					&& (((OMMVectorEntry) entry).getAction() != OMMVectorEntry.Action.CLEAR)) {
				doData(entry.getData());
			}
			break;
		case OMMTypes.FILTER_ENTRY:
			if (((OMMFilterEntry) entry).getAction() != OMMFilterEntry.Action.CLEAR) {
				doData(entry.getData());
			}
			break;
		default:
			doData(entry.getData());
			break;
		}
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
	private void doDataAnsiPage(OMMData data) {
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
					System.out.println("流数据" + buf.toString());
				}
			}
		}
	}

	public void setCurrentPage(Page currentPage) {
		this.currentPage = currentPage;
	}

}
