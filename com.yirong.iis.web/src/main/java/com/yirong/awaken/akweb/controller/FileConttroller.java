package com.yirong.awaken.akweb.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.ProtocolException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yirong.commons.eos.api.EosEif;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.commons.util.datatype.StringUtil;
import com.yirong.microservices.client.MsClient;
import com.yirong.microservices.client.entity.MsHttpRequest;
import com.yirong.microservices.client.entity.MsRpcResult;

@RestController
@RequestMapping("file")
public class FileConttroller {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private Environment env;
	
/*	@Autowired
	private EosEif EosEif;*/
	
	
	@RequestMapping(value = "/upFile", method = RequestMethod.POST)
	@ResponseBody
	public String upFile(@RequestParam(name="serviceName")String serviceName,
			@RequestParam(name="methodName") String methodName,
			@RequestParam(name="file")MultipartFile multipartFile,
			@RequestParam(name="fileId")String fileId, HttpServletRequest request,
			@RequestParam(name="structuring") Integer structuring,
			HttpServletResponse response) {
		
		//校验是否跳到登陆页
		//this.isToLogin(request, response, serviceName, methodName);
		String orgName = multipartFile.getOriginalFilename();
	    String prefix=orgName.substring(orgName.lastIndexOf(".")+1);
	    Path dirPath = this.getFileDirPath();
	    String filePath = dirPath + File.separator  + fileId;
		try {
			if (!Files.exists(dirPath)) {
				dirPath = Files.createDirectory(dirPath);
			}
		} catch (IOException e) {
			System.out.println(e);
		}
		File file = new File(filePath);
		try {
			Files.copy(multipartFile.getInputStream(), dirPath.resolve(fileId));
			FileInputStream fis = new FileInputStream(file);
			//保存到eos
			if(1==structuring){
				//转发到微服务进行解析
				methodName = methodName +"/" + fileId;
				MsRpcResult result = MsClient.invoke(new MsHttpRequest(serviceName, methodName, "POST", fis));
			}
			EosEif.uploadFile(fileId, file, null);
		} catch (Exception ex) {
			logger.error(ex);
		} finally {
			if (file.exists()) {
				file.delete();
			}
		}
		return "";
	}
	@RequestMapping(value = "/downFile", method = RequestMethod.POST)
	@ResponseBody
	public String downFile(@RequestParam("fileId") String fileId,String suffix, HttpServletRequest request,HttpServletResponse response) throws IOException {
		String dirTmp = env.getProperty("eos.dir.tmp");//文件下载路径
		File file = EosEif.downFile(fileId, dirTmp, response);
		FileInputStream fis = new FileInputStream(file);
		SimpleDateFormat n = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName =n.format(new Date());
		if(StringUtil.isNotNullOrEmpty(suffix)){
			fileName = fileName+"."+suffix;
		}
	    response.reset();// 解决weblogic下，会出现 Exceeded stated content-length of:
	    response.setContentType("application/x-download");// 设置response内容的类型 
	    response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(fileName, "UTF-8"));
	    BufferedInputStream bis = null;
	    BufferedOutputStream bos = null;
	    try {
	      bis = new BufferedInputStream(new FileInputStream(file));
	      bos = new BufferedOutputStream(response.getOutputStream());

	      IOUtils.copy(bis, bos);
	      response.flushBuffer();
	    } catch (ProtocolException e) {
	      //log.error("下载文件出错，weblogic中内容超过response申明长度!");
	    } catch (SocketException e) {
	      //log.error("java.net.socketException:没有进程来读取写入管道的数据。");
	    } catch (final IOException e) {
	      //log.error("下载文件出错！", e);
	    } finally {
	      if (bis != null) {
	        bis.close();
	      }
	      if (bos != null) {
	        bos.close();
	      }
	    }
		
		return null;
	}
	
	/**
	 * 获取临时文件存放路径
	 * @return
	 */
	private Path getFileDirPath() {
		String dirPath = env.getProperty("file.dir");
		Path fileDirPath = Paths.get(dirPath);
		try {
			if (!Files.exists(fileDirPath)) {
				fileDirPath = Files.createDirectory(fileDirPath);
			}
		} catch (IOException e) {
			System.out.println(e);
		}

		return fileDirPath;
	}
}
