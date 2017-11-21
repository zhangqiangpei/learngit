package com.yirong.iis.userweb.controller;

import com.yirong.commons.cache.eif.RedisCacheEif;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.commons.util.datatype.StringUtil;
import com.yirong.iis.userweb.service.KmUserDocsService;
import com.yirong.iis.userweb.service.KmUserWebAwakenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述：文件操作api
 *
 * @author 刘捷(liujie)
 *         <p>
 * 		创建时间 ：2017年10月16日 上午9:42:12
 *         </p>
 *
 *         <p>
 * 		修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@RestController
@RequestMapping("/file")
public class FileController {

    /**
     * web用户操作类
     */
    @Autowired
    private KmUserWebAwakenService kmUserWebAwakenService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 功能描述：文件下载
     *
     * @param request
     * @param response
     * @author 刘捷(liujie)
     *         <p>
     *         创建时间 ：2017年9月27日 上午11:18:18
     *         </p>
     *         <p>
     *         <p>
     *         修改历史：(修改人，修改时间，修改原因/内容)
     *         </p>
     */
    @RequestMapping(value = "/downFile", method = RequestMethod.POST)
    public Map downFile(@RequestParam("eosId") String eosId, HttpServletRequest request,
                        HttpServletResponse response) {
        Map<String, String> result = new HashMap<String, String>();
        // 验证判断
        if (StringUtil.isNullOrEmpty(eosId)) {
            return result;
        }
        InputStream is = null;
        String tokenId = this.getValidTokenId(request);
        Object[] resultObj = kmUserWebAwakenService.httpDownFile(eosId, tokenId);
        if (null == resultObj) {
            return result;
        }
        is = (InputStream) resultObj[0];
        if (null == is) {
            return result;
        }
        OutputStream os = null;
        try {
            // 设置头部
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(resultObj[1].toString(), "UTF-8"));
            // 写入到本地response
            os = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int i = is.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = is.read(buffer);
            }
        } catch (Exception e) {
            logger.error("流传输异常");
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                logger.error("InputStream关闭异常", e);
            }
            try {
                if (null != os) {
                    os.close();
                }
            } catch (IOException e) {
                logger.error("OutputStream关闭异常", e);
            }
        }
        return result;
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

    @Autowired
    private KmUserDocsService kmUserDocsService;

    /**
     * 功能描述：获取在线浏览路径
     *
     * @param request
     * @author 王杰
     *         <p>
     *         创建时间 ：2017-10-10 14:41:00
     *         </p>
     *         <p>
     *         修改历史：(修改人，修改时间，修改原因/内容)
     *         </p>
     */
    @RequestMapping(value = "/onlineGetFile", method = RequestMethod.POST)
    public Map onlineGetFile(@RequestParam("fileId") String fileId, @RequestParam("fileType") String fileType,
                             HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 验证判断
        if (StringUtil.isNullOrEmpty(fileId)) {
            map.put("code", "1");
            map.put("msg", "文件ID不能为空!");
        }
        String tokenId = this.getValidTokenId(request);
        Object result = kmUserDocsService.onlineGetFile(fileId, fileType, tokenId);
        map.put("code", "0");
        map.put("msg", "查询成功");
        map.put("data", result);
        return map;
    }
}
