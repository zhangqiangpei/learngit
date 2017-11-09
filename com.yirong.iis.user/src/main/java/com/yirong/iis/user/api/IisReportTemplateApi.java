package com.yirong.iis.user.api;

import com.yirong.commons.util.datatype.JsonUtil;
import com.yirong.commons.util.order.Order;
import com.yirong.iis.user.entity.IisReportTemplate;
import com.yirong.iis.user.userentity.IisReportTemplateUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yirong.iis.user.service.IisReportTemplateService;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述：报告模版表api接口
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
@RequestMapping("IisReportTemplateApi")
@ResponseBody
public class IisReportTemplateApi {

	/**
	 * 标准service注入
	 */
	@Autowired
	private IisReportTemplateService iisReportTemplateService;

	 /**
	  * 功能描述：新增报告模版表
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
	 @RequestMapping(value = "save", method = RequestMethod.POST)
	 public String save(@RequestBody String paramAll) {
		 // 获取参数信息
		 String param = JsonUtil.getJsonStrByAttrName(paramAll,
				 "context");
		 // 实体转换
		 IisReportTemplate iisReportTemplate = (IisReportTemplate) JsonUtil.StringToObject(param,
				 IisReportTemplate.class);
		 // 业务处理
		 Map map = this.iisReportTemplateService.saveIisReportTemplate(iisReportTemplate);
		 return JsonUtil.ObjectToString(map);
	 }

	 /**
	  * 功能描述：修改报告模版表
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
	 @RequestMapping(value = "update", method = RequestMethod.POST)
	 public String update(@RequestBody String paramAll) {
		 // 获取参数信息
		 String param = JsonUtil.getJsonStrByAttrName(paramAll,
				 "context");
		 // 实体转换
		 IisReportTemplate iisReportTemplate = (IisReportTemplate) JsonUtil.StringToObject(param,
				 IisReportTemplate.class);
		 // 业务处理
		 Map map = this.iisReportTemplateService.updateIisReportTemplate(iisReportTemplate);
		 return JsonUtil.ObjectToString(map);
	 }

	 /**
	  * 功能描述：根据ID查询报告模版表信息
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
	 @RequestMapping(value = "get", method = RequestMethod.POST)
	 public String get(@RequestBody String paramAll) {
		 // 获取参数信息
		 String pathName = "context/id";
		 String id = JsonUtil.getJsonStrByAttrName(paramAll, pathName);
		 // 处理业务
		 Map map = iisReportTemplateService.queryIisReportTemplateById(id);
		 return JsonUtil.ObjectToString(map);
	 }

	 /**
	  * 功能描述：删除报告模版表（批量）
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
	 @RequestMapping(value = "delete", method = RequestMethod.POST)
	 public String delete(@RequestBody String paramAll) {
		 // 获取参数信息
		 String pathName = "context/ids";
		 String ids = JsonUtil.getJsonStrByAttrName(paramAll, pathName);
		 // 处理业务
		 Map map = iisReportTemplateService.delIisReportTemplate(ids.replace("[","").replace("]","").replace("\"",""));
		 return JsonUtil.ObjectToString(map);
	 }

	 /**
	  * 功能描述：查询报告模版表列表信息
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
		 IisReportTemplateUserEntity psue = (IisReportTemplateUserEntity) JsonUtil
				 .StringToObject(param, IisReportTemplateUserEntity.class,calssMap);
		 // 处理业务
		 Map map = iisReportTemplateService.queryIisReportTemplateList(psue);
		 return JsonUtil.ObjectToString(map);
	 }

}
