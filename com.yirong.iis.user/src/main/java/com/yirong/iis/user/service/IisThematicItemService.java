package com.yirong.iis.user.service;

import java.util.List;
import java.util.Map;

import com.yirong.awaken.core.service.IBaseService;
import com.yirong.iis.user.entity.IisThematicItem;
import com.yirong.iis.user.userentity.IisThematicItemUserEntity;

 /**
 * 功能描述：专题模块表service接口
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-24 15:49:14
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
public interface IisThematicItemService extends IBaseService<IisThematicItem, String> {

	/**
	 * 功能描述：新增专题模块表
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-24 15:49:14
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisThematicItem
	 * @return
	 */
	Map saveIisThematicItem(IisThematicItem iisThematicItem);
	
	/**
	 * 功能描述：批量新增专题模块表
	 * 
	 * @author howie lee
	 *         <p>
	 *         创建时间 ：2017-11-24 15:49:14
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisThematicItems
	 * @return
	 */	
	Map saveBatchIisThematicItem(List<IisThematicItem> iisThematicItems);

	/**
	 * 功能描述：修改专题模块表
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-24 15:49:14
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisThematicItem
	 * @return
	 */
	Map updateIisThematicItem(IisThematicItem iisThematicItem);

	/**
	 * 功能描述：删除专题模块表（批量）
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-24 15:49:14
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param idStrs
	 * @return
	 */
	Map delIisThematicItem(String idStrs);

	/**
	 * 功能描述：根据ID查询专题模块表信息
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-24 15:49:14
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param id
	 * @return
	 */
	Map queryIisThematicItemById(String id);

	 /**
	 * 功能描述：查询专题模块表列表信息
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-24 15:49:14
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param ue
	 * @return
	 */
	Map queryIisThematicItemList(IisThematicItemUserEntity ue);

}
