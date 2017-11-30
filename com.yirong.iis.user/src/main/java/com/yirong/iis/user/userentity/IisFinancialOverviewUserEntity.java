package com.yirong.iis.user.userentity;


import java.io.Serializable;
import com.yirong.commons.util.entity.PageEntiry;

 /**
 * 功能描述：企业财务总览查询条件类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-22 17:21:53
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public class IisFinancialOverviewUserEntity extends PageEntiry implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 六大洲代码
	 */
	private String continentCode;
	
	/**
	 * 国家英文名
	 */
	private String countryEngName;
	
	/**
	 * 行业代码
	 */
	private String industryCode	;
	
	/*
	 * 开始日期
	 */
	private String startDt;
	
	/**
	 * 结束日期
	 */
	private String endDt;
	
	/**
	 * 频度
	 */
	private String frequency;
	
	/**
	 * 关键字
	 */
	private String keyWord;

	public String getContinentCode() {
		return continentCode;
	}

	public void setContinentCode(String continentCode) {
		this.continentCode = continentCode;
	}

	public String getCountryEngName() {
		return countryEngName;
	}

	public void setCountryEngName(String countryEngName) {
		this.countryEngName = countryEngName;
	}

	public String getIndustryCode() {
		return industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

	public String getStartDt() {
		return startDt;
	}

	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}

	public String getEndDt() {
		return endDt;
	}

	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
   
	
}