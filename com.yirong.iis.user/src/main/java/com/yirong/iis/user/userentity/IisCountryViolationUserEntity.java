package com.yirong.iis.user.userentity;
import java.io.Serializable;
import java.util.Date;

import com.yirong.commons.util.entity.PageEntiry;

 /**
 * 功能描述：国家违约概率表查询条件类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-29 09:44:05
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public class IisCountryViolationUserEntity extends PageEntiry implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	* 国家负债ID
	*/
	private String id;
	
	
	/**
	* 国家英文名
	*/
	private String englishName;
	
	
	/**
	* 机构评级-标普
	*/
	private String violationName;
	
	
	/**
	* 机构评级-穆迪
	*/
	private String violationValue;
	
	
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
	public String getEnglishName(){
		return this.englishName;
	}
		
	public void setEnglishName (String englishName){
		this.englishName=englishName;
	}
	public String getViolationName(){
		return this.violationName;
	}
		
	public void setViolationName (String violationName){
		this.violationName=violationName;
	}
	public String getViolationValue(){
		return this.violationValue;
	}
		
	public void setViolationValue (String violationValue){
		this.violationValue=violationValue;
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