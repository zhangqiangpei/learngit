package com.yirong.iis.user.userentity;


import java.io.Serializable;
import java.util.Date;

import com.yirong.commons.util.entity.PageEntiry;

/**
 * 功能描述：追踪表查询条件类
 *
 * @author
 *         <p>
 *         创建时间 ：2017-11-28 15:31:27
 *         </p>
 *
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public class IisProjectTrackUserEntity extends PageEntiry implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;


    /**
     * ID
     */
    private String id;


    /**
     * 名称
     */
    private String projectName;


    /**
     * 简介
     */
    private String projectInfo;


    /**
     * 跟踪类型编码(代码表)
     */
    private String typeCode;


    /**
     * 追踪原因
     */
    private String reason;


    /**
     * 关键字
     */
    private String keyWord;


    /**
     * 发布时间
     */
    private Date releaseTime;


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
    public String getProjectName(){
        return this.projectName;
    }

    public void setProjectName (String projectName){
        this.projectName=projectName;
    }
    public String getProjectInfo(){
        return this.projectInfo;
    }

    public void setProjectInfo (String projectInfo){
        this.projectInfo=projectInfo;
    }
    public String getTypeCode(){
        return this.typeCode;
    }

    public void setTypeCode (String typeCode){
        this.typeCode=typeCode;
    }
    public String getReason(){
        return this.reason;
    }

    public void setReason (String reason){
        this.reason=reason;
    }
    public String getKeyWord(){
        return this.keyWord;
    }

    public void setKeyWord (String keyWord){
        this.keyWord=keyWord;
    }
    public Date getReleaseTime(){
        return this.releaseTime;
    }

    public void setReleaseTime (Date releaseTime){
        this.releaseTime=releaseTime;
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
