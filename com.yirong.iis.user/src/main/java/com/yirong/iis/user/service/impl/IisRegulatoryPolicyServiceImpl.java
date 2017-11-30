package com.yirong.iis.user.service.impl;

import java.util.ArrayList;
import java.util.Date;
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
import com.yirong.iis.user.dao.IisRegulatoryPolicyDao;
import com.yirong.iis.user.entity.IisRegulatoryPolicy;
import com.yirong.iis.user.service.IisRegulatoryPolicyService;
import com.yirong.iis.user.userentity.IisRegulatoryPolicyUserEntity;

 /**
 * 功能描述：监管政策表service实现类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-27 09:56:48
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
@Service("IisRegulatoryPolicyServiceImpl")
public class IisRegulatoryPolicyServiceImpl extends BaseService<IisRegulatoryPolicy, String>
		implements IisRegulatoryPolicyService {

	/**
	 * 日志操作类
	 */
	private Logger logger = LoggerFactory
			.getLogger(IisRegulatoryPolicyServiceImpl.class);

	/**
	 * dao注入
	 */
	@Autowired
	private IisRegulatoryPolicyDao iisRegulatoryPolicyDao;

	 /**
	 * 功能描述：获取dao操作类
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-27 09:56:48
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	@Override
	public IBaseDao<IisRegulatoryPolicy, String> getBaseDao() {
		return iisRegulatoryPolicyDao;
	}

	/**
	 * 功能描述：新增监管政策表
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-27 09:56:48
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisRegulatoryPolicy
	 * @return
	 */
	@Override
	public Map saveIisRegulatoryPolicy(IisRegulatoryPolicy iisRegulatoryPolicy){
		// 根据编码及分类ID获取数据（唯一键）
		/*IisRegulatoryPolicy iisRegulatoryPolicyTemp = this.iisRegulatoryPolicyDao
				.findBy_____(_____);
		String id = iisRegulatoryPolicy.getId();
		if (null == iisRegulatoryPolicyTemp
				|| (StringUtil.isNotNullOrEmpty(id) && id.equals(iisRegulatoryPolicyTemp
						.getId()))) {// 该唯一键不存在 或者为“修改操作且修改本身数据”
			id = iisRegulatoryPolicy.getId();
			if (StringUtil.isNotNullOrEmpty(id)) {// 修改
				// 获取数据库对象
				iisRegulatoryPolicyTemp = this.iisRegulatoryPolicyDao.findOne(id);
				// 复制属性
				BeanUtil.copyPropertiesIgnoreNull(iisRegulatoryPolicy, iisRegulatoryPolicyTemp);
				this.save(iisRegulatoryPolicyTemp);
			} else {
				this.save(iisRegulatoryPolicy);
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
	 * 功能描述：修改监管政策表
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-27 09:56:48
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisRegulatoryPolicy
	 * @return
	 */
	@Override
	public Map updateIisRegulatoryPolicy(IisRegulatoryPolicy iisRegulatoryPolicy) {
		// 根据编号Id
		IisRegulatoryPolicy iisRegulatoryPolicyTemp = this.iisRegulatoryPolicyDao.findOne(iisRegulatoryPolicy
				.getId());
		if (null == iisRegulatoryPolicyTemp) {// 未查询到任何数据
			String promptInfo = "不存在ID：" + iisRegulatoryPolicy.getId();
			String result = ErrorPromptInfoUtil.getErrorInfo("00202",
					promptInfo);
			logger.warn(result);
			return ResultUtil.newError(result).toMap();
		} else {// 有该数据
			return saveIisRegulatoryPolicy(iisRegulatoryPolicy);
		}
	}
	
	/**
	 * 功能描述：删除监管政策表（批量）
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-27 09:56:48
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param idStrs
	 * @return
	 */
	 @Override
	public Map delIisRegulatoryPolicy(String idStrs) {
		// 处理id集合串
		String[] ids = idStrs.split(",");
		/** 先判断所有ID是否允许删除 **/
		StringBuffer sb = new StringBuffer();
		for (String id : ids) {
			// 判断ID是否存在
			IisRegulatoryPolicy iisRegulatoryPolicy = this.iisRegulatoryPolicyDao.findOne(id);
			if (null == iisRegulatoryPolicy) {// 不存在直接跳出循环
				String promptInfo = "不存在ID：" + id;
				sb.append(ErrorPromptInfoUtil.getErrorInfo("00202", promptInfo));
				break;
			}
		}
		// 处理业务
		if (StringUtil.isNullOrEmpty(sb.toString())) {// 所有数据均允许删除
			// 将id循环拼装成序列化集合
			for (String id : ids) {
				this.iisRegulatoryPolicyDao.delete(id);// 物理删除
			}
			return ResultUtil.newOk("操作成功").toMap();
		} else {// 不允许删除
			logger.warn(sb.toString());
			return ResultUtil.newError(sb.toString()).toMap();
		}
	}

	/**
	 * 功能描述：根据ID查询监管政策表信息
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-27 09:56:48
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param id
	 * @return
	 */
	@Override
	public Map queryIisRegulatoryPolicyById(String id) {
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
	 * 功能描述：查询监管政策表列表信息
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-27 09:56:48
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param ue
	 * @return
	 */
	@Override
	public Map queryIisRegulatoryPolicyList(IisRegulatoryPolicyUserEntity ue){
		/*// 拼装查询sql
		List<Object> param = new ArrayList<Object>();
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
	public Map queryList(IisRegulatoryPolicyUserEntity para) {
		if(null == para ){
			return ResultUtil.newError("参数错误!").toMap();
		}
		List<Object> param = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ID \"id\",FIlE_NAME \"fileName\",SOURCE \"source\",LANGUAGE \"language\",DATE_FORMAT(CREATE_TIME,'%Y-%m-%d') \"createTime\"");
		sql.append(" FROM IIS_REGULATORY_POLICY");
		sql.append(" WHERE 1=1");
		if(StringUtil.isNotNullOrEmpty(para.getCountryEngName())){
			sql.append(" AND COUNTRY_ENG_NAME = ?");
			param.add(para.getCountryEngName());
		}
		logger.info("查询成功");
		List<Map<String,Object>> result =  this.exeNativeQueryMap(sql.toString(), param);
		
		return ResultUtil.newOk("查询成功!").setData(result).toMap();	
	}

}
