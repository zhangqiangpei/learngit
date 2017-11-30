package com.yirong.iis.mm.api;

import java.util.HashMap;
import java.util.Map;

import com.yirong.iis.mm.entity.IisDiplomaticSituation;
import com.yirong.iis.mm.service.IisDiplomaticSituationService;
import com.yirong.iis.mm.userentity.IisDiplomaticSituationUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yirong.commons.util.order.Order;
import com.yirong.commons.util.datatype.JsonUtil;

 /**
 * 功能描述：外交情况表(与中国)api接口
 * 
 * @author 陈清沣
 *         <p>
 *         创建时间 ：2017-11-27 19:29:29
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("IisDiplomaticSituationApi")
@ResponseBody
public class IisDiplomaticSituationApi {

	/**
	 * 标准service注入
	 */
	@Autowired
	private IisDiplomaticSituationService iisDiplomaticSituationService;
	
	/**
	 * 功能描述：新增外交情况表(与中国)
	 * 
	 * @author 陈清沣
	 *         <p>
	 *         创建时间 ：2017-11-27 19:29:29
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
		String param = JsonUtil.getJsonStrByAttrName(paramAll,"context");
		// 实体转换
		IisDiplomaticSituation iisDiplomaticSituation = (IisDiplomaticSituation) JsonUtil.StringToObject(param,IisDiplomaticSituation.class);
		// 业务处理
		Map map = this.iisDiplomaticSituationService.saveIisDiplomaticSituation(iisDiplomaticSituation);
		return JsonUtil.ObjectToString(map);
	}

	/**
	 * 功能描述：修改外交情况表(与中国)
	 * 
	 * @author 陈清沣
	 *         <p>
	 *         创建时间 ：2017-11-27 19:29:29
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
		String param = JsonUtil.getJsonStrByAttrName(paramAll,"context");
		// 实体转换
		IisDiplomaticSituation iisDiplomaticSituation = (IisDiplomaticSituation) JsonUtil.StringToObject(param,IisDiplomaticSituation.class);
		// 业务处理
		Map map = this.iisDiplomaticSituationService.updateIisDiplomaticSituation(iisDiplomaticSituation);
		return JsonUtil.ObjectToString(map);
	}

	/**
	 * 功能描述：删除外交情况表(与中国)（批量）
	 * 
	 * @author 陈清沣
	 *         <p>
	 *         创建时间 ：2017-11-27 19:29:29
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param idStrs
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String delete(@RequestBody String paramAll) {
		// 获取参数信息
		String pathName = "context/ids";
		String ids = JsonUtil.getJsonStrByAttrName(paramAll, pathName);
		// 处理业务
		Map map = iisDiplomaticSituationService.delIisDiplomaticSituation(ids);
		return JsonUtil.ObjectToString(map);
	}

	 /**
	 * 功能描述：查询外交情况表(与中国)列表信息
	 * 
	 * @author 陈清沣
	 *         <p>
	 *         创建时间 ：2017-11-27 19:29:29
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param ue
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
		IisDiplomaticSituationUserEntity psue = (IisDiplomaticSituationUserEntity) JsonUtil.StringToObject(param, IisDiplomaticSituationUserEntity.class,calssMap);
		// 处理业务
		Map map = iisDiplomaticSituationService.queryIisDiplomaticSituationList(psue);
		return JsonUtil.ObjectToStringClob(map);
	}

}
