package com.yirong.iis.user.api;


import java.util.HashMap;
import java.util.Map;

import com.yirong.iis.user.entity.IisReport;
import com.yirong.iis.user.service.IisReportService;
import com.yirong.iis.user.userentity.IisReportUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yirong.commons.util.order.Order;
import com.yirong.commons.util.datatype.JsonUtil;

/**
 * 功能描述：报告表api接口
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
@RequestMapping("IisReportApi")
@ResponseBody
public class IisReportApi {

	/**
	 * 标准service注入
	 */
	@Autowired
	private IisReportService iisReportService;

	/**
	 * 功能描述：新增报告表
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
		IisReport iisReport = (IisReport) JsonUtil.StringToObject(param,
				IisReport.class);
		// 业务处理
		Map map = this.iisReportService.saveIisReport(iisReport);
		return JsonUtil.ObjectToString(map);
	}

	/**
	 * 功能描述：修改报告表
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
		IisReport iisReport = (IisReport) JsonUtil.StringToObject(param,
				IisReport.class);
		// 业务处理
		Map map = this.iisReportService.updateIisReport(iisReport);
		return JsonUtil.ObjectToString(map);
	}

	/**
	 * 功能描述：根据ID查询报告表信息
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
		Map map = iisReportService.queryIisReportById(id);
		return JsonUtil.ObjectToString(map);
	}

	/**
	 * 功能描述：删除报告表（批量）
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
		Map map = iisReportService.delIisReport(ids.replace("[","").replace("]","").replace("\"",""));
		return JsonUtil.ObjectToString(map);
	}

	/**
	 * 功能描述：查询报告表列表信息
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
		IisReportUserEntity psue = (IisReportUserEntity) JsonUtil
				.StringToObject(param, IisReportUserEntity.class,calssMap);
		// 处理业务
		Map map = iisReportService.queryIisReportList(psue);
		return JsonUtil.ObjectToString(map);
	}

}

