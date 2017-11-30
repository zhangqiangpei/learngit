package com.yirong.iis.user.userentity;
import java.io.Serializable;

import com.yirong.commons.util.entity.PageEntiry;

 /**
 * 功能描述：监管机构表查询条件类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-27 11:23:46
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public class IisRegulatorsUserEntity extends PageEntiry implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	* 编码
	*/
	private String id;
	
	
	/**
	* 国家英文名
	*/
	private String countryEngName;
	
	
	/**
	* 监管机构名称
	*/
	private String orgName;
	
	
	/**
	* 监管机构名称（英文）
	*/
	private String orgNameEn;
	
	public String summary;

	public String summaryEn;
	
	public String summaryChn;

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
	
	
}
	