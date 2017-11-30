package com.yirong.iis.user.userentity;


import java.io.Serializable;
import java.util.Date;

import com.yirong.commons.util.entity.PageEntiry;

 
/**
 * 
 * @ClassName: VeIisCompanyUserEntity  
 * @Description: TODO(企业视图查询条件类)
 * @author liny
 * @date 2017年11月28日 下午8:02:01 
 * @version V0.1
 */
public class VeIisCompanyUserEntity extends PageEntiry implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	* 编码
	*/
	private String id;
	
	/**
	 * 公司id
	 */
	public String companyId;
	
	/**
	 * 公司名称
	 */
	public String companyName;
	
	 /**
	  * 类型：关注企业、运营企业、电力企业、其他
	  */
	public String type;
 
	/**
	 * 国家英文名
	 */
	public String countryEnglishName;
	

	/**
	 * 行业代码 
	 */
	public String industryCode;
	
	/**
	 * 开始时间
	 */
	public String startDt;
	
	/**
	 * 结束时间
	 */
	public String endDt;
	
	/**
	 * 频度
	 */
	public String frequency;
	
	/**
	 * 关键字
	 */
	public String keyWord;
	
 
	/**
	* 创建时间
	*/
	private Date createTime;


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


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public String getCountryEnglishName() {
		return countryEnglishName;
	}


	public void setCountryEnglishName(String countryEnglishName) {
		this.countryEnglishName = countryEnglishName;
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