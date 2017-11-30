package com.yirong.iis.user.userentity;


import java.io.Serializable;

import com.yirong.commons.util.entity.PageEntiry;

 
/**
 * 
 * @ClassName: VeIisCompanyReportUserEntity  
 * @Description: TODO(企业财务报告视图查询条件类)
 * @author liny
 * @date 2017年11月28日 下午8:02:01 
 * @version V0.1
 */
public class VeIisCompanyReportUserEntity extends PageEntiry implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	* 编码
	*/
	private String id;
	
	/**
	 * 公司id
	 */
	public String companyId;
	
	
	/**
	* Annual全年，Interim季度
	*/
	public String type;
	
	/**
	* 财政年度
	*/
	public String fiscalYear;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}
	
	
}