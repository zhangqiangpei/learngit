package com.yirong.iis.user.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yirong.awaken.core.entity.IdEntity;

/**
 * 
 * @ClassName: SyCompanyOfficer  
 * @Description: TODO(公司高管视图entity类) 
 * @author liny
 * @date 2017年11月28日 下午7:39:40 
 * @version V0.1
 */
@Entity
@Table(name="VE_IIS_COMPANY_OFFICER")
public class VeIisCompanyOfficer extends IdEntity{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	* 公司id
	*/
	@Column(name="COMPANY_ID",nullable=false,length=32)
	public String companyId;
	
	
	@Column(name="RANK",nullable=true,length=10)
	public Integer rank;
	
	/**
	 * 姓
	 */
	@Column(name="LAST_NAME",nullable=true,length=50)
	public String lastName;
	
	/**
	 * 名
	 */
	@Column(name="FIRST_NAME",nullable=true,length=50)
	public String firstName;
	
	/**
	 * 英文名字中的中间名
	 */
	@Column(name="MI",nullable=true,length=500)
	public String mi;
	
	/**
	 * 年龄
	 */
	@Column(name="AGE",nullable=true,length=3)
	public Integer age;
	
	/**
	 * 职位开始时间
	 */
	@Column(name="TITLE_START",nullable=true,length=20)
	public String titleStart;
	
	/**
	 * 加入公司时间
	 */
	@Column(name="SINCE",nullable=true,length=20)
	public String since;
	
	/**
	 * 岗位
	 */
	@Column(name="TITLE",nullable=true,length=500)
	public String title;
 
	
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

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getTitleStart() {
		return titleStart;
	}

	public void setTitleStart(String titleStart) {
		this.titleStart = titleStart;
	}

	public String getSince() {
		return since;
	}

	public void setSince(String since) {
		this.since = since;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMi() {
		return mi;
	}

	public void setMi(String mi) {
		this.mi = mi;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}