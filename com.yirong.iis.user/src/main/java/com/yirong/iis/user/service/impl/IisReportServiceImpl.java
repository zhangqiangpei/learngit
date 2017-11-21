package com.yirong.iis.user.service.impl;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yirong.awaken.core.util.BeanUtil;
import com.yirong.commons.cache.eif.RedisCacheEif;
import com.yirong.commons.util.error.ErrorPromptInfoUtil;
import com.yirong.iis.user.service.KmUserAwakenService;
import com.yirong.iis.user.service.KmUserWebAwakenService;
import com.yirong.iis.user.userentity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.awaken.core.util.ResultUtil;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.commons.util.datatype.StringUtil;
import com.yirong.commons.util.entity.PageEntiry;
import com.yirong.iis.user.dao.IisReportDao;
import com.yirong.iis.user.entity.IisReport;
import com.yirong.iis.user.service.IisReportService;

/**
 * 功能描述：报告表service实现类
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
@Service("IisReportServiceImpl")
public class IisReportServiceImpl extends BaseService<IisReport, String>
		implements IisReportService {

	/**
	 * 日志操作类
	 */
	private Logger logger = LoggerFactory
			.getLogger(IisReportServiceImpl.class);

	/**
	 * dao注入
	 */
	@Autowired
	private IisReportDao iisReportDao;

	@Autowired
    private KmUserWebAwakenService kmUserWebAwakenService;

	@Autowired
    private KmUserAwakenService iisUserAwakenService;

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
	public IBaseDao<IisReport, String> getBaseDao() {
		return iisReportDao;
	}

	 /**
	  * 功能描述：新增报告表
	  *
	  * @author 林明铁
	  *         <p>
	  *         创建时间 ：2017-11-09 10:00:09
	  *         </p>
	  *
	  *         <p>
	  *         修改历史：(修改人，修改时间，修改原因/内容)
	  *         </p>
	  * @param iisReport
	  * @param tokenId
      * @return
	  */
	 @Override
	 public Map saveIisReport(IisReport iisReport, String tokenId){
		 // 根据编码及分类ID获取数据（唯一键）
		 IisReport iisReportTemp = this.iisReportDao
				 .findByReportName(iisReport.getReportName());
		 String id = iisReport.getId();
         String creator = RedisCacheEif.hget(tokenId, "id");
         String creatorName = RedisCacheEif.hget(tokenId, "userDisplayName");
         String createOrg = RedisCacheEif.hget(tokenId, "organizationId");
         String createOrgName = RedisCacheEif.hget(tokenId, "organizationName");
		 if (null == iisReportTemp
				 || (StringUtil.isNotNullOrEmpty(id) && id.equals(iisReportTemp
				 .getId()))) {// 该唯一键不存在 或者为“修改操作且修改本身数据”
			 id = iisReport.getId();
			 if (StringUtil.isNotNullOrEmpty(id)) {// 修改
				 // 获取数据库对象
				 iisReportTemp = this.iisReportDao.findOne(id);
				 // 复制属性
				 BeanUtil.copyPropertiesIgnoreNull(iisReport, iisReportTemp);
                 // 将文档转成docs
                 File reportDocs = this.reportToDocs(iisReportTemp);
                 if (!reportDocs.exists()){
                     return ResultUtil.newError("文件转成doc失败！").toMap();
                 }
                 // Eos上传文件
                 KmUserAwakenAddFileUserEntity iaafue = new KmUserAwakenAddFileUserEntity();
                 iaafue.setDocName(reportDocs.getName());
                 iaafue.setDocSize(Integer.parseInt(String.valueOf(reportDocs.length()/1024)));
                 iaafue.setOrgCode(createOrg);
                 iaafue.setOrgName(createOrgName);
                 iaafue.setCreator(creator);
                 iaafue.setCreatorName(creatorName);
                 String eosId = null;
                 try {
                     eosId = kmUserWebAwakenService.httpUploadFile(iaafue, reportDocs);
                 } catch (Exception e){
                     logger.error(e);
                     logger.error("文件上传到Eos失败");
                 } finally {
                     if (reportDocs.delete()) {
                         logger.info("临时文件删除成功");
                     } else {
                         logger.error("临时文件删除失败");
                     }
                 }
                 if (null == eosId){
                     return ResultUtil.newError("文件上传到Eos失败").toMap();
                 }
                 KmUserAwakenFileUserEntity iafue = new KmUserAwakenFileUserEntity();
                 iafue.setDocEosId(eosId);
                 String kmId = iisUserAwakenService.httpAddInfo(iafue);
                 if (null == kmId){
                     return ResultUtil.newError("知识添加失败").toMap();
                 }
                 iisReportTemp.setKmId(kmId);
                 iisReportTemp.setEosId(eosId);
				 this.save(iisReportTemp);
			 } else {
                 // 将文档转成docs
                 File reportDocs = this.reportToDocs(iisReport);
                 if (!reportDocs.exists()){
                     return ResultUtil.newError("文件转成doc失败！").toMap();
                 }
                 // Eos上传文件
                 KmUserAwakenAddFileUserEntity iaafue = new KmUserAwakenAddFileUserEntity();
                 iaafue.setDocName(reportDocs.getName());
                 iaafue.setDocSize(Integer.parseInt(String.valueOf(reportDocs.length()/1024)));
                 iaafue.setOrgCode(createOrg);
                 iaafue.setOrgName(createOrgName);
                 iaafue.setCreator(creator);
                 iaafue.setCreatorName(creatorName);
                 String eosId = null;
                 try {
                     eosId = kmUserWebAwakenService.httpUploadFile(iaafue, reportDocs);
                 } catch (Exception e){
                     logger.error(e);
                     logger.error("文件上传到Eos失败");
                 } finally {
                     if (reportDocs.delete()) {
                         logger.info("临时文件删除成功");
                     } else {
                         logger.error("临时文件删除失败");
                     }
                 }
                 if (null == eosId){
                     return ResultUtil.newError("文件上传到Eos失败").toMap();
                 }
                 KmUserAwakenFileUserEntity iafue = new KmUserAwakenFileUserEntity();
                 iafue.setDocEosId(eosId);
			 	String kmId = iisUserAwakenService.httpAddInfo(iafue);
			 	if (null == kmId){
                    return ResultUtil.newError("知识添加失败").toMap();
                }
			 	iisReport.setKmId(kmId);
			 	iisReport.setEosId(eosId);
				 this.save(iisReport);
			 }
			 return ResultUtil.newOk("操作成功").toMap();
		 } else {// 该名称及父类ID已存在
			 String result = ErrorPromptInfoUtil.getErrorInfo("00101");
			 logger.warn(result);
			 return ResultUtil.newError(result).toMap();
		 }
	 }

	 /**
	  * 功能描述：修改报告表
	  *
	  * @author 林明铁
	  *         <p>
	  *         创建时间 ：2017-11-09 10:00:09
	  *         </p>
	  *
	  *         <p>
	  *         修改历史：(修改人，修改时间，修改原因/内容)
	  *         </p>
	  * @param iisReport
	  * @return
	  */
	 @Override
	 public Map updateIisReport(IisReport iisReport, String tokenId) {
		 // 根据编号Id
		 IisReport iisReportTemp = this.iisReportDao.findOne(iisReport
				 .getId());
		 if (null == iisReportTemp) {// 未查询到任何数据
			 String promptInfo = "不存在ID：" + iisReport.getId();
			 String result = ErrorPromptInfoUtil.getErrorInfo("00102", promptInfo);
			 logger.warn(result);
			 return ResultUtil.newError(result).toMap();
		 } else {// 有该数据
             // 删除EOS文件
             KmUserAwakenOperUserEntity iuaoue = new KmUserAwakenOperUserEntity();
             iuaoue.setId(iisReportTemp.getEosId());
             iuaoue.setOperationtor(iisReportTemp.getCreator());
             iuaoue.setOperationtorName(RedisCacheEif.hget(tokenId, "userDisplayName"));
             if (!iisUserAwakenService.httpDeleteFile(iuaoue)){
                 ResultUtil.newError("删除EOS文件失败").toMap();
             }
             // 删除知识
             iuaoue.setId(iisReportTemp.getKmId());
             if (!iisUserAwakenService.httpDeleteInfo(iuaoue)){
                 ResultUtil.newOk("删除知识失败").toMap();
             }
			 return saveIisReport(iisReport, tokenId);
		 }
	 }

	 /**
	  * 功能描述：删除报告表（批量）
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
	  * @param tokenId
      * @return
	  */
	 @Override
	 public Map delIisReport(String idStrs, String tokenId) {
		 // 处理id集合串
		 String[] ids = idStrs.split(",");
		 /** 先判断所有ID是否允许删除 **/
         StringBuffer sb = new StringBuffer();
		 for (String id : ids) {
			 // 判断ID是否存在
             IisReport iisReport = this.iisReportDao.findOne(id);
             if (null == iisReport) {// 不存在直接跳出循环
				 String promptInfo = "不存在ID：" + id;
				 sb.append(ErrorPromptInfoUtil.getErrorInfo("00102", promptInfo));
				 break;
			 }
		 }
		 // 处理业务
		 if (StringUtil.isNullOrEmpty(sb.toString())) {// 所有数据均允许删除
			 // 将id循环拼装成序列化集合
			 for (String id : ids) {
                 IisReport iisReport = this.iisReportDao.findOne(id);
                 // 删除EOS文件
                 KmUserAwakenOperUserEntity iuaoue = new KmUserAwakenOperUserEntity();
                 iuaoue.setId(iisReport.getEosId());
                 iuaoue.setOperationtor(iisReport.getCreator());
                 iuaoue.setOperationtorName(RedisCacheEif.hget(tokenId, "userDisplayName"));
                 if (!iisUserAwakenService.httpDeleteFile(iuaoue)){
                     ResultUtil.newError("删除EOS文件失败").toMap();
                 }
                 // 删除知识
                 iuaoue.setId(iisReport.getKmId());
                 if (!iisUserAwakenService.httpDeleteInfo(iuaoue)){
                     ResultUtil.newOk("删除知识失败").toMap();
                 }
                 this.iisReportDao.delete(id);// 物理删除
			 }
			 return ResultUtil.newOk("操作成功").toMap();
		 } else {// 不允许删除
			 logger.warn(sb.toString());
			 return ResultUtil.newError(sb.toString()).toMap();
		 }
	 }

	 /**
	  * 功能描述：根据ID查询报告表信息
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
	 public Map queryIisReportById(String id) {
		 StringBuffer sql = new StringBuffer();
         sql.append("SELECT IR.ID AS id,");
         sql.append("IR.TYPE_ID AS typeId,");
         sql.append("IR.REPORT_NAME AS reportName,");
         sql.append("IR.REPORT_INFO AS reportInfo,");
         sql.append("IR.KM_ID AS kmId,");
         sql.append("IR.EOS_ID AS eosId,");
         sql.append("IR.CREATOR AS creator ");
         sql.append("FROM IIS_REPORT IR ");
         sql.append("WHERE IR.ID = ? ");
		 List<Object> param = new ArrayList<Object>();
		 param.add(id);
		 Map map = this.exeSqlMap(sql.toString(), param);
		 return ResultUtil.newOk("操作成功")
				 .setData(map).toMap();
	 }

	 /**
	  * 功能描述：查询报告表列表信息
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
	 public Map queryIisReportList(IisReportUserEntity ue){
		 // 拼装查询sql
		 List<Object> param = new ArrayList<Object>();
		 StringBuffer sql = new StringBuffer();
		 sql.append("SELECT IR.ID AS id,");
		 sql.append("IR.TYPE_ID AS typeId,");
		 sql.append("IR.REPORT_NAME AS reportName,");
		 sql.append("IR.REPORT_INFO AS reportInfo,");
		 sql.append("IR.KM_ID AS kmId,");
		 sql.append("(SELECT TYPE_NAME FROM IIS_REPORT_TYPE WHERE ID = IR.TYPE_ID ) AS typeName,");
		 sql.append("TO_CHAR(IR.CREATE_TIME, 'YYYY-MM-DD HH24:MI:SS') AS createTime,");
		 sql.append("IR.EOS_ID AS eosId,");
		 sql.append("IR.CREATOR AS creator ");
		 sql.append("FROM IIS_REPORT IR ");
		 sql.append("WHERE 1=1 ");
		 // 创建人
		 if (StringUtil.isNotNullOrEmpty(ue.getCreator())) {
			 sql.append("AND IR.CREATOR = ? ");
			 param.add("%" + ue.getCreator()+"%");
		 }
		 // 类型
         if (StringUtil.isNotNullOrEmpty(ue.getReportName())) {
             sql.append("AND IR.REPORT_NAME LIKE ? ");
             param.add("%" + ue.getReportName()+"%");
         }
          // 报告名
         if (StringUtil.isNotNullOrEmpty(ue.getTypeId())) {
             sql.append("AND IR.TYPE_ID = ? ");
             param.add(ue.getTypeId());
         }
		 // 获取数据
		 PageEntiry pageEntiry = this.findPageSQLMap(sql.toString(), param,
				 null, ue);
		 return ResultUtil.newOk("操作成功")
				 .setData(pageEntiry).toMap();
	 }

     /**
      * 功能描述：报告转换成docs，返回转换后文件
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
	 public File reportToDocs(IisReport iisReport) {
	     File reportDocs = new File(
	             this.getFileDirPath(null).toString() + File.separator + iisReport.getReportName()+".doc");
         InputStream inputStream = null;
         OutputStream os = null;
         logger.info("报告开始转成doc文件。");
         try {
             String urlString = environment.getProperty("htmlToDoc.URL");
             URL url = new URL(urlString);
             HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
             //设置发送数据
             httpURLConnection.setDoOutput(true);
             //设置接受数据
             httpURLConnection.setDoInput(true);
             //发送数据,使用输出流
             OutputStream outputStream = httpURLConnection.getOutputStream();
             String content = "html="+ URLEncoder.encode(iisReport.getReportInfo(), "UTF-8");
             outputStream.write(content.getBytes());
             outputStream.flush();
             //接收数据
             inputStream = httpURLConnection.getInputStream();
             //定义字节数组
             os = null;
             os = new FileOutputStream(reportDocs);
             int bytesRead = 0;
             byte[] buffer = new byte[3096];
             while ((bytesRead = inputStream.read(buffer, 0, 3096)) != -1) {
                 os.write(buffer, 0, bytesRead);
             }
         } catch (IOException e) {
             e.printStackTrace();
         }finally {
             try {
                 if (null != inputStream) {
                     inputStream.close();
                 }
             } catch (IOException e) {
                 logger.error("文件输入流关闭异常");
             }
             if (os != null){
                 try {
                     os.close();
                 } catch (IOException e) {
                     logger.error("文件输出流关闭异常");
                 }
             }
         }
         logger.info("报告转成docs结束。");
         return reportDocs;
	 }

	/**
      * 获取临时文件存放路径
      *
      * @return
      */
     private Path getFileDirPath(String path) {
         String dirPath;
         if (null == path) {
             dirPath = environment.getProperty("fileTemp.directory");
         } else {
             dirPath = path;
         }
         Path fileDirPath = Paths.get(dirPath);
         try {
             if (!Files.exists(fileDirPath)) {
                 fileDirPath = Files.createDirectory(fileDirPath);
             }
         } catch (IOException e) {
             logger.error(e);
         }
         return fileDirPath;
     }

 }
