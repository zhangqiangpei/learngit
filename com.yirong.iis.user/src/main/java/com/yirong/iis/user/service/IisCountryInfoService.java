package com.yirong.iis.user.service;

import java.util.Map;

import com.yirong.awaken.core.service.IBaseService;
import com.yirong.iis.user.entity.IisCountryInfo;
import com.yirong.iis.user.userentity.IisCountryInfoUserEntity;

/**
 * 功能描述：国家信息表service接口
 *
 * @author 林明铁
 *         <p>
 *         创建时间 ：2017-11-21 15:44:24
 *         </p>
 *
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
public interface IisCountryInfoService extends IBaseService<IisCountryInfo, String> {

	Map queryList(IisCountryInfoUserEntity para);

	Map queryIisCountryInfo(IisCountryInfoUserEntity para);
	
	Map queryCountrys(IisCountryInfoUserEntity para);
}
