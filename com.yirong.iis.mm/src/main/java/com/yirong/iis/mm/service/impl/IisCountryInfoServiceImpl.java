package com.yirong.iis.mm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yirong.iis.mm.dao.IisCountryInfoDao;
import com.yirong.iis.mm.entity.IisCountryInfo;
import com.yirong.iis.mm.service.IisCountryInfoService;
import com.yirong.iis.mm.userentity.IisCountryInfoUserEntity;
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
 * 功能描述：国家信息表service实现类
 *
 * @author 陈清沣
 * <p>
 * 创建时间 ：2017-11-24 18:16:59
 * </p>
 * <p>
 * <p>
 * 修改历史：(修改人，修改时间，修改原因/内容)
 * </p>
 */
@SuppressWarnings("rawtypes")
@Service("IisCountryInfoServiceImpl")
public class IisCountryInfoServiceImpl extends BaseService<IisCountryInfo, String> implements IisCountryInfoService {

    /**
     * 日志操作类
     */
    private Logger logger = LoggerFactory
            .getLogger(IisCountryInfoServiceImpl.class);

    /**
     * dao注入
     */
    @Autowired
    private IisCountryInfoDao iisCountryInfoDao;

    /**
     * 功能描述：获取dao操作类
     *
     * @author 陈清沣
     * <p>
     * 创建时间 ：2017-11-24 18:16:59
     * </p>
     * <p>
     * <p>
     * 修改历史：(修改人，修改时间，修改原因/内容)
     * </p>
     */
    @Override
    public IBaseDao<IisCountryInfo, String> getBaseDao() {
        return iisCountryInfoDao;
    }

    /**
     * 功能描述：新增国家信息表
     *
     * @param iisCountryInfo
     * @return
     * @author 陈清沣
     * <p>
     * 创建时间 ：2017-11-24 18:16:59
     * </p>
     * <p>
     * <p>
     * 修改历史：(修改人，修改时间，修改原因/内容)
     * </p>
     */
    @Override
    public Map saveIisCountryInfo(IisCountryInfo iisCountryInfo) {
        // 根据编码及分类ID获取数据（唯一键）
        IisCountryInfo iisCountryInfoTemp = this.iisCountryInfoDao.findOne(iisCountryInfo.getId());
        String id = iisCountryInfo.getId();
        if (null == iisCountryInfoTemp
                || (StringUtil.isNotNullOrEmpty(id) && id.equals(iisCountryInfoTemp
                .getId()))) {// 该唯一键不存在 或者为“修改操作且修改本身数据”
            id = iisCountryInfo.getId();
            if (StringUtil.isNotNullOrEmpty(id)) {// 修改
                // 获取数据库对象
                iisCountryInfoTemp = this.iisCountryInfoDao.findOne(id);
                // 复制属性
                BeanUtil.copyPropertiesIgnoreNull(iisCountryInfo, iisCountryInfoTemp);
                this.save(iisCountryInfoTemp);
            } else {
                this.save(iisCountryInfo);
            }
            return ResultUtil.newOk("操作成功").toMap();
        } else {// 该名称及父类ID已存在
            String result = ErrorPromptInfoUtil.getErrorInfo("00101");
            logger.warn(result);
            return ResultUtil.newError(result).toMap();
        }
    }

    /**
     * 功能描述：修改国家信息表
     *
     * @param iisCountryInfo
     * @return
     * @author 陈清沣
     * <p>
     * 创建时间 ：2017-11-24 18:16:59
     * </p>
     * <p>
     * <p>
     * 修改历史：(修改人，修改时间，修改原因/内容)
     * </p>
     */
    @Override
    public Map updateIisCountryInfo(IisCountryInfo iisCountryInfo) {
        // 根据编号Id
        IisCountryInfo iisCountryInfoTemp = this.iisCountryInfoDao.findOne(iisCountryInfo.getId());
        if (null == iisCountryInfoTemp) {// 未查询到任何数据
            String promptInfo = "不存在ID：" + iisCountryInfo.getId();
            String result = ErrorPromptInfoUtil.getErrorInfo("00102", promptInfo);
            logger.warn(result);
            return ResultUtil.newError(result).toMap();
        } else {// 有该数据
            return saveIisCountryInfo(iisCountryInfo);
        }
    }

