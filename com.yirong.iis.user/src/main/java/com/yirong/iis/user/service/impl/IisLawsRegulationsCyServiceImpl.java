package com.yirong.iis.user.service.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.awaken.core.dao.specification.RestrictionNames;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.awaken.core.util.ResultUtil;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.commons.util.datatype.StringUtil;
import com.yirong.commons.util.error.ErrorPromptInfoUtil;
import com.yirong.iis.user.dao.IisLawsRegulationsCyDao;
import com.yirong.iis.user.entity.IisLawsRegulations;
import com.yirong.iis.user.entity.IisLawsRegulationsCy;
import com.yirong.iis.user.service.IisLawsRegulationsCyService;
import com.yirong.iis.user.service.IisLawsRegulationsService;
import com.yirong.iis.user.userentity.IisLawsRegulationsCyUserEntity;

 /**
 * 功能描述：法律法规分类service实现类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-27 16:13:26
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
@Service("IisLawsRegulationsCyServiceImpl")
public class IisLawsRegulationsCyServiceImpl extends BaseService<IisLawsRegulationsCy, String>
		implements IisLawsRegulationsCyService {
	/**
	 * 日志操作类
	 */
	private Logger logger = LoggerFactory
			.getLogger(IisLawsRegulationsCyServiceImpl.class);

	@Autowired
	private IisLawsRegulationsService iisLawsRegulationsService;
	/**
	 * dao注入
	 */
	@Autowired
	private IisLawsRegulationsCyDao iisLawsRegulationsCyDao;

	 /**
	 * 功能描述：获取dao操作类
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-27 16:13:26
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	@Override
	public IBaseDao<IisLawsRegulationsCy, String> getBaseDao() {
		return iisLawsRegulationsCyDao;
	}

	/**
	 * 功能描述：新增法律法规分类
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-27 16:13:26
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisLawsRegulationsCy
	 * @return
	 */
	@Override
	public Map saveIisLawsRegulationsCy(IisLawsRegulationsCy iisLawsRegulationsCy){
		// 根据编码及分类ID获取数据（唯一键）
		/*IisLawsRegulationsCy iisLawsRegulationsCyTemp = this.iisLawsRegulationsCyDao
				.findBy_____(_____);
		String id = iisLawsRegulationsCy.getId();
		if (null == iisLawsRegulationsCyTemp
				|| (StringUtil.isNotNullOrEmpty(id) && id.equals(iisLawsRegulationsCyTemp
						.getId()))) {// 该唯一键不存在 或者为“修改操作且修改本身数据”
			id = iisLawsRegulationsCy.getId();
			if (StringUtil.isNotNullOrEmpty(id)) {// 修改
				// 获取数据库对象
				iisLawsRegulationsCyTemp = this.iisLawsRegulationsCyDao.findOne(id);
				// 复制属性
				BeanUtil.copyPropertiesIgnoreNull(iisLawsRegulationsCy, iisLawsRegulationsCyTemp);
				this.save(iisLawsRegulationsCyTemp);
			} else {
				this.save(iisLawsRegulationsCy);
			}
			return ResultUtil.newOk("操作成功").toMap();
		} else {// 该名称及父类ID已存在
			String result = ErrorPromptInfoUtil.getErrorInfo("00201");
			logger.warn(result);
			return ResultUtil.newError(result).toMap();
		}*/
		return null;
	}

	/**
	 * 功能描述：修改法律法规分类
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-27 16:13:26
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisLawsRegulationsCy
	 * @return
	 */
	@Override
	public Map updateIisLawsRegulationsCy(IisLawsRegulationsCy iisLawsRegulationsCy) {
		// 根据编号Id
		IisLawsRegulationsCy iisLawsRegulationsCyTemp = this.iisLawsRegulationsCyDao.findOne(iisLawsRegulationsCy
				.getId());
		if (null == iisLawsRegulationsCyTemp) {// 未查询到任何数据
			String promptInfo = "不存在ID：" + iisLawsRegulationsCy.getId();
			String result = ErrorPromptInfoUtil.getErrorInfo("00202",
					promptInfo);
			logger.warn(result);
			return ResultUtil.newError(result).toMap();
		} else {// 有该数据
			return saveIisLawsRegulationsCy(iisLawsRegulationsCy);
		}
	}
	
	/**
	 * 功能描述：删除法律法规分类（批量）
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-27 16:13:26
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param idStrs
	 * @return
	 */
	 @Override
	public Map delIisLawsRegulationsCy(String idStrs) {
		// 处理id集合串
		String[] ids = idStrs.split(",");
		/** 先判断所有ID是否允许删除 **/
		StringBuffer sb = new StringBuffer();
		for (String id : ids) {
			// 判断ID是否存在
			IisLawsRegulationsCy iisLawsRegulationsCy = this.iisLawsRegulationsCyDao.findOne(id);
			if (null == iisLawsRegulationsCy) {// 不存在直接跳出循环
				String promptInfo = "不存在ID：" + id;
				sb.append(ErrorPromptInfoUtil.getErrorInfo("00202", promptInfo));
				break;
			}
		}
		// 处理业务
		if (StringUtil.isNullOrEmpty(sb.toString())) {// 所有数据均允许删除
			// 将id循环拼装成序列化集合
			for (String id : ids) {
				this.iisLawsRegulationsCyDao.delete(id);// 物理删除
			}
			return ResultUtil.newOk("操作成功").toMap();
		} else {// 不允许删除
			logger.warn(sb.toString());
			return ResultUtil.newError(sb.toString()).toMap();
		}
	}

	/**
	 * 功能描述：根据ID查询法律法规分类信息
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-27 16:13:26
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param id
	 * @return
	 */
	@Override
	public Map queryIisLawsRegulationsCyById(String id) {
		StringBuffer sql = new StringBuffer();
		sql.append("");
		sql.append("");
		sql.append("");
		sql.append(" ");
		sql.append("");
		List<Object> param = new ArrayList<Object>();
		Map map = this.exeSqlMap(sql.toString(), param);
		return ResultUtil.newOk("操作成功")
				.setData(map).toMap();
	}

	/**
	 * 功能描述：查询法律法规分类列表信息
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-27 16:13:26
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param ue
	 * @return
	 */
	@Override
	public Map queryIisLawsRegulationsCyList(IisLawsRegulationsCyUserEntity ue){
		// 拼装查询sql
		/*List<Object> param = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("");
		sql.append("");
		sql.append("");
		sql.append("");
		// 标准编码
		if (StringUtil.isNotNullOrEmpty(ue.get_____())) {
			sql.append("");
			param.add("%");
		}
		// 获取数据
		PageEntiry pageEntiry = this.findPageSQLMap(sql.toString(), param,
				null, ue);
		return ResultUtil.newOk("操作成功")
				.setData(pageEntiry).toMap();*/
		return null;
	}

	@Override
	public Map queryList(IisLawsRegulationsCyUserEntity para) {
		List<IisLawsRegulationsCy>  list = this.findByProperty("countryEngName",para.getCountryEngName(),RestrictionNames.EQ);
		Map<String,Object> result = new HashMap<>();
		if(null != list && list.size()>0){
			List<Map<String,Object>> resultLsit = new ArrayList<Map<String,Object>>();
			List<Object> param = new ArrayList<Object>();
			StringBuffer idStr = new StringBuffer();
			for (int i = 0; i < list.size(); i++) {
				idStr.append("'"+list.get(i).getId()+"'");
				if(i!=(list.size()-1)){
					idStr.append(",");
				}
			}
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ID \"id\",CLASSIFY_ID \"classifyId\",TITLE \"title\"");
			sql.append("FROM IIS_LAWS_REGULATIONS ");
			sql.append("WHERE CLASSIFY_ID in ( "+idStr+" )");
			resultLsit = this.exeNativeQueryMap(sql.toString(), param);
			
			result.put("cy", list);
			result.put("laws", resultLsit);
		}
		return ResultUtil.newOk("查询成功!").setData(result).toMap();
	}

}
