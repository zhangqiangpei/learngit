package com.yirong.iis.user.userentity;

import java.io.Serializable;
import com.yirong.commons.util.entity.PageEntiry;

 /**
 * 功能描述：报告模版表查询条件类
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
public class IisReportTemplateUserEntity extends PageEntiry implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	* 报告模版ID
	*/
	private String id;
	
	
	/**
	* 模版名称
	*/
	private String templateName;
	
	
	/**
	* 模版内容
	*/
	private String templateInfo;

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


	public String getTemplateName() {
		return templateName;
	}


	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}


	public String getTemplateInfo() {
		return templateInfo;
	}


	public void setTemplateInfo(String templateInfo) {
		this.templateInfo = templateInfo;
	}

	 public String getCreator() {
		 return creator;
	 }

	 public void setCreator(String creator) {
		 this.creator = creator;
	 }
 }
