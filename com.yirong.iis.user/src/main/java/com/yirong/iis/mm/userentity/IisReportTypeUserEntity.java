package com.yirong.iis.mm.userentity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.yirong.commons.util.entity.PageEntiry;

 /**
 * 功能描述：报告分类表查询条件类
 * 
 * @author 林明铁
 *         <p>
 *         创建时间 ：2017-11-09 10:00:09
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public class IisReportTypeUserEntity extends PageEntiry implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	* 报告分类ID
	*/
	private String id;
	
	
	/**
	* 是否外部报告(1是、0否)
	*/
	private BigDecimal isOutside;
	
	
	/**
	* 分类名称
	*/
	private String typeName;
	
	
	/**
	* 文档总数
	*/
	private BigDecimal docsNum;
	
	
	/**
	* 是否系统(1是、0否)
	*/
	private BigDecimal isSystem;
	
	
	/**
	* 创建人
	*/
	private String creator;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public BigDecimal getIsOutside() {
		return isOutside;
	}


	public void setIsOutside(BigDecimal isOutside) {
		this.isOutside = isOutside;
	}


	public String getTypeName() {
		return typeName;
	}


	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	public BigDecimal getDocsNum() {
		return docsNum;
	}


	public void setDocsNum(BigDecimal docsNum) {
		this.docsNum = docsNum;
	}


	public BigDecimal getIsSystem() {
		return isSystem;
	}


	public void setIsSystem(BigDecimal isSystem) {
		this.isSystem = isSystem;
	}


	public String getCreator() {
		return creator;
	}


	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	

}
