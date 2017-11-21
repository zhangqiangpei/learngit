package com.yirong.iis.user.userentity;

import java.io.Serializable;

/**
 * 功能描述：awaken文件上传实体类
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
public class KmUserAwakenAddFileUserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 文档名称
	 */
	private String docName;

	/**
	 * 文档大小（单位：kb）
	 */
	private Integer docSize;

	/**
	 * 机构编码
	 */
	private String orgCode;

	/**
	 * 机构名称
	 */
	private String orgName;

	/**
	 * 创建人ID
	 */
	private String creator;

	/**
	 * 创建人名称
	 */
	private String creatorName;

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public Integer getDocSize() {
		return docSize;
	}

	public void setDocSize(Integer docSize) {
		this.docSize = docSize;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

}
