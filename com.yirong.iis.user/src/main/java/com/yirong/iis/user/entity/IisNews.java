package com.yirong.iis.user.entity;


import org.hibernate.annotations.GenericGenerator;

import com.yirong.awaken.core.entity.IdEntity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * 功能描述：新闻表entity类
 *
 * @author liny
 *         <p>
 *         创建时间 ：2017-11-21 15:43:12
 *         </p>
 *
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@Entity
@Table(name="IIS_NEWS")
public class IisNews extends IdEntity{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	* 企业名称
	*/
	@Column(name="COMPANY_NAME",nullable=true,length=100)
	public String companyName;
	
 
	/**
	* 标题
	*/
	@Column(name="TITLE",nullable=false,length=200)
	public String title;
	
	/**
	* 标题(英文)
	*/
	@Column(name="TITLE_EN",nullable=true,length=200)
	public String titleEn;
	
	/**
	* 标题(中文)
	*/
	@Column(name="TITLE_CHN",nullable=true,length=200)
	public String titleChn;
	
	/**
	* 内容
	*/
	@Column(name="CONTENT",nullable=false)
	public String content;
	
	/**
	* 内容(英文)
	*/
	@Column(name="CONTENT_EN",nullable=true)
	public String contentEn;
	
	/**
	* 内容(中文)
	*/
	@Column(name="CONTENT_CHN",nullable=true)
	public String contentChn;
	
	/**
	* 概要
	*/
	@Column(name="SUMMARY",nullable=true)
	public String summary;
	
	/**
	* 来源
	*/
	@Column(name="SOURCE",nullable=false,length=40)
	public String source;
	
	/**
	* 类型(代码表)：能源、企业、政治、财经、社会
	*/
	@Column(name="TYPE",nullable=false,length=6)
	public String type;
	
	/**
	* 国家英文名
	*/
	@Column(name="COUNTRY_ENG_NAME",nullable=false,length=100)
	public String countryEngName;
	
	/**
	* 国家中文名
	*/
	@Column(name="COUNTRY_CHN_NAME",nullable=true,length=100)
	public String countryChnName;
	
	/**
	* 所属洲(代码表)
	*/
	@Column(name="CONTINENT_CODE",nullable=true,length=6)
	public String continentCode;
	
	/**
	* 发布时间
	*/
	@Column(name="RELEASE_TIME",nullable=true)
	public Date releaseTime;
	
	/**
	* 采集时间
	*/
	@Column(name="COLLECTION_TIME",nullable=false)
	public Date collectionTime;
	
	/**
	* 创建人
	*/
	@Column(name="CREATOR",nullable=true,length=32)
	public String creator;
	
	/**
	* 创建时间
	*/
	@Column(name="CREATE_TIME",nullable=false)
	public Date createTime;
	
	/**
	* 修改人
	*/
	@Column(name="MODIFIER",nullable=true,length=32)
	public String modifier;
	
	/**
	* 修改时间
	*/
	@Column(name="MODIFY_TIME",nullable=true)
	public Date modifyTime;
	
   
	public String getCompanyName(){
		return this.companyName;
	}
		
	public void setCompanyName (String companyName){
		this.companyName=companyName;
	}
 
	public String getTitle(){
		return this.title;
	}
		
	public void setTitle (String title){
		this.title=title;
	}
	public String getTitleEn(){
		return this.titleEn;
	}
		
	public void setTitleEn (String titleEn){
		this.titleEn=titleEn;
	}
	public String getTitleChn(){
		return this.titleChn;
	}
		
	public void setTitleChn (String titleChn){
		this.titleChn=titleChn;
	}
	public String getContent(){
		return this.content;
	}
		
	public void setContent (String content){
		this.content=content;
	}
	public String getContentEn(){
		return this.contentEn;
	}
		
	public void setContentEn (String contentEn){
		this.contentEn=contentEn;
	}
	public String getContentChn(){
		return this.contentChn;
	}
		
	public void setContentChn (String contentChn){
		this.contentChn=contentChn;
	}
	public String getSummary(){
		return this.summary;
	}
		
	public void setSummary (String summary){
		this.summary=summary;
	}
	public String getSource(){
		return this.source;
	}
		
	public void setSource (String source){
		this.source=source;
	}
	public String getType(){
		return this.type;
	}
		
	public void setType (String type){
		this.type=type;
	}
	public String getCountryEngName(){
		return this.countryEngName;
	}
		
	public void setCountryEngName (String countryEngName){
		this.countryEngName=countryEngName;
	}
	public String getCountryChnName(){
		return this.countryChnName;
	}
		
	public void setCountryChnName (String countryChnName){
		this.countryChnName=countryChnName;
	}
	public String getContinentCode(){
		return this.continentCode;
	}
		
	public void setContinentCode (String continentCode){
		this.continentCode=continentCode;
	}
	public Date getReleaseTime(){
		return this.releaseTime;
	}
		
	public void setReleaseTime (Date releaseTime){
		this.releaseTime=releaseTime;
	}
	public Date getCollectionTime(){
		return this.collectionTime;
	}
		
	public void setCollectionTime (Date collectionTime){
		this.collectionTime=collectionTime;
	}
	public String getCreator(){
		return this.creator;
	}
		
	public void setCreator (String creator){
		this.creator=creator;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
		
	public void setCreateTime (Date createTime){
		this.createTime=createTime;
	}
	public String getModifier(){
		return this.modifier;
	}
		
	public void setModifier (String modifier){
		this.modifier=modifier;
	}
	public Date getModifyTime(){
		return this.modifyTime;
	}
		
	public void setModifyTime (Date modifyTime){
		this.modifyTime=modifyTime;
	}
 
}
