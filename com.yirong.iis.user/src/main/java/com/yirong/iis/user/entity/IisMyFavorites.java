package com.yirong.iis.user.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author MingTie
 * CreateDate：2017/11/20
 * Description：
 */
@Entity
@Table(name="IIS_MY_FAVORITES")
public class IisMyFavorites implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 个人收藏ID
     */
    @Id
    @GeneratedValue(generator="system-uuid",strategy = GenerationType.AUTO)
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name="ID",nullable=false,length=32)
    public String id;

    /**
     * 收藏对象ID
     */
    @Column(name="OBJ_ID",nullable=false,length=32)
    public String objId;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
