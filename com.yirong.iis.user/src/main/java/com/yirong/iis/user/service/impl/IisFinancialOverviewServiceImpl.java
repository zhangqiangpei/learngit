package com.yirong.iis.user.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.awaken.core.util.ResultUtil;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.commons.util.datatype.StringUtil;
import com.yirong.commons.util.entity.PageEntiry;
import com.yirong.iis.user.dao.IisFinancialOverviewDao;
import com.yirong.iis.user.entity.IisFinancialOverview;
import com.yirong.iis.user.service.IisFinancialOverviewService;
import com.yirong.iis.user.userentity.IisFinancialOverviewUserEntity;

 /**
 * 功能描述：企业财务总览service实现类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-22 17:21:53
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
@Service("IisFinancialOverviewServiceImpl")
public class IisFinancialOverviewServiceImpl extends BaseService<IisFinancialOverview, String>
		implements IisFinancialOverviewService {

	/**
	 * 日志操作类
	 */
	private Logger logger = LoggerFactory
			.getLogger(IisFinancialOverviewServiceImpl.class);

	/**
	 * dao注入
	 */
	@Autowired
	private IisFinancialOverviewDao iisFinancialOverviewDao;

	 /**
	 * 功能描述：获取dao操作类
	 * 
	 * @author 林明铁
	 *         <p>
	 *         创建时间 ：2017-11-22 17:21:53
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	@Override
	public IBaseDao<IisFinancialOverview, String> getBaseDao() {
		return iisFinancialOverviewDao;
	}

 
	/**
	 * 
	 * @Title: queryIisFinancialOverviewList 
	 * @Description: TODO(查询企业财务总览列表信息) 
	 * @param ue
	 * @return
	 */
	@Override
	public Map queryIisFinancialOverviewList(IisFinancialOverviewUserEntity ue){
		// 拼装查询sql
		List<Object> param = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT C.ID,C.COMPANY_NAME,C.COMPANY_CHN_NAME,O.GROSS_INCOME,O.NET_PROFIT,O.TOTAL_ASSETS,O.TOTAL_LIABILITIES,O.CAPITAL_EXPENDITURE,O.MARKET_VALUE,O.PRICE_OF_STOCK,O.PROFIT,O.EPS,O.A_BONUS,O.PB,O.PE,O.GRADE ");
		sql.append(" FROM  IIS_FINANCIAL_OVERVIEW O,IIS_COMPANY_LIST C WHERE O.COMPANY_ID = C.ID");
		if (StringUtil.isNotNullOrEmpty(ue.getContinentCode())) {
			sql.append(" AND C.CONTINENT_CODE = ?");
			param.add(ue.getContinentCode());
		}
		
		if (StringUtil.isNotNullOrEmpty(ue.getCountryEngName())) {
			sql.append(" AND C.COUNTRY_ENG_NAME = ?");
			param.add(ue.getCountryEngName());
		}
		
		if (StringUtil.isNotNullOrEmpty(ue.getIndustryCode())) {
			sql.append(" AND C.INDUSTRY_CODE = ?");
			param.add(ue.getIndustryCode());
		}
		
		if (StringUtil.isNotNullOrEmpty(ue.getContinentCode())) {
			sql.append(" AND C.CONTINENT_CODE = ?");
			param.add(ue.getContinentCode());
		}
		
		if (StringUtil.isNotNullOrEmpty(ue.getKeyWord())) {
			sql.append(" AND C.COMPANY_NAME like ?");
			param.add("%"+ue.getKeyWord()+"%");
		}
		
		if (StringUtil.isNotNullOrEmpty(ue.getFrequency())) {
			sql.append(" AND O.FREQUENCY =?");
			param.add(ue.getFrequency());
		}
		
		if (StringUtil.isNotNullOrEmpty(ue.getStartDt())) {
			sql.append(" AND TO_CHAR(O.CREATE_TIME,'YYYYMMDD') >= ?");
			param.add(ue.getStartDt());
		}
		
		if (StringUtil.isNotNullOrEmpty(ue.getEndDt())) {
			sql.append(" AND TO_CHAR(O.CREATE_TIME,'YYYYMMDD') <= ?");
			param.add(ue.getEndDt());
		}
		// 获取数据
		PageEntiry pageEntiry = this.findPageSQLMap(sql.toString(), param,null, ue);
		return ResultUtil.newOk("操作成功").setData(pageEntiry).toMap();
	}

}
