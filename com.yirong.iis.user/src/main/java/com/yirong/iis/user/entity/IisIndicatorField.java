package com.yirong.iis.user.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yirong.awaken.core.entity.IdEntity;

 /**
 * 功能描述：指标字段表entity类
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
@Table(name="IIS_INDICATOR_FIELD")
public class IisIndicatorField extends IdEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	* 字段排序
	*/
	@Column(name="FIELD_SORT",nullable=false,length=22)
	public Integer fieldSort;
	
	/**
	* 字段类型(代码表)
	*/
	@Column(name="FIELD_TYPE",nullable=false,length=6)
	public String fieldType;
	
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
	* 数据分类ID
	*/
	@Column(name="INDICATOR_ID",nullable=false,length=32)
	public String indicatorId;
	
	/**
	* 字段中文名
	*/
	@Column(name="FIELD_NAME",nullable=false,length=100)
	public String fieldName;
	
	/**
	* 字段英文名
	*/
	@Column(name="FIELD_CODE",nullable=false,length=40)
	public String fieldCode;
	
   
	public Integer getFieldSort(){
		return this.fieldSort;
	}
		
	public void setFieldSort (Integer fieldSort){
		this.fieldSort=fieldSort;
	}
	public String getFieldType(){
		return this.fieldType;
	}
		
	public void setFieldType (String fieldType){
		this.fieldType=fieldType;
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
	public String getId(){
		return this.id;
	}
		
	public void setId (String id){
		this.id=id;
	}
	public String getIndicatorId(){
		return this.indicatorId;
	}
		
	public void setIndicatorId (String indicatorId){
		this.indicatorId=indicatorId;
	}
	public String getFieldName(){
		return this.fieldName;
	}
		
	public void setFieldName (String fieldName){
		this.fieldName=fieldName;
	}
	public String getFieldCode(){
		return this.fieldCode;
	}
		
	public void setFieldCode (String fieldCode){
		this.fieldCode=fieldCode;
	}

   
}