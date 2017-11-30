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
 * 功能描述：监管政策表entity类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-27 09:56:48
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@Entity
@Table(name="IIS_REGULATORY_POLICY")
public class IisRegulatoryPolicy implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	* 编码
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
	* 文件名称
	*/
	@Column(name="FIlE_NAME",nullable=false,length=200)
	public String fileName;
	
	/**
	* 文件ID
	*/
	@Column(name="EOS_ID",nullable=true,length=32)
	public String eosId;
	
	/**
	* 来源
	*/
	@Column(name="SOURCE",nullable=true,length=200)
	public String source;
	
	/**
	* 语言
	*/
	@Column(name="LANGUAGE",nullable=true,length=100)
	public String language;
	
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
	public String getFileName(){
		return this.fileName;
	}
		
	public void setFileName (String fileName){
		this.fileName=fileName;
	}
	public String getEosId(){
		return this.eosId;
	}
		
	public void setEosId (String eosId){
		this.eosId=eosId;
	}
	public String getSource(){
		return this.source;
	}
		
	public void setSource (String source){
		this.source=source;
	}
	public String getLanguage(){
		return this.language;
	}
		
	public void setLanguage (String language){
		this.language=language;
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