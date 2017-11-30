package com.yirong.iis.user.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yirong.iis.user.dao.IisProjectTrackDetailDao;
import com.yirong.iis.user.entity.IisProjectTrackDetail;
import com.yirong.iis.user.service.IisProjectTrackDetailService;
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
 * 功能描述：跟踪明细表service实现类
 *
 * @author
 *         <p>
 *         创建时间 ：2017-11-28 15:32:14
 *         </p>
 *
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
@Service("IisProjectTrackDetailServiceImpl")
public class IisProjectTrackDetailServiceImpl extends BaseService<IisProjectTrackDetail, String>
        implements IisProjectTrackDetailService {

    /**
     * 日志操作类
     */
    private Logger logger = LoggerFactory
            .getLogger(IisProjectTrackDetailServiceImpl.class);

    /**
     * dao注入
     */
    @Autowired
    private IisProjectTrackDetailDao iisProjectTrackDetailDao;

    /**
     * 功能描述：获取dao操作类
     *
     * @author 林明铁
     *         <p>
     *         创建时间 ：2017-11-28 15:32:14
     *         </p>
     *
     *         <p>
     *         修改历史：(修改人，修改时间，修改原因/内容)
     *         </p>
     */
    @Override
    public IBaseDao<IisProjectTrackDetail, String> getBaseDao() {
        return iisProjectTrackDetailDao;
    }

    /**
     * 功能描述：新增跟踪明细表
     *
     * @author 林明铁
     *         <p>
     *         创建时间 ：2017-11-28 15:32:14
     *         </p>
     *
     *         <p>
     *         修改历史：(修改人，修改时间，修改原因/内容)
     *         </p>
     * @param iisProjectTrackDetail
     * @return
     */
    @Override
    public Map saveIisProjectTrackDetail(IisProjectTrackDetail iisProjectTrackDetail){
        // 根据编码及分类ID获取数据（唯一键）
        IisProjectTrackDetail iisProjectTrackDetailTemp = this.iisProjectTrackDetailDao
                .findByProIdAndObjId(iisProjectTrackDetail.getProId(), iisProjectTrackDetail.getObjId());
        String id = iisProjectTrackDetail.getId();
        if (null == iisProjectTrackDetailTemp
                || (StringUtil.isNotNullOrEmpty(id) && id.equals(iisProjectTrackDetailTemp
                .getId()))) {// 该唯一键不存在 或者为“修改操作且修改本身数据”
            id = iisProjectTrackDetail.getId();
            if (StringUtil.isNotNullOrEmpty(id)) {// 修改
                // 获取数据库对象
                iisProjectTrackDetailTemp = this.iisProjectTrackDetailDao.findOne(id);
                // 复制属性
                BeanUtil.copyPropertiesIgnoreNull(iisProjectTrackDetail, iisProjectTrackDetailTemp);
                this.save(iisProjectTrackDetailTemp);
            } else {
                this.save(iisProjectTrackDetail);
            }
            return ResultUtil.newOk("操作成功").toMap();
        } else {// 该名称及父类ID已存在
            String result = ErrorPromptInfoUtil.getErrorInfo("00501");
            logger.warn(result);
            return ResultUtil.newError(result).toMap();
        }
    }
}