    /**
     * 功能描述：删除国家信息表（批量）
     *
     * @param idStrs
     * @return
     * @author 陈清沣
     * <p>
     * 创建时间 ：2017-11-24 18:16:59
     * </p>
     * <p>
     * <p>
     * 修改历史：(修改人，修改时间，修改原因/内容)
     * </p>
     */
    @Override
    public Map delIisCountryInfo(String idStrs) {
        // 处理id集合串
        String[] ids = idStrs.split(",");
        /** 先判断所有ID是否允许删除 **/
        StringBuffer sb = new StringBuffer();
        for (String id : ids) {
            // 判断ID是否存在
            IisCountryInfo iisCountryInfo = this.iisCountryInfoDao.findOne(id);
            if (null == iisCountryInfo) {// 不存在直接跳出循环
                String promptInfo = "不存在ID：" + id;
                sb.append(ErrorPromptInfoUtil.getErrorInfo("00103", promptInfo));
                break;
            }
        }
        // 处理业务
        if (StringUtil.isNullOrEmpty(sb.toString())) {// 所有数据均允许删除
            // 将id循环拼装成序列化集合
            for (String id : ids) {
                this.iisCountryInfoDao.delete(id);// 物理删除
            }
            return ResultUtil.newOk("操作成功").toMap();
        } else {// 不允许删除
            logger.warn(sb.toString());
            return ResultUtil.newError(sb.toString()).toMap();
        }
    }

    /**
     * 功能描述：根据ID查询国家信息表信息
     *
     * @param id
     * @return
     * @author 陈清沣
     * <p>
     * 创建时间 ：2017-11-24 18:16:59
     * </p>
     * <p>
     * <p>
     * 修改历史：(修改人，修改时间，修改原因/内容)
     * </p>
     */
    @Override
    public Map queryIisCountryInfoById(String id) {

        List<Object> param = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT ISO2CODE,ISOCODE,ENGLISH_NAME,CHINESE_NAME,");
        sql.append("(SELECT NAME FROM SYS_DICTIONARY WHERE CODE = CONTINENT_CODE) CONTINENT_NAME ");
        sql.append("FROM IIS_COUNTRY_INFO ");
        sql.append("WHERE ID = ? ");
        param.add(id);
        Map map = this.exeSqlMap(sql.toString(), param);
        return ResultUtil.newOk("操作成功").setData(map).toMap();
    }

    /**
     * 功能描述：查询国家信息表列表信息
     *
     * @param ue
     * @return
     * @author 陈清沣
     * <p>
     * 创建时间 ：2017-11-24 18:16:59
     * </p>
     * <p>
     * <p>
     * 修改历史：(修改人，修改时间，修改原因/内容)
     * </p>
     */
    @Override
    public Map queryIisCountryInfoList(IisCountryInfoUserEntity ue) {
        // 拼装查询sql
        List<Object> param = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT ISO2CODE,ISOCODE,ENGLISH_NAME,CHINESE_NAME,CREATE_TIME,");
        sql.append("(SELECT NAME FROM SYS_DICTIONARY WHERE CODE = CONTINENT_CODE) CONTINENT_NAME ");
        sql.append("FROM IIS_COUNTRY_INFO ");
        sql.append("WHERE 1=1 ");
        // 所属州
        if (StringUtil.isNotNullOrEmpty(ue.getContinentCode())) {
            sql.append("AND CONTINENT_CODE = ?");
            param.add(ue.getContinentCode());
        }
        // 英文缩写
        if (StringUtil.isNotNullOrEmpty(ue.getIso2code())) {
            sql.append("AND (ISOCODE like ? OR ISO2CODE like ?)");
            param.add("%" + ue.getIso2code() + "%");
            param.add("%" + ue.getIso2code() + "%");
        }
        // 英文名
        if (StringUtil.isNotNullOrEmpty(ue.getEnglishName())) {
            sql.append("AND ENGLISH_NAME like ?");
            param.add(ue.getEnglishName());
        }
        // 中文名
        if (StringUtil.isNotNullOrEmpty(ue.getChineseName())) {
            sql.append("AND CHINESE_NAME like ?");
            param.add(ue.getChineseName());
        }
        // 获取数据
        PageEntiry pageEntiry = this.findPageSQLMap(sql.toString(), param, null, ue);
        return ResultUtil.newOk("操作成功").setData(pageEntiry).toMap();
    }

}
