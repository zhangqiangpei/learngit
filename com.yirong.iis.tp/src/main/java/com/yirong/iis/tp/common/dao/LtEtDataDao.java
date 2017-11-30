package com.yirong.iis.tp.common.dao;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.iis.tp.common.entity.LtEtData;

/**
 * 功能描述：elektron数据表dao接口
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
public interface LtEtDataDao extends IBaseDao<LtEtData, String> {

	/**
	 * 功能描述：根据编码ID及字段ID获取数据
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年11月22日 下午1:59:27
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @param codeId
	 * @param fieldId
	 *
	 */
	LtEtData findByCodeIdAndFieldId(String codeId, String fieldId);
}
