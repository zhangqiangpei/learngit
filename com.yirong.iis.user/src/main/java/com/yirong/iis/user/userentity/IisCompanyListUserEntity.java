package com.yirong.iis.user.userentity;


import java.io.Serializable;
import java.util.Date;

import com.yirong.commons.util.entity.PageEntiry;

/**
 * 
 * @ClassName: IisCompanyListUserEntity  
 * @Description: TODO(企业列表查询条件类) 
 * @author liny
 * @date 2017年11月22日 上午11:18:33 
 * @version V0.1
 */
public class IisCompanyListUserEntity extends PageEntiry implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	* 编码
	*/
	private String id;
	
	
	/**
	* 所属洲(代码表)
	*/
	private String continentCode;
	
	
	/**
	* 国家英文名
	*/
	private String countryEngName;
	
	
	/**
	* 行业(代码表)
	*/
	private String industryCode;
	
	
	/**
	* 类型，0-关注企业 1-运营企业 2-主要电力企业 9-其他
	*/
	private String type;
	
	
	/**
	* 企业名称
	*/
	private String companyName;
	
	
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
	public String getContinentCode(){
		return this.continentCode;
	}
		
	public void setContinentCode (String continentCode){
		this.continentCode=continentCode;
	}
	public String getCountryEngName(){
		return this.countryEngName;
	}
		
	public void setCountryEngName (String countryEngName){
		this.countryEngName=countryEngName;
	}
	public String getIndustryCode(){
		return this.industryCode;
	}
		
	public void setIndustryCode (String industryCode){
		this.industryCode=industryCode;
	}
	public String getType(){
		return this.type;
	}
		
	public void setType (String type){
		this.type=type;
	}
	public String getCompanyName(){
		return this.companyName;
	}
		
	public void setCompanyName (String companyName){
		this.companyName=companyName;
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