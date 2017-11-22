package com.yirong.iis.user.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yirong.awaken.core.entity.IdEntity;

/**
 * 
 * @ClassName: IisCompanyList  
 * @Description: TODO(企业列表entity类) 
 * @author liny
 * @date 2017年11月22日 上午11:06:04 
 * @version V0.1
 */
@Entity
@Table(name="IIS_COMPANY_LIST")
public class IisCompanyList  extends IdEntity{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

 
	/**
	* 所属洲(代码表)
	*/
	@Column(name="CONTINENT_CODE",nullable=false,length=6)
	public String continentCode;
	
	/**
	* 国家英文名
	*/
	@Column(name="COUNTRY_ENG_NAME",nullable=false,length=100)
	public String countryEngName;
	
	/**
	* 行业(代码表)
	*/
	@Column(name="INDUSTRY_CODE",nullable=false,length=6)
	public String industryCode;
	
	/**
	* 类型，0-关注企业 1-运营企业 2-主要电力企业 9-其他
	*/
	@Column(name="TYPE",nullable=false,length=1)
	public String type;
	
	/**
	* 企业名称
	*/
	@Column(name="COMPANY_NAME",nullable=false,length=200)
	public String companyName;
	
	/**
	* 创建人
	*/
	@Column(name="CREATOR",nullable=true,length=32)
	public String creator;
	
	/**
	* 创建时间
	*/
	@Column(name="CREATE_TIME",nullable=false)
	public Date createTime;
	
	/**
	* 修改人
	*/
	@Column(name="MODIFIER",nullable=true,length=32)
	public String modifier;
	
	/**
	* 修改时间
	*/
	@Column(name="MODIFY_TIME",nullable=true)
	public Date modifyTime;
	
 
	public String getContinentCode(){
		return this.continentCode;
	}
		
	public void setContinentCode (String continentCode){
		this.continentCode=continentCode;
	}
	public String getCountryEngName(){
		return this.countryEngName;
	}
		
	public void setCountryEngName (String countryEngName){
		this.countryEngName=countryEngName;
	}
	public String getIndustryCode(){
		return this.industryCode;
	}
		
	public void setIndustryCode (String industryCode){
		this.industryCode=industryCode;
	}
	public String getType(){
		return this.type;
	}
		
	public void setType (String type){
		this.type=type;
	}
	public String getCompanyName(){
		return this.companyName;
	}
		
	public void setCompanyName (String companyName){
		this.companyName=companyName;
	}
	public String getCreator(){
		return this.creator;
	}
		
	public void setCreator (String creator){
		this.creator=creator;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
		
	public void setCreateTime (Date createTime){
		this.createTime=createTime;
	}
	public String getModifier(){
		return this.modifier;
	}
		
	public void setModifier (String modifier){
		this.modifier=modifier;
	}
	public Date getModifyTime(){
		return this.modifyTime;
	}
		
	public void setModifyTime (Date modifyTime){
		this.modifyTime=modifyTime;
	}

   
}