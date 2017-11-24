package com.yirong.iis.tp.common.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yirong.awaken.core.entity.IdEntity;

 /**
 * 功能描述：人员企业联盟表entity类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-24 19:11:57
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@Entity
@Table(name="LT_TRKD_COMPANY_OFF_AFF")
public class LtTrkdCompanyOffAff extends IdEntity{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	* 公司id
	*/
	@Column(name="COMPANY_ID",nullable=false,length=32)
	public String companyId;
	
	/**
	* 高管id
	*/
	@Column(name="OFFICER_ID",nullable=false,length=32)
	public String officerId;

	@Column(name="AFFILIATION_ORDER",nullable=true,length=5)
	public Integer affiliationOrder;
	
	@Column(name="REQ_NO",nullable=true,length=20)
	public String reqNo;
	
	@Column(name="ORG_ID",nullable=true,length=20)
	public String orgId;
	
	@Column(name="NAME",nullable=true,length=100)
	public String name;
	
	@Column(name="LT_OFFICER_ID",nullable=true,length=10)
	public Integer ltOfficerId;
	
	@Column(name="OFFICER_TITLE",nullable=true,length=100)
	public String officerTitle;
	
	@Column(name="OFFICER_ACTIVE",nullable=true,length=1)
	public Integer officerActive;
	
	/**
	* 创建时间
	*/
	@Column(name="CREATE_TIME",nullable=true)
	public Date createTime;

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getOfficerId() {
		return officerId;
	}

	public void setOfficerId(String officerId) {
		this.officerId = officerId;
	}

	public Integer getAffiliationOrder() {
		return affiliationOrder;
	}

	public void setAffiliationOrder(Integer affiliationOrder) {
		this.affiliationOrder = affiliationOrder;
	}

	public String getReqNo() {
		return reqNo;
	}

	public void setReqNo(String reqNo) {
		this.reqNo = reqNo;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLtOfficerId() {
		return ltOfficerId;
	}

	public void setLtOfficerId(Integer ltOfficerId) {
		this.ltOfficerId = ltOfficerId;
	}

	public String getOfficerTitle() {
		return officerTitle;
	}

	public void setOfficerTitle(String officerTitle) {
		this.officerTitle = officerTitle;
	}

	public Integer getOfficerActive() {
		return officerActive;
	}

	public void setOfficerActive(Integer officerActive) {
		this.officerActive = officerActive;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}