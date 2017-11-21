package com.yirong.iis.user.entity;


import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * 功能描述：新闻表entity类
 *
 * @author 林明铁
 *         <p>
 *         创建时间 ：2017-11-21 15:43:12
 *         </p>
 *
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@Entity
@Table(name="IIS_NEWS")
public class IisNews implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 新闻ID
     */
    @Id
    @GeneratedValue(generator="system-uuid",strategy = GenerationType.AUTO)
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name="ID",nullable=false,length=32)
    public String id;

    /**
     * 所属国家ID
     */
    @Column(name="COUNTRY_ID",nullable=false,length=32)
    public String countryId;

    /**
     * 标题
     */
    @Column(name="TITLE",nullable=false,length=200)
    public String title;

    /**
     * 内容
     */
    @Column(name="CONTENT",nullable=false)
    public String content;

    /**
     * 来源
     */
    @Column(name="SOURCE",nullable=true,length=40)
    public String source;

    /**
     * 类型
     */
    @Column(name="TYPE",nullable=true,length=20)
    public String type;

    /**
     * 发布时间
     */
    @Column(name="RELEASE_TIME",nullable=true)
    public Date releaseTime;

    /**
     * 采集时间
     */
    @Column(name="COLLECTION_TIME",nullable=false)
    public Date collectionTime;

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
    public String getCountryId(){
        return this.countryId;
    }

    public void setCountryId (String countryId){
        this.countryId=countryId;
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
    public String getType(){
        return this.type;
    }

    public void setType (String type){
        this.type=type;
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


}
