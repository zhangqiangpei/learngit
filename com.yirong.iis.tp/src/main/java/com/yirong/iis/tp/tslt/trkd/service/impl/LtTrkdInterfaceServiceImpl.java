package com.yirong.iis.tp.tslt.trkd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.commons.util.order.Order;
import com.yirong.iis.tp.common.constant.LtConstant;
import com.yirong.iis.tp.common.dao.LtTrkdInterfaceDao;
import com.yirong.iis.tp.common.entity.LtTrkdInterfaceEntity;
import com.yirong.iis.tp.tslt.trkd.service.ILtTrkdInterfaceService;

@Service("ltTrkdInterfaceServiceImpl")
public class LtTrkdInterfaceServiceImpl extends BaseService<LtTrkdInterfaceEntity,String> implements ILtTrkdInterfaceService{
	
	@Autowired
	private LtTrkdInterfaceDao ltTrkdInterfaceDao;
	
	@Override
	public IBaseDao<LtTrkdInterfaceEntity, String> getBaseDao() {
		return ltTrkdInterfaceDao;
	}

	@Override
	public void initUrlCache() {
		List<LtTrkdInterfaceEntity> list = this.getAll(Order.basicOrder());
		LtConstant.trkdMap.clear();
		for(LtTrkdInterfaceEntity lt : list){
			LtConstant.trkdMap.put(lt.getId(), lt.getUrl());
		}
		
	}

}
