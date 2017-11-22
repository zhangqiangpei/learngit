package com.yirong.iis.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.awaken.core.service.impl.BaseService;
import com.yirong.awaken.core.util.ResultUtil;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.commons.util.datatype.StringUtil;
import com.yirong.iis.user.dao.IisDataClassifyDao;
import com.yirong.iis.user.entity.IisDataClassify;
import com.yirong.iis.user.service.IisDataClassifyService;

 /**
 * 功能描述：数据分类表service实现类
 * 
 * @author 
 *         <p>
 *         创建时间 ：2017-11-22 09:43:03
 *         </p>
 * 
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@SuppressWarnings("rawtypes")
@Service("IisDataClassifyServiceImpl")
public class IisDataClassifyServiceImpl extends BaseService<IisDataClassify, String>
		implements IisDataClassifyService {

	/**
	 * 日志操作类
	 */
	private Logger logger = LoggerFactory.getLogger(IisDataClassifyServiceImpl.class);

	/**
	 * dao注入
	 */
	@Autowired
	private IisDataClassifyDao iisDataClassifyDao;

	 /**
	 * 功能描述：获取dao操作类
	 * 
	 * @author 
	 *         <p>
	 *         创建时间 ：2017-11-22 09:43:03
	 *         </p>
	 * 
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	@Override
	public IBaseDao<IisDataClassify, String> getBaseDao() {
		return iisDataClassifyDao;
	}

	@Override
	public Map getFirstClassify() {
		List<IisDataClassify> list = iisDataClassifyDao.getFirstClassify();
		return ResultUtil.newOk("获取一级分类成功!").setData(list).toMap();
	}

	@Override
	public Map getSecondClassifyAndInfo(String infoName) {
		StringBuffer sql = new StringBuffer("SELECT  ");
		sql.append("	classify.id \"id\",classify.parent_id \"parentId\", ");
		sql.append("	classify.NAME \"name\",classify.priority \"priority\", ");
		sql.append("	info.id \"infoId\",info.name \"infoName\" ");
		sql.append(" FROM IIS_DATA_CLASSIFY classify ");
		sql.append(" LEFT JOIN IIS_INDICATOR_INFO info ON classify.id = info.DATA_CLASSIFY_ID ");
		sql.append(" WHERE classify.PARENT_ID is not null");
		if(StringUtil.isNotNullOrEmpty(infoName)){
			sql.append("	and info.NAME like '%"+infoName+"%' ");
		}
		sql.append("	   order by classify.PARENT_ID,classify.PRIORITY");
		
		//返回map
		Map<String,Object> map = new HashMap<String,Object>();
		List<IisDataClassify> secondClassifyList = iisDataClassifyDao.getSecondClassify();
		
		List<Map<String,Object>> list = this.exeNativeQueryMap(sql.toString(), null);
		
		JSONArray firstArr = new JSONArray();
		String upParentId = null;
		//遍历组装数据
		for(IisDataClassify classify : secondClassifyList){
			if(StringUtil.isNullOrEmpty(upParentId)){
				upParentId = classify.getParentId();
				map.put(upParentId, firstArr);
			}
			
			JSONObject secondJson = new JSONObject();
			JSONArray  arr = new JSONArray();
			for(Map<String,Object> se : list){
				if(classify.getId().equals(se.get("id").toString())){
					JSONObject json = new JSONObject();
					json.put("infoId", se.get("infoId").toString());
					json.put("infoName", se.get("infoName").toString());
					arr.add(json);
				}
			}
			secondJson.put("id", classify.getId());
			secondJson.put("name", classify.getName());
			secondJson.put("info", arr);
			
			if(arr.size() < 1){
				continue;
			}
			
			if(!map.containsKey(classify.getParentId())){
				map.put(upParentId, firstArr);
				firstArr = new JSONArray();
				firstArr.add(secondJson);
				upParentId = classify.getParentId();
			}else{
				firstArr.add(secondJson);
			}
		}
		
		if(StringUtil.isNotNullOrEmpty(upParentId)){
			map.put(upParentId, firstArr);
		}
		
		return ResultUtil.newOk("获取二级分类成功!").setData(map).toMap();
	}

}
