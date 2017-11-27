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
 * 功能描述：专题表entity类
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
@Table(name="IIS_THEMATIC")
public class IisThematic implements Serializable {

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
	* 专题名称
	*/
	@Column(name="THEMATIC_NAME",nullable=false,length=200)
	public String thematicName;
	
	/**
	* 专题分类ID
	*/
	@Column(name="THEMATIC_CLASSIFY",nullable=true,length=32)
	public String thematicClassify;
	
	/**
	* 专题布局信息
	*/
	@Column(name="THEMATIC_LAYOUT",nullable=true)
	public String thematicLayout;
	
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
	
	/**
	* 发布状态
	*/
	@Column(name="STATUS",nullable=true,length=32)
	public String status;
   
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