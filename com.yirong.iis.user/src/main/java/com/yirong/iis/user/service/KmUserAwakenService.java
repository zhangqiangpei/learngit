package com.yirong.iis.user.service;

import com.yirong.iis.user.userentity.KmUserAwakenFileUserEntity;
import com.yirong.iis.user.userentity.KmUserAwakenOperUserEntity;

/**
 * 功能描述：知识管理平台调用awaken平台service接口
 *
 * @author 刘捷(liujie)
 *         <p>
 *         创建时间 ：2017年9月7日 上午10:21:15
 *         </p>
 *
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public interface KmUserAwakenService {

	/**
	 * 功能描述：删除文件
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年9月26日 上午10:20:47
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @param ue
	 * @return
	 *
	 */
	boolean httpDeleteFile(KmUserAwakenOperUserEntity ue);

	/**
	 * 功能描述：新增知识信息
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年10月9日 下午3:58:58
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @param ue
	 * @return
	 *
	 */
	String httpAddInfo(KmUserAwakenFileUserEntity ue);

	/**
	 * 功能描述：删除知识信息
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年10月9日 下午4:00:28
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @param ue
	 * @return
	 *
	 */
	boolean httpDeleteInfo(KmUserAwakenOperUserEntity ue);

	/**
	 * 功能描述：更新知识信息
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年10月9日 下午4:01:45
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @param ue
	 * @return
	 *
	 */
	boolean httpUpdateInfo(KmUserAwakenFileUserEntity ue);

	

}
