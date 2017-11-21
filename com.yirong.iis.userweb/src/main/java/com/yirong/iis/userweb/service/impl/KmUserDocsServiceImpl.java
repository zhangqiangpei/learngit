package com.yirong.iis.userweb.service.impl;

import com.yirong.commons.cache.eif.LocalCacheEif;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.commons.util.compress.ZipUtil;
import com.yirong.commons.util.datatype.StringUtil;
import com.yirong.commons.util.file.FileUtil;
import com.yirong.iis.userweb.constant.KmUserWebConstants;
import com.yirong.iis.userweb.service.KmUserWebAwakenService;
import com.yirong.iis.userweb.service.KmUserDocsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：知识管理平台用户端文档service实现类
 *
 * @author 刘捷(liujie)
 *         <p>
 *         创建时间 ：2017年10月10日 上午10:14:20
 *         </p>
 *
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("unchecked")
@Service("KmUserDocsServiceImpl")
public class KmUserDocsServiceImpl implements KmUserDocsService {

	/**
	 * 日志操作类
	 */
	private Logger logger = LoggerFactory.getLogger(KmUserDocsServiceImpl.class);

	/**
	 * awaken接口service注入
	 */
	@Autowired
	private KmUserWebAwakenService awakenService;

	/**
	 * 功能描述：在线浏览文件
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年10月10日 上午10:16:22
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
	@Override
	public Object onlineGetFile(String fileId, String fileType, String tokenId) {
		if (StringUtil.isNullOrEmpty(fileId) || StringUtil.isNullOrEmpty(fileType)
				|| StringUtil.isNullOrEmpty(tokenId)) {
			return null;
		}
		Object result = null;
		if ("SWF".equals(fileType.toUpperCase())) {// swf
			result = httpGetOnlineSwfFile(fileId, tokenId);
		} else if ("PDF".equals(fileType.toUpperCase())) {// pdf
			result = httpGetOnlinePdfFile(fileId, tokenId);
		}
		return result;

	}

	/**
	 * 功能描述：在线浏览文件下载（pdf文件）
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年10月10日 上午9:52:16
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @param fileId
	 * @param tokenId
	 * @return
	 *
	 */
	private String httpGetOnlinePdfFile(String fileId, String tokenId) {
		// 先从缓存中获取该数据
		String cacheKey = KmUserWebConstants.FILE_TYPE_PDF + fileId;
		Object obj = LocalCacheEif.get(cacheKey);
		if (null == obj) {// 不存在，说明该文件还未下载过
			synchronized (cacheKey) {// 根据KEY进行锁（提高性能）
				obj = LocalCacheEif.get(cacheKey);// 排队获取锁后，先获取一次缓存（可能别人已经从接口获取出该数据）
				if (null == obj) {// 不存在，需走接口下载文件
					// 从接口获取文件
					InputStream is = awakenService.httpGetOnlineFile(fileId, "PDF", tokenId);
					if (null == is) {// 接口获取文件异常
						logger.error("pdf文件获取异常");
						return null;
					} else {// 获取文件成功
						// 拼装路径
						StringBuffer filePath = new StringBuffer();
						filePath.append(KmUserWebConstants.FILE_DOCS_BASE_PATH);
						filePath.append("pdf");
						filePath.append(File.separator);
						filePath.append(fileId);
						filePath.append(".pdf");
						// 写入文件
						boolean isOk = FileUtil.mkFile(filePath.toString(), is);
						if (isOk) {// 写入本地文件成功
							String path = localPathToWebPath(filePath.toString());
							LocalCacheEif.set(cacheKey, path);// 写入缓存
							return path;
						} else {
							logger.error("pdf写入本地文件失败");
							return null;
						}
					}
				} else {// 存在，直接返回
					return obj.toString();
				}
			}
		} else {// 存在，直接返回
			return obj.toString();
		}
	}

	/**
	 * 功能描述：在线浏览文件下载（swf文件）
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年10月10日 上午9:52:16
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @param fileId
	 * @param tokenId
	 * @return
	 *
	 */
	private List<String> httpGetOnlineSwfFile(String fileId, String tokenId) {
		// 先从缓存中获取该数据
		String cacheKey = KmUserWebConstants.FILE_TYPE_SWF + fileId;
		Object obj = LocalCacheEif.get(cacheKey);
		if (null == obj) {// 不存在，说明该文件还未下载过
			synchronized (cacheKey) {// 根据KEY进行锁（提高性能）
				obj = LocalCacheEif.get(cacheKey);// 排队获取锁后，先获取一次缓存（可能别人已经从接口获取出该数据）
				if (null == obj) {// 不存在，需走接口下载文件
					// 从接口获取文件
					InputStream is = awakenService.httpGetOnlineFile(fileId, "SWF", tokenId);
					if (null == is) {// 接口获取文件异常
						logger.error("swf压缩文件获取异常");
						return null;
					} else {// 获取文件成功
						// 拼装压缩包路径
						StringBuffer zipFilePath = new StringBuffer();
						zipFilePath.append(KmUserWebConstants.FILE_DOCS_BASE_PATH);
						zipFilePath.append("swf");
						zipFilePath.append(File.separator);
						zipFilePath.append(fileId);
						zipFilePath.append(".zip");
						// 写入文件
						boolean isOk = FileUtil.mkFile(zipFilePath.toString(), is);
						if (isOk) {// 写入本地压缩文件成功
							// 解压至当前文件夹（解压出来也是个文件夹，文件夹名称为fileId）
							isOk = ZipUtil.unzip(zipFilePath.toString());
							if (isOk) {// 解压成功
								// 删除压缩包
								FileUtil.delFile(zipFilePath.toString());
								// 获取文件夹路径
								String filePath = zipFilePath.toString().replace(".zip", "");
								// 获取文件夹
								File dirFile = new File(filePath);
								// 获取该文件夹下的所有文件
								File[] files = dirFile.listFiles();
								if (null != files && files.length > 0) {
									List<String> pathList = new ArrayList<String>();
									for (File file : files) {
										String path = localPathToWebPath(dirFile + File.separator + file.getName());
										pathList.add(path);
									}
									LocalCacheEif.set(cacheKey, pathList);// 写入缓存
									return pathList;
								} else {
									logger.error("解压出来的文件夹无任何文件");
									return null;
								}
							} else {// 解压失败
								logger.error("压缩包解压失败");
								return null;
							}
						} else {
							logger.error("swf压缩写入本地文件失败");
							return null;
						}
					}
				} else {// 存在，直接返回
					return (List<String>) obj;
				}
			}
		} else {// 存在，直接返回
			return (List<String>) obj;
		}
	}

	/**
	 * 功能描述：本地文件路径转成web路径
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年10月10日 上午10:35:35
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @param path
	 * @return
	 *
	 */
	private String localPathToWebPath(String path) {
		// 替换路径
		String oldPath = path.toString().replace(KmUserWebConstants.FILE_DOCS_BASE_PATH, "").replaceAll("\\\\", "/");
		return "docsFile/" + oldPath;
	}

}
