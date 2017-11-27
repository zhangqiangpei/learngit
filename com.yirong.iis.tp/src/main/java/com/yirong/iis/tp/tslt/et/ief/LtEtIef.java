package com.yirong.iis.tp.tslt.et.ief;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yirong.commons.sys.entity.SysDictionaryEntity;
import com.yirong.commons.sys.service.SysDictionaryService;
import com.yirong.iis.tp.common.entity.LtEtCode;
import com.yirong.iis.tp.tslt.et.service.LtEtCodeService;
import com.yirong.iis.tp.tslt.et.service.LtEtDataService;
import com.yirong.iis.tp.tslt.et.userentity.LtEtDataUserEntity;
import com.yirong.iis.tp.tslt.et.util.StarterConsumer;

/**
 * 功能描述：路透elektron 统一路口
 *
 * @author 刘捷(liujie)
 *         <p>
 *         创建时间 ：2017年11月22日 下午8:03:14
 *         </p>
 *
 *         <p>
 *         修改历史：(修改人，修改时间，修改原因/内容)
 *         </p>
 */
@Component
public class LtEtIef {

	/**
	 * 代码service注入
	 */
	private static LtEtCodeService ltEtCodeService;

	/**
	 * 数据service注入
	 */

	private static LtEtDataService dataService;

	/**
	 * 参数service注入
	 */
	private static SysDictionaryService sysDictionaryService;

	/**
	 * 功能描述：初始化
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年11月22日 下午8:03:58
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 *
	 */
	public static void run() {
		// 获取代码表类型信息
		List<SysDictionaryEntity> sdeList = sysDictionaryService.findListByCodeAndAppId("020", null);
		if (null != sdeList && sdeList.size() > 0) {
			for (SysDictionaryEntity sde : sdeList) {
				if("020".equals(sde.getCode())) {
					continue;
				}
				List<LtEtCode> list = ltEtCodeService.findByCodeType(sde.getCode());
				if (null != list && list.size() != 0) {
					StringBuffer codeStrs = new StringBuffer();
					int listLenth = list.size();
					for (int i = 0; i < listLenth; i++) {
						LtEtCode ltEtCode = list.get(i);
						if (i == (listLenth - 1)) {// 最后一个元素
							codeStrs.append(ltEtCode.getRicCode());
						} else {
							codeStrs.append(ltEtCode.getRicCode() + ",");
						}
					}
					// 异步线程运行
					StarterConsumer starterConsumer = new StarterConsumer("Demo::SESS_Demo", "ELEKTRON_DD", "user2",
							codeStrs.toString(), sde.getCode());
					starterConsumer.start();
				}
			}
		}
	}

	/**
	 * 功能描述：处理数据
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年11月22日 下午8:49:02
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 *
	 */
	public static void doData(List<LtEtDataUserEntity> ueList) {
		if (null != ueList && ueList.size() > 0) {
			dataService.doLtEtData(ueList);
		}
	}

	/**
	 * 功能描述：新增代码表
	 *
	 * @author 刘捷(liujie)
	 *         <p>
	 *         创建时间 ：2017年11月23日 下午9:05:03
	 *         </p>
	 *
	 *         <p>
	 *         修改历史：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 *
	 * @param code
	 * @param pcode
	 * @param isLink
	 * @return
	 *
	 */
	public static LtEtCode doLtEtCode(String code, String pcode, boolean isLink) {
		return ltEtCodeService.doLtEtCode(code, pcode, isLink);
	}

	@Autowired(required = true)
	public void setLtEtCodeService(LtEtCodeService ltEtCodeService) {
		LtEtIef.ltEtCodeService = ltEtCodeService;
	}

	@Autowired(required = true)
	public void setLtEtDataService(LtEtDataService dataService) {
		LtEtIef.dataService = dataService;
	}

	@Autowired(required = true)
	public void setSysDictionaryService(SysDictionaryService sysDictionaryService) {
		LtEtIef.sysDictionaryService = sysDictionaryService;
	}

}
