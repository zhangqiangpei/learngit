package com.yirong.iis.user.service.impl;

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
import com.yirong.iis.user.dao.IisThematicDao;
import com.yirong.iis.user.entity.IisThematic;
import com.yirong.iis.user.service.IisThematicService;
import com.yirong.iis.user.userentity.IisThematicUserEntity;

import net.sf.json.JSONObject;

 /**
 * 功能描述：专题表service实现类
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
@Service("IisThematicServiceImpl")
public class IisThematicServiceImpl extends BaseService<IisThematic, String>
		implements IisThematicService {

	/**
	 * 日志操作类
	 */
	private Logger logger = LoggerFactory
			.getLogger(IisThematicServiceImpl.class);

	/**
	 * dao注入
	 */
	@Autowired
	private IisThematicDao iisThematicDao;

	 /**
	 * 功能描述：获取dao操作类
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-24 15:49:14
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	@Override
	public IBaseDao<IisThematic, String> getBaseDao() {
		return iisThematicDao;
	}

	/**
	 * 功能描述：新增专题表
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-24 15:49:14
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisThematic
	 * @return
	 */
	@Override
	public Map saveIisThematic(IisThematic iisThematic){
		long count = this.entityCount("thematicName", iisThematic.getThematicName());
		if(count > 0){
			logger.info("专题名称已存在");
			return ResultUtil.newError("专题名称已存在!").toMap();
		}
		
		this.save(iisThematic);
		JSONObject jo = new JSONObject();
		jo.put("id", iisThematic.getId());
		return ResultUtil.newOk("操作成功").setData(jo).toMap();
	}

	/**
	 * 功能描述：修改专题表
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-24 15:49:14
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisThematic
	 * @return
	 */
	@Override
	public Map updateIisThematic(IisThematic iisThematic) {
		// 根据编号Id
		IisThematic iisThematicTemp = this.iisThematicDao.findOne(iisThematic
				.getId());
		if (null == iisThematicTemp) {// 未查询到任何数据
			String promptInfo = "不存在ID：" + iisThematic.getId();
			String result = ErrorPromptInfoUtil.getErrorInfo("00202",
					promptInfo);
			logger.warn(result);
			return ResultUtil.newError(result).toMap();
		} else {// 有该数据
			return saveIisThematic(iisThematic);
		}
	}
	
	/**
	 * 功能描述：删除专题表（批量）
	 * 
	 * @author 林明铁
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
	public Map delIisThematic(String idStrs) {
		// 处理id集合串
		String[] ids = idStrs.split(",");
		/** 先判断所有ID是否允许删除 **/
		StringBuffer sb = new StringBuffer();
		for (String id : ids) {
			// 判断ID是否存在
			IisThematic iisThematic = this.iisThematicDao.findOne(id);
			if (null == iisThematic) {// 不存在直接跳出循环
				String promptInfo = "不存在ID：" + id;
				sb.append(ErrorPromptInfoUtil.getErrorInfo("00202", promptInfo));
				break;
			}
		}
		// 处理业务
		if (StringUtil.isNullOrEmpty(sb.toString())) {// 所有数据均允许删除
			// 将id循环拼装成序列化集合
			for (String id : ids) {
				this.iisThematicDao.delete(id);// 物理删除
			}
			return ResultUtil.newOk("操作成功").toMap();
		} else {// 不允许删除
			logger.warn(sb.toString());
			return ResultUtil.newError(sb.toString()).toMap();
		}
	}

	/**
	 * 功能描述：根据ID查询专题表信息
	 * 
	 * @author 林明铁
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
	public Map queryIisThematicById(String id) {
		IisThematic iisThematic = this.get(id);
		return ResultUtil.newOk("操作成功").setData(iisThematic).toMap();
	}

	/**
	 * 功能描述：查询专题表列表信息
	 * 
	 * @author 林明铁
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
	public Map queryIisThematicList(IisThematicUserEntity ue){
		SimpleSpecificationBuilder simpbuilder = new SimpleSpecificationBuilder();
		
		if(StringUtil.isNotNullOrEmpty(ue.getThematicClassify())){
			simpbuilder.add("thematicClassify", RestrictionNames.EQ, ue.getThematicClassify());
		}

		if(StringUtil.isNotNullOrEmpty(ue.getThematicName())){
			simpbuilder.add("thematicName", RestrictionNames.LIKE, ue.getThematicName());
		}
 
		// 获取数据
		PageEntiry pageEntiry = this.findPage(simpbuilder.getOpers(), ue);
	 
		return ResultUtil.newOk("操作成功").setData(pageEntiry).toMap();
	}

}
