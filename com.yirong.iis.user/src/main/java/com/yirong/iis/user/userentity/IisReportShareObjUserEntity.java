package com.yirong.iis.user.userentity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.yirong.commons.util.entity.PageEntiry;

 /**
 * 功能描述：报告共享对象表查询条件类
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
public class IisReportShareObjUserEntity extends PageEntiry implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	* 报告共享对象ID
	*/
	private String id;
	
	
	/**
	* 所属报告共享ID
	*/
	private String reportShareId;
	
	
	/**
	* 所属报告ID
	*/
	private String reportId;
	
	
	/**
	* 对象ID
	*/
	private String objId;
	
	
	/**
	* 是否可下载(1是、0否)
	*/
	private BigDecimal isDownload;
	
	
	/**
	* 是否可在线浏览(1是、0否)
	*/
	private BigDecimal isOnlineBrowse;
	
	
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


	public String getReportShareId() {
		return reportShareId;
	}


	public void setReportShareId(String reportShareId) {
		this.reportShareId = reportShareId;
	}


	public String getReportId() {
		return reportId;
	}


	public void setReportId(String reportId) {
		this.reportId = reportId;
	}


	public String getObjId() {
		return objId;
	}


	public void setObjId(String objId) {
		this.objId = objId;
	}


	public BigDecimal getIsDownload() {
		return isDownload;
	}


	public void setIsDownload(BigDecimal isDownload) {
		this.isDownload = isDownload;
	}


	public BigDecimal getIsOnlineBrowse() {
		return isOnlineBrowse;
	}


	public void setIsOnlineBrowse(BigDecimal isOnlineBrowse) {
		this.isOnlineBrowse = isOnlineBrowse;
	}


	public String getCreator() {
		return creator;
	}


	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	

}
