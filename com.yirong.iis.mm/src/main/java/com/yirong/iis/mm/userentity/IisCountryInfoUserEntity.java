package com.yirong.iis.mm.userentity;

import java.io.Serializable;
import java.util.Date;

import com.yirong.commons.util.entity.PageEntiry;

 /**
 * 功能描述：国家信息表查询条件类
 * 
 * @author 陈清沣
 *         <p>
 *         创建时间 ：2017-11-24 18:16:59
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public class IisCountryInfoUserEntity extends PageEntiry implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	
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
	
	
	/**
	* 编码
	*/
	private String id;
	
	
	/**
	* 二位字母
	*/
	private String iso2code;
	
	
	/**
	* 三位字母
	*/
	private String isocode;
	
	
	/**
	* 国家英文名
	*/
	private String englishName;
	
	
	/**
	* 国家中文名称
	*/
	private String chineseName;
	
	
	/**
	* 所属洲(代码表)
	*/
	private String continentCode;
	
	
	/**
	* 创建人
	*/
	private String creator;
	
   
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
	public String getId(){
		return this.id;
	}
		
	public void setId (String id){
		this.id=id;
	}
	public String getIso2code(){
		return this.iso2code;
	}
		
	public void setIso2code (String iso2code){
		this.iso2code=iso2code;
	}
	public String getIsocode(){
		return this.isocode;
	}
		
	public void setIsocode (String isocode){
		this.isocode=isocode;
	}
	public String getEnglishName(){
		return this.englishName;
	}
		
	public void setEnglishName (String englishName){
		this.englishName=englishName;
	}
	public String getChineseName(){
		return this.chineseName;
	}
		
	public void setChineseName (String chineseName){
		this.chineseName=chineseName;
	}
	public String getContinentCode(){
		return this.continentCode;
	}
		
	public void setContinentCode (String continentCode){
		this.continentCode=continentCode;
	}
	public String getCreator(){
		return this.creator;
	}
		
	public void setCreator (String creator){
		this.creator=creator;
	}
   
}