package com.yirong.iis.mm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yirong.iis.mm.dao.IisDiplomaticSituationDao;
import com.yirong.iis.mm.entity.IisDiplomaticSituation;
import com.yirong.iis.mm.service.IisDiplomaticSituationService;
import com.yirong.iis.mm.userentity.IisDiplomaticSituationUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.commons.util.entity.PageEntiry;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.awaken.core.util.BeanUtil;
import com.yirong.awaken.core.util.ResultUtil;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.commons.util.datatype.StringUtil;
import com.yirong.commons.util.error.ErrorPromptInfoUtil;

 /**
 * 功能描述：外交情况表(与中国)service实现类
 * 
 * @author 陈清沣
 *         <p>
 *         创建时间 ：2017-11-27 19:29:29
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
@Service("IisDiplomaticSituationServiceImpl")
public class IisDiplomaticSituationServiceImpl extends BaseService<IisDiplomaticSituation, String>
		implements IisDiplomaticSituationService {

	/**
	 * 日志操作类
	 */
	private Logger logger = LoggerFactory.getLogger(IisDiplomaticSituationServiceImpl.class);

	/**
	 * dao注入
	 */
	@Autowired
	private IisDiplomaticSituationDao iisDiplomaticSituationDao;

	 /**
	 * 功能描述：获取dao操作类
	 * 
	 * @author 陈清沣
	 *         <p>
	 *         创建时间 ：2017-11-27 19:29:29
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	@Override
	public IBaseDao<IisDiplomaticSituation, String> getBaseDao() {
		return iisDiplomaticSituationDao;
	}

	/**
	 * 功能描述：新增外交情况表(与中国)
	 * 
	 * @author 陈清沣
	 *         <p>
	 *         创建时间 ：2017-11-27 19:29:29
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisDiplomaticSituation
	 * @return
	 */
	@Override
	public Map saveIisDiplomaticSituation(IisDiplomaticSituation iisDiplomaticSituation){
		// 根据编码及分类ID获取数据（唯一键）
		IisDiplomaticSituation iisDiplomaticSituationTemp = this.iisDiplomaticSituationDao.findOne(iisDiplomaticSituation.getId());
		String id = iisDiplomaticSituation.getId();
		if (null == iisDiplomaticSituationTemp
				|| (StringUtil.isNotNullOrEmpty(id) && id.equals(iisDiplomaticSituationTemp
						.getId()))) {// 该唯一键不存在 或者为“修改操作且修改本身数据”
			id = iisDiplomaticSituation.getId();
			if (StringUtil.isNotNullOrEmpty(id)) {// 修改
				// 获取数据库对象
				iisDiplomaticSituationTemp = this.iisDiplomaticSituationDao.findOne(id);
				// 复制属性
				BeanUtil.copyPropertiesIgnoreNull(iisDiplomaticSituation, iisDiplomaticSituationTemp);
				this.save(iisDiplomaticSituationTemp);
			} else {
				this.save(iisDiplomaticSituation);
			}
			return ResultUtil.newOk("操作成功").toMap();
		} else {// 该名称及父类ID已存在
			String result = ErrorPromptInfoUtil.getErrorInfo("00201");
			logger.warn(result);
			return ResultUtil.newError(result).toMap();
		}
	}

	/**
	 * 功能描述：修改外交情况表(与中国)
	 * 
	 * @author 陈清沣
	 *         <p>
	 *         创建时间 ：2017-11-27 19:29:29
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisDiplomaticSituation
	 * @return
	 */
	@Override
	public Map updateIisDiplomaticSituation(IisDiplomaticSituation iisDiplomaticSituation) {
		// 根据编号Id
		IisDiplomaticSituation iisDiplomaticSituationTemp = this.iisDiplomaticSituationDao.findOne(iisDiplomaticSituation.getId());
		if (null == iisDiplomaticSituationTemp) {// 未查询到任何数据
			String promptInfo = "不存在ID：" + iisDiplomaticSituation.getId();
			String result = ErrorPromptInfoUtil.getErrorInfo("00202",
					promptInfo);
			logger.warn(result);
			return ResultUtil.newError(result).toMap();
		} else {// 有该数据
			return saveIisDiplomaticSituation(iisDiplomaticSituation);
		}
	}
	
	/**
	 * 功能描述：删除外交情况表(与中国)（批量）
	 * 
	 * @author 陈清沣
	 *         <p>
	 *         创建时间 ：2017-11-27 19:29:29
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param idStrs
	 * @return
	 */
	 @Override
	public Map delIisDiplomaticSituation(String idStrs) {
		// 处理id集合串
		String[] ids = idStrs.split(",");
		/** 先判断所有ID是否允许删除 **/
		StringBuffer sb = new StringBuffer();
		for (String id : ids) {
			// 判断ID是否存在
			IisDiplomaticSituation iisDiplomaticSituation = this.iisDiplomaticSituationDao.findOne(id);
			if (null == iisDiplomaticSituation) {// 不存在直接跳出循环
				String promptInfo = "不存在ID：" + id;
				sb.append(ErrorPromptInfoUtil.getErrorInfo("00202", promptInfo));
				break;
			}
		}
		// 处理业务
		if (StringUtil.isNullOrEmpty(sb.toString())) {// 所有数据均允许删除
			// 将id循环拼装成序列化集合
			for (String id : ids) {
				this.iisDiplomaticSituationDao.delete(id);// 物理删除
			}
			return ResultUtil.newOk("操作成功").toMap();
		} else {// 不允许删除
			logger.warn(sb.toString());
			return ResultUtil.newError(sb.toString()).toMap();
		}
	}

	/**
	 * 功能描述：根据ID查询外交情况表(与中国)信息
	 * 
	 * @author 陈清沣
	 *         <p>
	 *         创建时间 ：2017-11-27 19:29:29
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param id
	 * @return
	 */
	@Override
	public Map queryIisDiplomaticSituationById(String id) {
		StringBuffer sql = new StringBuffer();
		sql.append("");
		sql.append("");
		sql.append("");
		sql.append(" ");
		sql.append("");
		List<Object> param = new ArrayList<Object>();
		Map map = this.exeSqlMap(sql.toString(), param);
		return ResultUtil.newOk("操作成功").setData(map).toMap();
	}

	/**
	 * 功能描述：查询外交情况表(与中国)列表信息
	 * 
	 * @author 陈清沣
	 *         <p>
	 *         创建时间 ：2017-11-27 19:29:29
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param ue
	 * @return
	 */
	@Override
	public Map queryIisDiplomaticSituationList(IisDiplomaticSituationUserEntity ue){
		// 拼装查询sql
		List<Object> param = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT CONTENT,CREATE_TIME ");
		sql.append("FROM IIS_DIPLOMATIC_SITUATION ");
		sql.append("WHERE 1 = 1 ");
		// 国家英文名
		if (StringUtil.isNotNullOrEmpty(ue.getEnglishName())) {
			sql.append("AND ENGLISH_NAME = ?");
			param.add(ue.getEnglishName());
		}
		// 获取数据
		PageEntiry pageEntiry = this.findPageSQLMap(sql.toString(), param,null, ue);
		return ResultUtil.newOk("操作成功").setData(pageEntiry).toMap();
	}

}
