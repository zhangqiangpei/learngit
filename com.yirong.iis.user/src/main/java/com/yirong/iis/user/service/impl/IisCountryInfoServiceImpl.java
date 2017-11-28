package com.yirong.iis.user.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yirong.iis.user.dao.IisCountryInfoDao;
import com.yirong.iis.user.entity.IisCountryInfo;
import com.yirong.iis.user.service.IisCountryInfoService;
import com.yirong.iis.user.userentity.IisCountryInfoUserEntity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.awaken.core.dao.specification.RestrictionNames;
import com.yirong.awaken.core.dao.specification.SimpleSpecificationBuilder;
import com.yirong.commons.util.entity.PageEntiry;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.awaken.core.util.BeanUtil;
import com.yirong.awaken.core.util.ResultUtil;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.commons.util.datatype.StringUtil;
import com.yirong.commons.util.error.ErrorPromptInfoUtil;
import com.yirong.commons.util.order.Order;

/**
 * 功能描述：国家信息表service实现类
 *
 * @author 林明铁
 *         <p>
 *         创建时间 ：2017-11-21 15:44:24
 *         </p>
 *
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
@Service("IisCountryInfoServiceImpl")
public class IisCountryInfoServiceImpl extends BaseService<IisCountryInfo, String>
        implements IisCountryInfoService {

    /**
     * 日志操作类
     */
    private Logger logger = LoggerFactory
            .getLogger(IisCountryInfoServiceImpl.class);

    /**
     * dao注入
     */
    @Autowired
    private IisCountryInfoDao iisCountryInfoDao;

    /**
     * 功能描述：获取dao操作类
     *
     * @author 林明铁
     *         <p>
     *         创建时间 ：2017-11-21 15:44:24
     *         </p>
     *
     *         <p>
     *         修改历史：(修改人，修改时间，修改原因/内容)
     *         </p>
     */
    @Override
    public IBaseDao<IisCountryInfo, String> getBaseDao() {
        return iisCountryInfoDao;
    }

	@Override
	public Map queryList(IisCountryInfoUserEntity para) {
		
		List<Object> param = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT t.ID \"id\", t.ISO2CODE \"iso2code\",t.ISOCODE \"isocode\",t.ENGLISH_NAME \"englishName\",t.CHINESE_NAME \"chineseName\",t.CONTINENT_CODE \"continentCode\"");
		sql.append(" FROM SY_COUNTRY t ");
		sql.append("where 1=1 ");
		if(StringUtil.isNotNullOrEmpty(para.getChineseName())){
			sql.append(" and ( t.ENGLISH_NAME LIKE ? OR t.CHINESE_NAME LIKE ? )");
			param.add("%"+para.getEnglishName()+"%");
			param.add("%"+para.getChineseName()+"%");
		}
		if(StringUtil.isNotNullOrEmpty(para.getContinentCode())){
			sql.append(" and t.CONTINENT_CODE = ?");
			param.add(para.getContinentCode());
		}
		// 获取数据
		List<Map<String,Object>> result =   this.exeNativeQueryMap(sql.toString(), param);
		
		return ResultUtil.newOk("查询成功!").setData(result).toMap();
	}

	@Override
	public Map queryIisCountryInfo(IisCountryInfoUserEntity para) {
		if(StringUtil.isNullOrEmpty(para.getEnglishName()) && StringUtil.isNullOrEmpty(para.getId())){
			return ResultUtil.newError("参数错误!").toMap();
		}
		List<Object> param = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT t.ISO2CODE \"iso2code\",t.ISOCODE \"isocode\",t.ENGLISH_NAME \"englishName\",t.CHINESE_NAME \"chineseName\",t.CONTINENT_CODE \"continentCode\",");
		sql.append("DATE_FORMAT(t.MODIFY_TIME,'%Y-%m-%d %H:%i:%s') \"modifyTime\",");
		sql.append(" (SELECT d.NAME FROM SYS_DiCTIONARY d WHERE t.CONTINENT_CODE = d.code) \"continentName\"");
		sql.append(" FROM SY_COUNTRY t ");
		sql.append("where 1=1 ");
		if(StringUtil.isNotNullOrEmpty(para.getEnglishName())){
			sql.append(" and t.ENGLISH_NAME  = ?");
			param.add(para.getEnglishName());
		}
		Map<String,Object> result = this.exeSqlMap(sql.toString(), param);
		return ResultUtil.newOk("查询成功!").setData(result).toMap();
	}

	/**
	 * 
	 * @Title: queryCountryS 
	 * @Description: TODO(查询国家列表) 
	 * @param para
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map queryCountrys(IisCountryInfoUserEntity para) {
		
		SimpleSpecificationBuilder simpbuilder = new SimpleSpecificationBuilder();
		
		if(StringUtil.isNotNullOrEmpty(para.getContinentCode())){
			simpbuilder.add("continentCode", RestrictionNames.EQ, para.getContinentCode());
		}
 
		List<IisCountryInfo>  list = this.findByProperty(simpbuilder.getOpers(), Order.asc("englishName"));
 
		return ResultUtil.newOk("查询成功!").setData(list).toMap();
	}
}
