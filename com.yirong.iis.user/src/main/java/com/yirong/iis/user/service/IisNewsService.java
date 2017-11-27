package com.yirong.iis.user.service;

import java.util.Map;

import com.yirong.awaken.core.service.IBaseService;
import com.yirong.iis.user.entity.IisNews;
import com.yirong.iis.user.userentity.IisNewsUserEntity;

 
/**
 * 
 * @ClassName: IisNewsService  
 * @Description: TODO(新闻表service接口) 
 * @author liny
 * @date 2017年11月24日 上午10:53:29 
 * @version V0.1
 */
@SuppressWarnings("rawtypes")
public interface IisNewsService extends IBaseService<IisNews, String> {
 
    /**
     * @author liny
     * @Title: queryIisNewsList 
     * @Description: TODO(查询新闻表列表信息) 
     * @param ue
     * @return Map
     */
    Map queryIisNewsList(IisNewsUserEntity ue);
    
    /**
     * 
     * @Title: queryIisNewsCompanyList 
     * @Description: TODO(查询关注企业新闻列表信息) 
     * @param ue
     * @return Map
     */
    Map queryIisNewsCompanyList(IisNewsUserEntity ue);
    
    /**
     * 
     * @Title: queryIisNewsById 
     * @Description: TODO(根据ID查询新闻表信息) 
     * @param id
     * @return Map
     */
    Map queryIisNewsById(String id);
}
