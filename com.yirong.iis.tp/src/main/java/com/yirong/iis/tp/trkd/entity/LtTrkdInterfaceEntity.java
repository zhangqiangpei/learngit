package com.yirong.iis.tp.trkd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yirong.awaken.core.entity.IdEntity;


/**
 * TRKD url对照表
 * @author lijp
 *
 */
@Entity
@Table(name="lt_trkd_interface")
public class LtTrkdInterfaceEntity extends IdEntity{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	* 分类
	*/
	@Column(name="catalog",nullable=false,length=30)
	public String catalog;
	
	
	
	/**
	* url
	*/
	@Column(name="url",nullable=true,length=255)
	public String url;



	public String getCatalog() {
		return catalog;
	}



	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}
	
}