package com.yirong.iis.user.entity;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.yirong.awaken.core.entity.IdEntity;

 /**
 * 功能描述：国家信用表entity类
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
@Entity
@Table(name="IIS_COUNTRY_CREDIT")
public class IisCountryCredit extends IdEntity{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	* 国家英文名
	*/
	@Column(name="ENGLISH_NAME",nullable=false,length=100)
	public String englishName;
	
	/**
	* 机构评级-标普
	*/
	@Column(name="BOND_RATING",nullable=true,length=100)
	public String bondRating;
	
	/**
	* 机构评级-穆迪
	*/
	@Column(name="RATING",nullable=true,length=100)
	public String rating;
	
	/**
	* 机构评级-惠誉
	*/
	@Column(name="GV_TEXT",nullable=true,length=100)
	public String gvText;
	
	/**
	* 信用排名-短期
	*/
	@Column(name="GEN_VAL1",nullable=true,length=100)
	public String genVal1;
	
	/**
	* 信用排名-长期
	*/
	@Column(name="GEN_VAL2",nullable=true,length=100)
	public String genVal2;
	
	/**
	* 创建人
	*/
	@Column(name="CREATOR",nullable=true,length=32)
	public String creator;
	
	/**
	* 创建时间
	*/
	@Column(name="CREATE_TIME",nullable=false)
	public Date createTime;
	
	/**
	* 修改人
	*/
	@Column(name="MODIFIER",nullable=true,length=32)
	public String modifier;
	
	/**
	* 修改时间
	*/
	@Column(name="MODIFY_TIME",nullable=true)
	public Date modifyTime;
	
   
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