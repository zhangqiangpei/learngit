package com.yirong.iis.user.entity;


import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * 功能描述：追踪表entity类
 *
 * @author 林明铁
 *         <p>
 *         创建时间 ：2017-11-28 15:31:27
 *         </p>
 *
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@Entity
@Table(name="IIS_PROJECT_TRACK")
public class IisProjectTrack implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
    @GeneratedValue(generator="system-uuid",strategy = GenerationType.AUTO)
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name="ID",nullable=false,length=32)
    public String id;

    /**
     * 名称
     */
    @Column(name="PROJECT_NAME",nullable=false,length=100)
    public String projectName;

    /**
     * 简介
     */
    @Column(name="PROJECT_INFO",nullable=true)
    public String projectInfo;

    /**
     * 跟踪类型编码(代码表)
     */
    @Column(name="TYPE_CODE",nullable=false,length=6)
    public String typeCode;

    /**
     * 追踪原因
     */
    @Column(name="REASON",nullable=true)
    public String reason;

    /**
     * 关键字
     */
    @Column(name="KEY_WORD",nullable=true,length=200)
    public String keyWord;

    /**
     * 发布时间
     */
    @Column(name="RELEASE_TIME",nullable=true)
    public Date releaseTime;

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