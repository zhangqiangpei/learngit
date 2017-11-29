package com.yirong.iis.user.entity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.yirong.awaken.core.entity.IdEntity;

 /**
 * 功能描述：国家负债表entity类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-29 09:44:05
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@Entity
@Table(name="IIS_COUNTRY_DEBT")
public class IisCountryDebt extends IdEntity{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	* 国家英文名
	*/
	@Column(name="ENGLISH_NAME",nullable=false,length=100)
	public String englishName;
	
	/**
	* 债务占GDP比率
	*/
	@Column(name="DEBT_GDP_PER",nullable=true,length=22)
	public BigDecimal debtGdpPer;
	
	/**
	* 可交易未偿还债务总额
	*/
	@Column(name="TRAN_REPAY_DEBT",nullable=true,length=22)
	public BigDecimal tranRepayDebt;
	
	/**
	* orld-Check 国家风险
	*/
	@Column(name="ORLD_CHECK",nullable=true,length=100)
	public String orldCheck;
	
	/**
	* GDP全球占比%
	*/
	@Column(name="GDP_WORLD_PER",nullable=true,length=22)
	public BigDecimal gdpWorldPer;
	
	/**
	* 相对购买力平价比率%
	*/
	@Column(name="BID_SPREAD_PER",nullable=true,length=22)
	public BigDecimal bidSpreadPer;
	
	/**
	* 外汇储备占进口%
	*/
	@Column(name="BOND_EQUIV_ASK_YLD_PER",nullable=true,length=22)
	public BigDecimal bondEquivAskYldPer;
	
	/**
	* 通胀率和汇率变动%
	*/
	@Column(name="BOND_EQUIV_MID_YLD_PER",nullable=true,length=22)
	public BigDecimal bondEquivMidYldPer;
	
	/**
	* 政府部门消费/私人消费%
	*/
	@Column(name="SWAP_SPREAD_PER",nullable=true,length=22)
	public BigDecimal swapSpreadPer;
	
	/**
	* 金融机构信贷总额占GDP%
	*/
	@Column(name="SEC_YLD_PER",nullable=true,length=22)
	public BigDecimal secYldPer;
	
	/**
	* 市场溢价比率-1年违约概率
	*/
	@Column(name="GEN_VAL3",nullable=true,length=100)
	public String genVal3;
	
	/**
	* 市场溢价比率-CDS中间价
	*/
	@Column(name="MID_SPREAD",nullable=true,length=100)
	public String midSpread;
	
	/**
	* 市场溢价比率-MPR
	*/
	@Column(name="MPR",nullable=true,length=100)
	public String mpr;
	
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
	
   
	public String getEnglishName(){
		return this.englishName;
	}
		
	public void setEnglishName (String englishName){
		this.englishName=englishName;
	}
	public BigDecimal getDebtGdpPer(){
		return this.debtGdpPer;
	}
		
	public void setDebtGdpPer (BigDecimal debtGdpPer){
		this.debtGdpPer=debtGdpPer;
	}
	public BigDecimal getTranRepayDebt(){
		return this.tranRepayDebt;
	}
		
	public void setTranRepayDebt (BigDecimal tranRepayDebt){
		this.tranRepayDebt=tranRepayDebt;
	}
	public String getOrldCheck(){
		return this.orldCheck;
	}
		
	public void setOrldCheck (String orldCheck){
		this.orldCheck=orldCheck;
	}
	public BigDecimal getGdpWorldPer(){
		return this.gdpWorldPer;
	}
		
	public void setGdpWorldPer (BigDecimal gdpWorldPer){
		this.gdpWorldPer=gdpWorldPer;
	}
	public BigDecimal getBidSpreadPer(){
		return this.bidSpreadPer;
	}
		
	public void setBidSpreadPer (BigDecimal bidSpreadPer){
		this.bidSpreadPer=bidSpreadPer;
	}
	public BigDecimal getBondEquivAskYldPer(){
		return this.bondEquivAskYldPer;
	}
		
	public void setBondEquivAskYldPer (BigDecimal bondEquivAskYldPer){
		this.bondEquivAskYldPer=bondEquivAskYldPer;
	}
	public BigDecimal getBondEquivMidYldPer(){
		return this.bondEquivMidYldPer;
	}
		
	public void setBondEquivMidYldPer (BigDecimal bondEquivMidYldPer){
		this.bondEquivMidYldPer=bondEquivMidYldPer;
	}
	public BigDecimal getSecYldPer(){
		return this.secYldPer;
	}
		
	public void setSecYldPer (BigDecimal secYldPer){
		this.secYldPer=secYldPer;
	}
	public String getGenVal3(){
		return this.genVal3;
	}
		
	public void setGenVal3 (String genVal3){
		this.genVal3=genVal3;
	}
	public String getMidSpread(){
		return this.midSpread;
	}
		
	public void setMidSpread (String midSpread){
		this.midSpread=midSpread;
	}
	public String getMpr(){
		return this.mpr;
	}
		
	public void setMpr (String mpr){
		this.mpr=mpr;
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

	public BigDecimal getSwapSpreadPer() {
		return swapSpreadPer;
	}

	public void setSwapSpreadPer(BigDecimal swapSpreadPer) {
		this.swapSpreadPer = swapSpreadPer;
	}

   
}