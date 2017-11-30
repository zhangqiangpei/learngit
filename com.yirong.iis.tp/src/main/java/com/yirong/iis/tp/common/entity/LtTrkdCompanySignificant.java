package com.yirong.iis.tp.common.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yirong.awaken.core.entity.IdEntity;

 /**
 * 功能描述：公司重大事件表entity类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-28 08:51:07
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@Entity
@Table(name="LT_TRKD_COMPANY_SIGNIFICANT")
public class LtTrkdCompanySignificant extends IdEntity{

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
	 * 国家，简写
	 */
	@Column(name="COUNTRY",nullable=true,length=30)
	public String country;
	
	/**
	 * 初始时间
	 */
	@Column(name="INITIATION",nullable=true,length=30)
	public String initiation;
	
	/**
	 * 最后修改时间
	 */
	@Column(name="LAST_UPDATE",nullable=true,length=30)
	public String lastUpdate;
	
	/**
	 * 重要性
	 */
	@Column(name="SIGNIFICANCE",nullable=true,length=10)
	public Integer significance;
	
	/**
	 * 分类
	 */
	@Column(name="TOPIC",nullable=true,length=100)
	public String topic;
	
	/**
	 * 标题
	 */
	@Column(name="HEADLINE",nullable=true,length=255)
	public String headline;
	
	/**
	 * 内容
	 */
	@Column(name="DESCRIPTION",nullable=true)
	public String description;
	
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getInitiation() {
		return initiation;
	}

	public void setInitiation(String initiation) {
		this.initiation = initiation;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Integer getSignificance() {
		return significance;
	}

	public void setSignificance(Integer significance) {
		this.significance = significance;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}