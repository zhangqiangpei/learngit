package com.yirong.iis.tp.tslt.et.service;

import java.util.List;

import com.yirong.awaken.core.service.IBaseService;
import com.yirong.iis.tp.common.entity.LtEtData;
import com.yirong.iis.tp.tslt.et.userentity.LtEtDataUserEntity;

/**
 * 功能描述：elektron数据表service接口
 * 
 * @author
 *         <p>
 *         创建时间 ：2017-11-20 15:53:48
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public interface LtEtDataService extends IBaseService<LtEtData, String> {

	/**
	 * 功能描述：新增elektron数据表
	 * 
	 * @author
	 *         <p>
	 *         创建时间 ：2017-11-20 15:53:48
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param ltEtData
	 * @return
	 */
	void doLtEtData(List<LtEtDataUserEntity> ueList);

}
