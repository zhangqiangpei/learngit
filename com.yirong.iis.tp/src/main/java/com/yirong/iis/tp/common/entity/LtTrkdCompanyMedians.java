package com.yirong.iis.tp.common.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yirong.awaken.core.entity.IdEntity;

 /**
 * 功能描述：公司中值表entity类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-24 19:10:49
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@Entity
@Table(name="LT_TRKD_COMPANY_MEDIANS")
public class LtTrkdCompanyMedians extends IdEntity{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	* 公司id
	*/
	@Column(name="COMPANY_ID",nullable=false,length=32)
	public String companyId;
	
	/**
	* 中值名称
	*/
	@Column(name="NAME",nullable=false,length=100)
	public String name;
	
	/**
	* 中值周期
	*/
	@Column(name="PERIOD",nullable=true,length=10)
	public String period;
	
	/**
	* 值
	*/
	@Column(name="VALUE",nullable=true,length=30)
	public String value;
	
	/**
	* 值类型
	*/
	@Column(name="TYPE",nullable=true,length=20)
	public String type;
	
	/**
	* 创建时间
	*/
	@Column(name="CREATE_TIME",nullable=true)
	public Date createTime;
	
   
	public String getId(){
		return this.id;
	}
		
	public void setId (String id){
		this.id=id;
	}
	public String getCompanyId(){
		return this.companyId;
	}
		
	public void setCompanyId (String companyId){
		this.companyId=companyId;
	}
	public String getName(){
		return this.name;
	}
		
	public void setName (String name){
		this.name=name;
	}
	public String getPeriod(){
		return this.period;
	}
		
	public void setPeriod (String period){
		this.period=period;
	}
	public String getValue(){
		return this.value;
	}
		
	public void setValue (String value){
		this.value=value;
	}
	public String getType(){
		return this.type;
	}
		
	public void setType (String type){
		this.type=type;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
		
	public void setCreateTime (Date createTime){
		this.createTime=createTime;
	}

   
}