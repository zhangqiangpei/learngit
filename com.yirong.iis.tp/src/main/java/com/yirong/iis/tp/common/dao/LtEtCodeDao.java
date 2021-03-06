package com.yirong.iis.tp.common.dao;

import java.util.List;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.iis.tp.common.entity.LtEtCode;

/**
 * 功能描述：elektron代码表dao接口
 * 
 * @author
 *         <p>
 *         创建时间 ：2017-11-20 15:49:46
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public interface LtEtCodeDao extends IBaseDao<LtEtCode, String> {

	/**
	 * 功能描述：根据路透编码获取数据
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年11月22日 下午8:21:34
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @param ricCode
	 * @return
	 *
	 */
	LtEtCode findByRicCode(String ricCode);

	/**
	 * 功能描述：根据分类获取代码
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年11月24日 上午9:02:59
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @param codeType
	 * @return
	 *
	 */
	List<LtEtCode> findByCodeType(String codeType);
}
