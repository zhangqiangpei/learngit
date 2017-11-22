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
import com.yirong.iis.user.dao.IisCompanyInformationDao;
import com.yirong.iis.user.entity.IisCompanyInformation;
import com.yirong.iis.user.service.IisCompanyInformationService;
import com.yirong.iis.user.userentity.IisCompanyInformationUserEntity;

 
/**
 * 
 * @ClassName: IisCompanyInformationServiceImpl  
 * @Description: TODO(企业资讯表service实现类) 
 * @author liny
 * @date 2017年11月22日 下午3:31:59 
 * @version V0.1
 */
@SuppressWarnings("rawtypes")
@Service("IisCompanyInformationServiceImpl")
public class IisCompanyInformationServiceImpl extends BaseService<IisCompanyInformation, String>
		implements IisCompanyInformationService {

	/**
	 * 日志操作类
	 */
	private Logger logger = LoggerFactory.getLogger(IisCompanyInformationServiceImpl.class);

	/**
	 * dao注入
	 */
	@Autowired
	private IisCompanyInformationDao iisCompanyInformationDao;

	/**
	 * 
	 * @Title: getBaseDao 
	 * @Description: TODO(获取dao操作类) 
	 * @return
	 */
	@Override
	public IBaseDao<IisCompanyInformation, String> getBaseDao() {
		return iisCompanyInformationDao;
	}
 
	/**
	 * 
	 * @Title: queryIisCompanyInformationList 
	 * @Description: TODO(查询企业资讯表列表信息) 
	 * @param ue
	 * @return
	 */
	@Override
	public Map queryIisCompanyInformationList(IisCompanyInformationUserEntity ue){
		SimpleSpecificationBuilder simpbuilder = new SimpleSpecificationBuilder();
		
		if(StringUtil.isNotNullOrEmpty(ue.getCompanyId())){
			simpbuilder.add("companyId", RestrictionNames.EQ, ue.getCompanyId());
		}
		
		if(StringUtil.isNotNullOrEmpty(ue.getCompanyName())){
			simpbuilder.add("companyName", RestrictionNames.LIKE, ue.getCompanyName());
		}
		
		if(StringUtil.isNotNullOrEmpty(ue.getTitle())){
			simpbuilder.add("title", RestrictionNames.LIKE, ue.getTitle());
		}
		
		if(StringUtil.isNotNullOrEmpty(ue.getContent())){
			simpbuilder.add("content", RestrictionNames.LIKE, ue.getContent());
		}
		
		// 获取数据
		PageEntiry pageEntiry = this.findPage(simpbuilder.getOpers(), ue);
		
		return ResultUtil.newOk("操作成功").setData(pageEntiry).toMap();
	}

}
