package com.yirong.iis.tp.tslt.trkd.service;

import com.yirong.awaken.core.service.IBaseService;
import com.yirong.iis.tp.common.entity.LtTrkdInterfaceEntity;

public interface ILtTrkdInterfaceService  extends IBaseService<LtTrkdInterfaceEntity,String>{
	
	/**
	 * 初始化url
	 */
	void initUrlCache();

}