package com.yirong.iis.user.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yirong.awaken.core.entity.IdEntity;

 

/**
 * 
 * @ClassName: IisCompanySurvey  
 * @Description: TODO(企业概况表entity类) 
 * @author liny
 * @date 2017年11月22日 上午11:14:40 
 * @version V0.1
 */
@Entity
@Table(name="IIS_COMPANY_SURVEY")
public class IisCompanySurvey extends IdEntity{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
 
	/**
	* 企业ID
	*/
	@Column(name="COMPANY_ID",nullable=false,length=32)
	public String companyId;
	
	/**
	* 企业名称
	*/
	@Column(name="COMPANY_NAME",nullable=false,length=200)
	public String companyName;
	
	/**
	* 企业概况
	*/
	@Column(name="CONTENT",nullable=false)
	public String content;
	
	/**
	* 企业概况(英文)
	*/
	@Column(name="CONTENT_ENG",nullable=true)
	public String contentEng;
	
	/**
	* 企业概况(中文)
	*/
	@Column(name="CONTENT_CHN",nullable=true)
	public String contentChn;
	
	/**
	* 来源
	*/
	@Column(name="LANGUAGE",nullable=true,length=100)
	public String language;
	
	/**
	* 上次更新时间
	*/
	@Column(name="LASTUPDATED",nullable=true)
	public Date lastupdated;
	
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
	
   
	public String getCompanyId(){
		return this.companyId;
	}
		
	public void setCompanyId (String companyId){
		this.companyId = companyId;
	}
	public String getCompanyName(){
		return this.companyName;
	}
		
	public void setCompanyName (String companyName){
		this.companyName=companyName;
	}
	public String getContent(){
		return this.content;
	}
		
	public void setContent (String content){
		this.content=content;
	}
	public String getContentEng(){
		return this.contentEng;
	}
		
	public void setContentEng (String contentEng){
		this.contentEng=contentEng;
	}
	public String getContentChn(){
		return this.contentChn;
	}
		
	public void setContentChn (String contentChn){
		this.contentChn=contentChn;
	}
	public String getLanguage(){
		return this.language;
	}
		
	public void setLanguage (String language){
		this.language=language;
	}
	public Date getLastupdated(){
		return this.lastupdated;
	}
		
	public void setLastupdated (Date lastupdated){
		this.lastupdated=lastupdated;
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