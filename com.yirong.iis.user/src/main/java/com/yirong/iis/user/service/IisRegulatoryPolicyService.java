package com.yirong.iis.user.service;

import java.util.Map;

import com.yirong.awaken.core.service.IBaseService;
import com.yirong.iis.user.entity.IisRegulatoryPolicy;
import com.yirong.iis.user.userentity.IisRegulatoryPolicyUserEntity;

 /**
 * 功能描述：监管政策表service接口
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-27 09:56:48
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
public interface IisRegulatoryPolicyService extends IBaseService<IisRegulatoryPolicy, String> {

	/**
	 * 功能描述：新增监管政策表
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-27 09:56:48
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisRegulatoryPolicy
	 * @return
	 */
	Map saveIisRegulatoryPolicy(IisRegulatoryPolicy iisRegulatoryPolicy);

	/**
	 * 功能描述：修改监管政策表
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-27 09:56:48
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisRegulatoryPolicy
	 * @return
	 */
	Map updateIisRegulatoryPolicy(IisRegulatoryPolicy iisRegulatoryPolicy);

	/**
	 * 功能描述：删除监管政策表（批量）
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-27 09:56:48
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param idStrs
	 * @return
	 */
	Map delIisRegulatoryPolicy(String idStrs);

	/**
	 * 功能描述：根据ID查询监管政策表信息
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-27 09:56:48
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param id
	 * @return
	 */
	Map queryIisRegulatoryPolicyById(String id);

	 /**
	 * 功能描述：查询监管政策表列表信息
	 * 
	 * @author 刘慧卿
	 *         <p>
	 *         创建时间 ：2017-11-27 09:56:48
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param ue
	 * @return
	 */
	Map queryIisRegulatoryPolicyList(IisRegulatoryPolicyUserEntity ue);

	Map queryList(IisRegulatoryPolicyUserEntity para);

}
