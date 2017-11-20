package com.yirong.iis.user.service.impl;

import java.util.ArrayList;
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
import com.yirong.commons.util.entity.PageEntiry;
import com.yirong.iis.user.dao.IisReportTypeDao;
import com.yirong.iis.user.entity.IisReportType;
import com.yirong.iis.user.service.IisReportTypeService;
import com.yirong.iis.user.userentity.IisReportTypeUserEntity;

/**
* 功能描述：报告分类表service实现类
* 
* @author 林明铁
*         <p>
*         创建时间 ：2017-11-09 10:00:09
*         </p>
* 
*         <p>
*         修改历史：(修改人，修改时间，修改原因/内容)
*         </p>
*/
@SuppressWarnings("rawtypes")
@Service("IisReportTypeServiceImpl")
public class IisReportTypeServiceImpl extends BaseService<IisReportType, String>
		implements IisReportTypeService {

	/**
	 * 日志操作类
	 */
	private Logger logger = LoggerFactory
			.getLogger(IisReportTypeServiceImpl.class);

	/**
	 * dao注入
	 */
	@Autowired
	private IisReportTypeDao iisReportTypeDao;

	 /**
	 * 功能描述：获取dao操作类
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-09 10:00:09
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	@Override
	public IBaseDao<IisReportType, String> getBaseDao() {
		return iisReportTypeDao;
	}

	/**
	 * 功能描述：查询报告分类表列表信息
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-09 10:00:09
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param ue
	 * @return
	 */
	@Override
	public Map queryIisReportTypeList(IisReportTypeUserEntity ue){
		// 拼装查询sql
		List<Object> param = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT IRT.ID AS id,");
		sql.append("IRT.TYPE_NAME AS typeName ");
		sql.append("FROM IIS_REPORT_TYPE IRT ");
		sql.append("WHERE 1=1 ");
		// 标准编码
		if (StringUtil.isNotNullOrEmpty(ue.getTypeName())) {
			sql.append("AND IRT.TYPE_NAME LIKE ? ");
			param.add("%" + ue.getTypeName() + "%");
		}
		// 标准编码
		if (StringUtil.isNotNullOrEmpty(ue.getIsOutside().toString())) {
			sql.append("AND IRT.IS_OUTSIDE = ? ");
			param.add(ue.getIsOutside());
		}
		// 获取数据
		List<Map<String, Object>> mapList = this.exeNativeQueryMap(sql.toString(), param);
		return ResultUtil.newOk("操作成功")
				.setData(mapList).toMap();
	}
	
}
