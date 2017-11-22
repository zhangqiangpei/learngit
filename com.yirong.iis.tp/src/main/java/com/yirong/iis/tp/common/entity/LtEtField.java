package com.yirong.iis.tp.common.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 功能描述：elektron字段表entity类
 * 
 * @author
 *         <p>
 *         创建时间 ：2017-11-20 15:53:48
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@Entity
@Table(name = "LT_ET_FIELD")
public class LtEtField implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * elektron字段ID
	 */
	@Id
	@GeneratedValue(generator = "system-uuid", strategy = GenerationType.AUTO)
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", nullable = false, length = 32)
	public String id;

	/**
	 * 字段唯一标识
	 */
	@Column(name = "FIELD_ID", nullable = false, length = 22)
	public String fieldId;

	/**
	 * 字段编码
	 */
	@Column(name = "FIELD_CODE", nullable = false, length = 40)
	public String fieldCode;

	/**
	 * 字段中文名称
	 */
	@Column(name = "FIELD_NAME", nullable = false, length = 40)
	public String fieldName;

	/**
	 * 字段英文名称
	 */
	@Column(name = "FIELD_ENGLISH_NAME", nullable = false, length = 40)
	public String fieldEnglishName;

	/**
	 * 字段类型
	 */
	@Column(name = "FIELD_TYPE", nullable = false, length = 40)
	public String fieldType;

	/**
	 * 字段中文描述
	 */
	@Column(name = "FIELD_DESC", nullable = true, length = 400)
	public String fieldDesc;

	/**
	 * 字段英文描述
	 */
	@Column(name = "FIELD_ENGLISH_DESC", nullable = true, length = 400)
	public String fieldEnglishDesc;

	/**
	 * 创建人
	 */
	@Column(name = "CREATOR", nullable = true, length = 32)
	public String creator;

	/**
	 * 创建时间
	 */
	@Column(name = "CREATE_TIME", nullable = false)
	public Date createTime;

	/**
	 * 修改人
	 */
	@Column(name = "MODIFIER", nullable = true, length = 32)
	public String modifier;

	/**
	 * 修改时间
	 */
	@Column(name = "MODIFY_TIME", nullable = true)
	public Date modifyTime;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFieldId() {
		return this.fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	public String getFieldCode() {
		return this.fieldCode;
	}

	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
	}

	public String getFieldName() {
		return this.fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldEnglishName() {
		return this.fieldEnglishName;
	}

	public void setFieldEnglishName(String fieldEnglishName) {
		this.fieldEnglishName = fieldEnglishName;
	}

	public String getFieldType() {
		return this.fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getFieldDesc() {
		return this.fieldDesc;
	}

	public void setFieldDesc(String fieldDesc) {
		this.fieldDesc = fieldDesc;
	}

	public String getFieldEnglishDesc() {
		return this.fieldEnglishDesc;
	}

	public void setFieldEnglishDesc(String fieldEnglishDesc) {
		this.fieldEnglishDesc = fieldEnglishDesc;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getModifier() {
		return this.modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

}