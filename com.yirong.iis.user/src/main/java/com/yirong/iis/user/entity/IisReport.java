package com.yirong.iis.user.entity;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

 /**
 * 功能描述：报告表entity类
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
@Table(name="IIS_REPORT")
public class IisReport implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	* 报告ID
	*/
	@Id
	@GeneratedValue(generator="system-uuid",strategy = GenerationType.AUTO)
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name="ID",nullable=false,length=32)
	public String id;
	
	/**
	* 所属报告分类ID
	*/
	@Column(name="TYPE_ID",nullable=false,length=32)
	public String typeId;
	
	/**
	* 报告名称
	*/
	@Column(name="REPORT_NAME",nullable=false,length=200)
	public String reportName;
	
	/**
	* 报告内容
	*/
	@Column(name="REPORT_INFO",nullable=false)
	public String reportInfo;
	
	/**
	* 知识ID
	*/
	@Column(name="KM_ID",nullable=true,length=32)
	public String kmId;
	
	/**
	* 文件ID
	*/
	@Column(name="EOS_ID",nullable=true,length=32)
	public String eosId;
	
	/**
	* 是否开放(1是、0否)
	*/
	@Column(name="IS_OPEN",nullable=false,length=22)
	public BigDecimal isOpen;
	
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
	public String getTypeId(){
		return this.typeId;
	}
		
	public void setTypeId (String typeId){
		this.typeId=typeId;
	}
	public String getReportName(){
		return this.reportName;
	}
		
	public void setReportName (String reportName){
		this.reportName=reportName;
	}
	public String getReportInfo(){
		return this.reportInfo;
	}
		
	public void setReportInfo (String reportInfo){
		this.reportInfo=reportInfo;
	}
	public String getKmId(){
		return this.kmId;
	}
		
	public void setKmId (String kmId){
		this.kmId=kmId;
	}
	public String getEosId(){
		return this.eosId;
	}
		
	public void setEosId (String eosId){
		this.eosId=eosId;
	}
	public BigDecimal getIsOpen(){
		return this.isOpen;
	}
		
	public void setIsOpen (BigDecimal isOpen){
		this.isOpen=isOpen;
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