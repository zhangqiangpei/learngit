package com.yirong.iis.user.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yirong.awaken.core.util.BeanUtil;
import com.yirong.commons.util.error.ErrorPromptInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.awaken.core.util.ResultUtil;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.commons.util.datatype.StringUtil;
import com.yirong.commons.util.entity.PageEntiry;
import com.yirong.iis.user.dao.IisReportTemplateDao;
import com.yirong.iis.user.entity.IisReportTemplate;
import com.yirong.iis.user.service.IisReportTemplateService;
import com.yirong.iis.user.userentity.IisReportTemplateUserEntity;

 /**
 * 功能描述：报告模版表service实现类
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
@Service("IisReportTemplateServiceImpl")
public class IisReportTemplateServiceImpl extends BaseService<IisReportTemplate, String>
		implements IisReportTemplateService {

	/**
	 * 日志操作类
	 */
	private Logger logger = LoggerFactory
			.getLogger(IisReportTemplateServiceImpl.class);

	/**
	 * dao注入
	 */
	@Autowired
	private IisReportTemplateDao iisReportTemplateDao;

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
	public IBaseDao<IisReportTemplate, String> getBaseDao() {
		return iisReportTemplateDao;
	}

     /**
      * 功能描述：新增报告模版表
      *
      * @author 林明铁
      *         <p>
      *         创建时间 ：2017-11-09 10:00:09
      *         </p>
      *
      *         <p>
      *         修改历史：(修改人，修改时间，修改原因/内容)
      *         </p>
      * @param iisReportTemplate
      * @return
      */
     @Override
     public Map saveIisReportTemplate(IisReportTemplate iisReportTemplate){
         // 根据编码及分类ID获取数据（唯一键）
         IisReportTemplate iisReportTemplateTemp = this.iisReportTemplateDao
                 .findByCreatorAndTemplateName(iisReportTemplate.getCreator(), iisReportTemplate.getTemplateName());
         String id = iisReportTemplate.getId();
         if (null == iisReportTemplateTemp
                 || (StringUtil.isNotNullOrEmpty(id) && id.equals(iisReportTemplateTemp.getId()))) {
             // 该唯一键不存在 或者为“修改操作且修改本身数据”
             id = iisReportTemplate.getId();
             if (StringUtil.isNotNullOrEmpty(id)) {// 修改
                 // 获取数据库对象
                 iisReportTemplateTemp = this.iisReportTemplateDao.findOne(id);
                 // 复制属性
                 BeanUtil.copyPropertiesIgnoreNull(iisReportTemplate, iisReportTemplateTemp);
                 this.save(iisReportTemplateTemp);
             } else {
                 this.save(iisReportTemplate);
             }
             return ResultUtil.newOk("操作成功").toMap();
         } else {// 该名称及父类ID已存在
             String result = ErrorPromptInfoUtil.getErrorInfo("00401");
             logger.warn(result);
             return ResultUtil.newError(result).toMap();
         }
     }

     /**
      * 功能描述：修改报告模版表
      *
      * @author 林明铁
      *         <p>
      *         创建时间 ：2017-11-09 10:00:09
      *         </p>
      *
      *         <p>
      *         修改历史：(修改人，修改时间，修改原因/内容)
      *         </p>
      * @param iisReportTemplate
      * @return
      */
     @Override
     public Map updateIisReportTemplate(IisReportTemplate iisReportTemplate) {
         // 根据编号Id
         IisReportTemplate iisReportTemplateTemp = this.iisReportTemplateDao.findOne(iisReportTemplate
                 .getId());
         if (null == iisReportTemplateTemp) {// 未查询到任何数据
             String promptInfo = "不存在ID：" + iisReportTemplate.getId();
             String result = ErrorPromptInfoUtil.getErrorInfo("00402",
                     promptInfo);
             logger.warn(result);
             return ResultUtil.newError(result).toMap();
         } else {// 有该数据
             return saveIisReportTemplate(iisReportTemplate);
         }
     }

     /**
      * 功能描述：删除报告模版表（批量）
      *
      * @author 林明铁
      *         <p>
      *         创建时间 ：2017-11-09 10:00:09
      *         </p>
      *
      *         <p>
      *         修改历史：(修改人，修改时间，修改原因/内容)
      *         </p>
      * @param idStrs
      * @return
      */
     @Override
     public Map delIisReportTemplate(String idStrs) {
         // 处理id集合串
         String[] ids = idStrs.split(",");
         /** 先判断所有ID是否允许删除 **/
         StringBuffer sb = new StringBuffer();
         for (String id : ids) {
             // 判断ID是否存在
             IisReportTemplate iisReportTemplate = this.iisReportTemplateDao.findOne(id);
             if (null == iisReportTemplate) {// 不存在直接跳出循环
                 String promptInfo = "不存在ID：" + id;
                 sb.append(ErrorPromptInfoUtil.getErrorInfo("00402", promptInfo));
                 break;
             }
         }
         // 处理业务
         if (StringUtil.isNullOrEmpty(sb.toString())) {// 所有数据均允许删除
             // 将id循环拼装成序列化集合
             for (String id : ids) {
                 this.iisReportTemplateDao.delete(id);// 物理删除
             }
             return ResultUtil.newOk("操作成功").toMap();
         } else {// 不允许删除
             logger.warn(sb.toString());
             return ResultUtil.newError(sb.toString()).toMap();
         }
     }

     /**
      * 功能描述：根据ID查询报告模版表信息
      *
      * @author 林明铁
      *         <p>
      *         创建时间 ：2017-11-09 10:00:09
      *         </p>
      *
      *         <p>
      *         修改历史：(修改人，修改时间，修改原因/内容)
      *         </p>
      * @param id
      * @return
      */
     @Override
     public Map queryIisReportTemplateById(String id) {
         StringBuffer sql = new StringBuffer();
         sql.append("SELECT IRT.ID AS id,");
         sql.append("IRT.TEMPLATE_NAME AS templateName,");
         sql.append("IRT.TEMPLATE_INFO AS templateInfo ");
         sql.append("FROM IIS_REPORT_TEMPLATE IRT ");
         sql.append("WHERE IRT.ID = ? ");
         List<Object> param = new ArrayList<Object>();
         param.add(id);
         Map map = this.exeSqlMap(sql.toString(), param);
         return ResultUtil.newOk("操作成功").setData(map).toMap();
     }

	/**
	 * 功能描述：查询报告模版表列表信息
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
	public Map queryIisReportTemplateList(IisReportTemplateUserEntity ue){
		// 拼装查询sql
		List<Object> param = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT IRT.ID AS id,");
		sql.append("IRT.TEMPLATE_NAME AS templateName,");
		sql.append("IRT.TEMPLATE_INFO AS templateInfo ");
		sql.append("FROM IIS_REPORT_TEMPLATE IRT ");
		sql.append("WHERE IRT.CREATOR = ? ");
		param.add(ue.getCreator());
		// 添加模板名称
		if (StringUtil.isNotNullOrEmpty(ue.getTemplateName())) {
			sql.append("AND IRT.TEMPLATE_NAME LIKE ? ");
			param.add("%" + ue.getTemplateName() +"%");
		}
		// 获取数据
		PageEntiry pageEntiry = this.findPageSQLMap(sql.toString(), param, null, ue);
		return ResultUtil.newOk("操作成功")
				.setData(pageEntiry).toMap();
	}

}
