package com.yirong.iis.tp.tslt.trkd.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.yirong.awaken.core.util.ResultUtil;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.commons.sys.eif.SysParameterEif;
import com.yirong.commons.util.datatype.StringUtil;
import com.yirong.iis.tp.common.constant.LtConstant;

@SuppressWarnings("unchecked")
public abstract class LtHttpService {
	
	@Autowired
	private ILtTokenService ltTokenService;
	
	@Autowired
	private ILtTrkdRequestLogService ltTrkdRequestLogService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 根据code取到对应的接口
	 * @param code
	 * @return
	 */
	public Map<String, Object> request(Map<String, Object> param){
		//token过期重新获取token
		if(StringUtil.isNullOrEmpty(LtConstant.ltToken) || new Date().getTime() >= LtConstant.expiration.getTime()){
			Map<String,Object> tokenMap = ltTokenService.getToken();
			if(null == tokenMap || !tokenMap.containsKey("code") ||
					ResultUtil.STATUS_CODE_SUCCESS != Integer.parseInt(tokenMap.get("code").toString())){
				logger.error("路透token获取失败，接口请求失败！");
				return ResultUtil.newError("路透token获取失败，接口请求失败！").toMap();
			}
		}
		
		return exec(param);
	}
	
	/**
	 * 组装http head 公共头部
	 * @return
	 */
	public Map<String,String> getHeadMap(){
		Map<String,String> headMap = new HashMap<String,String>();
		headMap.put("X-Trkd-Auth-Token", LtConstant.ltToken);
		headMap.put("X-Trkd-Auth-ApplicationID", SysParameterEif.getValueByCode("Trkd-ApplicationID"));
		return headMap;
	}
	
	/**
	 * 组装http url
	 * @param code
	 * @return
	 */
	public String getHttpUrl(String code){
		return new StringBuffer("http://").append(SysParameterEif.getValueByCode("Trkd-Url")).append(LtConstant.trkdMap.get(code)).toString();
	}
	
	/**
	 * 添加请求操作日志记录
	 * @param name
	 * @param param
	 * @param result
	 * @param status
	 */
	public void addRequestLog(String name,String param,String result,Integer status){
		try {
			ltTrkdRequestLogService.addRequestLog(name,param,result,status);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * 执行请求，处理数据
	 * @param param
	 * @return
	 */
	public abstract Map<String, Object> exec(Map<String, Object> param);
	
}
