package com.yirong.iis.mm.service;

import java.util.Map;

import com.yirong.awaken.core.service.IBaseService;
import com.yirong.iis.mm.entity.IisPoliticalEnvironment;
import com.yirong.iis.mm.userentity.IisPoliticalEnvironmentUserEntity;

/**
 * 功能描述：政治环境表service接口
 * 
 * @author 陈清沣
 *         <p>
 *         创建时间 ：2017-11-30 10:42:31
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
public interface IisPoliticalEnvironmentService extends IBaseService<IisPoliticalEnvironment, String> {

	/**
	 * 功能描述：新增政治环境表
	 * 
	 * @author 陈清沣
	 *         <p>
	 *         创建时间 ：2017-11-30 10:42:31
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisPoliticalEnvironment
	 * @return
	 */
	Map saveIisPoliticalEnvironment(IisPoliticalEnvironment iisPoliticalEnvironment);

	/**
	 * 功能描述：修改政治环境表
	 * 
	 * @author 陈清沣
	 *         <p>
	 *         创建时间 ：2017-11-30 10:42:31
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisPoliticalEnvironment
	 * @return
	 */
	Map updateIisPoliticalEnvironment(IisPoliticalEnvironment iisPoliticalEnvironment);

	/**
	 * 功能描述：删除政治环境表（批量）
	 * 
	 * @author 陈清沣
	 *         <p>
	 *         创建时间 ：2017-11-30 10:42:31
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param idStrs
	 * @return
	 */
	Map delIisPoliticalEnvironment(String idStrs);

	/**
	 * 功能描述：根据ID查询政治环境表信息
	 * 
	 * @author 陈清沣
	 *         <p>
	 *         创建时间 ：2017-11-30 10:42:31
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param id
	 * @return
	 */
	Map queryIisPoliticalEnvironmentById(String id);

	 /**
	 * 功能描述：查询政治环境表列表信息
	 * 
	 * @author 陈清沣
	 *         <p>
	 *         创建时间 ：2017-11-30 10:42:31
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param ue
	 * @return
	 */
	Map queryIisPoliticalEnvironmentList(IisPoliticalEnvironmentUserEntity ue);

}
