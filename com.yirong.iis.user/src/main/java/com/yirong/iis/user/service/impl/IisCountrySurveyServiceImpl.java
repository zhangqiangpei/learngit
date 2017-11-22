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
import com.yirong.iis.user.dao.IisCountrySurveyDao;
import com.yirong.iis.user.entity.IisCountrySurvey;
import com.yirong.iis.user.service.IisCountrySurveyService;
import com.yirong.iis.user.userentity.IisCountrySurveyUserEntity;

 /**
 * 功能描述：国家概括表service实现类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-22 14:07:44
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
@Service("IisCountrySurveyServiceImpl")
public class IisCountrySurveyServiceImpl extends BaseService<IisCountrySurvey, String>
		implements IisCountrySurveyService {

	/**
	 * 日志操作类
	 */
	private Logger logger = LoggerFactory
			.getLogger(IisCountrySurveyServiceImpl.class);

	/**
	 * dao注入
	 */
	@Autowired
	private IisCountrySurveyDao iisCountrySurveyDao;

	 /**
	 * 功能描述：获取dao操作类
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-22 14:07:44
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	@Override
	public IBaseDao<IisCountrySurvey, String> getBaseDao() {
		return iisCountrySurveyDao;
	}

	/**
	 * 功能描述：新增国家概括表
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-22 14:07:44
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisCountrySurvey
	 * @return
	 */
	@Override
	public Map saveIisCountrySurvey(IisCountrySurvey iisCountrySurvey){
//		// 根据编码及分类ID获取数据（唯一键）
//		IisCountrySurvey iisCountrySurveyTemp = this.iisCountrySurveyDao
//				.findBy_____(_____);
//		String id = iisCountrySurvey.getId();
//		if (null == iisCountrySurveyTemp
//				|| (StringUtil.isNotNullOrEmpty(id) && id.equals(iisCountrySurveyTemp
//						.getId()))) {// 该唯一键不存在 或者为“修改操作且修改本身数据”
//			id = iisCountrySurvey.getId();
//			if (StringUtil.isNotNullOrEmpty(id)) {// 修改
//				// 获取数据库对象
//				iisCountrySurveyTemp = this.iisCountrySurveyDao.findOne(id);
//				// 复制属性
//				BeanUtil.copyPropertiesIgnoreNull(iisCountrySurvey, iisCountrySurveyTemp);
//				this.save(iisCountrySurveyTemp);
//			} else {
//				this.save(iisCountrySurvey);
//			}
//			return ResultUtil.newOk("操作成功").toMap();
//		} else {// 该名称及父类ID已存在
//			String result = ErrorPromptInfoUtil.getErrorInfo("00201");
//			logger.warn(result);
//			return ResultUtil.newError(result).toMap();
//		}
		return null;
	}

	/**
	 * 功能描述：修改国家概括表
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-22 14:07:44
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisCountrySurvey
	 * @return
	 */
	@Override
	public Map updateIisCountrySurvey(IisCountrySurvey iisCountrySurvey) {
		// 根据编号Id
		IisCountrySurvey iisCountrySurveyTemp = this.iisCountrySurveyDao.findOne(iisCountrySurvey
				.getId());
		if (null == iisCountrySurveyTemp) {// 未查询到任何数据
			String promptInfo = "不存在ID：" + iisCountrySurvey.getId();
			String result = ErrorPromptInfoUtil.getErrorInfo("00202",
					promptInfo);
			logger.warn(result);
			return ResultUtil.newError(result).toMap();
		} else {// 有该数据
			return saveIisCountrySurvey(iisCountrySurvey);
		}
	}
	
	/**
	 * 功能描述：删除国家概括表（批量）
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-22 14:07:44
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param idStrs
	 * @return
	 */
	 @Override
	public Map delIisCountrySurvey(String idStrs) {
		// 处理id集合串
		String[] ids = idStrs.split(",");
		/** 先判断所有ID是否允许删除 **/
		StringBuffer sb = new StringBuffer();
		for (String id : ids) {
			// 判断ID是否存在
			IisCountrySurvey iisCountrySurvey = this.iisCountrySurveyDao.findOne(id);
			if (null == iisCountrySurvey) {// 不存在直接跳出循环
				String promptInfo = "不存在ID：" + id;
				sb.append(ErrorPromptInfoUtil.getErrorInfo("00202", promptInfo));
				break;
			}
		}
		// 处理业务
		if (StringUtil.isNullOrEmpty(sb.toString())) {// 所有数据均允许删除
			// 将id循环拼装成序列化集合
			for (String id : ids) {
				this.iisCountrySurveyDao.delete(id);// 物理删除
			}
			return ResultUtil.newOk("操作成功").toMap();
		} else {// 不允许删除
			logger.warn(sb.toString());
			return ResultUtil.newError(sb.toString()).toMap();
		}
	}

	/**
	 * 功能描述：根据ID查询国家概括表信息
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-22 14:07:44
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param id
	 * @return
	 */
	@Override
	public Map queryIisCountrySurveyById(String id) {
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
	 * 功能描述：查询国家概括表列表信息
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-22 14:07:44
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param ue
	 * @return
	 */
	@Override
	public Map queryIisCountrySurveyList(IisCountrySurveyUserEntity ue){
		// 拼装查询sql
//		List<Object> param = new ArrayList<Object>();
//		StringBuffer sql = new StringBuffer();
//		sql.append("");
//		sql.append("");
//		sql.append("");
//		sql.append("");
//		// 标准编码
//		if (StringUtil.isNotNullOrEmpty(ue.get_____())) {
//			sql.append("");
//			param.add("%");
//		}
//		// 获取数据
//		PageEntiry pageEntiry = this.findPageSQLMap(sql.toString(), param,
//				null, ue);
//		return ResultUtil.newOk("操作成功")
//				.setData(pageEntiry).toMap();
		return null;
	}

}
