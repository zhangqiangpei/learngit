package com.yirong.iis.user.userentity;
import java.io.Serializable;
import java.util.Date;

import com.yirong.commons.util.entity.PageEntiry;

 /**
 * 功能描述：法律法规表查询条件类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-27 16:13:26
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public class IisLawsRegulationsUserEntity extends PageEntiry implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	* 编码
	*/
	private String id;
	
	
	/**
	* 分类ID
	*/
	private String classifyId;
	
	
	/**
	* 标题
	*/
	private String title;
	
	
	/**
	* 内容
	*/
	private String content;
	
	
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
	public String getClassifyId(){
		return this.classifyId;
	}
		
	public void setClassifyId (String classifyId){
		this.classifyId=classifyId;
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