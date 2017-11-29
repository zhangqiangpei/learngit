package com.yirong.iis.user.service.impl;

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
import com.yirong.iis.user.dao.VeIisCompanyOfficerDao;
import com.yirong.iis.user.entity.VeIisCompanyOfficer;
import com.yirong.iis.user.service.IisCompanyOfficerService;
import com.yirong.iis.user.userentity.VeIisCompanyOfficerUserEntity;

 
/**
 * 
 * @ClassName: IisCompanyOfficerServiceImpl  
 * @Description: TODO(企业高管视图service实现类)
 * @author liny
 * @date 2017年11月28日 下午8:06:40 
 * @version V0.1
 */
@SuppressWarnings("rawtypes")
@Service("IisCompanyOfficerServiceImpl")
public class IisCompanyOfficerServiceImpl  extends BaseService<VeIisCompanyOfficer, String> implements IisCompanyOfficerService {

	/**
	 * 日志操作类
	 */
	private Logger logger = LoggerFactory.getLogger(IisCompanyListServiceImpl.class);

	/**
	 * dao注入
	 */
	@Autowired
	private VeIisCompanyOfficerDao dao;

	/**
	 * 
	 * @Title: getBaseDao 
	 * @Description: TODO(获取dao操作类) 
	 * @return
	 */
	@Override
	public IBaseDao<VeIisCompanyOfficer, String> getBaseDao() {
		return dao;
	}
	 
	 
	
	/**
	 * 
	 * @Title: queryIisCompanyList 
	 * @Description: TODO(查询某个企业高管列表信息) 
	 * @param companyId
	 * @return Map
	 */
	@Override
	public Map queryIisCompanyOfficerList(VeIisCompanyOfficerUserEntity ue) {
		SimpleSpecificationBuilder simpbuilder = new SimpleSpecificationBuilder();
		
		if(StringUtil.isNotNullOrEmpty(ue.getCompanyId())){
			simpbuilder.add("companyId", RestrictionNames.EQ, ue.getCompanyId());
		}
		 
		// 获取数据
		PageEntiry pageEntiry = this.findPage(simpbuilder.getOpers(), ue);
	 
		return ResultUtil.newOk("操作成功").setData(pageEntiry).toMap();
	}

 
}
