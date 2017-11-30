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
	 * 字段类型
	 */
	@Column(name = "FIELD_TYPE", nullable = true, length = 6)
	public String fieldType;

	/**
	 * 数值
	 */
	@Column(name = "DATA_VALUE", nullable = true, length = 100)
	public String dataValue;

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

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

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

	public String getDataValue() {
		return dataValue;
	}

	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}
}