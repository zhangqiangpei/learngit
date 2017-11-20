package com.yirong.iis.user.userentity;

import java.io.Serializable;

/**
 * 功能描述：awaken文件下载实体类
 *
 * @author 刘捷(liujie)
 *         <p>
 *         创建时间 ：2017年9月25日 下午2:51:13
 *         </p>
 *
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public class IisUserAwakenOperUserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ID（知识或者EOS）
	 */
	private String id;

	/**
	 * 操作人Id
	 */
	private String operationtor;

	/**
	 * 操作人名称
	 */
	private String operationtorName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOperationtor() {
		return operationtor;
	}

	public void setOperationtor(String operationtor) {
		this.operationtor = operationtor;
	}

	public String getOperationtorName() {
		return operationtorName;
	}

	public void setOperationtorName(String operationtorName) {
		this.operationtorName = operationtorName;
	}

}
