package com.yirong.iis.user.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

 /**
 * 功能描述：监管机构表entity类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-27 11:23:45
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@Entity
@Table(name="IIS_REGULATORS")
public class IisRegulators implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	* 编码
	*/
	@Id
	@GeneratedValue(generator="system-uuid",strategy = GenerationType.AUTO)
	@GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name="ID",nullable=false,length=32)
	public String id;
	
	/**
	* 国家英文名
	*/
	@Column(name="COUNTRY_ENG_NAME",nullable=false,length=100)
	public String countryEngName;
	
	/**
	* 监管机构名称
	*/
	@Column(name="ORG_NAME",nullable=false,length=200)
	public String orgName;
	
	/**
	* 监管机构名称（英文）
	*/
	@Column(name="ORG_NAME_EN",nullable=true,length=32)
	public String orgNameEn;
	
	/**
	* 监管机构简介
	*/
	@Column(name="SUMMARY",nullable=true)
	public String summary;
	
	/**
	* 监管机构简介
	*/
	@Column(name="SUMMARY_EN",nullable=true)
	public String summaryEn;
	
	/**
	* 监管机构简介
	*/
	@Column(name="SUMMARY_CHN",nullable=true)
	public String summaryChn; 
	
	/**
	* 来源
	*/
	@Column(name="SOURCE",nullable=true,length=200)
	public String source;
	
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCountryEngName() {
		return countryEngName;
	}

	public void setCountryEngName(String countryEngName) {
		this.countryEngName = countryEngName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgNameEn() {
		return orgNameEn;
	}

	public void setOrgNameEn(String orgNameEn) {
		this.orgNameEn = orgNameEn;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getSummaryEn() {
		return summaryEn;
	}

	public void setSummaryEn(String summaryEn) {
		this.summaryEn = summaryEn;
	}

	public String getSummaryChn() {
		return summaryChn;
	}

	public void setSummaryChn(String summaryChn) {
		this.summaryChn = summaryChn;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	
}
	
