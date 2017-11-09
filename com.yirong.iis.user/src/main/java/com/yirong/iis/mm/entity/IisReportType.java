package com.yirong.iis.mm.entity;



import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

 /**
 * 功能描述：报告分类表entity类
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
@Table(name="IIS_REPORT_TYPE")
public class IisReportType implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	* 报告分类ID
	*/
	@Column(name="ID",nullable=false,length=32)
	public String id;
	
	/**
	* 是否外部报告(1是、0否)
	*/
	@Column(name="IS_OUTSIDE",nullable=false,length=22)
	public BigDecimal isOutside;
	
	/**
	* 分类名称
	*/
	@Column(name="TYPE_NAME",nullable=false,length=100)
	public String typeName;
	
	/**
	* 文档总数
	*/
	@Column(name="DOCS_NUM",nullable=false,length=22)
	public BigDecimal docsNum;
	
	/**
	* 是否系统(1是、0否)
	*/
	@Column(name="IS_SYSTEM",nullable=false,length=22)
	public BigDecimal isSystem;
	
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
	public BigDecimal getIsOutside(){
		return this.isOutside;
	}
		
	public void setIsOutside (BigDecimal isOutside){
		this.isOutside=isOutside;
	}
	public String getTypeName(){
		return this.typeName;
	}
		
	public void setTypeName (String typeName){
		this.typeName=typeName;
	}
	public BigDecimal getDocsNum(){
		return this.docsNum;
	}
		
	public void setDocsNum (BigDecimal docsNum){
		this.docsNum=docsNum;
	}
	public BigDecimal getIsSystem(){
		return this.isSystem;
	}
		
	public void setIsSystem (BigDecimal isSystem){
		this.isSystem=isSystem;
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