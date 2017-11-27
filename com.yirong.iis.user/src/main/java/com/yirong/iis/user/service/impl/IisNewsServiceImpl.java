package com.yirong.iis.user.service.impl;

import java.util.ArrayList;
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
import com.yirong.iis.user.dao.IisNewsDao;
import com.yirong.iis.user.entity.IisNews;
import com.yirong.iis.user.service.IisNewsService;
import com.yirong.iis.user.userentity.IisNewsUserEntity;

 
/**
 * 
 * @ClassName: IisNewsServiceImpl  
 * @Description: TODO(新闻表service实现类) 
 * @author liny
 * @date 2017年11月24日 上午10:50:41 
 * @version V0.1
 */
@SuppressWarnings("rawtypes")
@Service("IisNewsServiceImpl")
public class IisNewsServiceImpl extends BaseService<IisNews, String>
        implements IisNewsService {

    /**
     * 日志操作类
     */
    private Logger logger = LoggerFactory
            .getLogger(IisNewsServiceImpl.class);

    /**
     * dao注入
     */
    @Autowired
    private IisNewsDao iisNewsDao;

    
    /**
     * 
     * @Title: getBaseDao 
     * @Description: TODO(liny) 
     * @return
     */
    @Override
    public IBaseDao<IisNews, String> getBaseDao() {
        return iisNewsDao;
    }

   

    /**
     * @author liny
     * @Title: queryIisNewsList 
     * @Description: TODO(查询新闻表列表信息) 
     * @param ue
     * @return Map
     */
	@SuppressWarnings("unchecked")
	@Override
	public Map queryIisNewsList(IisNewsUserEntity ue) {
		SimpleSpecificationBuilder simpbuilder = new SimpleSpecificationBuilder();
		
		if(StringUtil.isNotNullOrEmpty(ue.getType())){
			simpbuilder.add("type", RestrictionNames.EQ, ue.getType());
		}
		
		if(StringUtil.isNotNullOrEmpty(ue.getCompanyName())){
			simpbuilder.add("companyName", RestrictionNames.LIKE, ue.getCompanyName());
		}
		
		if(StringUtil.isNotNullOrEmpty(ue.getCountryChnName())){
			simpbuilder.add("countryChnName", RestrictionNames.LIKE, ue.getCountryChnName());
		}
		
		if(StringUtil.isNotNullOrEmpty(ue.getCountryEngName())){
			simpbuilder.add("countryEngName", RestrictionNames.LIKE, ue.getCountryEngName());
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


	/**
	 * 
	 * @Title: queryIisNewsCompanyList 
	 * @Description: TODO(查询关注企业新闻列表信息)  
	 * @param ue
	 * @return
	 */
	@Override
	public Map queryIisNewsCompanyList(IisNewsUserEntity ue) {
		// 拼装查询sql
 
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT N.ID,N.TITLE,N.TITLE_EN,N.TITLE_CHN,N.SUMMARY,N.SOURCE,N.COMPANY_NAME,N.COUNTRY_ENG_NAME,N.COUNTRY_CHN_NAME,TO_CHAR(N.CREATE_TIME,'YYYY-MM-DD HH24:MI:SS') AS CREATE_TIME");
		sql.append("  FROM IIS_NEWS N,IIS_COMPANY_LIST C WHERE N.COMPANY_NAME = C.COMPANY_NAME AND C.TYPE='0' ORDER BY N.CREATE_TIME DESC");
		 
		// 获取数据
		PageEntiry pageEntiry = this.findPageSQLMap(sql.toString(), null, null, ue);
		return ResultUtil.newOk("操作成功").setData(pageEntiry).toMap();
	}
 

	@Override
	public Map queryIisNewsById(String id) {
		IisNews entity =  this.get(id);
		return ResultUtil.newOk("操作成功").setData(entity).toMap();
	}

}
