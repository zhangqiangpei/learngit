package com.yirong.iis.tp.common.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yirong.awaken.core.entity.IdEntity;

 /**
 * 功能描述：公司财务报表entity类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-24 18:41:19
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@Entity
@Table(name="LT_TRKD_COMPANY_FINANCE_REPORT")
public class LtTrkdCompanyFinanceReport extends IdEntity{

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
	* coa名全称
	*/
	@Column(name="NAME",nullable=true,length=100)
	public String name;
	
	/**
	* coa编码
	*/
	@Column(name="COA_CODE",nullable=true,length=50)
	public String coaCode;
	
	/**
	* 类型（INC=损益表 ，BAL=资产负债表，CAS=现金流表）
	*/
	@Column(name="COA_TYPE",nullable=true,length=10)
	public String coaType;
	
	/**
	* Annual全年，Interim季度
	*/
	@Column(name="TYPE",nullable=true,length=10)
	public String type;
	
	/**
	* 结束时间
	*/
	@Column(name="END_DATE",nullable=true,length=20)
	public String endDate;
	
	/**
	* 财政年度
	*/
	@Column(name="FISCAL_YEAR",nullable=true,length=10)
	public String fiscalYear;
	
	/**
	* 1,2,3,4财政期间号码
	*/
	@Column(name="FISCAL_PERIOD_NUMBER",nullable=true,length=22)
	public Integer fiscalPeriodNumber;
	
	/**
	* 周期长度
	*/
	@Column(name="PERIOD_LENGTH",nullable=true,length=22)
	public Integer periodLength;
	
	/**
	* 周期类型
	*/
	@Column(name="PERIOD_TYPE",nullable=true,length=10)
	public String periodType;
	
	/**
	* 更新类型
	*/
	@Column(name="UPDATE_TYPE",nullable=true,length=30)
	public String updateType;
	
	/**
	* 报表日期
	*/
	@Column(name="STATEMENT_DATE",nullable=true,length=20)
	public String statementDate;
	
	/**
	* 审计单位
	*/
	@Column(name="AUDITOR_NAME",nullable=true,length=50)
	public String auditorName;
	
	/**
	* 审计意见
	*/
	@Column(name="AUDITOR_OPINION",nullable=true,length=50)
	public String auditorOpinion;
	
	/**
	* 来源
	*/
	@Column(name="SOURCE",nullable=true,length=50)
	public String source;
	
	/**
	* coa值
	*/
	@Column(name="COA_VALUE",nullable=true,length=30)
	public String coaValue;
	
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
	public String getCoaCode(){
		return this.coaCode;
	}
		
	public void setCoaCode (String coaCode){
		this.coaCode=coaCode;
	}
	public String getCoaType(){
		return this.coaType;
	}
		
	public void setCoaType (String coaType){
		this.coaType=coaType;
	}
	public String getType(){
		return this.type;
	}
		
	public void setType (String type){
		this.type=type;
	}
	public String getEndDate(){
		return this.endDate;
	}
		
	public void setEndDate (String endDate){
		this.endDate=endDate;
	}
	public String getFiscalYear(){
		return this.fiscalYear;
	}
		
	public void setFiscalYear (String fiscalYear){
		this.fiscalYear=fiscalYear;
	}
	public Integer getFiscalPeriodNumber(){
		return this.fiscalPeriodNumber;
	}
		
	public void setFiscalPeriodNumber (Integer fiscalPeriodNumber){
		this.fiscalPeriodNumber=fiscalPeriodNumber;
	}
	public Integer getPeriodLength(){
		return this.periodLength;
	}
		
	public void setPeriodLength (Integer periodLength){
		this.periodLength=periodLength;
	}
	public String getPeriodType(){
		return this.periodType;
	}
		
	public void setPeriodType (String periodType){
		this.periodType=periodType;
	}
	public String getUpdateType(){
		return this.updateType;
	}
		
	public void setUpdateType (String updateType){
		this.updateType=updateType;
	}
	public String getStatementDate(){
		return this.statementDate;
	}
		
	public void setStatementDate (String statementDate){
		this.statementDate=statementDate;
	}
	public String getAuditorName(){
		return this.auditorName;
	}
		
	public void setAuditorName (String auditorName){
		this.auditorName=auditorName;
	}
	public String getAuditorOpinion(){
		return this.auditorOpinion;
	}
		
	public void setAuditorOpinion (String auditorOpinion){
		this.auditorOpinion=auditorOpinion;
	}
	public String getSource(){
		return this.source;
	}
		
	public void setSource (String source){
		this.source=source;
	}
	public String getCoaValue(){
		return this.coaValue;
	}
		
	public void setCoaValue (String coaValue){
		this.coaValue=coaValue;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
		
	public void setCreateTime (Date createTime){
		this.createTime=createTime;
	}

   
}