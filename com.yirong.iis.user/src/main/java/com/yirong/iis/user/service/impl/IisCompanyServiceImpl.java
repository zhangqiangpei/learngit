package com.yirong.iis.user.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.awaken.core.dao.specification.RestrictionNames;
import com.yirong.awaken.core.dao.specification.SimpleSpecificationBuilder;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.awaken.core.util.ResultUtil;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.commons.util.datatype.StringUtil;
import com.yirong.commons.util.entity.PageEntiry;
import com.yirong.iis.user.dao.VeIisCompanyDao;
import com.yirong.iis.user.entity.VeIisCompany;
import com.yirong.iis.user.service.IisCompanyService;
import com.yirong.iis.user.userentity.VeIisCompanyUserEntity;

 
/**
 * 
 * @ClassName: IisCompanyServiceImpl  
 * @Description: TODO(企业视图service实现类)
 * @author liny
 * @date 2017年11月28日 下午8:06:40 
 * @version V0.1
 */
@SuppressWarnings("rawtypes")
@Service("IisCompanyServiceImpl")
public class IisCompanyServiceImpl  extends BaseService<VeIisCompany, String> implements IisCompanyService {

	/**
	 * 日志操作类
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * dao注入
	 */
	@Autowired
	private VeIisCompanyDao dao;

	/**
	 * 
	 * @Title: getBaseDao 
	 * @Description: TODO(获取dao操作类) 
	 * @return
	 */
	@Override
	public IBaseDao<VeIisCompany, String> getBaseDao() {
		return dao;
	}
	
	/**
	 * 
	 * @Title: queryIisCompanyById 
	 * @Description: TODO(根据ID查询企业信息) 
	 * @param id
	 * @return Map
	 */
	@Override
	public Map queryIisCompanyById(String id) {
		VeIisCompany entity = this.get(id);
		return ResultUtil.newOk("操作成功").setData(entity).toMap();
	}

	/**
	 * 
	 * @Title: queryIisCompanyByCompanyId 
	 * @Description: TODO(根据CompanyId查询企业信息) 
	 * @param id
	 * @return Map
	 */
	@Override
	public Map queryIisCompanyByCompanyId(String companyId) {
		VeIisCompany entity = this.findByProperty("companyId", companyId);
		return ResultUtil.newOk("操作成功").setData(entity).toMap();
	}
	
	
	/**
	 * 
	 * @Title: queryIisCompanyList 
	 * @Description: TODO(查询企业列表列表信息) 
	 * @param ue
	 * @return Map
	 */
	@Override
	public Map queryIisCompanyList(VeIisCompanyUserEntity ue) {
		
		SimpleSpecificationBuilder simpbuilder = new SimpleSpecificationBuilder();
		
		
		if(StringUtil.isNotNullOrEmpty(ue.getCountryEnglishName())){
			simpbuilder.add("countryEnglishName", RestrictionNames.EQ, ue.getCountryEnglishName());
		}
		
		if(StringUtil.isNotNullOrEmpty(ue.getIndustryCode())){
			simpbuilder.add("industryCode", RestrictionNames.EQ, ue.getIndustryCode());
		}
 
		if(StringUtil.isNotNullOrEmpty(ue.getCompanyId())){
			simpbuilder.add("companyId", RestrictionNames.EQ, ue.getCompanyId());
		}
		
		if(StringUtil.isNotNullOrEmpty(ue.getType())){
			simpbuilder.add("type", RestrictionNames.EQ, ue.getType());
		}

		if(StringUtil.isNotNullOrEmpty(ue.getCompanyName())){
			simpbuilder.add("companyName", RestrictionNames.LIKE, ue.getCompanyName());
		}
 
		// 获取数据
		PageEntiry pageEntiry = this.findPage(simpbuilder.getOpers(), ue);
	 
		return ResultUtil.newOk("操作成功").setData(pageEntiry).toMap();
	}

 
}
