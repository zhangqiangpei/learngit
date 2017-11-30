package com.yirong.iis.user.userentity;
import java.io.Serializable;
import java.util.Date;

import com.yirong.commons.util.entity.PageEntiry;

 /**
 * 功能描述：重大事件表查询条件类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-28 10:58:00
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public class IisKeyEventsUserEntity extends PageEntiry implements Serializable {

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
	* 标题
	*/
	private String title;
	
	
	/**
	* 内容
	*/
	private String content;
	
	
	/**
	* 来源
	*/
	private String source;
	
	
	/**
	* 发布时间
	*/
	private Date releaseTime;
	
	
	/**
	* 采集时间
	*/
	private Date collectionTime;
	
	private String queryYear;
	
	
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
	public String getTitle(){
		return this.title;
	}
		
	public void setTitle (String title){
		this.title=title;
	}
	public String getContent(){
		return this.content;
	}
		
	public void setContent (String content){
		this.content=content;
	}
	public String getSource(){
		return this.source;
	}
		
	public void setSource (String source){
		this.source=source;
	}
	public Date getReleaseTime(){
		return this.releaseTime;
	}
		
	public void setReleaseTime (Date releaseTime){
		this.releaseTime=releaseTime;
	}
	public Date getCollectionTime(){
		return this.collectionTime;
	}
		
	public void setCollectionTime (Date collectionTime){
		this.collectionTime=collectionTime;
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

	public String getQueryYear() {
		return queryYear;
	}

	public void setQueryYear(String queryYear) {
		this.queryYear = queryYear;
	}
   
}