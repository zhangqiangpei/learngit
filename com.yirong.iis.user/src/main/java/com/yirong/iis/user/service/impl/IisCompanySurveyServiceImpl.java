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
import com.yirong.commons.util.error.ErrorPromptInfoUtil;
import com.yirong.iis.user.dao.IisCompanySurveyDao;
import com.yirong.iis.user.entity.IisCompanySurvey;
import com.yirong.iis.user.service.IisCompanySurveyService;
import com.yirong.iis.user.userentity.IisCompanySurveyUserEntity;

 
/**
 * 
 * @ClassName: IisCompanySurveyServiceImpl  
 * @Description: TODO(企业概况表service实现类) 
 * @author liny
 * @date 2017年11月22日 上午11:24:48 
 * @version V0.1
 */
@SuppressWarnings("rawtypes")
@Service("IisCompanySurveyServiceImpl")
public class IisCompanySurveyServiceImpl extends BaseService<IisCompanySurvey, String>
		implements IisCompanySurveyService {

	/**
	 * 日志操作类
	 */
	private Logger logger = LoggerFactory
			.getLogger(IisCompanySurveyServiceImpl.class);

	/**
	 * dao注入
	 */
	@Autowired
	private IisCompanySurveyDao iisCompanySurveyDao;
 
	/**
	 * 
	 * @Title: getBaseDao 
	 * @Description: TODO(获取dao操作类) 
	 * @return
	 */
	@Override
	public IBaseDao<IisCompanySurvey, String> getBaseDao() {
		return iisCompanySurveyDao;
	}

 
	/**
	 * 
	 * @Title: saveIisCompanySurvey 
	 * @Description: TODO(新增企业概况表) 
	 * @param iisCompanySurvey
	 * @return
	 */
	@Override
	public Map saveIisCompanySurvey(IisCompanySurvey iisCompanySurvey){
		// 根据编码及分类ID获取数据（唯一键）
		this.save(iisCompanySurvey);
		
		return ResultUtil.newOk("操作成功").toMap();
	}

 
	/**
	 * 
	 * @Title: updateIisCompanySurvey 
	 * @Description: TODO(修改企业概况表) 
	 * @param iisCompanySurvey
	 * @return
	 */
	@Override
	public Map updateIisCompanySurvey(IisCompanySurvey iisCompanySurvey) {
		// 根据编号Id
		IisCompanySurvey iisCompanySurveyTemp = this.get(iisCompanySurvey.getId());
		if (null == iisCompanySurveyTemp) {// 未查询到任何数据
			String promptInfo = "不存在ID：" + iisCompanySurvey.getId();
			String result = ErrorPromptInfoUtil.getErrorInfo("00202",
					promptInfo);
			logger.warn(result);
			return ResultUtil.newError(result).toMap();
		} else {// 有该数据
			BeanUtil.copyPropertiesIgnoreNull(iisCompanySurvey,iisCompanySurveyTemp);
			iisCompanySurveyTemp.setModifyTime(new Date());
			this.save(iisCompanySurveyTemp);
			return ResultUtil.newOk("操作成功").toMap();
		}
	}
 
	/**
	 * 
	 * @Title: delIisCompanySurvey 
	 * @Description: TODO(删除企业概况表（批量）) 
	 * @param idStrs
	 * @return
	 */
	 @Override
	public Map delIisCompanySurvey(String idStrs) {
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
	  * @Title: queryIisCompanySurveyById 
	  * @Description: TODO(根据ID查询企业概况表信息) 
	  * @param id
	  * @return
	  */
	@Override
	public Map queryIisCompanySurveyById(String id) {
		IisCompanySurvey companySurvey = this.get(id);
		return ResultUtil.newOk("操作成功").setData(companySurvey).toMap();
	}

 
	/**
	 * 
	 * @Title: queryIisCompanySurveyList 
	 * @Description: TODO(查询企业概况表列表信息) 
	 * @param ue
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map queryIisCompanySurveyList(IisCompanySurveyUserEntity ue){
		SimpleSpecificationBuilder simpbuilder = new SimpleSpecificationBuilder();
		
		if(StringUtil.isNotNullOrEmpty(ue.getCompanyId())){
			simpbuilder.add("companyId", RestrictionNames.EQ, ue.getCompanyId());
		}
		
		if(StringUtil.isNotNullOrEmpty(ue.getCompanyName())){
			simpbuilder.add("companyName", RestrictionNames.LIKE, ue.getCompanyName());
		}
 
		// 获取数据
		PageEntiry pageEntiry = this.findPage(simpbuilder.getOpers(), ue);
	 
		return ResultUtil.newOk("操作成功").setData(pageEntiry).toMap();
	}

}
