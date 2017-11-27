package com.yirong.iis.tp.common.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yirong.awaken.core.entity.IdEntity;

@Entity
@Table(name="LT_TRKD_COMPANY_INDUSTRY")
public class LtTrkdCompanyIndustry extends IdEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 公司id
	 */
	@Column(name="COMPANY_ID",nullable=false,length=32)
	public String companyId;
	
	/**
	 * 类型
	 */
	@Column(name="TYPE",nullable=true,length=20)
	public String type;
	
	/**
	 * 排序
	 */
	@Column(name="SEQUENCE",nullable=true,length=10)
	public Integer sequence;
	
	/**
	 * 编码
	 */
	@Column(name="CODE",nullable=true,length=20)
	public Integer code;
	
	/**
	 * 描述
	 */
	@Column(name="DESCRIPTION",nullable=true,length=100)
	public String description;
	
	@Column(name="MNEMONIC",nullable=true,length=100)
	public String mnemonic;
	
	/**
	 * 创建时间
	 */
	@Column(name="CREATE_TIME",nullable=false)
	public String createTime;

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMnemonic() {
		return mnemonic;
	}

	public void setMnemonic(String mnemonic) {
		this.mnemonic = mnemonic;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}