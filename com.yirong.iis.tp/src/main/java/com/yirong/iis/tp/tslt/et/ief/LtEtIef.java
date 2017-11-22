package com.yirong.iis.tp.tslt.et.ief;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yirong.iis.tp.common.entity.LtEtCode;
import com.yirong.iis.tp.tslt.et.service.LtEtCodeService;
import com.yirong.iis.tp.tslt.et.service.LtEtDataService;
import com.yirong.iis.tp.tslt.et.userentity.LtEtDataUserEntity;
import com.yirong.iis.tp.tslt.et.util.CommandLine;
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
	 * 数据service处理接口
	 */

	private static LtEtDataService dataService;

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
		List<LtEtCode> list = ltEtCodeService.findAll();
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
			CommandLine commondLine = new CommandLine("Demo::SESS_Demo", "ELEKTRON_DD", "user2", codeStrs.toString());
			StarterConsumer sc = new StarterConsumer(commondLine);
			sc.run();
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

	@Autowired(required = true)
	public void setLtEtCodeService(LtEtCodeService ltEtCodeService) {
		LtEtIef.ltEtCodeService = ltEtCodeService;
	}

	@Autowired(required = true)
	public void setLtEtDataService(LtEtDataService dataService) {
		LtEtIef.dataService = dataService;
	}

}
