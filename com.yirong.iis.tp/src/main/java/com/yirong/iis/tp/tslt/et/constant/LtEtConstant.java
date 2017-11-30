package com.yirong.iis.tp.tslt.et.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述：路透elektron 常量
 *
 * @author 刘捷(liujie)
 *         <p>
 *         创建时间 ：2017年11月23日 上午8:54:31
 *         </p>
 *
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
public class LtEtConstant {

	/**
	 * 字段类型映射变量
	 */
	public final static Map<String, String> FIELD_TYPE_MAP = getFieldTypeMap();

	/**
	 * 功能描述：获取字段类型映射信息
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年11月23日 上午8:57:59
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @return
	 *
	 */
	private static Map<String, String> getFieldTypeMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("TIME", "017001");// 例子：22:30
		map.put("INTEGER", "017002");// 例子：7603
		map.put("TIME_SECONDS", "017001");// 例子：23:24:51
		map.put("DATE", "017004");// 例子：22 NOV 2017
		map.put("PRICE", "017005");// 例子：1.1824
		map.put("ENUMERATED", "017001");// 例子：
		map.put("ALPHANUMERIC", "017001");// 例子：BIFZAR=R
		map.put("BINARY", "017002");// 例子：52480
		return map;
	}
}
