package com.yirong.iis.tp.tslt.et.userentity;

/**
 * 功能描述：
 *
 * @author 刘捷(liujie)
 *         <p>
 *         创建时间 ：2017年11月22日 下午1:53:50
 *         </p>
 *
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public class LtEtDataUserEntity {

	/**
	 * 路透编码
	 */
	private String ricCode;

	/**
	 * 编码类型
	 */
	private String codeType;

	/**
	 * 字段ID
	 */
	private String fleldId;
	
	/**
	 * 数据
	 */
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRicCode() {
		return ricCode;
	}

	public void setRicCode(String ricCode) {
		this.ricCode = ricCode;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getFleldId() {
		return fleldId;
	}

	public void setFleldId(String fleldId) {
		this.fleldId = fleldId;
	}

}
