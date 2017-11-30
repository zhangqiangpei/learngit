package com.yirong.iis.user.userentity;


import java.io.Serializable;

import com.yirong.commons.util.entity.PageEntiry;

 
/**
 * 
 * @ClassName: VeIisCompanyOfficerUserEntity  
 * @Description: TODO(企业高管视图查询条件类)
 * @author liny
 * @date 2017年11月28日 下午8:02:01 
 * @version V0.1
 */
public class VeIisCompanyOfficerUserEntity extends PageEntiry implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	* 编码
	*/
	private String id;
	
	/**
	 * 
	 */
	public String companyId;

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
	
}