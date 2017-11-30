package com.yirong.iis.user.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yirong.iis.user.dao.IisProjectTrackDao;
import com.yirong.iis.user.entity.IisProjectTrack;
import com.yirong.iis.user.service.IisProjectTrackService;
import com.yirong.iis.user.userentity.IisProjectTrackUserEntity;
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
 * 功能描述：追踪表service实现类
 *
 * @author
 *         <p>
 *         创建时间 ：2017-11-28 15:31:27
 *         </p>
 *
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
@Service("IisProjectTrackServiceImpl")
public class IisProjectTrackServiceImpl extends BaseService<IisProjectTrack, String>
        implements IisProjectTrackService {

    /**
     * 日志操作类
     */
    private Logger logger = LoggerFactory
            .getLogger(IisProjectTrackServiceImpl.class);

    /**
     * dao注入
     */
    @Autowired
    private IisProjectTrackDao iisProjectTrackDao;

    /**
     * 功能描述：获取dao操作类
     *
     * @author 林明铁
     *         <p>
     *         创建时间 ：2017-11-28 15:31:27
     *         </p>
     *
     *         <p>
     *         修改历史：(修改人，修改时间，修改原因/内容)
     *         </p>
     */
    @Override
    public IBaseDao<IisProjectTrack, String> getBaseDao() {
        return iisProjectTrackDao;
    }

    /**
     * 功能描述：新增追踪表
     *
     * @author 林明铁
     *         <p>
     *         创建时间 ：2017-11-28 15:31:27
     *         </p>
     *
     *         <p>
     *         修改历史：(修改人，修改时间，修改原因/内容)
     *         </p>
     * @param iisProjectTrack
     * @return
     */
    @Override
    public Map saveIisProjectTrack(IisProjectTrack iisProjectTrack){
        // 根据编码及分类ID获取数据（唯一键）
        IisProjectTrack iisProjectTrackTemp = this.iisProjectTrackDao
                .findByCreatorAndTypeCodeAndProjectName(
                        iisProjectTrack.getCreator(), iisProjectTrack.getTypeCode(), iisProjectTrack.getProjectName());
        String id = iisProjectTrack.getId();
        if (null == iisProjectTrackTemp
                || (StringUtil.isNotNullOrEmpty(id) && id.equals(iisProjectTrackTemp
                .getId()))) {// 该唯一键不存在 或者为“修改操作且修改本身数据”
            id = iisProjectTrack.getId();
            if (StringUtil.isNotNullOrEmpty(id)) {// 修改
                // 获取数据库对象
                iisProjectTrackTemp = this.iisProjectTrackDao.findOne(id);
                // 复制属性
                BeanUtil.copyPropertiesIgnoreNull(iisProjectTrack, iisProjectTrackTemp);
                this.save(iisProjectTrackTemp);
            } else {
                this.save(iisProjectTrack);
            }
            return ResultUtil.newOk("操作成功").toMap();
        } else {// 该名称及父类ID已存在
            String result = ErrorPromptInfoUtil.getErrorInfo("00201");
            logger.warn(result);
            return ResultUtil.newError(result).toMap();
        }
    }
    /**
     * 功能描述：查询追踪表列表信息
     *
     * @author 林明铁
     *         <p>
     *         创建时间 ：2017-11-28 15:31:27
     *         </p>
     *
     *         <p>
     *         修改历史：(修改人，修改时间，修改原因/内容)
     *         </p>
     * @param ue
     * @return
     */
    @Override
    public Map queryIisProjectTrackList(IisProjectTrackUserEntity ue){
        // 拼装查询sql
        List<Object> param = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT ID, ");
        sql.append("PROJECT_NAME ");
        sql.append("FROM IIS_PROJECT_TRACK ");
        sql.append("WHERE 1=1 ");
        // 标准编码
        if (StringUtil.isNotNullOrEmpty(ue.getTypeCode())) {
            sql.append("AND TYPE_CODE = ? ");
            param.add(ue.getTypeCode());
        }
        // 获取数据
        PageEntiry pageEntiry = this.findPageSQLMap(sql.toString(), param,
                null, ue);
        return ResultUtil.newOk("操作成功")
                .setData(pageEntiry).toMap();
    }
}
