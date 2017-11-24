package com.yirong.iis.user.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yirong.commons.es.constant.EsMatchNames;
import com.yirong.commons.es.eif.EsClientEif;
import com.yirong.commons.util.order.EsSelect;
import com.yirong.commons.util.order.Where;
import com.yirong.iis.user.constant.esConstants;
import com.yirong.iis.user.dao.IisNewsDao;
import com.yirong.iis.user.entity.IisNews;
import com.yirong.iis.user.service.IisNewsService;
import com.yirong.iis.user.userentity.IisNewsUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.commons.util.entity.PageEntiry;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.awaken.core.util.ResultUtil;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.commons.util.datatype.StringUtil;

/**
 * 功能描述：新闻表service实现类
 *
 * @author 林明铁
 *         <p>
 *         创建时间 ：2017-11-21 15:43:12
 *         </p>
 *
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
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
     * 功能描述：获取dao操作类
     *
     * @author 林明铁
     *         <p>
     *         创建时间 ：2017-11-21 15:43:12
     *         </p>
     *
     *         <p>
     *         修改历史：(修改人，修改时间，修改原因/内容)
     *         </p>
     */
    @Override
    public IBaseDao<IisNews, String> getBaseDao() {
        return iisNewsDao;
    }


}
