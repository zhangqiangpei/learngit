package com.yirong.iis.user.userentity;

import java.io.Serializable;
import java.util.Date;

import com.yirong.commons.util.entity.PageEntiry;

 /**
 * 功能描述：专题表查询条件类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-24 15:49:14
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public class IisThematicUserEntity extends PageEntiry implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	* ID
	*/
	private String id;
	
	
	/**
	* 专题名称
	*/
	private String thematicName;
	
	
	/**
	* 专题分类ID
	*/
	private String thematicClassify;
	
	
	/**
	* 专题布局信息
	*/
	private String thematicLayout;
	
	
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
	
	
	/**
	* 发布状态
	*/
	private String status;
   
	public String getId(){
		return this.id;
	}
		
	public void setId (String id){
		this.id=id;
	}
	public String getThematicName(){
		return this.thematicName;
	}
		
	public void setThematicName (String thematicName){
		this.thematicName=thematicName;
	}
	public String getThematicClassify(){
		return this.thematicClassify;
	}
		
	public void setThematicClassify (String thematicClassify){
		this.thematicClassify=thematicClassify;
	}
	public String getThematicLayout(){
		return this.thematicLayout;
	}
		
	public void setThematicLayout (String thematicLayout){
		this.thematicLayout=thematicLayout;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
   
}