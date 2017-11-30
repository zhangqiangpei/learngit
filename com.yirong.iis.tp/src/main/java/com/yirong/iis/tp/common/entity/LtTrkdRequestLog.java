package com.yirong.iis.tp.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yirong.awaken.core.entity.IdEntity;

/**
 * trkd 请求日志表
 * @author lijp
 *
 */
@Entity
@Table(name="LT_TRKD_REQUEST_LOG")
public class LtTrkdRequestLog extends IdEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 请求接口名称
	 */
	@Column(name="NAME",nullable=false,length=100)
	public String name;
	
	/**
	 * 请求参数
	 */
	@Column(name="PARAM",nullable=true)
	public String param;
	
	/**
	 * 请求结果
	 */
	@Column(name="RESULT",nullable=true)
	public String result;
	
	/**
	 * 创建时间
	 */
	@Column(name="CREATE_TIME",nullable=false)
	public Date createTime;
	
	/**
	 *状态，1接口请求成功，0请求失败，2请求成功保存失败
	 */
	@Column(name="STATUS",nullable=true)
	private Integer status;
	
	/**
	* 公司唯一标识
	*/
	@Column(name="COMPANY_ID",nullable=false,length=20)
	public String companyId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
}
