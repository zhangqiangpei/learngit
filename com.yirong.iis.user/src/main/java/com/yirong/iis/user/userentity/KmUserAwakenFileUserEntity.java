package com.yirong.iis.user.userentity;

import java.io.Serializable;
import java.util.List;

/**
 * 功能描述：awaken文件上传及获取实体类
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
public class KmUserAwakenFileUserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 知识id
	 */
	private String id;

	/**
	 * eos唯一标识
	 */
	private String docEosId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getDocEosId() {
		return docEosId;
	}

	public void setDocEosId(String docEosId) {
		this.docEosId = docEosId;
	}
}
