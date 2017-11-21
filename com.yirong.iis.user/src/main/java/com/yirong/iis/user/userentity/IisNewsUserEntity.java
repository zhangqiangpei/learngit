package com.yirong.iis.user.userentity;


import java.io.Serializable;
import java.util.Date;

import com.yirong.commons.util.entity.PageEntiry;

/**
 * 功能描述：新闻表查询条件类
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
public class IisNewsUserEntity extends PageEntiry implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;


    /**
     * 新闻ID
     */
    private String id;


    /**
     * 所属国家ID
     */
    private String countryId;


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
     * 类型
     */
    private String type;


    /**
     * 发布时间
     */
    private Date releaseTime;


    /**
     * 采集时间
     */
    private Date collectionTime;


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
