package com.yirong.iis.user.entity;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yirong.awaken.core.entity.IdEntity;


/**
 * 
 * @ClassName: VeIisCompany  
 * @Description: TODO(企业视图entity类) 
 * @author liny
 * @date 2017年11月28日 下午7:32:38 
 * @version V0.1
 */
@Entity
@Table(name="VE_IIS_COMPANY")
public class VeIisCompany   extends IdEntity{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/*@Column(name="ID", nullable= false, unique=true,length=32)
	public String id;*/
	
 
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
	* 公司名称(中文名)
	*/
	@Column(name="COMPANY_CHN_NAME",nullable=true,length=200)
	public String companyChnName;
	
	
	/**
	 * 国家英文名
	 */
	@Column(name="COUNTRY_ENGLISH_NAME",nullable=true,length=100)
	public String countryEnglishName;
	
	/**
	 * 行业名称
	 */
	@Column(name="INDUSTRY_NAME",nullable=true,length=100)
	public String industryName;
	
	
	/**
	 * 行业代码 
	 */
	@Column(name="INDUSTRY_CODE",nullable=true,length=9)
	public String industryCode;
 
	/**
	* 公司简介
	*/
	@Column(name="TEXT_INFO",nullable=true)
	public String textInfo;
	
	
	/**
	* 公司简介(英文)
	*/
	@Column(name="TEXT_INFO_ENG",nullable=true)
	public String textInfoEng;
	
	/**
	* 公司简介(中文)
	*/
	@Column(name="TEXT_INFO_CHN",nullable=true)
	public String textInfoChn;
	
	
	 /**
	  * 类型：关注企业、运营企业、电力企业、其他
	  */
	@Column(name="TYPE",nullable=true)
	public String type;
	
	
	/**
	* 创建时间
	*/
	@Column(name="CREATE_TIME",nullable=false)
	public Date createTime;


	public String getCompanyId() {
		return companyId;
	}


	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public String getCompanyChnName() {
		return companyChnName;
	}


	public void setCompanyChnName(String companyChnName) {
		this.companyChnName = companyChnName;
	}


	public String getTextInfo() {
		return textInfo;
	}


	public void setTextInfo(String textInfo) {
		this.textInfo = textInfo;
	}


	public String getTextInfoEng() {
		return textInfoEng;
	}


	public void setTextInfoEng(String textInfoEng) {
		this.textInfoEng = textInfoEng;
	}


	public String getTextInfoChn() {
		return textInfoChn;
	}


	public void setTextInfoChn(String textInfoChn) {
		this.textInfoChn = textInfoChn;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public String getCountryEnglishName() {
		return countryEnglishName;
	}


	public void setCountryEnglishName(String countryEnglishName) {
		this.countryEnglishName = countryEnglishName;
	}


	public String getIndustryName() {
		return industryName;
	}


	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}


	public String getIndustryCode() {
		return industryCode;
	}


	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
 
	
}