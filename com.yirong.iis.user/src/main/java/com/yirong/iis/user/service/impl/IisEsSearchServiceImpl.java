package com.yirong.iis.user.service.impl;

import com.yirong.awaken.core.util.ResultUtil;
import com.yirong.commons.es.constant.EsMatchNames;
import com.yirong.commons.es.eif.EsClientEif;
import com.yirong.commons.util.datatype.StringUtil;
import com.yirong.commons.util.entity.PageEntiry;
import com.yirong.commons.util.order.EsSelect;
import com.yirong.commons.util.order.Where;
import com.yirong.iis.user.constant.esConstants;
import com.yirong.iis.user.service.IisEsSearchService;
import com.yirong.iis.user.userentity.IisNewsUserEntity;
import com.yirong.iis.user.userentity.IisReportUserEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author MingTie
 * CreateDate：2017/11/24
 * Description：Es搜索
 */
@SuppressWarnings("rawtypes")
@Service("IisEsSearchServiceImpl")
public class IisEsSearchServiceImpl implements IisEsSearchService {
    /**
     * 功能描述：搜索新闻
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
    public Map esSearchNews(IisNewsUserEntity ue, String tokenId) {
        /** 查询条件 **/
        List<Where> whereList = this.getNewsEsWhere(ue, tokenId);
        /** 返回结果 **/
        EsSelect select = new EsSelect();
        if (StringUtil.isNotNullOrEmpty(ue.getKeywords())) {
            select.setKeyword(ue.getKeywords().trim());
            select.setKeywordFields(new String[]{esConstants.SUMMARY});
        }
//        select.setAnalyzer("ik_max_word");
        select.setIsHighlight(true);
        select.setIncludes(new String[]{esConstants.TITLE, esConstants.TITLE_CN, esConstants.TITLE_EN,
                esConstants.COMPANY_NAME, esConstants.SUMMARY,esConstants.SOURCE,
                esConstants.RELEASE_TIME,esConstants.NEWS_TYPE,esConstants.COUNTRY_CHN_NAME,
        esConstants.COUNTRY_ENG_NAME});
        // 获取数据
        PageEntiry page = EsClientEif.textSearch(esConstants.NEWS_INDEX_NAME, esConstants.NEWS_TYPE_NAME, ue,
                whereList, select);
        return ResultUtil.newOk("操作成功").setData(page).toMap();
    }


    /**
     * 功能描述：根据ID搜索新闻
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
    public Map getNewsById(String id) {
        if (StringUtil.isNotNullOrEmpty(id)){
            Map<String, Object> news =
                    EsClientEif.getById(esConstants.NEWS_INDEX_NAME, esConstants.NEWS_TYPE_NAME, id);
            return ResultUtil.newOk("操作成功").setData(news).toMap();
        } else {
            return ResultUtil.newError("传入的新闻ID为空").toMap();
        }
    }

    /**
     * 功能描述：搜索报告
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
    public Map esSearchReport(IisReportUserEntity ue, String tokenId) {
        /** 查询条件 **/
        List<Where> whereList = new ArrayList<Where>();
        if (StringUtil.isNotNullOrEmpty(ue.getReportInfo())){
            Where where = new Where();
            // 报告内容
            where.setFieldName(esConstants.CONTENT);
            where.setFieldValue(ue.getReportInfo());
            where.setOperationType(EsMatchNames.LIKENC);
            whereList.add(where);
        }
        /** 返回结果 **/
        EsSelect select = new EsSelect();
        if (StringUtil.isNotNullOrEmpty(ue.getKeywords())) {
            select.setKeyword(ue.getKeywords().trim());
            select.setKeywordFields(new String[]{esConstants.TITLE});
        }
        select.setAnalyzer("ik_max_word");
        select.setIsHighlight(true);
        select.setIncludes(new String[]{esConstants.TITLE});
        // 获取数据
        PageEntiry page = null;
        EsClientEif.textSearch(esConstants.REPORT_INDEX_NAME, esConstants.REPORT_TYPE_NAME, ue,
                whereList, select);
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
    private List<Where> getNewsEsWhere(IisNewsUserEntity ue, String tokenId) {
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
