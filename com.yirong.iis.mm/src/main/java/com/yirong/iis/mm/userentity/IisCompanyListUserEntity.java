package com.yirong.iis.mm.userentity;

import java.io.Serializable;
import java.sql.Clob;
import java.util.Date;

import com.yirong.commons.util.entity.PageEntiry;

/**
 * 
 * 功能描述:企业列表查询条件类
 *
 * @author 薛雅芳
 *         <p>
 *         修改时间:2017年11月28日下午4:30:13
 *         </p>
 *         
 *         <p>
 *         修改历史:(修改人,修改时间,修改原因/内容)
 *         </p>
 */
public class IisCompanyListUserEntity extends PageEntiry implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	* 编码
	*/
	private String id;
	
	/**
	* 公司唯一标识
	*/
	private String companyId;
	
	
	/**
	* 企业名称
	*/
	private String companyName;
	
	/**
	* 企业中文名	
	*/
	private String companyChnName;
	
	/**
	* 公司简介(英文)
	*/
	private Clob textInfoEng;
	
	/**
	* 公司简介(中文)
	*/
	public Clob textInfoChn;
	
	/**
	* 类型(代码表)
	*/
	private String type;
	
	/**
	* 创建人
	*/
	private String creator;
	
	/**
	* 创建时间
	*/
	private Date createTime;
	
	/**
	* 修改人
	*/
	private String modifier;
	
	/**
	* 修改时间
	*/
	private Date modity_time;

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
