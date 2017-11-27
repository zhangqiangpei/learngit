package com.yirong.iis.user.service;

import java.util.Map;

import com.yirong.awaken.core.service.IBaseService;
import com.yirong.iis.user.entity.IisDiplomaticSituation;
import com.yirong.iis.user.userentity.IisDiplomaticSituationUserEntity;

 /**
 * 功能描述：外交情况表(与中国)service接口
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-24 16:08:11
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
public interface IisDiplomaticSituationService extends IBaseService<IisDiplomaticSituation, String> {

	Map queryList(IisDiplomaticSituationUserEntity psue);

}
