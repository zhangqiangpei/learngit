package com.yirong.iis.user.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yirong.awaken.core.entity.IdEntity;

 
/**
 * 
 * @ClassName: IisFinancialOverview  
 * @Description: TODO(企业财务总览entity类) 
 * @author liny
 * @date 2017年11月22日 下午5:23:29 
 * @version V0.1
 */
@Entity
@Table(name="IIS_FINANCIAL_OVERVIEW")
public class IisFinancialOverview extends IdEntity{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

 
	
	/**
	* 企业ID
	*/
	@Column(name="COMPANY_ID",nullable=false,length=32)
	public String companyId;
	
	/**
	* 总收入
	*/
	@Column(name="GROSS_INCOME",nullable=true,length=22)
	public Double grossIncome;
	
	/**
	* 净收益
	*/
	@Column(name="NET_PROFIT",nullable=true,length=22)
	public Double netProfit;
	
	/**
	* 总资产
	*/
	@Column(name="TOTAL_ASSETS",nullable=true,length=22)
	public Double totalAssets;
	
	/**
	* 总负债
	*/
	@Column(name="TOTAL_LIABILITIES",nullable=true,length=22)
	public Double totalLiabilities;
	
	/**
	* 资本性支出
	*/
	@Column(name="CAPITAL_EXPENDITURE",nullable=true,length=22)
	public Double capitalExpenditure;
	
	/**
	* 市值
	*/
	@Column(name="MARKET_VALUE",nullable=true,length=22)
	public Double marketValue;
	
	/**
	* 股价
	*/
	@Column(name="PRICE_OF_STOCK",nullable=true,length=22)
	public Double priceOfStock;
	
	/**
	* 收益
	*/
	@Column(name="PROFIT",nullable=true,length=22)
	public Double profit;
	
	/**
	* EPS
	*/
	@Column(name="EPS",nullable=true,length=22)
	public Double eps;
	
	/**
	* 分红
	*/
	@Column(name="A_BONUS",nullable=true,length=22)
	public Double aBonus;
	
	/**
	* 评级
	*/
	@Column(name="GRADE",nullable=true,length=5)
	public String grade;
	
	/**
	* PB
	*/
	@Column(name="PB",nullable=true,length=22)
	public Double pb;
	
	/**
	* PE
	*/
	@Column(name="PE",nullable=true,length=22)
	public Double pe;
	
	/**
	* 频度(代码表)
	*/
	@Column(name="FREQUENCY",nullable=false,length=6)
	public String frequency;
	
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
	public String getCompanyId(){
		return this.companyId;
	}
		
	public void setCompanyId (String companyId){
		this.companyId=companyId;
	}
	public Double getGrossIncome(){
		return this.grossIncome;
	}
		
	public void setGrossIncome (Double grossIncome){
		this.grossIncome=grossIncome;
	}
	public Double getNetProfit(){
		return this.netProfit;
	}
		
	public void setNetProfit (Double netProfit){
		this.netProfit=netProfit;
	}
	public Double getTotalAssets(){
		return this.totalAssets;
	}
		
	public void setTotalAssets (Double totalAssets){
		this.totalAssets=totalAssets;
	}
	public Double getTotalLiabilities(){
		return this.totalLiabilities;
	}
		
	public void setTotalLiabilities (Double totalLiabilities){
		this.totalLiabilities=totalLiabilities;
	}
	public Double getCapitalExpenditure(){
		return this.capitalExpenditure;
	}
		
	public void setCapitalExpenditure (Double capitalExpenditure){
		this.capitalExpenditure=capitalExpenditure;
	}
	public Double getMarketValue(){
		return this.marketValue;
	}
		
	public void setMarketValue (Double marketValue){
		this.marketValue=marketValue;
	}
	public Double getPriceOfStock(){
		return this.priceOfStock;
	}
		
	public void setPriceOfStock (Double priceOfStock){
		this.priceOfStock=priceOfStock;
	}
	public Double getProfit(){
		return this.profit;
	}
		
	public void setProfit (Double profit){
		this.profit=profit;
	}
	public Double getEps(){
		return this.eps;
	}
		
	public void setEps (Double eps){
		this.eps=eps;
	}
	public Double getABonus(){
		return this.aBonus;
	}
		
	public void setABonus (Double aBonus){
		this.aBonus=aBonus;
	}
	public String getGrade(){
		return this.grade;
	}
		
	public void setGrade (String grade){
		this.grade=grade;
	}
	public Double getPb(){
		return this.pb;
	}
		
	public void setPb (Double pb){
		this.pb=pb;
	}
	public Double getPe(){
		return this.pe;
	}
		
	public void setPe (Double pe){
		this.pe=pe;
	}
	public String getFrequency(){
		return this.frequency;
	}
		
	public void setFrequency (String frequency){
		this.frequency=frequency;
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