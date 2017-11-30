package com.yirong.iis.mm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yirong.iis.mm.dao.IisCountrySurveyDao;
import com.yirong.iis.mm.entity.IisCountrySurvey;
import com.yirong.iis.mm.service.IisCountrySurveyService;
import com.yirong.iis.mm.userentity.IisCountrySurveyUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.commons.util.entity.PageEntiry;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.awaken.core.util.BeanUtil;
import com.yirong.awaken.core.util.ResultUtil;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.commons.util.datatype.StringUtil;
import com.yirong.commons.util.error.ErrorPromptInfoUtil;

/**
 * 功能描述：国家概括表service实现类
 *
 * @author 陈清沣
 * <p>
 * 创建时间 ：2017-11-27 19:29:29
 * </p>
 * <p>
 * <p>
 * 修改历史：(修改人，修改时间，修改原因/内容)
 * </p>
 */
@SuppressWarnings("rawtypes")
@Service("IisCountrySurveyServiceImpl")
public class IisCountrySurveyServiceImpl extends BaseService<IisCountrySurvey, String>
        implements IisCountrySurveyService {

    /**
     * 日志操作类
     */
    private Logger logger = LoggerFactory.getLogger(IisCountrySurveyServiceImpl.class);

    /**
     * dao注入
     */
    @Autowired
    private IisCountrySurveyDao iisCountrySurveyDao;

    /**
     * 功能描述：获取dao操作类
     *
     * @author 陈清沣
     * <p>
     * 创建时间 ：2017-11-27 19:29:29
     * </p>
     * <p>
     * <p>
     * 修改历史：(修改人，修改时间，修改原因/内容)
     * </p>
     */
    @Override
    public IBaseDao<IisCountrySurvey, String> getBaseDao() {
        return iisCountrySurveyDao;
    }

    /**
     * 功能描述：新增国家概括表
     *
     * @param iisCountrySurvey
     * @return
     * @author 陈清沣
     * <p>
     * 创建时间 ：2017-11-27 19:29:29
     * </p>
     * <p>
     * <p>
     * 修改历史：(修改人，修改时间，修改原因/内容)
     * </p>
     */
    @Override
    public Map saveIisCountrySurvey(IisCountrySurvey iisCountrySurvey) {
        // 根据编码及分类ID获取数据（唯一键）
        IisCountrySurvey iisCountrySurveyTemp = this.iisCountrySurveyDao.getByCountryEngNameAndFieldName(iisCountrySurvey.getCountryEngName(),iisCountrySurvey.getFieldName());
        String id = iisCountrySurvey.getId();
        if (null == iisCountrySurveyTemp
                || (StringUtil.isNotNullOrEmpty(id) && id.equals(iisCountrySurveyTemp
                .getId()))) {// 该唯一键不存在 或者为“修改操作且修改本身数据”
            id = iisCountrySurvey.getId();
            if (StringUtil.isNotNullOrEmpty(id)) {// 修改
                // 获取数据库对象
                iisCountrySurveyTemp = this.iisCountrySurveyDao.findOne(id);
                // 复制属性
                BeanUtil.copyPropertiesIgnoreNull(iisCountrySurvey, iisCountrySurveyTemp);
                this.save(iisCountrySurveyTemp);
            } else {
                this.save(iisCountrySurvey);
            }
            return ResultUtil.newOk("操作成功").toMap();
        } else {// 该名称及父类ID已存在
            String result = ErrorPromptInfoUtil.getErrorInfo("00201");
            logger.warn(result);
            return ResultUtil.newError(result).toMap();
        }
    }

    /**
     * 功能描述：修改国家概括表
     *
     * @param iisCountrySurvey
     * @return
     * @author 陈清沣
     * <p>
     * 创建时间 ：2017-11-27 19:29:29
     * </p>
     * <p>
     * <p>
     * 修改历史：(修改人，修改时间，修改原因/内容)
     * </p>
     */
    @Override
    public Map updateIisCountrySurvey(IisCountrySurvey iisCountrySurvey) {
        // 根据编号Id
        IisCountrySurvey iisCountrySurveyTemp = this.iisCountrySurveyDao.findOne(iisCountrySurvey.getId());
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
     * @param idStrs
     * @return
     * @author 陈清沣
     * <p>
     * 创建时间 ：2017-11-27 19:29:29
     * </p>
     * <p>
     * <p>
     * 修改历史：(修改人，修改时间，修改原因/内容)
     * </p>
     */
    @Override
    public Map delIisCountrySurvey(String idStrs) {
        // 处理id集合串
        String[] ids = idStrs.replace("[","").replace("]","").replace("\"","").split(",");
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
     * @param id
     * @return
     * @author 陈清沣
     * <p>
     * 创建时间 ：2017-11-27 19:29:29
     * </p>
     * <p>
     * <p>
     * 修改历史：(修改人，修改时间，修改原因/内容)
     * </p>
     */
    @Override
    public Map queryIisCountrySurveyById(String id) {
        List<Object> param = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT ID \"id\",FIELD_NAME,FIELD_VALUE ");
        sql.append("FROM IIS_COUNTRY_SURVEY ");
        sql.append("WHERE ID = ? ");
        param.add(id);
        Map map = this.exeSqlMap(sql.toString(), param);
        return ResultUtil.newOk("操作成功").setData(map).toMap();
    }

    /**
     * 功能描述：查询国家概括表列表信息
     *
     * @param ue
     * @return
     * @author 陈清沣
     * <p>
     * 创建时间 ：2017-11-27 19:29:29
     * </p>
     * <p>
     * <p>
     * 修改历史：(修改人，修改时间，修改原因/内容)
     * </p>
     */
    @Override
    public Map queryIisCountrySurveyList(IisCountrySurveyUserEntity ue) {
        // 拼装查询sql
        List<Object> param = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT ID \"id\",FIELD_NAME,FIELD_VALUE,CREATE_TIME,");
        sql.append("(SELECT USER_DISPLAY_NAME FROM SYS_USER WHERE ID = CREATOR) CREATOR_NAME ");
        sql.append("FROM IIS_COUNTRY_SURVEY ");
        sql.append("WHERE 1=1 ");
        // 字段名称
        if (StringUtil.isNotNullOrEmpty(ue.getCountryEngName())) {
            sql.append("AND COUNTRY_ENG_NAME = ?");
            param.add(ue.getCountryEngName());
        }
        // 字段名称
        if (StringUtil.isNotNullOrEmpty(ue.getFieldName())) {
            sql.append("AND FIELD_NAME like ?");
            param.add("%" + ue.getFieldName() + "%");
        }
        // 获取数据
        PageEntiry pageEntiry = this.findPageSQLMap(sql.toString(), param, null, ue);
        return ResultUtil.newOk("操作成功").setData(pageEntiry).toMap();
    }

}
