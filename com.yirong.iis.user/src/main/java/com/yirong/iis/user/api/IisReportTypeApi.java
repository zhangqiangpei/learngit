package com.yirong.iis.user.api;

import com.yirong.commons.util.datatype.JsonUtil;
import com.yirong.commons.util.order.Order;
import com.yirong.iis.user.userentity.IisReportTypeUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yirong.iis.user.service.IisReportTypeService;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述：报告分类表api接口
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
@RequestMapping("IisReportTypeApi")
@ResponseBody
public class IisReportTypeApi {

	/**
	 * 标准service注入
	 */
	@Autowired
	private IisReportTypeService iisReportTypeService;

	 /**
	  * 功能描述：查询报告分类表列表信息
	  *
	  * @author 林明铁
	  *         <p>
	  *         创建时间 ：2017-11-09 10:00:09
	  *         </p>
	  *
	  *         <p>
	  *         修改历史：(修改人，修改时间，修改原因/内容)
	  *         </p>
	  * @param paramAll
	  * @return
	  */
	 @RequestMapping(value = "list", method = RequestMethod.POST)
	 public String list(@RequestBody String paramAll) {
		 // 获取参数信息
		 String param = JsonUtil.getJsonStrByAttrName(paramAll,
				 "context");
		 // 定义转换对象属性类
		 Map<String, Class> calssMap = new HashMap<String, Class>();
		 calssMap.put("orders", Order.class);
		 // 实体转换
		 IisReportTypeUserEntity psue = (IisReportTypeUserEntity) JsonUtil
				 .StringToObject(param, IisReportTypeUserEntity.class,calssMap);
		 // 处理业务
		 Map map = iisReportTypeService.queryIisReportTypeList(psue);
		 return JsonUtil.ObjectToStringClob(map);
	 }

	/**
	 * 功能描述：查询报告分类表列表信息
	 *
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-09 10:00:09
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param paramAll
	 * @return
	 */
	@RequestMapping(value = "listFiveRecord", method = RequestMethod.POST)
	public String listFiveRecord(@RequestBody String paramAll) {
		// 处理业务
		Map map = iisReportTypeService.queryIisReportTypeListFiveRecord();
		return JsonUtil.ObjectToStringClob(map);
	}

}
