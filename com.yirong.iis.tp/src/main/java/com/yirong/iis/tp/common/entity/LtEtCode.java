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
 * 功能描述：elektron代码表entity类
 * 
 * @author
 *         <p>
 *         创建时间 ：2017-11-20 15:49:46
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@Entity
@Table(name = "LT_ET_CODE")
public class LtEtCode implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * elektron代码ID
	 */
	@Id
	@GeneratedValue(generator = "system-uuid", strategy = GenerationType.AUTO)
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", nullable = false, length = 32)
	public String id;

	/**
	 * 国家英文名
	 */
	@Column(name = "COUNTRY_ENGLISH_NAME", nullable = true, length = 100)
	public String countryEnglishName;

	/**
	 * 路透代码(RIC)
	 */
	@Column(name = "RIC_CODE", nullable = false, length = 20)
	public String ricCode;

	/**
	 * 代码名称
	 */
	@Column(name = "CODE_NAME", nullable = false, length = 100)
	public String codeName;

	/**
	 * 所属分类(代码表:020)
	 */
	@Column(name = "CODE_TYPE", nullable = false, length = 6)
	public String codeType;

	/**
	 * 代码描述
	 */
	@Column(name = "CODE_DESC", nullable = true, length = 400)
	public String codeDesc;

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

	public String getCountryEnglishName() {
		return countryEnglishName;
	}

	public void setCountryEnglishName(String countryEnglishName) {
		this.countryEnglishName = countryEnglishName;
	}

	public String getRicCode() {
		return this.ricCode;
	}

	public void setRicCode(String ricCode) {
		this.ricCode = ricCode;
	}

	public String getCodeName() {
		return this.codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getCodeType() {
		return this.codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getCodeDesc() {
		return this.codeDesc;
	}

	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
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