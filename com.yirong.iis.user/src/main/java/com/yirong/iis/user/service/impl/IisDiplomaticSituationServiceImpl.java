package com.yirong.iis.user.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.awaken.core.util.ResultUtil;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.commons.util.datatype.StringUtil;
import com.yirong.iis.user.dao.IisDiplomaticSituationDao;
import com.yirong.iis.user.entity.IisDiplomaticSituation;
import com.yirong.iis.user.service.IisDiplomaticSituationService;
import com.yirong.iis.user.userentity.IisDiplomaticSituationUserEntity;

 /**
 * 功能描述：外交情况表(与中国)service实现类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-24 16:08:11
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
	private Logger logger = LoggerFactory
			.getLogger(IisDiplomaticSituationServiceImpl.class);

	/**
	 * dao注入
	 */
	@Autowired
	private IisDiplomaticSituationDao iisDiplomaticSituationDao;

	 /**
	 * 功能描述：获取dao操作类
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-24 16:08:11
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

	@Override
	public Map queryList(IisDiplomaticSituationUserEntity psue) {
		if(null == psue ){
			return ResultUtil.newError("参数错误!").toMap();
		}
		List<Object> param = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ENGLISH_NAME \"englishName\",CONTENT \"content\",DATE_FORMAT(MODIFY_TIME,'%Y-%m-%d %H:%i:%s') \"modifyTime\"");
		sql.append(" FROM IIS_DIPLOMATIC_SITUATION");
		sql.append(" WHERE 1=1");
		if(StringUtil.isNotNullOrEmpty(psue.getEnglishName())){
			sql.append(" AND ENGLISH_NAME = ?");
			param.add(psue.getEnglishName());
		}
		logger.info("查询成功");
		Map<String,Object> result =  this.exeSqlMap(sql.toString(), param);
		
		return ResultUtil.newOk("查询成功!").setData(result).toMap();	
	}
}
