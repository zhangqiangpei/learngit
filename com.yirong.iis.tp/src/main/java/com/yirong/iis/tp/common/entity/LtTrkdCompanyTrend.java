package com.yirong.iis.tp.common.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yirong.awaken.core.entity.IdEntity;

 /**
 * 功能描述：公司推荐趋势表entity类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-24 18:33:19
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@Entity
@Table(name="LT_TRKD_COMPANY_TREND")
public class LtTrkdCompanyTrend extends IdEntity{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	* 选项
	*/
	@Column(name="OPINION",nullable=true,length=20)
	public String opinion;
	
	/**
	* 描述
	*/
	@Column(name="DESC",nullable=true,length=30)
	public String desc;
	
	/**
	* 编码
	*/
	@Column(name="CODE",nullable=true,length=10)
	public String code;
	
	/**
	* 期间类型
	*/
	@Column(name="PERIOD_TYPE",nullable=true,length=20)
	public String periodType;
	
	/**
	* 值
	*/
	@Column(name="VALUE",nullable=true,length=20)
	public String value;
	
	/**
	* 创建时间
	*/
	@Column(name="CREATE_TIME",nullable=false)
	public Date createTime;
	
	/**
	* 公司id
	*/
	@Column(name="COMPANY_ID",nullable=false,length=32)
	public String companyId;
	
   
	public String getId(){
		return this.id;
	}
		
	public void setId (String id){
		this.id=id;
	}
	public String getOpinion(){
		return this.opinion;
	}
		
	public void setOpinion (String opinion){
		this.opinion=opinion;
	}
	public String getDesc(){
		return this.desc;
	}
		
	public void setDesc (String desc){
		this.desc=desc;
	}
	public String getCode(){
		return this.code;
	}
		
	public void setCode (String code){
		this.code=code;
	}
	public String getPeriodType(){
		return this.periodType;
	}
		
	public void setPeriodType (String periodType){
		this.periodType=periodType;
	}
	public String getValue(){
		return this.value;
	}
		
	public void setValue (String value){
		this.value=value;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
		
	public void setCreateTime (Date createTime){
		this.createTime=createTime;
	}
	public String getCompanyId(){
		return this.companyId;
	}
		
	public void setCompanyId (String companyId){
		this.companyId=companyId;
	}

   
}