package com.yirong.iis.tp.common.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yirong.awaken.core.entity.IdEntity;

 /**
 * 功能描述：公司高管履历表entity类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-24 19:36:42
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@Entity
@Table(name="LT_TRKD_COMPANY_OFFICER_POST")
public class LtTrkdCompanyOfficerPost extends IdEntity{

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
	@Column(name="OFFICER_ID",nullable=true,length=32)
	public String officerId;
	
	/**
	* 开始时间
	*/
	@Column(name="START_DATE",nullable=true,length=20)
	public String startDate;
	
	/**
	* 结束时间
	*/
	@Column(name="END_DATE",nullable=true,length=20)
	public String endDate;
	
	/**
	* 长标题
	*/
	@Column(name="LONG_TITLE",nullable=true,length=255)
	public String longTitle;
	
	/**
	* 排序
	*/
	@Column(name="SEQUENCE",nullable=true,length=22)
	public Integer sequence;
	
	@Column(name="TITLE_ID",nullable=true,length=20)
	public String titleId;
	
	@Column(name="TITLE_VALUE",nullable=true,length=100)
	public String titleValue;
	
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

	public String getOfficerId() {
		return officerId;
	}

	public void setOfficerId(String officerId) {
		this.officerId = officerId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getLongTitle() {
		return longTitle;
	}

	public void setLongTitle(String longTitle) {
		this.longTitle = longTitle;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getTitleId() {
		return titleId;
	}

	public void setTitleId(String titleId) {
		this.titleId = titleId;
	}

	public String getTitleValue() {
		return titleValue;
	}

	public void setTitleValue(String titleValue) {
		this.titleValue = titleValue;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}