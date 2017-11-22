package com.yirong.iis.user.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.yirong.awaken.core.dao.IBaseDao;
import com.yirong.iis.user.entity.IisDataClassify;

 /**
 * 功能描述：数据分类表dao接口
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
public interface IisDataClassifyDao extends IBaseDao<IisDataClassify, String> {
	
	@Query(value ="select * from iis_data_classify  where parent_id is null order by priority asc", nativeQuery = true)
	List<IisDataClassify> getFirstClassify();
	
	@Query(value ="select * from iis_data_classify  where parent_id is not null order by PARENT_ID,priority desc", nativeQuery = true)
	List<IisDataClassify> getSecondClassify();

}
