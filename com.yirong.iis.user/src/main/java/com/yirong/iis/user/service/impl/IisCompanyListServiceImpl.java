package com.yirong.iis.user.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.awaken.core.dao.specification.RestrictionNames;
import com.yirong.awaken.core.dao.specification.SimpleSpecificationBuilder;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.awaken.core.util.BeanUtil;
import com.yirong.awaken.core.util.ResultUtil;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.commons.util.datatype.StringUtil;
import com.yirong.commons.util.entity.PageEntiry;
import com.yirong.iis.user.dao.IisCompanyListDao;
import com.yirong.iis.user.entity.IisCompanyList;
import com.yirong.iis.user.service.IisCompanyListService;
import com.yirong.iis.user.userentity.IisCompanyListUserEntity;

/**
 * 
 * @ClassName: IisCompanyListServiceImpl  
 * @Description: TODO(企业列表service实现类) 
 * @author liny
 * @date 2017年11月22日 下午1:55:59 
 * @version V0.1
 */
@SuppressWarnings("rawtypes")
@Service("IisCompanyListServiceImpl")
public class IisCompanyListServiceImpl extends BaseService<IisCompanyList, String>
		implements IisCompanyListService {

	/**
	 * 日志操作类
	 */
	private Logger logger = LoggerFactory.getLogger(IisCompanyListServiceImpl.class);

	/**
	 * dao注入
	 */
	@Autowired
	private IisCompanyListDao iisCompanyListDao;

	/**
	 * 
	 * @Title: getBaseDao 
	 * @Description: TODO(获取dao操作类) 
	 * @return
	 */
	@Override
	public IBaseDao<IisCompanyList, String> getBaseDao() {
		return iisCompanyListDao;
	}

	/**
	 * 
	 * @Title: saveIisCompanyList 
	 * @Description: TODO(新增企业列表) 
	 * @param iisCompanyList
	 * @return
	 */
	@Override
	public Map saveIisCompanyList(IisCompanyList iisCompanyList){
		//（唯一键）查重判断
		long count = this.entityCount("companyName", iisCompanyList.getCompanyName());
		if(count > 0){
			logger.info("企业名称已存在");
			return ResultUtil.newError("企业名称已存在!").toMap();
		}
		
		iisCompanyList.setCreateTime(new Date());
		this.save(iisCompanyList);
		return ResultUtil.newOk("操作成功").toMap();
 
	}

 
	/**
	 * 
	 * @Title: updateIisCompanyList 
	 * @Description: TODO(修改企业列表) 
	 * @param iisCompanyList
	 * @return
	 */
	@Override
	public Map updateIisCompanyList(IisCompanyList iisCompanyList) {
		// 根据编号Id
		IisCompanyList iisCompanyListTemp = this.get(iisCompanyList.getId());
		if(!iisCompanyList.getCompanyName().equals(iisCompanyListTemp.getCompanyName())){
			long count = this.entityCount("companyName", iisCompanyList.getCompanyName());
			if(count > 0){
				logger.info("企业名称已存在");
				return ResultUtil.newError("企业名称已存在!").toMap();
			}
		}
		
		BeanUtil.copyPropertiesIgnoreNull(iisCompanyList,iisCompanyListTemp);
		iisCompanyListTemp.setModifyTime(new Date());
		this.save(iisCompanyListTemp);
		return ResultUtil.newOk("操作成功").toMap(); 
	}
 
	
	/**
	 * 
	 * @Title: delIisCompanyList 
	 * @Description: TODO(删除企业列表（批量）) 
	 * @param idStrs
	 * @return
	 */
	 @Override
	public Map delIisCompanyList(String idStrs) {
		// 处理id集合串
		String[] ids = idStrs.split(",");
		/** 先判断所有ID是否允许删除 **/
		List<String> idslist = new ArrayList<String>();
		for (String id : ids) {
			idslist.add(id);
		}
	 
		this.deleteAll(idslist);
 
		return ResultUtil.newOk("操作成功").toMap();
	}
 
	 /**
	  * 
	  * @Title: queryIisCompanyListById 
	  * @Description: TODO(根据ID查询企业列表信息) 
	  * @param id
	  * @return
	  */
	@Override
	public Map queryIisCompanyListById(String id) {
		IisCompanyList iisCompanyList = this.get(id);
		return ResultUtil.newOk("操作成功").setData(iisCompanyList).toMap();
	}

	/**
	 * 
	 * @Title: queryIisCompanyListList 
	 * @Description: TODO(查询企业列表列表信息) 
	 * @param ue
	 * @return
	 */
	@Override
	public Map queryIisCompanyListList(IisCompanyListUserEntity ue){
		
		SimpleSpecificationBuilder simpbuilder = new SimpleSpecificationBuilder();
		
		if(StringUtil.isNotNullOrEmpty(ue.getContinentCode())){
			simpbuilder.add("continentCode", RestrictionNames.EQ, ue.getContinentCode());
		}
		
		if(StringUtil.isNotNullOrEmpty(ue.getCountryEngName())){
			simpbuilder.add("countryEngName", RestrictionNames.EQ, ue.getCountryEngName());
		}

		if(StringUtil.isNotNullOrEmpty(ue.getIndustryCode())){
			simpbuilder.add("industryCode", RestrictionNames.EQ, ue.getIndustryCode());
		}

		if(StringUtil.isNotNullOrEmpty(ue.getCompanyName())){
			simpbuilder.add("companyName", RestrictionNames.LIKE, ue.getCompanyName());
		}
 
		// 获取数据
		PageEntiry pageEntiry = this.findPage(simpbuilder.getOpers(), ue);
	 
		return ResultUtil.newOk("操作成功").setData(pageEntiry).toMap();
	}

}
