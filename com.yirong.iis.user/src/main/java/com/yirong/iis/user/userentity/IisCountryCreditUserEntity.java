package com.yirong.iis.user.userentity;
import java.io.Serializable;
import java.util.Date;

import com.yirong.commons.util.entity.PageEntiry;

 /**
 * 功能描述：国家信用表查询条件类
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
public class IisCountryCreditUserEntity extends PageEntiry implements Serializable {

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
	private String bondRating;
	
	
	/**
	* 机构评级-穆迪
	*/
	private String rating;
	
	
	/**
	* 机构评级-惠誉
	*/
	private String gvText;
	
	
	/**
	* 信用排名-短期
	*/
	private String genVal1;
	
	
	/**
	* 信用排名-长期
	*/
	private String genVal2;
	
	
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
	public String getBondRating(){
		return this.bondRating;
	}
		
	public void setBondRating (String bondRating){
		this.bondRating=bondRating;
	}
	public String getRating(){
		return this.rating;
	}
		
	public void setRating (String rating){
		this.rating=rating;
	}
	public String getGvText(){
		return this.gvText;
	}
		
	public void setGvText (String gvText){
		this.gvText=gvText;
	}
	public String getGenVal1(){
		return this.genVal1;
	}
		
	public void setGenVal1 (String genVal1){
		this.genVal1=genVal1;
	}
	public String getGenVal2(){
		return this.genVal2;
	}
		
	public void setGenVal2 (String genVal2){
		this.genVal2=genVal2;
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