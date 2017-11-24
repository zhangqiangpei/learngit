package com.yirong.iis.tp.common.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yirong.awaken.core.entity.IdEntity;

 /**
 * 功能描述：公司高管表entity类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-24 19:30:17
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@Entity
@Table(name="LT_TRKD_COMPANY_OFFICER")
public class LtTrkdCompanyOfficer extends IdEntity{

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
	 * 状态
	 */
	@Column(name="STATUS",nullable=true,length=10)
	public String status;
	
	@Column(name="RANK",nullable=true,length=10)
	public Integer rank;
	
	/**
	 * 人员id
	 */
	@Column(name="PERSON_ID",nullable=true,length=20)
	public String personId;
	
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
	 * 前缀
	 */
	@Column(name="PREFERREDP_NAME",nullable=true,length=20)
	public String preferredpName;
	
	/**
	 * 首选名称
	 */
	@Column(name="PREFIX",nullable=true,length=50)
	public String prefix;
	
	/**
	 * 中间初始
	 */
	@Column(name="MIDDLE_INITIAL",nullable=true,length=20)
	public String middleInitial;
	
	/**
	 * 年龄
	 */
	@Column(name="AGE",nullable=true,length=3)
	public Integer age;
	
	/**
	 * 性别
	 */
	@Column(name="SEX",nullable=true,length=2)
	public Integer sex;
	
	/**
	 * 入职日期
	 */
	@Column(name="OFFICER_START",nullable=true,length=20)
	public String officerStart;
	
	@Column(name="DIRECTOR_START",nullable=true,length=20)
	public String directorStart;
	
	/**
	 * 个人简历
	 */
	@Column(name="BIOGRAPHY",nullable=true)
	public String biography;
	
	@Column(name="ACTIVE",nullable=true,length=1)
	public Integer active;
	
	@Column(name="PERSON_ACTIVE",nullable=true,length=1)
	public Integer personActive;
	
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
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

	public String getPreferredpName() {
		return preferredpName;
	}

	public void setPreferredpName(String preferredpName) {
		this.preferredpName = preferredpName;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getOfficerStart() {
		return officerStart;
	}

	public void setOfficerStart(String officerStart) {
		this.officerStart = officerStart;
	}

	public String getDirectorStart() {
		return directorStart;
	}

	public void setDirectorStart(String directorStart) {
		this.directorStart = directorStart;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public Integer getPersonActive() {
		return personActive;
	}

	public void setPersonActive(Integer personActive) {
		this.personActive = personActive;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}