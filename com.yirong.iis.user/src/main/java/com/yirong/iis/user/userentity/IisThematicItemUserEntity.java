package com.yirong.iis.user.userentity;

import java.io.Serializable;
import java.util.Date;

import com.yirong.commons.util.entity.PageEntiry;

 /**
 * 功能描述：专题模块表查询条件类
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
public class IisThematicItemUserEntity extends PageEntiry implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	* ID
	*/
	private String id;
	
	
	/**
	* 专题ID
	*/
	private String thematicId;
	
	
	/**
	* 专题模块ID
	*/
	private String thematicItemId;
	
	
	/**
	* 专题模块数据
	*/
	private String thematicItemData;
	
	
	/**
	* 专题模块类型
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
	private Date modifyTime;
	
   
	public String getId(){
		return this.id;
	}
		
	public void setId (String id){
		this.id=id;
	}
	public String getThematicId(){
		return this.thematicId;
	}
		
	public void setThematicId (String thematicId){
		this.thematicId=thematicId;
	}
	public String getThematicItemId(){
		return this.thematicItemId;
	}
		
	public void setThematicItemId (String thematicItemId){
		this.thematicItemId=thematicItemId;
	}
	public String getThematicItemData(){
		return this.thematicItemData;
	}
		
	public void setThematicItemData (String thematicItemData){
		this.thematicItemData=thematicItemData;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
   
}