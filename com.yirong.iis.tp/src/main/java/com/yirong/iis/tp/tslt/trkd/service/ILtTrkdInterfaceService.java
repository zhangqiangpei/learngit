package com.yirong.iis.tp.trkd.service;

import com.yirong.awaken.core.service.IBaseService;
import com.yirong.iis.tp.trkd.entity.LtTrkdInterfaceEntity;

public interface ILtTrkdInterfaceService  extends IBaseService<LtTrkdInterfaceEntity,String>{
	
	/**
	 * 初始化url
	 */
	void initUrlCache();

}