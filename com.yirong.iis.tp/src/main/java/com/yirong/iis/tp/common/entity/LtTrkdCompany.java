package com.yirong.iis.tp.common.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yirong.awaken.core.entity.IdEntity;

 /**
 * 功能描述：trkd公司表entity类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-24 18:38:31
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@Entity
@Table(name="LT_TRKD_COMPANY")
public class LtTrkdCompany extends IdEntity{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	* 公司简介
	*/
	@Column(name="TEXT_INFO",nullable=true)
	public String textInfo;
	
	/**
	* 公司唯一标识
	*/
	@Column(name="COMPANY_ID",nullable=false,length=20)
	public String companyId;
	
	/**
	* 公司名称
	*/
	@Column(name="COMPANY_NAME",nullable=true,length=100)
	public String companyName;
	
	/**
	* 股票代码
	*/
	@Column(name="TICKER_SYMBOL",nullable=true,length=20)
	public String tickerSymbol;
	
	/**
	* 总部所属国家
	*/
	@Column(name="COUNTRY_ENGLISH_NAME",nullable=true,length=100)
	public String countryEnglishName;
	
	/**
	* 所属经济领域代码
	*/
	@Column(name="ECONOMIC_SECTOR_CODE",nullable=true,length=22)
	public Integer economicSectorCode;
	
	/**
	* 所属经济领域名称
	*/
	@Column(name="ECONOMIC_SECTOR_NAME",nullable=true,length=100)
	public String economicSectorName;
	
	/**
	* 所属商业领域代码
	*/
	@Column(name="BUSINESS_SECTOR_CODE",nullable=true,length=22)
	public Integer businessSectorCode;
	
	/**
	* 所属商业领域名称
	*/
	@Column(name="BUSINESS_SECTOR_NAME",nullable=true,length=100)
	public String businessSectorName;
	
	/**
	* 所属行业分组代码
	*/
	@Column(name="INDUSTRY_GROUP_CODE",nullable=true,length=22)
	public Integer industryGroupCode;
	
	/**
	* 所属行业分组名称
	*/
	@Column(name="INDUSTRY_GROUP_NAME",nullable=true,length=100)
	public String industryGroupName;
	
	/**
	* 所属行业代码
	*/
	@Column(name="INDUSTRY_CODE",nullable=true,length=22)
	public Integer industryCode;
	
	/**
	* 所属行业名称
	*/
	@Column(name="INDUSTRY_NAME",nullable=true,length=100)
	public String industryName;
	
	/**
	* 主营业务代码
	*/
	@Column(name="ACTIVITY_CODE",nullable=true,length=22)
	public Long activityCode;
	
	/**
	* 主营业务名称
	*/
	@Column(name="ACTIVITY_NAME",nullable=true,length=100)
	public String activityName;
	
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
	
	/**
	* 雇员
	*/
	@Column(name="EMPLOYEES",nullable=true,length=10)
	public String employees;
	
	/**
	* 公司主页
	*/
	@Column(name="WEB_LINKS",nullable=true,length=255)
	public String webLinks;
	
	/**
	* 街道地址
	*/
	@Column(name="STREET_ADDRESS",nullable=true,length=255)
	public String streetAddress;
	
	/**
	* 市
	*/
	@Column(name="CITY",nullable=true,length=50)
	public String city;
	
	/**
	* 邮政编码
	*/
	@Column(name="POSTAL_CODE",nullable=true,length=20)
	public String postalCode;
	
	/**
	* 国家/地区
	*/
	@Column(name="STATE_OR_REGION",nullable=true,length=50)
	public String stateOrRegion;
	
	public String getTextInfo(){
		return this.textInfo;
	}
		
	public void setTextInfo (String textInfo){
		this.textInfo=textInfo;
	}
	public String getId(){
		return this.id;
	}
		
	public void setId (String id){
		this.id=id;
	}
	public String getCompanyId(){
		return this.companyId;
	}
		
	public void setCompanyId (String companyId){
		this.companyId=companyId;
	}
	public String getCompanyName(){
		return this.companyName;
	}
		
	public void setCompanyName (String companyName){
		this.companyName=companyName;
	}
	public String getTickerSymbol(){
		return this.tickerSymbol;
	}
		
	public void setTickerSymbol (String tickerSymbol){
		this.tickerSymbol=tickerSymbol;
	}
	public String getCountryEnglishName(){
		return this.countryEnglishName;
	}
		
	public void setCountryEnglishName (String countryEnglishName){
		this.countryEnglishName=countryEnglishName;
	}
	public Integer getEconomicSectorCode(){
		return this.economicSectorCode;
	}
		
	public void setEconomicSectorCode (Integer economicSectorCode){
		this.economicSectorCode=economicSectorCode;
	}
	public String getEconomicSectorName(){
		return this.economicSectorName;
	}
		
	public void setEconomicSectorName (String economicSectorName){
		this.economicSectorName=economicSectorName;
	}
	public Integer getBusinessSectorCode(){
		return this.businessSectorCode;
	}
		
	public void setBusinessSectorCode (Integer businessSectorCode){
		this.businessSectorCode=businessSectorCode;
	}
	public String getBusinessSectorName(){
		return this.businessSectorName;
	}
		
	public void setBusinessSectorName (String businessSectorName){
		this.businessSectorName=businessSectorName;
	}
	public Integer getIndustryGroupCode(){
		return this.industryGroupCode;
	}
		
	public void setIndustryGroupCode (Integer industryGroupCode){
		this.industryGroupCode=industryGroupCode;
	}
	public String getIndustryGroupName(){
		return this.industryGroupName;
	}
		
	public void setIndustryGroupName (String industryGroupName){
		this.industryGroupName=industryGroupName;
	}
	public Integer getIndustryCode(){
		return this.industryCode;
	}
		
	public void setIndustryCode (Integer industryCode){
		this.industryCode=industryCode;
	}
	public String getIndustryName(){
		return this.industryName;
	}
		
	public void setIndustryName (String industryName){
		this.industryName=industryName;
	}
	public Long getActivityCode(){
		return this.activityCode;
	}
		
	public void setActivityCode (Long activityCode){
		this.activityCode=activityCode;
	}
	public String getActivityName(){
		return this.activityName;
	}
		
	public void setActivityName (String activityName){
		this.activityName=activityName;
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

	public String getEmployees(){
		return this.employees;
	}
		
	public void setEmployees (String employees){
		this.employees=employees;
	}

	public String getWebLinks(){
		return this.webLinks;
	}
		
	public void setWebLinks (String webLinks){
		this.webLinks=webLinks;
	}
	public String getStreetAddress(){
		return this.streetAddress;
	}
		
	public void setStreetAddress (String streetAddress){
		this.streetAddress=streetAddress;
	}
	public String getCity(){
		return this.city;
	}
		
	public void setCity (String city){
		this.city=city;
	}
	public String getPostalCode(){
		return this.postalCode;
	}
		
	public void setPostalCode (String postalCode){
		this.postalCode=postalCode;
	}
	public String getStateOrRegion(){
		return this.stateOrRegion;
	}
		
	public void setStateOrRegion (String stateOrRegion){
		this.stateOrRegion=stateOrRegion;
	}
   
}