package com.yirong.iis.user.userentity;


import java.io.Serializable;
import java.util.Date;

import com.yirong.commons.util.entity.PageEntiry;

 
/**
 * 
 * @ClassName: IisCompanyInformationUserEntity  
 * @Description: TODO(企业资讯表查询条件类) 
 * @author liny
 * @date 2017年11月22日 上午11:18:01 
 * @version V0.1
 */
public class IisCompanyInformationUserEntity extends PageEntiry implements Serializable {

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
	* 标题
	*/
	private String title;
	
	
	/**
	* 内容
	*/
	private String content;
	
	
	/**
	* 采集时间
	*/
	private Date collectionTime;
	
	
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
	
	
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName(){
		return this.companyName;
	}
		
	public void setCompanyName (String companyName){
		this.companyName=companyName;
	}
	public String getTitle(){
		return this.title;
	}
		
	public void setTitle (String title){
		this.title=title;
	}
	public String getContent(){
		return this.content;
	}
		
	public void setContent (String content){
		this.content=content;
	}
	public Date getCollectionTime(){
		return this.collectionTime;
	}
		
	public void setCollectionTime (Date collectionTime){
		this.collectionTime=collectionTime;
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