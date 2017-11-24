package com.yirong.iis.tp.tslt.et.util;

/**
 * 功能描述：消费者开始线程类
 *
 * @author 刘捷(liujie)
 *         <p>
 *         创建时间 ：2017年11月24日 上午9:10:35
 *         </p>
 *
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public class StarterConsumer extends Thread {

	/**
	 * 参数类
	 */
	private CommandLine commandLine;

	/**
	 * 功能描述：构造函数
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年11月24日 上午9:12:53
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public StarterConsumer(String session, String serviceName, String user, String iteamName,
			String eventQueueName) {
		commandLine = new CommandLine(session, serviceName, user, iteamName, eventQueueName);
	}

	/**
	 * 功能描述：开始运行
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年11月24日 上午9:11:52
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 *
	 */
	@Override
	public void run() {
		ConsumerClient sc = new ConsumerClient(commandLine);
		sc.run();
	}

}
