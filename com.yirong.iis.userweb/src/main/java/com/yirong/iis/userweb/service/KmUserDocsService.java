package com.yirong.iis.userweb.service;

/**
 * 功能描述：知识管理平台用户端文档service接口
 *
 * @author 刘捷(liujie)
 *         <p>
 *         创建时间 ：2017年10月10日 上午10:13:54
 *         </p>
 *
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public interface KmUserDocsService {

	/**
	 * 功能描述：在线浏览文件
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年10月10日 上午10:16:09
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @param fileId
	 * @param fileType
	 * @param tokenId
	 * @return
	 *
	 */
	Object onlineGetFile(String fileId, String fileType, String tokenId);
}
