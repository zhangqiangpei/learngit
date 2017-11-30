package com.yirong.iis.user.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yirong.iis.user.dao.IisCorrectDao;
import com.yirong.iis.user.entity.IisCorrect;
import com.yirong.iis.user.service.IisCorrectService;
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
 * 功能描述：修正表service实现类
 *
 * @author
 *         <p>
 *         创建时间 ：2017-11-28 14:52:54
 *         </p>
 *
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
@Service("IisCorrectServiceImpl")
public class IisCorrectServiceImpl extends BaseService<IisCorrect, String>
        implements IisCorrectService {

    /**
     * 日志操作类
     */
    private Logger logger = LoggerFactory
            .getLogger(IisCorrectServiceImpl.class);

    /**
     * dao注入
     */
    @Autowired
    private IisCorrectDao iisCorrectDao;

    /**
     * 功能描述：获取dao操作类
     *
     * @author 林明铁
     *         <p>
     *         创建时间 ：2017-11-28 14:52:54
     *         </p>
     *
     *         <p>
     *         修改历史：(修改人，修改时间，修改原因/内容)
     *         </p>
     */
    @Override
    public IBaseDao<IisCorrect, String> getBaseDao() {
        return iisCorrectDao;
    }

    /**
     * 功能描述：新增修正表
     *
     * @author 林明铁
     *         <p>
     *         创建时间 ：2017-11-28 14:52:54
     *         </p>
     *
     *         <p>
     *         修改历史：(修改人，修改时间，修改原因/内容)
     *         </p>
     * @param iisCorrect
     * @return
     */
    @Override
    public Map saveIisCorrect(IisCorrect iisCorrect){
        // 根据编码及分类ID获取数据（唯一键）
        IisCorrect iisCorrectTemp = this.iisCorrectDao
                .findBySourceContentAndSourceLanguage(
                        iisCorrect.getSourceContent(), iisCorrect.getSourceLanguage());
        String id = iisCorrect.getId();
        if (null == iisCorrectTemp
                || (StringUtil.isNotNullOrEmpty(id) && id.equals(iisCorrectTemp
                .getId()))) {// 该唯一键不存在 或者为“修改操作且修改本身数据”
            id = iisCorrect.getId();
            if (StringUtil.isNotNullOrEmpty(id)) {// 修改
                // 获取数据库对象
                iisCorrectTemp = this.iisCorrectDao.findOne(id);
                // 复制属性
                BeanUtil.copyPropertiesIgnoreNull(iisCorrect, iisCorrectTemp);
                this.save(iisCorrectTemp);
            } else {
                this.save(iisCorrect);
            }
            return ResultUtil.newOk("操作成功").toMap();
        } else {// 该名称及父类ID已存在
            String result = ErrorPromptInfoUtil.getErrorInfo("00601");
            logger.warn(result);
            return ResultUtil.newError(result).toMap();
        }
    }
}
