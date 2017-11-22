package com.yirong.iis.user.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yirong.awaken.core.entity.IdEntity;

 /**
 * 功能描述：指标信息表entity类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-22 09:43:03
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@Entity
@Table(name="IIS_INDICATOR_INFO")
public class IisIndicatorInfo extends IdEntity {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	* 数据分类ID
	*/
	@Column(name="DATA_CLASSIFY_ID",nullable=false,length=32)
	public String dataClassifyId;
	
	/**
	* 指标名称
	*/
	@Column(name="NAME",nullable=false,length=256)
	public String name;
	
	/**
	* 表名称
	*/
	@Column(name="TABLE_NAME",nullable=false,length=256)
	public String tableName;
	
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
	public String getDataClassifyId(){
		return this.dataClassifyId;
	}
		
	public void setDataClassifyId (String dataClassifyId){
		this.dataClassifyId=dataClassifyId;
	}
	public String getName(){
		return this.name;
	}
		
	public void setName (String name){
		this.name=name;
	}
	public String getTableName(){
		return this.tableName;
	}
		
	public void setTableName (String tableName){
		this.tableName=tableName;
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