package com.yirong.iis.mmweb.controller;

import com.yirong.commons.cache.eif.RedisCacheEif;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.commons.util.datatype.StringUtil;
import com.yirong.commons.util.file.FileUtil;
import com.yirong.commons.util.file.PropertiesFileUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

/**
 * 功能描述：文件操作api
 *
 * @author 刘捷(liujie)
 * <p>
 * 创建时间 ：2017年10月16日 上午9:42:12
 * </p>
 * <p>
 * <p>
 * 修改历史：(修改人，修改时间，修改原因/内容)
 * </p>
 */
@RestController
@RequestMapping("/file")
public class FileController {


    private Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 保存国旗图片文件
     *
     * @param multipartFile
     * @return
     */
    @RequestMapping(value = "/upFlagFile", method = RequestMethod.POST)
    @ResponseBody
    public String upFlagFile(@RequestParam(name = "file") MultipartFile multipartFile, @RequestParam(name = "engName") String engName, HttpServletRequest request) {
        InputStream is = null;
        OutputStream os = null;
        String targetFileName = null;
        try {
            targetFileName = engName + multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
            File file = new File(this.getFileDirPath(null) + File.separator + targetFileName);
            if (!file.exists()){
                FileUtil.delFile(targetFileName);
            }
            is = multipartFile.getInputStream();
            os = new FileOutputStream(file);
            int bytesRead;
            byte[] buffer = new byte[3096];
            while ((bytesRead = is.read(buffer, 0, 3096)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    logger.error("文件输入流关闭异常");
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    logger.error("文件输出流关闭异常");
                }
            }
        }
        return targetFileName;
    }


    /**
     * 获取临时文件存放路径
     *
     * @return
     */
    private Path getFileDirPath(String path) {
        // 配置文件路径
        String configPath = System.getProperty("user.dir") + File.separator + "config" + File.separator;
        // 获取配置信息
        Map<String, String> map = PropertiesFileUtil.getPropertiesFileToMapFromOutFile(configPath + "yr-awaken.properties");
        String dirPath;
        if (null == path) {
            dirPath = map.get("country.flag.save.path");
        } else {
            dirPath = path;
        }
        Path fileDirPath = Paths.get(dirPath);
        try {
            if (!Files.exists(fileDirPath)) {
                fileDirPath = Files.createDirectory(fileDirPath);
            }
        } catch (IOException e) {
            logger.error(e);
        }
        return fileDirPath;
    }

    /**
     * 获得TokenId
     *
     * @param request
     * @return
     */
    private String getValidTokenId(HttpServletRequest request) {
        String tokenId = null;
        Cookie[] cookies = request.getCookies();
        if (null != cookies && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if ("TOKENID".equals(cookie.getName())) {// 有token
                    // 可能存在多个token情况（前一个未失效，用户重新登录）
                    String tokenIdTemp = cookie.getValue();
                    String userName = RedisCacheEif.hget(tokenIdTemp, "userName");
                    if (StringUtil.isNotNullOrEmpty(userName)) {// 缓存有信息（说明用户登录过）
                        tokenId = tokenIdTemp;
                        break;// 跳出循环
                    }
                }
            }
        }
        return tokenId;
    }

}
