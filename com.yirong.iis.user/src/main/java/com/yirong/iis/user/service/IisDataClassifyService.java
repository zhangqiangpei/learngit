package com.yirong.iis.user.service;

import java.util.Map;

import com.yirong.awaken.core.service.IBaseService;
import com.yirong.iis.user.entity.IisDataClassify;

 /**
 * 功能描述：数据分类表service接口
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-22 09:43:03
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
public interface IisDataClassifyService extends IBaseService<IisDataClassify, String> {
	
	/**
	 * 获取一级分类
	 * @return
	 */
	Map getFirstClassify();
	
	/**
	 * 获取全部二级节点与指标信息
	 * @param infoName 
	 * @return
	 */
	Map getSecondClassifyAndInfo(String infoName);

	

}
