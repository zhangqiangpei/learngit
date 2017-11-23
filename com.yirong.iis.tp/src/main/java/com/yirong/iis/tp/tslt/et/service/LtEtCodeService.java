package com.yirong.iis.tp.tslt.et.service;

import java.util.List;

import com.yirong.awaken.core.service.IBaseService;
import com.yirong.iis.tp.common.entity.LtEtCode;

/**
 * 功能描述：elektron代码表service接口
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
public interface LtEtCodeService extends IBaseService<LtEtCode, String> {

	/**
	 * 功能描述：新增路透编码
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年11月23日 下午8:58:29
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @param ltEtCode
	 *
	 */
	LtEtCode doLtEtCode(String code, String pcode, boolean isLink);

	/**
	 * 功能描述：根据路透编码获取代码信息（缓存）
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年11月22日 下午8:06:25
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @return
	 *
	 */
	LtEtCode cacheFindByRicCode(String ricCode);

	/**
	 * 功能描述：查询所有编码信息
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年11月22日 下午8:41:19
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @return
	 *
	 */
	List<LtEtCode> findAll();

}
