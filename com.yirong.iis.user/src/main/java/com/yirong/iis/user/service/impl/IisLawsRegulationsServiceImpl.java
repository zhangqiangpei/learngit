package com.yirong.iis.user.service.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.awaken.core.util.BeanUtil;
import com.yirong.awaken.core.util.ResultUtil;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.commons.util.datatype.StringUtil;
import com.yirong.commons.util.entity.PageEntiry;
import com.yirong.commons.util.error.ErrorPromptInfoUtil;
import com.yirong.iis.user.dao.IisLawsRegulationsDao;
import com.yirong.iis.user.entity.IisLawsRegulations;
import com.yirong.iis.user.service.IisLawsRegulationsService;
import com.yirong.iis.user.userentity.IisLawsRegulationsUserEntity;

 /**
 * 功能描述：法律法规表service实现类
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
@Service("IisLawsRegulationsServiceImpl")
public class IisLawsRegulationsServiceImpl extends BaseService<IisLawsRegulations, String>
		implements IisLawsRegulationsService {

	/**
	 * 日志操作类
	 */
	private Logger logger = LoggerFactory
			.getLogger(IisLawsRegulationsServiceImpl.class);

	/**
	 * dao注入
	 */
	@Autowired
	private IisLawsRegulationsDao iisLawsRegulationsDao;

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
	public IBaseDao<IisLawsRegulations, String> getBaseDao() {
		return iisLawsRegulationsDao;
	}

	/**
	 * 功能描述：新增法律法规表
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-27 16:13:26
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisLawsRegulations
	 * @return
	 */
	@Override
	public Map saveIisLawsRegulations(IisLawsRegulations iisLawsRegulations){
		// 根据编码及分类ID获取数据（唯一键）
		/*IisLawsRegulations iisLawsRegulationsTemp = this.iisLawsRegulationsDao
				.findBy_____(_____);
		String id = iisLawsRegulations.getId();
		if (null == iisLawsRegulationsTemp
				|| (StringUtil.isNotNullOrEmpty(id) && id.equals(iisLawsRegulationsTemp
						.getId()))) {// 该唯一键不存在 或者为“修改操作且修改本身数据”
			id = iisLawsRegulations.getId();
			if (StringUtil.isNotNullOrEmpty(id)) {// 修改
				// 获取数据库对象
				iisLawsRegulationsTemp = this.iisLawsRegulationsDao.findOne(id);
				// 复制属性
				BeanUtil.copyPropertiesIgnoreNull(iisLawsRegulations, iisLawsRegulationsTemp);
				this.save(iisLawsRegulationsTemp);
			} else {
				this.save(iisLawsRegulations);
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
	 * 功能描述：修改法律法规表
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-27 16:13:26
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisLawsRegulations
	 * @return
	 */
	@Override
	public Map updateIisLawsRegulations(IisLawsRegulations iisLawsRegulations) {
		// 根据编号Id
		IisLawsRegulations iisLawsRegulationsTemp = this.iisLawsRegulationsDao.findOne(iisLawsRegulations
				.getId());
		if (null == iisLawsRegulationsTemp) {// 未查询到任何数据
			String promptInfo = "不存在ID：" + iisLawsRegulations.getId();
			String result = ErrorPromptInfoUtil.getErrorInfo("00202",
					promptInfo);
			logger.warn(result);
			return ResultUtil.newError(result).toMap();
		} else {// 有该数据
			return saveIisLawsRegulations(iisLawsRegulations);
		}
	}
	
	/**
	 * 功能描述：删除法律法规表（批量）
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
	public Map delIisLawsRegulations(String idStrs) {
		// 处理id集合串
		String[] ids = idStrs.split(",");
		/** 先判断所有ID是否允许删除 **/
		StringBuffer sb = new StringBuffer();
		for (String id : ids) {
			// 判断ID是否存在
			IisLawsRegulations iisLawsRegulations = this.iisLawsRegulationsDao.findOne(id);
			if (null == iisLawsRegulations) {// 不存在直接跳出循环
				String promptInfo = "不存在ID：" + id;
				sb.append(ErrorPromptInfoUtil.getErrorInfo("00202", promptInfo));
				break;
			}
		}
		// 处理业务
		if (StringUtil.isNullOrEmpty(sb.toString())) {// 所有数据均允许删除
			// 将id循环拼装成序列化集合
			for (String id : ids) {
				this.iisLawsRegulationsDao.delete(id);// 物理删除
			}
			return ResultUtil.newOk("操作成功").toMap();
		} else {// 不允许删除
			logger.warn(sb.toString());
			return ResultUtil.newError(sb.toString()).toMap();
		}
	}

	/**
	 * 功能描述：根据ID查询法律法规表信息
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
	public Map queryIisLawsRegulationsById(String id) {
		if(StringUtil.isNullOrEmpty(id)){
			return ResultUtil.newError("参数错误!").toMap();
		}
		List<Object> param = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ID \"id\",CLASSIFY_ID \"classifyId\",TITLE \"title\",CONTENT \"content\"");
		sql.append("FROM IIS_LAWS_REGULATIONS ");
		sql.append("WHERE ID = ?");
		param.add(id);
		Map map = this.exeSqlMap(sql.toString(), param);
		return ResultUtil.newOk("操作成功").setData(map).toMap();
	}

	/**
	 * 功能描述：查询法律法规表列表信息
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
	public Map queryIisLawsRegulationsList(IisLawsRegulationsUserEntity ue){
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

}
