package com.yirong.iis.user.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yirong.awaken.core.entity.IdEntity;


/**
 * 
 * @ClassName: VeIisCompanyRatios  
 * @Description: TODO(企业股市概括视图entity类) 
 * @author liny
 * @date 2017年11月28日 下午7:32:38 
 * @version V0.1
 */
@Entity
@Table(name="VE_IIS_COMPANY_RATIOS")
public class VeIisCompanyRatios  extends IdEntity{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

 
	/**
	* 公司唯一标识
	*/
	@Column(name="COMPANY_ID",nullable=false,length=20)
	public String companyId;
	
	/**
	* 分组名
	*/
	@Column(name="GROUP_ID",nullable=true,length=100)
	public String groupId;
	
	/**
	* 字段名称
	*/
	@Column(name="FIELD_NAME",nullable=true,length=30)
	public String fieldName;
	
	/**
	* 名称
	*/
	@Column(name="NAME",nullable=true,length=255)
	public String name;
	
	
	/**
	* 字段值
	*/
	@Column(name="FIELD_VALUE",nullable=true,length=20)
	public String fieldValue;
	 
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

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
 
}