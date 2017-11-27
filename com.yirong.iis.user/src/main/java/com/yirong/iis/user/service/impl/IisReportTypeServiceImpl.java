package com.yirong.iis.user.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.awaken.core.util.ResultUtil;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.commons.util.datatype.StringUtil;
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


    @Autowired
    private Environment environment;

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
		sql.append(" IRT.IS_OUTSIDE AS isOutside,");
		sql.append("IRT.TYPE_NAME AS typeName ");
		sql.append("FROM IIS_REPORT_TYPE IRT ");
		sql.append("WHERE 1=1 ");
		// 标准编码
		if (StringUtil.isNotNullOrEmpty(ue.getTypeName())) {
			sql.append("AND IRT.NEWS_TYPE_NAME LIKE ? ");
			param.add("%" + ue.getTypeName() + "%");
		}
		// 标准编码
		if (null != ue.getIsOutside()) {
			sql.append("AND IRT.IS_OUTSIDE = ? ");
			param.add(ue.getIsOutside());
		}
		// 获取数据
		List<Map<String, Object>> mapList = this.exeNativeQueryMap(sql.toString(), param);
		return ResultUtil.newOk("操作成功")
				.setData(mapList).toMap();
	}

	/**
	 * 功能描述：获取每个分类的五条记录
	 *
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-09 10:00:09
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @return
	 */
	@Override
	public Map queryIisReportTypeListFiveRecord() {
        // 拼装查询sql
        List<Object> param = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT ID,REPORT_NAME,TYPE_ID,CREATE_TIME,REPORT_INFO,KM_ID,EOS_ID,");
        sql.append("(SELECT TYPE_NAME FROM IIS_REPORT_TYPE WHERE ID = temp.TYPE_ID) AS TYPE_NAME ");
        sql.append("FROM (");
        sql.append("select ID,REPORT_NAME,TYPE_ID,TO_CHAR(CREATE_TIME,'YYYY-MM-DD') AS CREATE_TIME,REPORT_INFO,KM_ID,EOS_ID,rank() over ");
        sql.append("(partition by TYPE_ID order by CREATE_TIME desc) rn FROM IIS_REPORT) temp ");
        sql.append("where rn < 6 ");
        String insiderReport = environment.getProperty("show.insider.report");
        // 是否显示内部报告
        if (!"1".equals(insiderReport)){
            // 只显示外部报告
            sql.append("AND temp.TYPE_ID IN (SELECT ID FROM IIS_REPORT_TYPE WHERE IS_OUTSIDE = 1) ");
        }
        List<Map<String, Object>> types = this.exeNativeQueryMap(sql.toString(), param);
        Map<String, List<Map<String, Object>>> type = new HashMap<String, List<Map<String, Object>>>();
        for (Map<String, Object> t : types ){
            if (!type.containsKey(t.get("TYPE_ID"))){
                List<Map<String, Object>> k = new ArrayList<Map<String, Object>>();
                k.add(t);
                type.put(t.get("TYPE_ID").toString(), k);
            } else {
                type.get(t.get("TYPE_ID")).add(t);
            }
        }
        return ResultUtil.newOk("操作成功").setData(type).toMap();
	}

	/**
	 * 功能描述：获取每个分类的三条记录
	 *
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-09 10:00:09
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @return
	 */
    @Override
    public Map queryIisReportTypeListThreeRecord(String reportName) {
        // 拼装查询sql
        List<Object> param = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT ID,IS_OUTSIDE,TYPE_NAME,DOCS_NUM,IS_SYSTEM,CREATE_TIME ");
        sql.append("FROM IIS_REPORT_TYPE WHERE 1=1 ");
        String insiderReport = environment.getProperty("show.insider.report");
        // 是否显示内部报告
        if (!"1".equals(insiderReport)){
            // 只显示外部报告
            sql.append("AND IS_OUTSIDE = 1 ");
        }
        List<Map<String, Object>> result = this.exeNativeQueryMap(sql.toString(), param);
        for (Map<String, Object> t : result){
            t.put("reports", this.getReports(t.get("ID").toString(), reportName));
        }
        return ResultUtil.newOk("操作成功").setData(result).toMap();
    }

    /**
     * 功能描述：根据分类id获取该分类下的三条记录
     *
     * @author 林明铁
     *         <p>
     *         创建时间 ：2017-11-09 10:00:09
     *         </p>
     *
     *         <p>
     *         修改历史：(修改人，修改时间，修改原因/内容)
     *         </p>
     * @return
     */
    private List<Map<String, Object>> getReports(String id, String reportName) {
        List<Object> param = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer();
	    sql.append("SELECT ID,TYPE_ID,REPORT_SOURCE,REPORT_NAME,KM_ID,EOS_ID,CREATOR,TO_CHAR(CREATE_TIME,'YYYY-MM-DD') AS CREATE_TIME ");
	    sql.append("FROM IIS_REPORT ");
	    sql.append("WHERE TYPE_ID = ? ");
	    param.add(id);
	    if (StringUtil.isNotNullOrEmpty(reportName)){
	        sql.append("AND REPORT_NAME LIKE ? ");
	        param.add("%"+reportName +"%");
        }
        sql.append(" AND rownum<=3 order by CREATE_TIME desc ");
        return this.exeNativeQueryMap(sql.toString(), param);
	}

}
