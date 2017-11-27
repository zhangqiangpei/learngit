package com.yirong.iis.tp.common.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yirong.awaken.core.entity.IdEntity;

 /**
 * 功能描述：公司比率表entity类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-24 19:43:48
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@Entity
@Table(name="LT_TRKD_COMPANY_RATIOS")
public class LtTrkdCompanyRatios extends IdEntity{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	* 价格货币
	*/
	@Column(name="PRICE_CURRENCY",nullable=true,length=20)
	public String priceCurrency;
	
	/**
	* 报告货币
	*/
	@Column(name="REPORTING_CURRENCY",nullable=true,length=30)
	public String reportingCurrency;
	
	/**
	* 汇率
	*/
	@Column(name="EXCHANGE_RATE",nullable=true,length=22)
	public Integer exchangeRate;
	
	/**
	* 
最近的可用日期
	*/
	@Column(name="LATEST_AVAILABLE_DATE",nullable=true,length=20)
	public String latestAvailableDate;
	
	/**
	* 分组名
	*/
	@Column(name="GROUP_ID",nullable=true,length=100)
	public String groupId;
	
	/**
	* 字段名称
	*/
	@Column(name="FIELD_NAME",nullable=true,length=30)
	public String fieldName;
	
	/**
	* 字段类型
	*/
	@Column(name="TYPE",nullable=true,length=10)
	public String type;
	
	/**
	* 值
	*/
	@Column(name="VALUE",nullable=true,length=20)
	public String value;
	
	/**
	* 创建时间
	*/
	@Column(name="CREATE_TIME",nullable=true)
	public Date createTime;
	
	/**
	* 公司id
	*/
	@Column(name="COMPANY_ID",nullable=true,length=32)
	public String companyId;
	
   
	public String getId(){
		return this.id;
	}
		
	public void setId (String id){
		this.id=id;
	}
	public String getPriceCurrency(){
		return this.priceCurrency;
	}
		
	public void setPriceCurrency (String priceCurrency){
		this.priceCurrency=priceCurrency;
	}
	public String getReportingCurrency(){
		return this.reportingCurrency;
	}
		
	public void setReportingCurrency (String reportingCurrency){
		this.reportingCurrency=reportingCurrency;
	}
	public Integer getExchangeRate(){
		return this.exchangeRate;
	}
		
	public void setExchangeRate (Integer exchangeRate){
		this.exchangeRate=exchangeRate;
	}
	public String getLatestAvailableDate(){
		return this.latestAvailableDate;
	}
		
	public void setLatestAvailableDate (String latestAvailableDate){
		this.latestAvailableDate=latestAvailableDate;
	}
	public String getGroupId(){
		return this.groupId;
	}
		
	public void setGroupId (String groupId){
		this.groupId=groupId;
	}
	public String getFieldName(){
		return this.fieldName;
	}
		
	public void setFieldName (String fieldName){
		this.fieldName=fieldName;
	}
	public String getType(){
		return this.type;
	}
		
	public void setType (String type){
		this.type=type;
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