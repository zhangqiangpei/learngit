package com.yirong.iis.user.userentity;

import java.io.Serializable;
import java.util.Date;

import com.yirong.commons.util.entity.PageEntiry;

 /**
 * 功能描述：监管政策表查询条件类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-27 09:56:48
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public class IisRegulatoryPolicyUserEntity extends PageEntiry implements Serializable {

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
	* 文件名称
	*/
	private String fileName;
	
	
	/**
	* 文件ID
	*/
	private String eosId;
	
	
	/**
	* 来源
	*/
	private String source;
	
	
	/**
	* 来源
	*/
	private String language;
	
	
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
	public String getFileName(){
		return this.fileName;
	}
		
	public void setFileName (String fileName){
		this.fileName=fileName;
	}
	public String getEosId(){
		return this.eosId;
	}
		
	public void setEosId (String eosId){
		this.eosId=eosId;
	}
	public String getSource(){
		return this.source;
	}
		
	public void setSource (String source){
		this.source=source;
	}
	public String getLanguage(){
		return this.language;
	}
		
	public void setLanguage (String language){
		this.language=language;
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