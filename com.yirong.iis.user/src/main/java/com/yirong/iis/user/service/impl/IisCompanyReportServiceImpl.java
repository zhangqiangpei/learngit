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
import com.yirong.iis.user.dao.VeIisCompanyFinanceReportDao;
import com.yirong.iis.user.entity.VeIisCompanyFinanceReport;
import com.yirong.iis.user.service.IisCompanyReportService;
import com.yirong.iis.user.userentity.VeIisCompanyReportUserEntity;

 
/**
 * 
 * @ClassName: IisCompanyReportServiceImpl  
 * @Description: TODO(企业高管视图service实现类)
 * @author liny
 * @date 2017年11月28日 下午8:06:40 
 * @version V0.1
 */
@SuppressWarnings("rawtypes")
@Service("IisCompanyReportServiceImpl")
public class IisCompanyReportServiceImpl  extends BaseService<VeIisCompanyFinanceReport, String> implements IisCompanyReportService {

	/**
	 * 日志操作类
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * dao注入
	 */
	@Autowired
	private VeIisCompanyFinanceReportDao dao;

	/**
	 * 
	 * @Title: getBaseDao 
	 * @Description: TODO(获取dao操作类) 
	 * @return
	 */
	@Override
	public IBaseDao<VeIisCompanyFinanceReport, String> getBaseDao() {
		return dao;
	}
	 
	 
 
	@Override
	public Map queryIisCompanyReportList(VeIisCompanyReportUserEntity ue) {
		SimpleSpecificationBuilder simpbuilder = new SimpleSpecificationBuilder();
		
		if(StringUtil.isNotNullOrEmpty(ue.getCompanyId())){
			simpbuilder.add("companyId", RestrictionNames.EQ, ue.getCompanyId());
		}
		
		if(StringUtil.isNotNullOrEmpty(ue.getType())){
			simpbuilder.add("type", RestrictionNames.EQ, ue.getType());
		}
		
		if(StringUtil.isNotNullOrEmpty(ue.getFiscalYear())){
			simpbuilder.add("fiscalYear", RestrictionNames.EQ, ue.getFiscalYear());
		}
		 
		// 获取数据
		PageEntiry pageEntiry = this.findPage(simpbuilder.getOpers(), ue);
	 
		return ResultUtil.newOk("操作成功").setData(pageEntiry).toMap();
	}

 
}
