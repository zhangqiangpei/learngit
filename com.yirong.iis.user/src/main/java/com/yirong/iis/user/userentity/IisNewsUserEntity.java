package com.yirong.iis.user.userentity;


import java.io.Serializable;
import java.util.Date;

import com.yirong.commons.util.entity.PageEntiry;

/**
 * 功能描述：新闻表查询条件类
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
public class IisNewsUserEntity extends PageEntiry implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 关键字
     */
    private String keywords;
    /**
     * 标题
     */
    private String title;
    /**
     * 中文标题
     */
    private String titleCn;
    /**
     * 内容
     */
    private String content;
    /**
     * 中文内容
     */
    private String contentCn;
    /**
     * 概要
     */
    private String summary;
    /**
     * 来源
     */
    private String source;
    /**
     * 类型
     */
    private String type;
    /**
     * 发布时间
     */
    private String releaseTime;
    /**
     * 国家ID
     */
    private String countryId;
    /**
     * 国家名称
     */
    private String countryName;
    /**
     * 所属洲编码
     */
    private String continentCode;

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleCn() {
        return titleCn;
    }

    public void setTitleCn(String titleCn) {
        this.titleCn = titleCn;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentCn() {
        return contentCn;
    }

    public void setContentCn(String contentCn) {
        this.contentCn = contentCn;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getContinentCode() {
        return continentCode;
    }

    public void setContinentCode(String continentCode) {
        this.continentCode = continentCode;
    }
}
