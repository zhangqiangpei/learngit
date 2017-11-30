package com.yirong.iis.user.service.impl;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.awaken.core.util.BeanUtil;
import com.yirong.awaken.core.util.ResultUtil;
import com.yirong.commons.cache.eif.RedisCacheEif;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.commons.util.datatype.StringUtil;
import com.yirong.commons.util.error.ErrorPromptInfoUtil;
import com.yirong.iis.user.dao.IisMyFavoritesDao;
import com.yirong.iis.user.entity.IisMyFavorites;
import com.yirong.iis.user.service.IisMyFavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 功能描述：报告表service实现类
 *
 * @author 林明铁
 *         <p>
 *         创建时间 ：2017-11-09 10:00:09
 *         </p>
 *
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
@Service("IisMyFavoritesServiceImpl")
public class IisMyFavoritesServiceImpl extends BaseService<IisMyFavorites, String>
        implements IisMyFavoritesService {

    /**
     * 日志操作类
     */
    private Logger logger = LoggerFactory
            .getLogger(IisReportServiceImpl.class);

    /**
     * dao注入
     */
    @Autowired
    private IisMyFavoritesDao iisMyFavoritesDao;

    /**
     * 功能描述：获取dao操作类
     *
     * @author 林明铁
     *         <p>
     *         创建时间 ：2017-11-09 10:00:09
     *         </p>
     *
     *         <p>
     *         修改历史：(修改人，修改时间，修改原因/内容)
     *         </p>
     */
    @Override
    public IBaseDao<IisMyFavorites, String> getBaseDao() {
        return iisMyFavoritesDao;
    }

    /**
     * 功能描述：新增我的收藏
     *
     * @author 林明铁
     *         <p>
     *         创建时间 ：2017-11-09 10:00:09
     *         </p>
     *
     *         <p>
     *         修改历史：(修改人，修改时间，修改原因/内容)
     *         </p>
     * @return
     */
    @Override
    public Map saveIisMyFavorites(IisMyFavorites iisMyFavorites) {
        // 根据编码及分类ID获取数据（唯一键）
        IisMyFavorites iisMyFavoritesTemp = this.iisMyFavoritesDao
                .findByCreatorAndObjId(iisMyFavorites.getCreator(), iisMyFavorites.getObjId());
        String id = iisMyFavorites.getId();
        if (null == iisMyFavoritesTemp
                || (StringUtil.isNotNullOrEmpty(id) && id.equals(iisMyFavoritesTemp.getId()))) {
            // 该唯一键不存在 或者为“修改操作且修改本身数据”
            id = iisMyFavorites.getId();
            if (StringUtil.isNotNullOrEmpty(id)) {// 修改
                // 获取数据库对象
                iisMyFavoritesTemp = this.iisMyFavoritesDao.findOne(id);
                // 复制属性
                BeanUtil.copyPropertiesIgnoreNull(iisMyFavorites, iisMyFavoritesTemp);
                this.save(iisMyFavoritesTemp);
            } else {
                this.save(iisMyFavorites);
            }
            return ResultUtil.newOk("操作成功").toMap();
        } else {// 该名称及父类ID已存在
            String result = ErrorPromptInfoUtil.getErrorInfo("00401");
            logger.warn(result);
            return ResultUtil.newError(result).toMap();
        }
    }

    /**
     * 功能描述：根据ID查询我的收藏
     *
     * @author 林明铁
     *         <p>
     *         创建时间 ：2017-11-09 10:00:09
     *         </p>
     *
     *         <p>
     *         修改历史：(修改人，修改时间，修改原因/内容)
     *         </p>
     * @return
     */
    @Override
    public Map queryIisMyFavoritesByCreatorAndObjId(String creator, String id) {
        StringBuffer sql = new StringBuffer();
        List<Object> param = new ArrayList<Object>();
        sql.append("SELECT ID AS id ");
        sql.append("FROM IIS_MY_FAVORITES ");
        sql.append("WHERE OBJ_ID = ? ");
        param.add(id);
        sql.append("AND CREATOR = ? ");
        param.add(creator);
        List<Map<String, Object>> mapList = this.exeNativeQueryMap(sql.toString(), param);
        return ResultUtil.newOk("操作成功").setData(mapList).toMap();
    }

    /**
     * 功能描述：删除我的收藏
     *
     * @author 林明铁
     *         <p>
     *         创建时间 ：2017-11-09 10:00:09
     *         </p>
     *
     *         <p>
     *         修改历史：(修改人，修改时间，修改原因/内容)
     *         </p>
     * @return
     */
    @Override
    public Map delIisMyFavorite(String idStrs, String tokenId) {
        // 处理id集合串
        String[] ids = idStrs.split(",");
        String creator = RedisCacheEif.hget(tokenId, "id");
        /** 先判断所有ID是否允许删除 **/
        StringBuffer sb = new StringBuffer();
        for (String id : ids) {
            // 判断ID是否存在
            IisMyFavorites iisMyFavoritesTemplate = this.iisMyFavoritesDao.findByCreatorAndObjId(creator, id);
            if (null == iisMyFavoritesTemplate) {// 不存在直接跳出循环
                String promptInfo = "不存在ID：" + id;
                sb.append(ErrorPromptInfoUtil.getErrorInfo("00402", promptInfo));
                break;
            }
        }
        // 处理业务
        if (StringUtil.isNullOrEmpty(sb.toString())) {// 所有数据均允许删除
            // 将id循环拼装成序列化集合
            for (String id : ids) {
                IisMyFavorites iisMyFavoritesTemplate = this.iisMyFavoritesDao.findByCreatorAndObjId(creator, id);
                this.iisMyFavoritesDao.delete(iisMyFavoritesTemplate);// 物理删除
            }
            return ResultUtil.newOk("操作成功").toMap();
        } else {// 不允许删除
            logger.warn(sb.toString());
            return ResultUtil.newError(sb.toString()).toMap();
        }
    }
}
