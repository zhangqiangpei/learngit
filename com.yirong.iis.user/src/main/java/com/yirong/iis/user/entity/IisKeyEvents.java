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
 * 功能描述：重大事件表entity类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-28 10:57:59
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@Entity
@Table(name="IIS_KEY_EVENTS")
public class IisKeyEvents implements Serializable {

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
	* 标题
	*/
	@Column(name="TITLE",nullable=false)
	public String title;
	
	/**
	* 内容
	*/
	@Column(name="CONTENT",nullable=false)
	public String content;
	
	/**
	* 来源
	*/
	@Column(name="SOURCE",nullable=true,length=200)
	public String source;
	
	/**
	* 发布时间
	*/
	@Column(name="RELEASE_TIME",nullable=true)
	public Date releaseTime;
	
	/**
	* 采集时间
	*/
	@Column(name="COLLECTION_TIME",nullable=false)
	public Date collectionTime;
	
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
	public String getSource(){
		return this.source;
	}
		
	public void setSource (String source){
		this.source=source;
	}
	public Date getReleaseTime(){
		return this.releaseTime;
	}
		
	public void setReleaseTime (Date releaseTime){
		this.releaseTime=releaseTime;
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