package com.yirong.iis.tp.tslt.et.util;

import com.reuters.rfa.common.Client;
import com.reuters.rfa.common.Event;
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
	public void processEvent(Event arg0) {

	}

}
