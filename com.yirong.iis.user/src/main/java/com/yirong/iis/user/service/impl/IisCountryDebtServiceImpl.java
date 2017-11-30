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
import com.yirong.iis.user.dao.IisCountryDebtDao;
import com.yirong.iis.user.entity.IisCountryDebt;
import com.yirong.iis.user.service.IisCountryDebtService;
import com.yirong.iis.user.userentity.IisCountryDebtUserEntity;

 /**
 * 功能描述：国家负债表service实现类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-29 09:44:05
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
@Service("IisCountryDebtServiceImpl")
public class IisCountryDebtServiceImpl extends BaseService<IisCountryDebt, String>
		implements IisCountryDebtService {

	/**
	 * 日志操作类
	 */
	private Logger logger = LoggerFactory
			.getLogger(IisCountryDebtServiceImpl.class);

	/**
	 * dao注入
	 */
	@Autowired
	private IisCountryDebtDao iisCountryDebtDao;

	 /**
	 * 功能描述：获取dao操作类
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-29 09:44:05
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	@Override
	public IBaseDao<IisCountryDebt, String> getBaseDao() {
		return iisCountryDebtDao;
	}

	/**
	 * 功能描述：新增国家负债表
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-29 09:44:05
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisCountryDebt
	 * @return
	 */
	@Override
	public Map saveIisCountryDebt(IisCountryDebt iisCountryDebt){
		// 根据编码及分类ID获取数据（唯一键）
		/*IisCountryDebt iisCountryDebtTemp = this.iisCountryDebtDao
				.findBy_____(_____);
		String id = iisCountryDebt.getId();
		if (null == iisCountryDebtTemp
				|| (StringUtil.isNotNullOrEmpty(id) && id.equals(iisCountryDebtTemp
						.getId()))) {// 该唯一键不存在 或者为“修改操作且修改本身数据”
			id = iisCountryDebt.getId();
			if (StringUtil.isNotNullOrEmpty(id)) {// 修改
				// 获取数据库对象
				iisCountryDebtTemp = this.iisCountryDebtDao.findOne(id);
				// 复制属性
				BeanUtil.copyPropertiesIgnoreNull(iisCountryDebt, iisCountryDebtTemp);
				this.save(iisCountryDebtTemp);
			} else {
				this.save(iisCountryDebt);
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
	 * 功能描述：修改国家负债表
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-29 09:44:05
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisCountryDebt
	 * @return
	 */
	@Override
	public Map updateIisCountryDebt(IisCountryDebt iisCountryDebt) {
		// 根据编号Id
		IisCountryDebt iisCountryDebtTemp = this.iisCountryDebtDao.findOne(iisCountryDebt
				.getId());
		if (null == iisCountryDebtTemp) {// 未查询到任何数据
			String promptInfo = "不存在ID：" + iisCountryDebt.getId();
			String result = ErrorPromptInfoUtil.getErrorInfo("00202",
					promptInfo);
			logger.warn(result);
			return ResultUtil.newError(result).toMap();
		} else {// 有该数据
			return saveIisCountryDebt(iisCountryDebt);
		}
	}
	
	/**
	 * 功能描述：删除国家负债表（批量）
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-29 09:44:05
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param idStrs
	 * @return
	 */
	 @Override
	public Map delIisCountryDebt(String idStrs) {
		// 处理id集合串
		String[] ids = idStrs.split(",");
		/** 先判断所有ID是否允许删除 **/
		StringBuffer sb = new StringBuffer();
		for (String id : ids) {
			// 判断ID是否存在
			IisCountryDebt iisCountryDebt = this.iisCountryDebtDao.findOne(id);
			if (null == iisCountryDebt) {// 不存在直接跳出循环
				String promptInfo = "不存在ID：" + id;
				sb.append(ErrorPromptInfoUtil.getErrorInfo("00202", promptInfo));
				break;
			}
		}
		// 处理业务
		if (StringUtil.isNullOrEmpty(sb.toString())) {// 所有数据均允许删除
			// 将id循环拼装成序列化集合
			for (String id : ids) {
				this.iisCountryDebtDao.delete(id);// 物理删除
			}
			return ResultUtil.newOk("操作成功").toMap();
		} else {// 不允许删除
			logger.warn(sb.toString());
			return ResultUtil.newError(sb.toString()).toMap();
		}
	}

	/**
	 * 功能描述：根据ID查询国家负债表信息
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-29 09:44:05
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param id
	 * @return
	 */
	@Override
	public Map queryIisCountryDebtById(String id) {
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
	 * 功能描述：查询国家负债表列表信息
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-29 09:44:05
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param ue
	 * @return
	 */
	@Override
	public Map queryIisCountryDebtList(IisCountryDebtUserEntity ue){
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
	public Map queryIisCountryDebtByName(String englishName) {
		if(StringUtil.isNullOrEmpty(englishName)){
			return ResultUtil.newError("参数错误!").toMap();
		}
		IisCountryDebt entity = this.findByProperty("englishName", englishName);
		return ResultUtil.newOk("查询成功!").setData(entity).toMap();
	}

}
