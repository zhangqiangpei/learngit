package com.yirong.iis.user.entity;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

 /**
 * 功能描述：报告共享对象表entity类
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
@Table(name="IIS_REPORT_SHARE_OBJ")
public class IisReportShareObj implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	* 报告共享对象ID
	*/
	@Column(name="ID",nullable=false,length=32)
	public String id;
	
	/**
	* 所属报告共享ID
	*/
	@Column(name="REPORT_SHARE_ID",nullable=false,length=32)
	public String reportShareId;
	
	/**
	* 所属报告ID
	*/
	@Column(name="REPORT_ID",nullable=false,length=32)
	public String reportId;
	
	/**
	* 对象ID
	*/
	@Column(name="OBJ_ID",nullable=false,length=32)
	public String objId;
	
	/**
	* 是否可下载(1是、0否)
	*/
	@Column(name="IS_DOWNLOAD",nullable=false,length=22)
	public BigDecimal isDownload;
	
	/**
	* 是否可在线浏览(1是、0否)
	*/
	@Column(name="IS_ONLINE_BROWSE",nullable=false,length=22)
	public BigDecimal isOnlineBrowse;
	
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
	public String getReportShareId(){
		return this.reportShareId;
	}
		
	public void setReportShareId (String reportShareId){
		this.reportShareId=reportShareId;
	}
	public String getReportId(){
		return this.reportId;
	}
		
	public void setReportId (String reportId){
		this.reportId=reportId;
	}
	public String getObjId(){
		return this.objId;
	}
		
	public void setObjId (String objId){
		this.objId=objId;
	}
	public BigDecimal getIsDownload(){
		return this.isDownload;
	}
		
	public void setIsDownload (BigDecimal isDownload){
		this.isDownload=isDownload;
	}
	public BigDecimal getIsOnlineBrowse(){
		return this.isOnlineBrowse;
	}
		
	public void setIsOnlineBrowse (BigDecimal isOnlineBrowse){
		this.isOnlineBrowse=isOnlineBrowse;
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