package com.yirong.iis.mm.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.awaken.core.util.ResultUtil;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.commons.util.datatype.StringUtil;
import com.yirong.commons.util.entity.PageEntiry;
import com.yirong.commons.util.error.ErrorPromptInfoUtil;
import com.yirong.iis.mm.dao.IisCompanyListDao;
import com.yirong.iis.mm.entity.IisCompanyList;
import com.yirong.iis.mm.service.IisCompanyListService;
import com.yirong.iis.mm.userentity.IisCompanyListUserEntity;

/**
 * 
 * 功能描述:企业列表Service实现类
 *
 * @author 薛雅芳
 *         <p>
 *         修改时间:2017年11月28日下午3:47:09
 *         </p>
 *         
 *         <p>
 *         修改历史:(修改人,修改时间,修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
@Service("IisCompanyListServiceImpl")
public class IisCompanyListServiceImpl extends BaseService<IisCompanyList, String> implements IisCompanyListService {

	/**
     * 日志操作类
     */
    private Logger logger = LoggerFactory
            .getLogger(IisCompanyListServiceImpl.class);

    /**
     * dao注入
     */
    @Autowired
    private IisCompanyListDao iisCompanyListDao;
	
    /**
     * 
     * 功能描述：获取dao操作类
     *
     * @author 薛雅芳
     *         <p>
     *         创建时间 ：2017年11月28日下午3:53:05
     *         </p>
     *
     *         <p>
     *         修改历史：(修改人，修改时间，修改原因/内容)
     *         </p>
     * @param paramAll
     * @return
     */
    @Override
    public IBaseDao<IisCompanyList, String> getBaseDao() {
        return iisCompanyListDao;
    }
	
    /**
	 * 
	 * 功能描述：删除企业列表(批量)
	 *
	 * @author 薛雅芳
	 *         <p>
	 *         创建时间 ：2017年11月28日下午3:44:17
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param paramAll
	 * @return
	 */
	@Override
	public Map delIisCompanyList(String idStrs) {
		// 处理id集合串
        String[] ids = idStrs.split(",");
        /** 先判断所有ID是否允许删除 **/
        StringBuffer sb = new StringBuffer();
        for (String id : ids) {
            // 判断ID是否存在
            IisCompanyList iisCompanyList = this.iisCompanyListDao.findOne(id);
            if (null == iisCompanyList) {// 不存在直接跳出循环
                String promptInfo = "不存在ID：" + id;
                sb.append(ErrorPromptInfoUtil.getErrorInfo("00103", promptInfo));
                break;
            }
        }
        // 处理业务
        if (StringUtil.isNullOrEmpty(sb.toString())) {// 所有数据均允许删除
            // 将id循环拼装成序列化集合
            for (String id : ids) {
                this.iisCompanyListDao.delete(id);// 物理删除
            }
            return ResultUtil.newOk("操作成功").toMap();
        } else {// 不允许删除
            logger.warn(sb.toString());
            return ResultUtil.newError(sb.toString()).toMap();
        }
	}

	/**
	 * 
	 * 功能描述：查询企业列表的列表信息
	 *
	 * @author 薛雅芳
	 *         <p>
	 *         创建时间 ：2017年11月28日下午4:19:33
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param paramAll
	 * @return
	 */
	@Override
	public Map queryIisCompanyListList(IisCompanyListUserEntity ue) {
		// 拼装查询sql
        List<Object> param = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT ID \"id\",COMPANY_ID,COMPANY_NAME,COMPANY_CHN_NAME,TEXT_INFO_ENG,TEXT_INFO_CHN,CREATOR,CREATE_TIME,MODIFIER,MODIFY_TIME,");
        sql.append("(SELECT NAME FROM SYS_DICTIONARY WHERE CODE = TYPE) TYPE ");
        sql.append("FROM IIS_COMPANY ");
        sql.append("WHERE 1=1 ");
        
        // 类型
        if (StringUtil.isNotNullOrEmpty(ue.getType())) {
            sql.append("AND TYPE = ?");
            param.add(ue.getType());
        }
        // 公司名称
        if (StringUtil.isNotNullOrEmpty(ue.getCompanyName())) {
            sql.append("AND COMPANY_NAME like ?");
            param.add("%" + ue.getCompanyName() + "%");
        }
        // 公司中文名
        if (StringUtil.isNotNullOrEmpty(ue.getCompanyChnName())) {
            sql.append("AND COMPANY_CHN_NAME like ?");
            param.add("%" + ue.getCompanyChnName() + "%");
        }
     
        // 获取数据
        PageEntiry pageEntiry = this.findPageSQLMap(sql.toString(), param, null, ue);
        return ResultUtil.newOk("操作成功").setData(pageEntiry).toMap();
	}

	/**
	 * 
	 * 功能描述：根据国家类型排除去查找国家
	 *
	 * @author 薛雅芳
	 *         <p>
	 *         创建时间 ：2017年11月30日上午11:51:51
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 * @param paramAll
	 * @return
	 */
	@Override
	public Map queryIisCountryListByTypeId(String typeId) {
		// 拼装查询sql
        List<Object> param = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer();
		sql.append(
				"SELECT ID \"key\",COMPANY_NAME \"label\" FROM SY_COMPANY WHERE ID NOT IN(SELECT ID FROM IIS_COMPANY WHERE TYPE=?)");
		param.add(typeId);
	
        List<Map<String, Object>> list = this.exeNativeQueryMap(sql.toString(), param);
        /*System.err.println("list");
        for (Map<String, Object> map : list) {
        	for (Map.Entry<String, Object> entry : map.entrySet()) {  
        		   System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());  
        		  }  
		}*/
        
        // 获取数据
        Map map = ResultUtil.newOk("操作成功").setData(list).toMap();
    	for (Object object : map.entrySet()) {  
    		  System.out.println(object);
    	}  
		return ResultUtil.newOk("操作成功").setData(list).toMap();
	}

}
