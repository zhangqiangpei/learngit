package com.yirong.iis.mm.service;

import java.util.Map;

import com.yirong.awaken.core.service.IBaseService;
import com.yirong.iis.mm.entity.IisDiplomaticSituation;
import com.yirong.iis.mm.userentity.IisDiplomaticSituationUserEntity;

/**
 * 功能描述：外交情况表(与中国)service接口
 * 
 * @author 陈清沣
 *         <p>
 *         创建时间 ：2017-11-27 19:29:29
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
public interface IisDiplomaticSituationService extends IBaseService<IisDiplomaticSituation, String> {

	/**
	 * 功能描述：新增外交情况表(与中国)
	 * 
	 * @author 陈清沣
	 *         <p>
	 *         创建时间 ：2017-11-27 19:29:29
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisDiplomaticSituation
	 * @return
	 */
	Map saveIisDiplomaticSituation(IisDiplomaticSituation iisDiplomaticSituation);

	/**
	 * 功能描述：修改外交情况表(与中国)
	 * 
	 * @author 陈清沣
	 *         <p>
	 *         创建时间 ：2017-11-27 19:29:29
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisDiplomaticSituation
	 * @return
	 */
	Map updateIisDiplomaticSituation(IisDiplomaticSituation iisDiplomaticSituation);

	/**
	 * 功能描述：删除外交情况表(与中国)（批量）
	 * 
	 * @author 陈清沣
	 *         <p>
	 *         创建时间 ：2017-11-27 19:29:29
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param idStrs
	 * @return
	 */
	Map delIisDiplomaticSituation(String idStrs);

	 /**
	 * 功能描述：查询外交情况表(与中国)列表信息
	 * 
	 * @author 陈清沣
	 *         <p>
	 *         创建时间 ：2017-11-27 19:29:29
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param ue
	 * @return
	 */
	Map queryIisDiplomaticSituationList(IisDiplomaticSituationUserEntity ue);

}
