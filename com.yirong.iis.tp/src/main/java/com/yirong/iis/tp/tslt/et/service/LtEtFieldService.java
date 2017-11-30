package com.yirong.iis.tp.tslt.et.service;

import com.yirong.awaken.core.service.IBaseService;
import com.yirong.iis.tp.common.entity.LtEtField;

/**
 * 功能描述：elektron字段表service接口
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
public interface LtEtFieldService extends IBaseService<LtEtField, String> {

	/**
	 * 功能描述：根据字段ID获取字段信息（缓存）
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年11月22日 下午8:28:26
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @param fieldId
	 * @return
	 *
	 */
	LtEtField cacheFindByFieldId(String fieldId);
}
