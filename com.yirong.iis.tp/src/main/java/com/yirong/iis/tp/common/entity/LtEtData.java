package com.yirong.iis.tp.common.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 功能描述：elektron数据表entity类
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
@Table(name = "LT_ET_DATA")
public class LtEtData implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * elektron数据ID
	 */
	@Id
	@GeneratedValue(generator = "system-uuid", strategy = GenerationType.AUTO)
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", nullable = false, length = 32)
	public String id;

	/**
	 * 所属代码ID
	 */
	@Column(name = "CODE_ID", nullable = false, length = 32)
	public String codeId;

	/**
	 * 所属字段ID
	 */
	@Column(name = "FIELD_ID", nullable = true, length = 32)
	public String fieldId;

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
	 * 字符串值
	 */
	@Column(name = "STRING_VALUE", nullable = true, length = 100)
	public String stringValue;

	/**
	 * 浮点型值
	 */
	@Column(name = "FLOAT_VALUE", nullable = true, length = 22)
	public BigDecimal floatValue;

	/**
	 * 时间型值
	 */
	@Column(name = "DATE_VALUE", nullable = true)
	public Date dateValue;

	/**
	 * 创建时间
	 */
	@Column(name = "CREATE_TIME", nullable = false)
	public Date createTime;

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

	public String getCodeId() {
		return this.codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public String getFieldId() {
		return this.fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	public String getStringValue() {
		return this.stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public BigDecimal getFloatValue() {
		return this.floatValue;
	}

	public void setFloatValue(BigDecimal floatValue) {
		this.floatValue = floatValue;
	}

	public Date getDateValue() {
		return this.dateValue;
	}

	public void setDateValue(Date dateValue) {
		this.dateValue = dateValue;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldEnglishName() {
		return fieldEnglishName;
	}

	public void setFieldEnglishName(String fieldEnglishName) {
		this.fieldEnglishName = fieldEnglishName;
	}

	public String getFieldDesc() {
		return fieldDesc;
	}

	public void setFieldDesc(String fieldDesc) {
		this.fieldDesc = fieldDesc;
	}

	public String getFieldEnglishDesc() {
		return fieldEnglishDesc;
	}

	public void setFieldEnglishDesc(String fieldEnglishDesc) {
		this.fieldEnglishDesc = fieldEnglishDesc;
	}

}