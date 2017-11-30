package com.yirong.iis.user.userentity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.yirong.commons.util.entity.PageEntiry;

 /**
 * 功能描述：国家负债表查询条件类
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
public class IisCountryDebtUserEntity extends PageEntiry implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	* 国家负债ID
	*/
	private String id;
	
	
	/**
	* 国家英文名
	*/
	private String englishName;
	
	
	/**
	* 债务占GDP比率
	*/
	private BigDecimal debtGdpPer;
	
	
	/**
	* 可交易未偿还债务总额
	*/
	private BigDecimal tranRepayDebt;
	
	
	/**
	* orld-Check 国家风险
	*/
	private String orldCheck;
	
	
	/**
	* GDP全球占比%
	*/
	private BigDecimal gdpWorldPer;
	
	
	/**
	* 相对购买力平价比率%
	*/
	private BigDecimal bidSpreadPer;
	
	
	/**
	* 外汇储备占进口%
	*/
	private BigDecimal bondEquivAskYldPer;
	
	
	/**
	* 通胀率和汇率变动%
	*/
	private BigDecimal bondEquivMidYldPer;
	
	
	/**
	* 政府部门消费/私人消费%
	*/
	private BigDecimal swapSpreadPer;
	
	
	/**
	* 金融机构信贷总额占GDP%
	*/
	private BigDecimal secYldPer;
	
	
	/**
	* 市场溢价比率-1年违约概率
	*/
	private String genVal3;
	
	
	/**
	* 市场溢价比率-CDS中间价
	*/
	private String midSpread;
	
	
	/**
	* 市场溢价比率-MPR
	*/
	private String mpr;
	
	
	/**
	* 创建人
	*/
	private String creator;
	
	
	/**
	* 创建时间
	*/
	private Date createTime;
	
	
	/**
	* 修改人
	*/
	private String modifier;
	
	
	/**
	* 修改时间
	*/
	private Date modifyTime;
	
   
	public String getId(){
		return this.id;
	}
		
	public void setId (String id){
		this.id=id;
	}
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
	
	public BigDecimal getSwapSpreadPer() {
		return swapSpreadPer;
	}

	public void setSwapSpreadPer(BigDecimal swapSpreadPer) {
		this.swapSpreadPer = swapSpreadPer;
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
   
}