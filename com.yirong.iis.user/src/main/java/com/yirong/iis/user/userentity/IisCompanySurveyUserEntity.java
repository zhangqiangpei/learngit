package com.yirong.iis.user.userentity;


import java.io.Serializable;
import java.util.Date;

import com.yirong.commons.util.entity.PageEntiry;

/**
 * 
 * @ClassName: IisCompanySurveyUserEntity  
 * @Description: TODO(企业概况表查询条件类) 
 * @author liny
 * @date 2017年11月22日 上午11:19:09 
 * @version V0.1
 */
public class IisCompanySurveyUserEntity extends PageEntiry implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	* 编码
	*/
	private String id;
	
	
	/**
	* 企业ID
	*/
	private String companyId;
	
	
	/**
	* 企业名称
	*/
	private String companyName;
	
	
	/**
	* 企业概况
	*/
	private String content;
	
	
	/**
	* 企业概况(英文)
	*/
	private String contentEng;
	
	
	/**
	* 企业概况(中文)
	*/
	private String contentChn;
	
	
	/**
	* 来源
	*/
	private String language;
	
	
	/**
	* 上次更新时间
	*/
	private Date lastupdated;
	
	
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
	private Date modifyTime;
	
   
	public String getId(){
		return this.id;
	}
		
	public void setId (String id){
		this.id=id;
	}
	public String getCompanyId(){
		return this.companyId;
	}
		
	public void setCompanyId (String companyId){
		this.companyId=companyId;
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