package com.yirong.iis.user.entity;


import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * 功能描述：国家信息表entity类
 *
 * @author 林明铁
 *         <p>
 *         创建时间 ：2017-11-21 15:44:24
 *         </p>
 *
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@Entity
@Table(name="SY_COUNTRY")
public class IisCountryInfo implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

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

    /**
     * 编码
     */
    @Id
    @GeneratedValue(generator="system-uuid",strategy = GenerationType.AUTO)
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name="ID",nullable=false,length=32)
    public String id;

    /**
     * 二位字母
     */
    @Column(name="ISO2CODE",nullable=false,length=2)
    public String iso2code;

    /**
     * 三位字母
     */
    @Column(name="ISOCODE",nullable=false,length=3)
    public String isocode;

    /**
     * 国家英文名
     */
    @Column(name="ENGLISH_NAME",nullable=false,length=100)
    public String englishName;

    /**
     * 国家中文名称
     */
    @Column(name="CHINESE_NAME",nullable=false,length=100)
    public String chineseName;

    /**
     * 所属洲(代码表)
     */
    @Column(name="CONTINENT_CODE",nullable=false,length=6)
    public String continentCode;

    /**
     * 创建人
     */
    @Column(name="CREATOR",nullable=true,length=32)
    public String creator;


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
    public String getId(){
        return this.id;
    }

    public void setId (String id){
        this.id=id;
    }
    public String getIso2code(){
        return this.iso2code;
    }

    public void setIso2code (String iso2code){
        this.iso2code=iso2code;
    }
    public String getIsocode(){
        return this.isocode;
    }

    public void setIsocode (String isocode){
        this.isocode=isocode;
    }
    public String getEnglishName(){
        return this.englishName;
    }

    public void setEnglishName (String englishName){
        this.englishName=englishName;
    }
    public String getChineseName(){
        return this.chineseName;
    }

    public void setChineseName (String chineseName){
        this.chineseName=chineseName;
    }
    public String getContinentCode(){
        return this.continentCode;
    }

    public void setContinentCode (String continentCode){
        this.continentCode=continentCode;
    }
    public String getCreator(){
        return this.creator;
    }

    public void setCreator (String creator){
        this.creator=creator;
    }


}
