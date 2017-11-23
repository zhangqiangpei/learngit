package com.yirong.iis.user.userentity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.yirong.commons.util.entity.PageEntiry;

 /**
 * 功能描述：报告表查询条件类
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
public class IisReportUserEntity extends PageEntiry implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

     /**
      * 关键字
      */
	private String keywords;

	 /**
	  * 外部报告
	  */
	private  String isOutside;
	
	/**
	* 报告ID
	*/
	private String id;
	
	
	/**
	* 所属报告分类ID
	*/
	private String typeId;
	
	
	/**
	* 报告名称
	*/
	private String reportName;
	
	
	/**
	* 报告内容
	*/
	private String reportInfo;
	
	
	/**
	* 知识ID
	*/
	private String kmId;
	
	
	/**
	* 文件ID
	*/
	private String eosId;

	/**
	* 创建人
	*/
	private String creator;

     public String getKeywords() {
         return keywords;
     }

     public void setKeywords(String keywords) {
         this.keywords = keywords;
     }

     public String getIsOutside() {
		 return isOutside;
	 }

	 public void setIsOutside(String isOutside) {
		 this.isOutside = isOutside;
	 }

	 public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getReportInfo() {
		return reportInfo;
	}

	public void setReportInfo(String reportInfo) {
		this.reportInfo = reportInfo;
	}

	public String getKmId() {
		return kmId;
	}

	public void setKmId(String kmId) {
		this.kmId = kmId;
	}

	public String getEosId() {
		return eosId;
	}

	public void setEosId(String eosId) {
		this.eosId = eosId;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
}
