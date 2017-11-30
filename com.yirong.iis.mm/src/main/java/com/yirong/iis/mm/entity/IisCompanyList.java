package com.yirong.iis.mm.entity;

import java.io.Serializable;
import java.sql.Clob;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/**
 * 功能描述:企业列表entity类
 * 
 * @author 薛雅芳
 *         <p>
 *         创建时间 ：2017-11-28 13:08:59
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@Entity
@Table(name="IIS_COMPANY")
public class IisCompanyList implements Serializable {
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
	* 公司唯一标识
	*/
	@Column(name="COMPANY_ID",nullable=false,length=20)
	public String companyId;
	
	
	/**
	* 企业名称
	*/
	@Column(name="COMPANY_NAME",nullable=false,length=100)
	public String companyName;
	
	/**
	* 企业中文名	
	*/
	@Column(name="COMPANY_CHN_NAME",nullable=true,length=200)
	public String companyChnName;
	
	/**
	* 公司简介(英文)
	*/
	@Column(name="TEXT_INFO_ENG",nullable=true,length=4000)
	public Clob textInfoEng;
	
	/**
	* 公司简介(中文)
	*/
	@Column(name="TEXT_INFO_CHN",nullable=true,length=4000)
	public Clob textInfoChn;
	
	/**
	* 类型(代码表)
	*/
	@Column(name="TYPE",nullable=false,length=6)
	public String type;
	
	/**
	* 创建人
	*/
	@Column(name="CREATOR",nullable=true,length=32)
	public String creator;
	
	/**
	* 创建时间
	*/
	@Column(name="CREATE_TIME",nullable=false,length=7)
	public Date createTime;
	
	/**
	* 修改人
	*/
	@Column(name="MODIFIER",nullable=true,length=32)
	public String modifier;
	
	/**
	* 修改时间
	*/
	@Column(name="MODIFY_TIME",nullable=true,length=7)
	public Date modity_time;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public Clob getTextInfoEng() {
		return textInfoEng;
	}

	public void setTextInfoEng(Clob textInfoEng) {
		this.textInfoEng = textInfoEng;
	}

	public Clob getTextInfoChn() {
		return textInfoChn;
	}

	public void setTextInfoChn(Clob textInfoChn) {
		this.textInfoChn = textInfoChn;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public Date getModity_time() {
		return modity_time;
	}

	public void setModity_time(Date modity_time) {
		this.modity_time = modity_time;
	}
	
}
