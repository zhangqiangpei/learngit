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
import com.yirong.iis.user.dao.IisKeyEventsDao;
import com.yirong.iis.user.entity.IisKeyEvents;
import com.yirong.iis.user.service.IisKeyEventsService;
import com.yirong.iis.user.userentity.IisKeyEventsUserEntity;

 /**
 * 功能描述：重大事件表service实现类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-28 10:58:00
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
@Service("IisKeyEventsServiceImpl")
public class IisKeyEventsServiceImpl extends BaseService<IisKeyEvents, String>
		implements IisKeyEventsService {

	/**
	 * 日志操作类
	 */
	private Logger logger = LoggerFactory
			.getLogger(IisKeyEventsServiceImpl.class);

	/**
	 * dao注入
	 */
	@Autowired
	private IisKeyEventsDao iisKeyEventsDao;

	 /**
	 * 功能描述：获取dao操作类
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-28 10:58:00
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	@Override
	public IBaseDao<IisKeyEvents, String> getBaseDao() {
		return iisKeyEventsDao;
	}

	/**
	 * 功能描述：新增重大事件表
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-28 10:58:00
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisKeyEvents
	 * @return
	 */
	@Override
	public Map saveIisKeyEvents(IisKeyEvents iisKeyEvents){
		// 根据编码及分类ID获取数据（唯一键）
		/*IisKeyEvents iisKeyEventsTemp = this.iisKeyEventsDao
				.findBy_____(_____);
		String id = iisKeyEvents.getId();
		if (null == iisKeyEventsTemp
				|| (StringUtil.isNotNullOrEmpty(id) && id.equals(iisKeyEventsTemp
						.getId()))) {// 该唯一键不存在 或者为“修改操作且修改本身数据”
			id = iisKeyEvents.getId();
			if (StringUtil.isNotNullOrEmpty(id)) {// 修改
				// 获取数据库对象
				iisKeyEventsTemp = this.iisKeyEventsDao.findOne(id);
				// 复制属性
				BeanUtil.copyPropertiesIgnoreNull(iisKeyEvents, iisKeyEventsTemp);
				this.save(iisKeyEventsTemp);
			} else {
				this.save(iisKeyEvents);
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
	 * 功能描述：修改重大事件表
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-28 10:58:00
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param iisKeyEvents
	 * @return
	 */
	@Override
	public Map updateIisKeyEvents(IisKeyEvents iisKeyEvents) {
		// 根据编号Id
		IisKeyEvents iisKeyEventsTemp = this.iisKeyEventsDao.findOne(iisKeyEvents
				.getId());
		if (null == iisKeyEventsTemp) {// 未查询到任何数据
			String promptInfo = "不存在ID：" + iisKeyEvents.getId();
			String result = ErrorPromptInfoUtil.getErrorInfo("00202",
					promptInfo);
			logger.warn(result);
			return ResultUtil.newError(result).toMap();
		} else {// 有该数据
			return saveIisKeyEvents(iisKeyEvents);
		}
	}
	
	/**
	 * 功能描述：删除重大事件表（批量）
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-28 10:58:00
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param idStrs
	 * @return
	 */
	 @Override
	public Map delIisKeyEvents(String idStrs) {
		// 处理id集合串
		String[] ids = idStrs.split(",");
		/** 先判断所有ID是否允许删除 **/
		StringBuffer sb = new StringBuffer();
		for (String id : ids) {
			// 判断ID是否存在
			IisKeyEvents iisKeyEvents = this.iisKeyEventsDao.findOne(id);
			if (null == iisKeyEvents) {// 不存在直接跳出循环
				String promptInfo = "不存在ID：" + id;
				sb.append(ErrorPromptInfoUtil.getErrorInfo("00202", promptInfo));
				break;
			}
		}
		// 处理业务
		if (StringUtil.isNullOrEmpty(sb.toString())) {// 所有数据均允许删除
			// 将id循环拼装成序列化集合
			for (String id : ids) {
				this.iisKeyEventsDao.delete(id);// 物理删除
			}
			return ResultUtil.newOk("操作成功").toMap();
		} else {// 不允许删除
			logger.warn(sb.toString());
			return ResultUtil.newError(sb.toString()).toMap();
		}
	}

	/**
	 * 功能描述：根据ID查询重大事件表信息
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-28 10:58:00
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param id
	 * @return
	 */
	@Override
	public Map queryIisKeyEventsById(String id) {
		if(StringUtil.isNullOrEmpty(id)){
			return ResultUtil.newError("参数错误!").toMap();
		}
		// 拼装查询sql
		List<Object> param = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ID \"id\",TITLE \"title\",CONTENT \"content\",SOURCE \"source\",");
		sql.append("DATE_FORMAT(RELEASE_TIME,' %Y-%m-%d ') \"releaseTime\" ");
		sql.append(" FROM IIS_KEY_EVENTS");
		sql.append(" WHERE ID = ? ");
		param.add(id);
		Map map = this.exeSqlMap(sql.toString(), param);
		return ResultUtil.newOk("操作成功")
				.setData(map).toMap();
	}

	/**
	 * 功能描述：查询重大事件表列表信息
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-28 10:58:00
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param ue
	 * @return
	 */
	@Override
	public Map queryIisKeyEventsList(IisKeyEventsUserEntity ue){
		// 拼装查询sql
		List<Object> param = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ID \"id\",TITLE \"title\",");
		sql.append("DATE_FORMAT(RELEASE_TIME,' %Y-%m-%d ') \"releaseTime\" ");
		sql.append(" FROM IIS_KEY_EVENTS");
		sql.append(" WHERE 1=1 ");
		// 标准编码
		if (StringUtil.isNotNullOrEmpty(ue.getCountryEngName())) {
			sql.append(" AND COUNTRY_ENG_NAME = ?");
			param.add(ue.getCountryEngName());
		}
		if (StringUtil.isNotNullOrEmpty(ue.getQueryYear())){
			sql.append(" AND DATE_FORMAT(RELEASE_TIME,' %Y-%m-%d ') LIKE ?");
			param.add(ue.getQueryYear()+"%");
		}
		// 获取数据
		PageEntiry pageEntiry = this.findPageSQLMap(sql.toString(), param,
				null, ue);
		return ResultUtil.newOk("操作成功")
				.setData(pageEntiry).toMap();
	}

}
