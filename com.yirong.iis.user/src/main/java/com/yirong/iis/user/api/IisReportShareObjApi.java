package com.yirong.iis.user.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yirong.iis.user.service.IisReportShareObjService;

 /**
 * 功能描述：报告共享对象表api接口
 * 
 * @author 林明铁
 *         <p>
 *         创建时间 ：2017-11-09 10:00:09
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("IisReportShareObjApi")
@ResponseBody
public class IisReportShareObjApi {

	/**
	 * 标准service注入
	 */
	@Autowired
	private IisReportShareObjService iisReportShareObjService;

}
