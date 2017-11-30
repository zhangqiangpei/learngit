package com.yirong.iis.user.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.awaken.core.dao.specification.RestrictionNames;
import com.yirong.awaken.core.dao.specification.SimpleSpecificationBuilder;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.awaken.core.util.ResultUtil;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.commons.util.datatype.StringUtil;
import com.yirong.commons.util.entity.PageEntiry;
import com.yirong.commons.util.error.ErrorPromptInfoUtil;
import com.yirong.iis.user.dao.IisThematicItemDao;
import com.yirong.iis.user.entity.IisThematicItem;
import com.yirong.iis.user.service.IisThematicItemService;
import com.yirong.iis.user.userentity.IisThematicItemUserEntity;

 /**
 * 功能描述：专题模块表service实现类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-24 15:49:14
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
@Service("IisThematicItemServiceImpl")
public class IisThematicItemServiceImpl extends BaseService<IisThematicItem, String>
		implements IisThematicItemService {

	/**
	 * 日志操作类
	 */
	private Logger logger = LoggerFactory
			.getLogger(IisThematicItemServiceImpl.class);

	/**
	 * dao注入
	 */
	@Autowired
	private IisThematicItemDao iisThematicItemDao;

	 /**
	 * 功能描述：获取dao操作类
	 * 
	 * @author howie lee
	 *         <p>
	 *         创建时间 ：2017-11-24 15:49:14
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	@Override
	public IBaseDao<IisThematicItem, String> getBaseDao() {
		return iisThematicItemDao;
	}

	/**
	 * 功能描述：新增专题模块表
	 * 
	 * @author howie lee
	 *         <p>
	 *         创建时间 ：2017-11-24 15:49:14
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisThematicItem
	 * @return
	 */
	@Override
	public Map saveIisThematicItem(IisThematicItem iisThematicItem){
		long count = this.entityCount("thematicItemId", iisThematicItem.getThematicItemId());
		if(count > 0){
			logger.info("专题模块项已存在");
			return ResultUtil.newError("专题模块项已存在!").toMap();
		}
		
		this.save(iisThematicItem);
		return ResultUtil.newOk("操作成功").toMap();
	}

	/**
	 * 功能描述：批量新增专题模块表
	 * 
	 * @author howie lee
	 *         <p>
	 *         创建时间 ：2017-11-24 15:49:14
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisThematicItems
	 * @return
	 */
	@Override
	public Map saveBatchIisThematicItem(List<IisThematicItem> iisThematicItems){
		this.saveAll(iisThematicItems);
		return ResultUtil.newOk("操作成功").toMap();
	}
	
	/**
	 * 功能描述：修改专题模块表
	 * 
	 * @author howie lee
	 *         <p>
	 *         创建时间 ：2017-11-24 15:49:14
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisThematicItem
	 * @return
	 */
	@Override
	public Map updateIisThematicItem(IisThematicItem iisThematicItem) {
		// 根据编号Id
		IisThematicItem iisThematicItemTemp = this.iisThematicItemDao.findOne(iisThematicItem
				.getId());
		if (null == iisThematicItemTemp) {// 未查询到任何数据
			String promptInfo = "不存在ID：" + iisThematicItem.getId();
			String result = ErrorPromptInfoUtil.getErrorInfo("00202",
					promptInfo);
			logger.warn(result);
			return ResultUtil.newError(result).toMap();
		} else {// 有该数据
			return saveIisThematicItem(iisThematicItem);
		}
	}
	
	/**
	 * 功能描述：删除专题模块表（批量）
	 * 
	 * @author howie lee
	 *         <p>
	 *         创建时间 ：2017-11-24 15:49:14
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param idStrs
	 * @return
	 */
	 @Override
	public Map delIisThematicItem(String idStrs) {
		// 处理id集合串
		String[] ids = idStrs.split(",");
		/** 先判断所有ID是否允许删除 **/
		StringBuffer sb = new StringBuffer();
		for (String id : ids) {
			// 判断ID是否存在
			IisThematicItem iisThematicItem = this.iisThematicItemDao.findOne(id);
			if (null == iisThematicItem) {// 不存在直接跳出循环
				String promptInfo = "不存在ID：" + id;
				sb.append(ErrorPromptInfoUtil.getErrorInfo("00202", promptInfo));
				break;
			}
		}
		// 处理业务
		if (StringUtil.isNullOrEmpty(sb.toString())) {// 所有数据均允许删除
			// 将id循环拼装成序列化集合
			for (String id : ids) {
				this.iisThematicItemDao.delete(id);// 物理删除
			}
			return ResultUtil.newOk("操作成功").toMap();
		} else {// 不允许删除
			logger.warn(sb.toString());
			return ResultUtil.newError(sb.toString()).toMap();
		}
	}

	/**
	 * 功能描述：根据ID查询专题模块表信息
	 * 
	 * @author howie lee
	 *         <p>
	 *         创建时间 ：2017-11-24 15:49:14
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param id
	 * @return
	 */
	@Override
	public Map queryIisThematicItemById(String id) {
		IisThematicItem iisThematicItem = this.get(id);
		return ResultUtil.newOk("操作成功").setData(iisThematicItem).toMap();
	}

	/**
	 * 功能描述：查询专题模块表列表信息
	 * 
	 * @author howie lee
	 *         <p>
	 *         创建时间 ：2017-11-24 15:49:14
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param ue
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map queryIisThematicItemList(IisThematicItemUserEntity ue){

		SimpleSpecificationBuilder simpbuilder = new SimpleSpecificationBuilder();
		
		if(StringUtil.isNotNullOrEmpty(ue.getThematicId())){
			simpbuilder.add("thematicId", RestrictionNames.EQ, ue.getThematicId());
		}

		// 获取数据
		PageEntiry pageEntiry = this.findPage(simpbuilder.getOpers(), ue);
	 
		return ResultUtil.newOk("操作成功").setData(pageEntiry).toMap();
	}

}
