package com.yirong.iis.mm.userentity;

import java.io.Serializable;
import com.yirong.commons.util.entity.PageEntiry;

 /**
 * 功能描述：报告共享表查询条件类
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
public class IisReportShareUserEntity extends PageEntiry implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	* 报告共享ID
	*/
	private String id;
	
	
	/**
	* 所属报告ID
	*/
	private String reportId;
	
	
	/**
	* 共享类型(代码表:012)
	*/
	private String shareType;
	
	
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


	public String getReportId() {
		return reportId;
	}


	public void setReportId(String reportId) {
		this.reportId = reportId;
	}


	public String getShareType() {
		return shareType;
	}


	public void setShareType(String shareType) {
		this.shareType = shareType;
	}


	public String getCreator() {
		return creator;
	}


	public void setCreator(String creator) {
		this.creator = creator;
	}
	

}
