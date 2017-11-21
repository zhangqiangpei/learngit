package com.yirong.iis.user.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yirong.iis.user.dao.IisCountryInfoDao;
import com.yirong.iis.user.entity.IisCountryInfo;
import com.yirong.iis.user.service.IisCountryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.commons.util.entity.PageEntiry;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.awaken.core.util.BeanUtil;
import com.yirong.awaken.core.util.ResultUtil;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.commons.util.datatype.StringUtil;
import com.yirong.commons.util.error.ErrorPromptInfoUtil;

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
}
