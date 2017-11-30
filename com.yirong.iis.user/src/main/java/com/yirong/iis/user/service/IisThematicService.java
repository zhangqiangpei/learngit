package com.yirong.iis.user.service;

import java.util.Map;

import com.yirong.awaken.core.service.IBaseService;
import com.yirong.iis.user.entity.IisThematic;
import com.yirong.iis.user.userentity.IisThematicUserEntity;

 /**
 * 功能描述：专题表service接口
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
public interface IisThematicService extends IBaseService<IisThematic, String> {

	/**
	 * 功能描述：新增专题表
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-24 15:49:14
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisThematic
	 * @return
	 */
	Map saveIisThematic(IisThematic iisThematic);

	/**
	 * 功能描述：修改专题表
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-24 15:49:14
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisThematic
	 * @return
	 */
	Map updateIisThematic(IisThematic iisThematic);

	/**
	 * 功能描述：删除专题表（批量）
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
	Map delIisThematic(String idStrs);

	/**
	 * 功能描述：根据ID查询专题表信息
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
	Map queryIisThematicById(String id);

	 /**
	 * 功能描述：查询专题表列表信息
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
	Map queryIisThematicList(IisThematicUserEntity ue);

}
