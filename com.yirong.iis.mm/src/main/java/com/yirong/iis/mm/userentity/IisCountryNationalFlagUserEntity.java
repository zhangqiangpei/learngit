package com.yirong.iis.mm.userentity;

import java.io.Serializable;
import com.yirong.commons.util.entity.PageEntiry;
import java.util.Date;

 /**
 * 功能描述：国家国旗表查询条件类
 * 
 * @author 陈清沣
 *         <p>
 *         创建时间 ：2017-11-29 10:40:39
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public class IisCountryNationalFlagUserEntity extends PageEntiry implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	* 编码
	*/
	private String id;
	
	
	/**
	* 国家英文名
	*/
	private String countryEngName;
	
	
	/**
	* 国旗照片
	*/
	private String nationalFlag;
	
	
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
	public String getNationalFlag(){
		return this.nationalFlag;
	}
		
	public void setNationalFlag (String nationalFlag){
		this.nationalFlag=nationalFlag;
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