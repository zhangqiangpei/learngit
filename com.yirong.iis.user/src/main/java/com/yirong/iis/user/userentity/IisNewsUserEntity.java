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
     * 英文标题
     */
    private String titleEn;
    /**
     * 内容
     */
    private String content;
    /**
     * 中文内容
     */
    private String contentCn;
    /**
     * 英文内容
     */
    private String contentEn;
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
	* 企业名称
	*/
	private String companyName;
	
    /**
     * 国家英文名称
     */
    private String countryEngName;
    /**
     * 国家中文名称
     */
    private String countryChnName;
    /**
     * 所属洲编码
     */
    private String continentCode;
    /**
     * 发布时间
     */
    private String releaseTime;
    /**
     * 采集时间
     */
    private String collectionTime;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 修改人
     */
    private String modifier;
    /**
     * 修改时间
     */
    private String modifyTime;

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

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getContentEn() {
        return contentEn;
    }

    public void setContentEn(String contentEn) {
        this.contentEn = contentEn;
    }

    public String getCountryEngName() {
        return countryEngName;
    }

    public void setCountryEngName(String countryEngName) {
        this.countryEngName = countryEngName;
    }

    public String getCountryChnName() {
        return countryChnName;
    }

    public void setCountryChnName(String countryChnName) {
        this.countryChnName = countryChnName;
    }

    public String getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(String collectionTime) {
        this.collectionTime = collectionTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getContinentCode() {
        return continentCode;
    }

    public void setContinentCode(String continentCode) {
        this.continentCode = continentCode;
    }

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
    
    
}
