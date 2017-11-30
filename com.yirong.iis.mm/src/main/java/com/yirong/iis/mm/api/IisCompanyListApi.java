package com.yirong.iis.mm.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yirong.commons.util.datatype.JsonUtil;
import com.yirong.commons.util.order.Order;
import com.yirong.iis.mm.service.IisCompanyListService;
import com.yirong.iis.mm.userentity.IisCompanyListUserEntity;

/**
 * 
 * 功能描述:企业列表api接口
 *
 * @author 薛雅芳
 *         <p>
 *         修改时间:2017年11月28日下午3:25:18
 *         </p>
 *         
 *         <p>
 *         修改历史:(修改人,修改时间,修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("IisCompanyListApi")
public class IisCompanyListApi {
	
	/**
	 * 标准service注入
	 */
	@Autowired
	private IisCompanyListService iisCompanyListService;

	
	
	/**
	 * 
	 * 功能描述：查找出除选择id中国家列表中已经选择的国家的其他国家
	 *
	 * @author 薛雅芳
	 *         <p>
	 *         创建时间 ：2017年11月30日上午11:12:09
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param paramAll
	 * @return
	 */
	@RequestMapping(value = "selectByTypeId", method = RequestMethod.POST)
	public String selectByTypeId(@RequestBody String paramAll){
		//获取参数信息
		// 获取参数信息
		String pathName = "context/typeId";
		String typeId = JsonUtil.getJsonStrByAttrName(paramAll, pathName);
		//System.err.print(typeId);
		// 处理业务
		Map map = iisCompanyListService.queryIisCountryListByTypeId(typeId);
		String jsonStr = JsonUtil.ObjectToString(map);
		//System.err.println(jsonStr);
		return jsonStr;
	}
	
	
	/**
	 * 
	 * 功能描述：删除企业列表
	 *
	 * @author 薛雅芳
	 *         <p>
	 *         创建时间 ：2017年11月28日下午3:38:03
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
		Map map = iisCompanyListService.delIisCompanyList(ids);
		return JsonUtil.ObjectToString(map);
	}
	
	/**
	 * 
	 * 功能描述：查询企业列表信息
	 *
	 * @author 薛雅芳
	 *         <p>
	 *         创建时间 ：2017年11月28日下午4:15:37
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
		Map<String, Class> classMap = new HashMap<String, Class>();
		classMap.put("orders", Order.class);
		// 实体转换
		IisCompanyListUserEntity psue = (IisCompanyListUserEntity) JsonUtil.StringToObject(param, IisCompanyListUserEntity.class,classMap);
		// 处理业务
		Map map = iisCompanyListService.queryIisCompanyListList(psue);
		return JsonUtil.ObjectToStringClob(map);
	}
}
