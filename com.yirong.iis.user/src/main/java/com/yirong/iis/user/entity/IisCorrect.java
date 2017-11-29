package com.yirong.iis.user.entity;


import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * 功能描述：修正表entity类
 *
 * @author 林明铁
 *         <p>
 *         创建时间 ：2017-11-28 14:52:54
 *         </p>
 *
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@Entity
@Table(name="IIS_CORRECT")
public class IisCorrect implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 翻译ID
     */
    @Id
    @GeneratedValue(generator="system-uuid",strategy = GenerationType.AUTO)
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name="ID",nullable=false,length=32)
    public String id;

    /**
     * 源语言
     */
    @Column(name="SOURCE_LANGUAGE",nullable=false,length=20)
    public String sourceLanguage;

    /**
     * 源内容
     */
    @Column(name="SOURCE_CONTENT",nullable=false,length=200)
    public String sourceContent;

    /**
     * 目标语言
     */
    @Column(name="TARGET_LANGUAGE",nullable=false,length=20)
    public String targetLanguage;

    /**
     * 目标内容
     */
    @Column(name="TARGET_CONTENT",nullable=false,length=200)
    public String targetContent;

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
    public String getSourceLanguage(){
        return this.sourceLanguage;
    }

    public void setSourceLanguage (String sourceLanguage){
        this.sourceLanguage=sourceLanguage;
    }
    public String getSourceContent(){
        return this.sourceContent;
    }

    public void setSourceContent (String sourceContent){
        this.sourceContent=sourceContent;
    }
    public String getTargetLanguage(){
        return this.targetLanguage;
    }

    public void setTargetLanguage (String targetLanguage){
        this.targetLanguage=targetLanguage;
    }
    public String getTargetContent(){
        return this.targetContent;
    }

    public void setTargetContent (String targetContent){
        this.targetContent=targetContent;
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