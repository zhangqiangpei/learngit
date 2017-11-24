package com.yirong.iis.user.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yirong.commons.es.constant.EsMatchNames;
import com.yirong.commons.es.eif.EsClientEif;
import com.yirong.commons.util.order.EsSelect;
import com.yirong.commons.util.order.Where;
import com.yirong.iis.user.constant.esConstants;
import com.yirong.iis.user.dao.IisNewsDao;
import com.yirong.iis.user.entity.IisNews;
import com.yirong.iis.user.service.IisNewsService;
import com.yirong.iis.user.userentity.IisNewsUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.commons.util.entity.PageEntiry;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.awaken.core.util.ResultUtil;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.commons.util.datatype.StringUtil;

/**
 * 功能描述：新闻表service实现类
 *
 * @author 林明铁
 *         <p>
 *         创建时间 ：2017-11-21 15:43:12
 *         </p>
 *
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
@Service("IisNewsServiceImpl")
public class IisNewsServiceImpl extends BaseService<IisNews, String>
        implements IisNewsService {

    /**
     * 日志操作类
     */
    private Logger logger = LoggerFactory
            .getLogger(IisNewsServiceImpl.class);

    /**
     * dao注入
     */
    @Autowired
    private IisNewsDao iisNewsDao;

    /**
     * 功能描述：获取dao操作类
     *
     * @author 林明铁
     *         <p>
     *         创建时间 ：2017-11-21 15:43:12
     *         </p>
     *
     *         <p>
     *         修改历史：(修改人，修改时间，修改原因/内容)
     *         </p>
     */
    @Override
    public IBaseDao<IisNews, String> getBaseDao() {
        return iisNewsDao;
    }

    /**
     * 功能描述：搜索
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
    public Map esSearch(IisNewsUserEntity ue, String tokenId) {
        /** 查询条件 **/
        List<Where> whereList = this.getEsWhere(ue, tokenId);
        /** 返回结果 **/
        EsSelect select = new EsSelect();
        if (StringUtil.isNotNullOrEmpty(ue.getKeywords())) {
            select.setKeyword(ue.getKeywords().trim());
            select.setKeywordFields(new String[]{esConstants.TITLE, esConstants.TITLE_CN,esConstants.TITLE_EN,
                    esConstants.CONTENT, esConstants.CONTENT_CN,esConstants.CONTENT_EN,
            esConstants.COUNTRY_CHN_NAME,esConstants.COUNTRY_ENG_NAME,
            esConstants.SUMMARY});
        }
        select.setAnalyzer("ik_max_word");
        select.setIsHighlight(true);
        select.setIncludes(new String[]{esConstants.TITLE, esConstants.TITLE_CN,
                esConstants.CONTENT,esConstants.CONTENT_CN, esConstants.SUMMARY});
        // 获取数据
        PageEntiry page = EsClientEif.textSearch(esConstants.INDEX_NEWS_NAME, esConstants.TYPE_NEWS_NAME, ue,
                whereList, select, esConstants.class);
        return ResultUtil.newOk("操作成功").setData(page).toMap();
    }

    /**
     * 功能描述：根据查询条件拼装es查询where
     *
     * @param ue
     * @return
     * @author
     * <p>
     * 创建时间 ：2017年10月12日 下午2:22:03
     * </p>
     * <p>
     * <p>
     * 修改历史：(修改人，修改时间，修改原因/内容)
     * </p>
     */
    private List<Where> getEsWhere(IisNewsUserEntity ue, String tokenId) {
        List<Where> whereList = new ArrayList<Where>();
        String format = "yyyy-MM-dd";
        // 上传时间（开始）
//        if (StringUtil.isNotNullOrEmpty(ue.getReleaseTime())) {
//            Where we = new Where();
//            we.setFieldName(esConstants.RELEASE_TIME);
//            we.setFieldValue(ue.getReleaseTime());
//            we.setOperationType(EsMatchNames.GEC);
//            we.setFormatType(format);
//            whereList.add(we);
//        }
        // 标题
        // 中文标题
        // 内容
        // 中文内容
        // 概要
        // 新闻类型
        if (StringUtil.isNotNullOrEmpty(ue.getType())){
            Where where = new Where();
            where.setFieldName(esConstants.NEWS_TYPE);
            where.setFieldValue(ue.getType());
            where.setOperationType(EsMatchNames.EQC);
            whereList.add(where);
        }
        // 来源
        if (StringUtil.isNotNullOrEmpty(ue.getSource())){
            Where where = new Where();
            where.setFieldName(esConstants.SOURCE);
            where.setFieldValue(ue.getSource());
            where.setOperationType(EsMatchNames.EQC);
            whereList.add(where);
        }
        // 国家英文名称
        if (StringUtil.isNotNullOrEmpty(ue.getCountryEngName())){
            Where where = new Where();
            where.setFieldName(esConstants.COUNTRY_ENG_NAME);
            where.setFieldValue(ue.getCountryEngName());
            where.setOperationType(EsMatchNames.EQC);
            whereList.add(where);
        }
        // 国家中文名称
        if (StringUtil.isNotNullOrEmpty(ue.getCountryChnName())){
            Where where = new Where();
            where.setFieldName(esConstants.COUNTRY_CHN_NAME);
            where.setFieldValue(ue.getCountryChnName());
            where.setOperationType(EsMatchNames.EQC);
            whereList.add(where);
        }
        // 所属洲编码
        if (StringUtil.isNotNullOrEmpty(ue.getContinentCode())){
            Where where = new Where();
            where.setFieldName(esConstants.CONTINENT_CODE);
            where.setFieldValue(ue.getContinentCode());
            where.setOperationType(EsMatchNames.EQC);
            whereList.add(where);
        }
        return whereList;
    }

}
