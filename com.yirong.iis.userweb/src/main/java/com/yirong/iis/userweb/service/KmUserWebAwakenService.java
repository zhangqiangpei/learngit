package com.yirong.iis.userweb.service;


import com.yirong.iis.userweb.userentity.IisUserAwakenAddFileUserEntity;

import java.io.File;
import java.io.InputStream;

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
	String httpUploadFile(IisUserAwakenAddFileUserEntity ue, File file);

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
	 * @param ue
	 * @return
	 *
	 */
	Object[] httpDownFile(String eosId, String tokenId);

	/**
	 * 功能描述：在线浏览
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
	 * @param ue
	 * @return
	 *
	 */
	InputStream httpGetOnlineFile(String fileId, String fileType, String tokenId);

}
