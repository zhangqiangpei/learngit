package com.yirong.iis.user.entity;


import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

/**
 * 功能描述：跟踪明细表entity类
 *
 * @author 林明铁
 *         <p>
 *         创建时间 ：2017-11-28 15:32:14
 *         </p>
 *
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@Entity
@Table(name="IIS_PROJECT_TRACK_DETAIL")
public class IisProjectTrackDetail implements Serializable {

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
     * 资讯id
     */
    @Column(name="OBJ_ID",nullable=false,length=32)
    public String objId;

    /**
     * 项目id
     */
    @Column(name="PRO_ID",nullable=false,length=32)
    public String proId;

    /**
     * 是否标记点
     */
    @Column(name="IS_SIGN",nullable=true,length=1)
    public String isSign;

    /**
     * 顺序
     */
    @Column(name="OBJ_ORDER",nullable=false,length=22)
    public BigDecimal objOrder;

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

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getObjId(){
        return this.objId;
    }

    public void setObjId (String objId){
        this.objId=objId;
    }
    public String getIsSign(){
        return this.isSign;
    }

    public void setIsSign (String isSign){
        this.isSign=isSign;
    }
    public BigDecimal getObjOrder(){
        return this.objOrder;
    }

    public void setObjOrder (BigDecimal objOrder){
        this.objOrder=objOrder;
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