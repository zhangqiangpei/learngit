package com.yirong.iis.user.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

 /**
 * 功能描述：专题模块表entity类
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
@Entity
@Table(name="IIS_THEMATIC_ITEM")
public class IisThematicItem implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	* ID
	*/
	@Id
	@GeneratedValue(generator="system-uuid",strategy = GenerationType.AUTO)
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name="ID",nullable=false,length=32)
	public String id;
	
	/**
	* 专题ID
	*/
	@Column(name="THEMATIC_ID",nullable=false,length=32)
	public String thematicId;
	
	/**
	* 专题模块ID
	*/
	@Column(name="THEMATIC_ITEM_ID",nullable=false,length=32)
	public String thematicItemId;
	
	/**
	* 专题模块数据
	*/
	@Column(name="THEMATIC_ITEM_DATA",nullable=true)
	public String thematicItemData;
	
	/**
	* 专题模块类型
	*/
	@Column(name="TYPE",nullable=true)
	public String type;
	
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