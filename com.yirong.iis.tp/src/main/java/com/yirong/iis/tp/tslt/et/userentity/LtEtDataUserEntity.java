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
	 * 字段ID
	 */
	private short fleldId;

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

	public short getFleldId() {
		return fleldId;
	}

	public void setFleldId(short fleldId) {
		this.fleldId = fleldId;
	}

}
