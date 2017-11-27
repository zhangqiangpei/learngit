package com.yirong.iis.tp.common.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yirong.awaken.core.entity.IdEntity;

 /**
 * 功能描述：公司股票信息表entity类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-24 19:07:02
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@Entity
@Table(name="LT_TRKD_COMPANY_ISSUED")
public class LtTrkdCompanyIssued extends IdEntity{
	
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
	* 类型
	*/
	@Column(name="TYPE",nullable=true,length=10)
	public String type;
	
	/**
	* 面值货币
	*/
	@Column(name="PAR_CURRENCY",nullable=true,length=20)
	public String parCurrency;
	
	/**
	* 面值
	*/
	@Column(name="PAR_VALUE",nullable=true,length=22)
	public Integer parValue;
	
	/**
	* 股票授权
	*/
	@Column(name="SHS_AUTHORIZED",nullable=true,length=22)
	public Integer shsAuthorized;
	
	/**
	* 股票授权日期
	*/
	@Column(name="SHS_AUTHORIZED_DATE",nullable=true,length=20)
	public String shsAuthorizedDate;
	
	/**
	* 分享出去
	*/
	@Column(name="SHS_OUT",nullable=true,length=22)
	public Integer shsOut;
	
	/**
	* 分享出去日期
	*/
	@Column(name="SHS_OUT_DATE",nullable=true,length=20)
	public String shsOutDate;
	
	/**
	* 浮动估计
	*/
	@Column(name="ISSUE_FLOAT",nullable=true,length=22)
	public Integer issueFloat;
	
	/**
	* 日期
	*/
	@Column(name="FLOAT_DATE",nullable=true,length=20)
	public String floatDate;
	
	/**
	* 已发行股份
	*/
	@Column(name="SHS_ISSUED",nullable=true,length=22)
	public Integer shsIssued;
	
	/**
	* 日期
	*/
	@Column(name="SHS_ISSUED_DATE",nullable=true,length=20)
	public String shsIssuedDate;
	
	/**
	* 股票Treas
	*/
	@Column(name="SHS_TREAS",nullable=true,length=22)
	public Integer shsTreas;
	
	/**
	* 日期
	*/
	@Column(name="SHS_TREAS_DATE",nullable=true,length=20)
	public String shsTreasDate;
	
	/**
	* 投票
	*/
	@Column(name="VOTES",nullable=true,length=22)
	public Integer votes;
	
	/**
	* 转换因子
	*/
	@Column(name="CONVERSION_FACTOR",nullable=true,length=22)
	public Integer conversionFactor;
	
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
	public String getType(){
		return this.type;
	}
		
	public void setType (String type){
		this.type=type;
	}
	public String getParCurrency(){
		return this.parCurrency;
	}
		
	public void setParCurrency (String parCurrency){
		this.parCurrency=parCurrency;
	}
	public Integer getParValue(){
		return this.parValue;
	}
		
	public void setParValue (Integer parValue){
		this.parValue=parValue;
	}
	public Integer getShsAuthorized(){
		return this.shsAuthorized;
	}
		
	public void setShsAuthorized (Integer shsAuthorized){
		this.shsAuthorized=shsAuthorized;
	}
	public String getShsAuthorizedDate(){
		return this.shsAuthorizedDate;
	}
		
	public void setShsAuthorizedDate (String shsAuthorizedDate){
		this.shsAuthorizedDate=shsAuthorizedDate;
	}
	public Integer getShsOut(){
		return this.shsOut;
	}
		
	public void setShsOut (Integer shsOut){
		this.shsOut=shsOut;
	}
	public String getShsOutDate(){
		return this.shsOutDate;
	}
		
	public void setShsOutDate (String shsOutDate){
		this.shsOutDate=shsOutDate;
	}
	public Integer getIssueFloat(){
		return this.issueFloat;
	}
		
	public void setIssueFloat (Integer issueFloat){
		this.issueFloat=issueFloat;
	}
	public String getFloatDate(){
		return this.floatDate;
	}
		
	public void setFloatDate (String floatDate){
		this.floatDate=floatDate;
	}
	public Integer getShsIssued(){
		return this.shsIssued;
	}
		
	public void setShsIssued (Integer shsIssued){
		this.shsIssued=shsIssued;
	}
	public String getShsIssuedDate(){
		return this.shsIssuedDate;
	}
		
	public void setShsIssuedDate (String shsIssuedDate){
		this.shsIssuedDate=shsIssuedDate;
	}
	public Integer getShsTreas(){
		return this.shsTreas;
	}
		
	public void setShsTreas (Integer shsTreas){
		this.shsTreas=shsTreas;
	}
	public String getShsTreasDate(){
		return this.shsTreasDate;
	}
		
	public void setShsTreasDate (String shsTreasDate){
		this.shsTreasDate=shsTreasDate;
	}
	public Integer getVotes(){
		return this.votes;
	}
		
	public void setVotes (Integer votes){
		this.votes=votes;
	}
	public Integer getConversionFactor(){
		return this.conversionFactor;
	}
		
	public void setConversionFactor (Integer conversionFactor){
		this.conversionFactor=conversionFactor;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
		
	public void setCreateTime (Date createTime){
		this.createTime=createTime;
	}

   
}