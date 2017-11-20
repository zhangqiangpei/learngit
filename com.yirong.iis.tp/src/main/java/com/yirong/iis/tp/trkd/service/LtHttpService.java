package com.yirong.iis.tp.trkd.service;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.yirong.awaken.core.util.ResultUtil;
import com.yirong.commons.logging.Logger;
import com.yirong.commons.logging.LoggerFactory;
import com.yirong.commons.util.datatype.StringUtil;
import com.yirong.iis.tp.trkd.constant.LtConstant;

@SuppressWarnings("unchecked")
public abstract class LtHttpService {
	
	@Autowired
	private ILtTokenService ltTokenService;
	
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
	
	public abstract Map<String, Object> exec(Map<String, Object> param);
	
}
