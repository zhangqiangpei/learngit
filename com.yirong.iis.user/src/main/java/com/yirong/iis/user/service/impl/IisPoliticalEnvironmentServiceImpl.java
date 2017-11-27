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
import com.yirong.iis.user.dao.IisPoliticalEnvironmentDao;
import com.yirong.iis.user.entity.IisPoliticalEnvironment;
import com.yirong.iis.user.service.IisPoliticalEnvironmentService;
import com.yirong.iis.user.userentity.IisPoliticalEnvironmentUserEntity;

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
@Service("IisPoliticalEnvironmentServiceImpl")
public class IisPoliticalEnvironmentServiceImpl extends BaseService<IisPoliticalEnvironment, String>
        implements IisPoliticalEnvironmentService {

    /**
     * 日志操作类
     */
    private Logger logger = LoggerFactory
            .getLogger(IisPoliticalEnvironmentServiceImpl.class);

    /**
     * dao注入
     */
    @Autowired
    private IisPoliticalEnvironmentDao iisPoliticalEnvironmentDao;

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
    public IBaseDao<IisPoliticalEnvironment, String> getBaseDao() {
        return iisPoliticalEnvironmentDao;
    }

	@Override
	public Map queryList(IisPoliticalEnvironmentUserEntity psue) {
		if(null == psue ){
			return ResultUtil.newError("参数错误!").toMap();
		}
		List<Object> param = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ID \"id\",COUNTRY_ENG_NAME \"countryEngName\",FIELD_NAME \"fieldName\",FIELD_VALUE \"fieldValue\",DATE_FORMAT(MODIFY_TIME,'%Y-%m-%d %H:%i:%s') \"modifyTime\"");
		sql.append(" FROM IIS_POLITICAL_ENVIRONMENT");
		sql.append(" WHERE 1=1");
		if(StringUtil.isNotNullOrEmpty(psue.getCountryEngName())){
			sql.append(" AND COUNTRY_ENG_NAME = ?");
			param.add(psue.getCountryEngName());
		}
		logger.info("查询成功");
		List<Map<String,Object>> result =  this.exeNativeQueryMap(sql.toString(), param);
		
		return ResultUtil.newOk("查询成功!").setData(result).toMap();	
	}

	
}
