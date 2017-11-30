package com.yirong.iis.user.service;

import java.util.Map;

import com.yirong.awaken.core.service.IBaseService;
import com.yirong.iis.user.entity.IisKeyEvents;
import com.yirong.iis.user.userentity.IisKeyEventsUserEntity;

 /**
 * 功能描述：重大事件表service接口
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-28 10:58:00
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
public interface IisKeyEventsService extends IBaseService<IisKeyEvents, String> {

	/**
	 * 功能描述：新增重大事件表
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-28 10:58:00
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisKeyEvents
	 * @return
	 */
	Map saveIisKeyEvents(IisKeyEvents iisKeyEvents);

	/**
	 * 功能描述：修改重大事件表
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-28 10:58:00
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisKeyEvents
	 * @return
	 */
	Map updateIisKeyEvents(IisKeyEvents iisKeyEvents);

	/**
	 * 功能描述：删除重大事件表（批量）
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-28 10:58:00
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param idStrs
	 * @return
	 */
	Map delIisKeyEvents(String idStrs);

	/**
	 * 功能描述：根据ID查询重大事件表信息
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-28 10:58:00
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param id
	 * @return
	 */
	Map queryIisKeyEventsById(String id);

	 /**
	 * 功能描述：查询重大事件表列表信息
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-28 10:58:00
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param ue
	 * @return
	 */
	Map queryIisKeyEventsList(IisKeyEventsUserEntity ue);

}
