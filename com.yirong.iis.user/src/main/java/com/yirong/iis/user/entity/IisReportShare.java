package com.yirong.iis.user.entity;


import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

 /**
 * 功能描述：报告共享表entity类
 * 
 * @author 林明铁
 *         <p>
 *         创建时间 ：2017-11-09 10:00:09
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@Entity
@Table(name="IIS_REPORT_SHARE")
public class IisReportShare implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	* 报告共享ID
	*/
	@Id
	@GeneratedValue(generator="system-uuid",strategy = GenerationType.AUTO)
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name="ID",nullable=false,length=32)
	public String id;
	
	/**
	* 所属报告ID
	*/
	@Column(name="REPORT_ID",nullable=false,length=32)
	public String reportId;
	
	/**
	* 共享类型(代码表:012)
	*/
	@Column(name="SHARE_TYPE",nullable=false,length=6)
	public String shareType;
	
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
	public String getReportId(){
		return this.reportId;
	}
		
	public void setReportId (String reportId){
		this.reportId=reportId;
	}
	public String getShareType(){
		return this.shareType;
	}
		
	public void setShareType (String shareType){
		this.shareType=shareType;
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