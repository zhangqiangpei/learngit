package com.yirong.iis.mm.userentity;

import java.io.Serializable;
import com.yirong.commons.util.entity.PageEntiry;
import java.util.Date;

 /**
 * 功能描述：政治环境表查询条件类
 * 
 * @author 陈清沣
 *         <p>
 *         创建时间 ：2017-11-30 10:42:31
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public class IisPoliticalEnvironmentUserEntity extends PageEntiry implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	* ID
	*/
	private String id;
	
	
	/**
	* 国家英文名
	*/
	private String countryEngName;
	
	
	/**
	* 字段名称
	*/
	private String fieldName;
	
	
	/**
	* 字段值
	*/
	private String fieldValue;
	
	
	/**
	* 创建人
	*/
	private String creator;
	
	
	/**
	* 创建时间
	*/
	private Date createTime;
	
	
	/**
	* 修改人
	*/
	private String modifier;
	
	
	/**
	* 修改时间
	*/
	private Date modifyTime;
	
   
	public String getId(){
		return this.id;
	}
		
	public void setId (String id){
		this.id=id;
	}
	public String getCountryEngName(){
		return this.countryEngName;
	}
		
	public void setCountryEngName (String countryEngName){
		this.countryEngName=countryEngName;
	}
	public String getFieldName(){
		return this.fieldName;
	}
		
	public void setFieldName (String fieldName){
		this.fieldName=fieldName;
	}
	public String getFieldValue(){
		return this.fieldValue;
	}
		
	public void setFieldValue (String fieldValue){
		this.fieldValue=fieldValue;
	}
	public String getCreator(){
		return this.creator;
	}
		
	public void setCreator (String creator){
		this.creator=creator;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
		
	public void setCreateTime (Date createTime){
		this.createTime=createTime;
	}
	public String getModifier(){
		return this.modifier;
	}
		
	public void setModifier (String modifier){
		this.modifier=modifier;
	}
	public Date getModifyTime(){
		return this.modifyTime;
	}
		
	public void setModifyTime (Date modifyTime){
		this.modifyTime=modifyTime;
	}
   
}