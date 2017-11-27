package com.yirong.iis.tp.common.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yirong.awaken.core.entity.IdEntity;

 /**
 * 功能描述：公司预测数据表entity类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-24 18:42:58
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@Entity
@Table(name="LT_TRKD_COMPANY_FORECAST_DATA")
public class LtTrkdCompanyForecastData  extends IdEntity{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 共识类型
	 */
	@Column(name="CONSENSUS_TYPE",nullable=true,length=20)
	public String consensusType;
	
	/**
	 * 会计年度
	 */
	@Column(name="CUR_FISCAL_YEAR",nullable=true,length=10)
	public String curFiscalYear;
	
	/**
	 * 会计年度结束月份
	 */
	@Column(name="CUR_FISCAL_YEAR_END_MONTH",nullable=true,length=10)
	public String curFiscalYearEndMonth;
	
	@Column(name="CUR_INTERIM_END_CAL_YEAR",nullable=true,length=10)
	public String curInterimEndCalYear;
	
	@Column(name="CUR_INTERIM_END_MONTH",nullable=true,length=10)
	public String curInterimEndMonth;
	
	@Column(name="EARNINGS_BASIS",nullable=true,length=20)
	public String earningsBasis;
	
	/**
	 * 字段名称
	 */
	@Column(name="FIELD_NAME",nullable=true,length=100)
	public String fieldName;
	
	/**
	 * 类型
	 */
	@Column(name="TYPE",nullable=true,length=10)
	public String type;
	
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
	 * 公司id
	 */
	@Column(name="COMPANY_ID",nullable=true,length=32)
	public String companyId;
	
	/**
	* 创建时间
	*/
	@Column(name="CREATE_TIME",nullable=false)
	public Date createTime;

	public String getConsensusType() {
		return consensusType;
	}

	public void setConsensusType(String consensusType) {
		this.consensusType = consensusType;
	}

	public String getCurFiscalYear() {
		return curFiscalYear;
	}

	public void setCurFiscalYear(String curFiscalYear) {
		this.curFiscalYear = curFiscalYear;
	}

	public String getCurFiscalYearEndMonth() {
		return curFiscalYearEndMonth;
	}

	public void setCurFiscalYearEndMonth(String curFiscalYearEndMonth) {
		this.curFiscalYearEndMonth = curFiscalYearEndMonth;
	}

	public String getCurInterimEndCalYear() {
		return curInterimEndCalYear;
	}

	public void setCurInterimEndCalYear(String curInterimEndCalYear) {
		this.curInterimEndCalYear = curInterimEndCalYear;
	}

	public String getCurInterimEndMonth() {
		return curInterimEndMonth;
	}

	public void setCurInterimEndMonth(String curInterimEndMonth) {
		this.curInterimEndMonth = curInterimEndMonth;
	}

	public String getEarningsBasis() {
		return earningsBasis;
	}

	public void setEarningsBasis(String earningsBasis) {
		this.earningsBasis = earningsBasis;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPeriodType() {
		return periodType;
	}

	public void setPeriodType(String periodType) {
		this.periodType = periodType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
