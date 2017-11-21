package com.yirong.iis.user.service;

import com.yirong.iis.user.userentity.KmUserAwakenAddFileUserEntity;

import java.io.File;

/**
 * 功能描述：调用awaken平台service接口
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
public interface KmUserWebAwakenService {

	/**
	 * 功能描述：上传文件
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年9月25日 下午3:24:43
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
	String httpUploadFile(KmUserAwakenAddFileUserEntity ue, File file);

	/**
	 * 功能描述：下载文件
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年9月26日 上午10:10:29
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @return
	 *
	 */
	Object[] httpDownFile(String eosId, String tokenId);

}
