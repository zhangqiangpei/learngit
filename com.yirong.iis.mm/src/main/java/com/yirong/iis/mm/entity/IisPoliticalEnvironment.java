package com.yirong.iis.mm.entity;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

 /**
 * 功能描述：政治环境表entity类
 * 
 * @author 陈清沣
 *         <p>
 *         创建时间 ：2017-11-30 10:42:31
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@Entity
@Table(name="IIS_POLITICAL_ENVIRONMENT")
public class IisPoliticalEnvironment implements Serializable {

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
	* 国家英文名
	*/
	@Column(name="COUNTRY_ENG_NAME",nullable=false,length=100)
	public String countryEngName;
	
	/**
	* 字段名称
	*/
	@Column(name="FIELD_NAME",nullable=false,length=100)
	public String fieldName;
	
	/**
	* 字段值
	*/
	@Column(name="FIELD_VALUE",nullable=false)
	public String fieldValue;
	
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
	public String getCountryEngName(){
		return this.countryEngName;
	}
		
	public void setCountryEngName (String countryEngName){
		this.countryEngName=countryEngName;
	}
	public String getFieldName(){
		return this.fieldName;
	}
		
	public void setFieldName (String fieldName){
		this.fieldName=fieldName;
	}
	public String getFieldValue(){
		return this.fieldValue;
	}
		
	public void setFieldValue (String fieldValue){
		this.fieldValue=fieldValue;
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