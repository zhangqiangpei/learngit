package com.yirong.iis.user.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yirong.commons.cache.eif.RedisCacheEif;
import com.yirong.iis.user.dao.IisMyReportDao;
import com.yirong.iis.user.entity.IisMyReport;
import com.yirong.iis.user.service.IisMyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.awaken.core.util.BeanUtil;
import com.yirong.awaken.core.util.ResultUtil;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.commons.util.datatype.StringUtil;
import com.yirong.commons.util.error.ErrorPromptInfoUtil;

/**
 * 功能描述：个人报告表service实现类
 *
 * @author
 *         <p>
 *         创建时间 ：2017-11-27 15:20:20
 *         </p>
 *
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
@Service("IisMyReportServiceImpl")
public class IisMyReportServiceImpl extends BaseService<IisMyReport, String>
        implements IisMyReportService {

    /**
     * 日志操作类
     */
    private Logger logger = LoggerFactory
            .getLogger(IisMyReportServiceImpl.class);

    /**
     * dao注入
     */
    @Autowired
    private IisMyReportDao iisMyReportDao;

    /**
     * 功能描述：获取dao操作类
     *
     * @author 林明铁
     *         <p>
     *         创建时间 ：2017-11-27 15:20:20
     *         </p>
     *
     *         <p>
     *         修改历史：(修改人，修改时间，修改原因/内容)
     *         </p>
     */
    @Override
    public IBaseDao<IisMyReport, String> getBaseDao() {
        return iisMyReportDao;
    }

    /**
     * 功能描述：新增个人报告表
     *
     * @author 林明铁
     *         <p>
     *         创建时间 ：2017-11-27 15:20:20
     *         </p>
     *
     *         <p>
     *         修改历史：(修改人，修改时间，修改原因/内容)
     *         </p>
     * @param iisMyReport
     * @return
     */
    @Override
    public Map saveIisMyReport(IisMyReport iisMyReport){
        // 根据编码及分类ID获取数据（唯一键）
        IisMyReport iisMyReportTemp = this.iisMyReportDao
                .findByCreatorAndObjId(iisMyReport.getCreator(), iisMyReport.getObjId());
        String id = iisMyReport.getId();
        if (null == iisMyReportTemp
                || (StringUtil.isNotNullOrEmpty(id) && id.equals(iisMyReportTemp
                .getId()))) {// 该唯一键不存在 或者为“修改操作且修改本身数据”
            id = iisMyReport.getId();
            if (StringUtil.isNotNullOrEmpty(id)) {// 修改
                // 获取数据库对象
                iisMyReportTemp = this.iisMyReportDao.findOne(id);
                // 复制属性
                BeanUtil.copyPropertiesIgnoreNull(iisMyReport, iisMyReportTemp);
                this.save(iisMyReportTemp);
            } else {
                this.save(iisMyReport);
            }
            return ResultUtil.newOk("操作成功").toMap();
        } else {// 该名称及父类ID已存在
            String result = ErrorPromptInfoUtil.getErrorInfo("00201");
            logger.warn(result);
            return ResultUtil.newError(result).toMap();
        }
    }

    /**
     * 功能描述：删除个人报告表（批量）
     *
     * @author 林明铁
     *         <p>
     *         创建时间 ：2017-11-27 15:20:20
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
    public Map delIisMyReport(String idStrs, String tokenId) {
        // 处理id集合串
        String[] ids = idStrs.split(",");
        String creator = RedisCacheEif.hget(tokenId, "id");
        /** 先判断所有ID是否允许删除 **/
        StringBuffer sb = new StringBuffer();
        for (String id : ids) {
            // 判断ID是否存在
            IisMyReport iisMyReport = this.iisMyReportDao.findByCreatorAndObjId(creator, id);
            if (null == iisMyReport) {// 不存在直接跳出循环
                String promptInfo = "不存在ID：" + id;
                sb.append(ErrorPromptInfoUtil.getErrorInfo("00202", promptInfo));
                break;
            }
        }
        // 处理业务
        if (StringUtil.isNullOrEmpty(sb.toString())) {// 所有数据均允许删除
            // 将id循环拼装成序列化集合
            for (String id : ids) {
                IisMyReport iisMyReport = this.iisMyReportDao.findByCreatorAndObjId(creator, id);
                this.iisMyReportDao.delete(iisMyReport);// 物理删除
            }
            return ResultUtil.newOk("操作成功").toMap();
        } else {// 不允许删除
            logger.warn(sb.toString());
            return ResultUtil.newError(sb.toString()).toMap();
        }
    }

    /**
     * 功能描述：根据创建者和对象ID查询
     *
     * @author 林明铁
     *         <p>
     *         创建时间 ：2017-11-27 15:20:20
     *         </p>
     *
     *         <p>
     *         修改历史：(修改人，修改时间，修改原因/内容)
     *         </p>
     * @return
     */
    @Override
    public Map queryIisMyReportByCreatorAndObjId(String creator, String objId) {
        StringBuffer sql = new StringBuffer();
        List<Object> param = new ArrayList<Object>();
        sql.append("SELECT ID AS id ");
        sql.append("FROM IIS_MY_REPORT ");
        sql.append("WHERE OBJ_ID = ? ");
        param.add(objId);
        sql.append("AND CREATOR = ? ");
        param.add(creator);
        List<Map<String, Object>> mapList = this.exeNativeQueryMap(sql.toString(), param);
        return ResultUtil.newOk("操作成功").setData(mapList).toMap();
    }
}
