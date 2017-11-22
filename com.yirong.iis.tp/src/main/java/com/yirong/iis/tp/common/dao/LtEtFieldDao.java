package com.yirong.iis.tp.common.dao;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.iis.tp.common.entity.LtEtField;

/**
 * 功能描述：elektron字段表dao接口
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
public interface LtEtFieldDao extends IBaseDao<LtEtField, String> {

	/**
	 * 功能描述：根据字段ID获取字段信息
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 * 		创建时间 ：2017年11月22日 下午8:27:53
	 *         </p>
	 *
	 *         <p>
	 * 		修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @param fieldId
	 * @return
	 *
	 */
	LtEtField findByFieldId(String fieldId);
}
