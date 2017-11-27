package com.yirong.iis.tp.common.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yirong.awaken.core.entity.IdEntity;

 /**
 * 功能描述：公司IndexMemberships表 entity类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-24 18:51:41
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@Entity
@Table(name="LT_TRKD_COMPANY_INDEX")
public class LtTrkdCompanyIndex extends IdEntity{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	* 公司id
	*/
	@Column(name="COMPANY_ID",nullable=false,length=32)
	public String companyId;
	
	/**
	* ric编码
	*/
	@Column(name="INDEX_RIC",nullable=true,length=20)
	public String indexRic;
	
	/**
	* 公司名称
	*/
	@Column(name="INDEX_VALUE",nullable=true,length=100)
	public String indexValue;
	
	/**
	* 创建时间
	*/
	@Column(name="CREATE_TIME",nullable=true)
	public Date createTime;
	
   
	public String getId(){
		return this.id;
	}
		
	public void setId (String id){
		this.id=id;
	}
	public String getCompanyId(){
		return this.companyId;
	}
		
	public void setCompanyId (String companyId){
		this.companyId=companyId;
	}
	public String getIndexRic(){
		return this.indexRic;
	}
		
	public void setIndexRic (String indexRic){
		this.indexRic=indexRic;
	}
	public String getIndexValue(){
		return this.indexValue;
	}
		
	public void setIndexValue (String indexValue){
		this.indexValue=indexValue;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
		
	public void setCreateTime (Date createTime){
		this.createTime=createTime;
	}

   
}