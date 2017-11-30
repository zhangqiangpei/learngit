package com.yirong.iis.mm.service.impl;

import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import com.yirong.commons.util.file.FileUtil;
import com.yirong.commons.util.file.PropertiesFileUtil;
import com.yirong.iis.mm.dao.IisCountryNationalFlagDao;
import com.yirong.iis.mm.entity.IisCountryNationalFlag;
import com.yirong.iis.mm.service.IisCountryNationalFlagService;
import com.yirong.iis.mm.userentity.IisCountryNationalFlagUserEntity;
import oracle.sql.BLOB;
import org.hibernate.engine.jdbc.SerializableBlobProxy;
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

import javax.imageio.ImageIO;

/**
 * 功能描述：国家国旗表service实现类
 *
 * @author 陈清沣
 * <p>
 * 创建时间 ：2017-11-29 10:40:39
 * </p>
 * <p>
 * <p>
 * 修改历史：(修改人，修改时间，修改原因/内容)
 * </p>
 */
@SuppressWarnings("rawtypes")
@Service("IisCountryNationalFlagServiceImpl")
public class IisCountryNationalFlagServiceImpl extends BaseService<IisCountryNationalFlag, String>
        implements IisCountryNationalFlagService {

    /**
     * 日志操作类
     */
    private Logger logger = LoggerFactory.getLogger(IisCountryNationalFlagServiceImpl.class);

    /**
     * dao注入
     */
    @Autowired
    private IisCountryNationalFlagDao iisCountryNationalFlagDao;

    /**
     * 功能描述：获取dao操作类
     *
     * @author 陈清沣
     * <p>
     * 创建时间 ：2017-11-29 10:40:39
     * </p>
     * <p>
     * <p>
     * 修改历史：(修改人，修改时间，修改原因/内容)
     * </p>
     */
    @Override
    public IBaseDao<IisCountryNationalFlag, String> getBaseDao() {
        return iisCountryNationalFlagDao;
    }

    /**
     * 功能描述：新增国家国旗表
     *
     * @param engName
     * @return
     * @author 陈清沣
     * <p>
     * 创建时间 ：2017-11-29 10:40:39
     * </p>
     * <p>
     * <p>
     * 修改历史：(修改人，修改时间，修改原因/内容)
     * </p>
     */
    @Override
    public Map saveIisCountryNationalFlag(String engName, boolean isUpdate) {
        // 根据编码及分类ID获取数据（唯一键）
        IisCountryNationalFlag iisCountryNationalFlagTemp = this.iisCountryNationalFlagDao.findByCountryEngName(engName);
        if (null == iisCountryNationalFlagTemp || isUpdate) {
            if (isUpdate) {// 修改
                // 获取数据库对象
                iisCountryNationalFlagTemp = this.iisCountryNationalFlagDao.findByCountryEngName(engName);
                byte[] bytes = getFlogBytes(engName);
                if (bytes != null && bytes.length > 0) {
                    iisCountryNationalFlagTemp.setNationalFlag(bytes);
                    this.save(iisCountryNationalFlagTemp);
                }
            } else {
                byte[] bytes = getFlogBytes(engName);
                if (bytes != null && bytes.length > 0) {
                    IisCountryNationalFlag flag = new IisCountryNationalFlag();
                    flag.setCountryEngName(engName);
                    flag.setCreateTime(new Date());
                    flag.setNationalFlag(bytes);
                    this.save(flag);
                    return ResultUtil.newOk("操作成功").toMap();
                }
                return ResultUtil.newError("国旗文件不存在").toMap();
            }
            return ResultUtil.newOk("操作成功").toMap();
        } else {// 该名称及父类ID已存在
            String result = ErrorPromptInfoUtil.getErrorInfo("00201");
            logger.warn(result);
            return ResultUtil.newError(result).toMap();
        }
    }

    /**
     * 功能描述：修改国家国旗表
     *
     * @param engName
     * @return
     * @author 陈清沣
     * <p>
     * 创建时间 ：2017-11-29 10:40:39
     * </p>
     * <p>
     * <p>
     * 修改历史：(修改人，修改时间，修改原因/内容)
     * </p>
     */
    @Override
    public Map updateIisCountryNationalFlag(String engName) {
        // 根据编号Id
        IisCountryNationalFlag iisCountryNationalFlagTemp = this.iisCountryNationalFlagDao.findByCountryEngName(engName);
        if (null == iisCountryNationalFlagTemp) {// 未查询到任何数据
            String promptInfo = "不存在英文名：" + engName;
            String result = ErrorPromptInfoUtil.getErrorInfo("00202",
                    promptInfo);
            logger.warn(result);
            return ResultUtil.newError(result).toMap();
        } else {// 有该数据
            return saveIisCountryNationalFlag(engName, true);
        }
    }

    /**
     * 功能描述：删除国家国旗表（批量）
     *
     * @param idStrs
     * @return
     * @author 陈清沣
     * <p>
     * 创建时间 ：2017-11-29 10:40:39
     * </p>
     * <p>
     * <p>
     * 修改历史：(修改人，修改时间，修改原因/内容)
     * </p>
     */
    @Override
    public Map delIisCountryNationalFlag(String idStrs) {
        // 处理id集合串
        String[] ids = idStrs.split(",");
        /** 先判断所有ID是否允许删除 **/
        StringBuffer sb = new StringBuffer();
        for (String id : ids) {
            // 判断ID是否存在
            IisCountryNationalFlag iisCountryNationalFlag = this.iisCountryNationalFlagDao.findOne(id);
            if (null == iisCountryNationalFlag) {// 不存在直接跳出循环
                String promptInfo = "不存在ID：" + id;
                sb.append(ErrorPromptInfoUtil.getErrorInfo("00202", promptInfo));
                break;
            }
        }
        // 处理业务
        if (StringUtil.isNullOrEmpty(sb.toString())) {// 所有数据均允许删除
            // 将id循环拼装成序列化集合
            for (String id : ids) {
                this.iisCountryNationalFlagDao.delete(id);// 物理删除
            }
            return ResultUtil.newOk("操作成功").toMap();
        } else {// 不允许删除
            logger.warn(sb.toString());
            return ResultUtil.newError(sb.toString()).toMap();
        }
    }

    /**
     * 功能描述：根据英文名查询国家国旗表信息
     *
     * @param
     * @return
     * @author 陈清沣
     * <p>
     * 创建时间 ：2017-11-29 10:40:39
     * </p>
     * <p>
     * <p>
     * 修改历史：(修改人，修改时间，修改原因/内容)
     * </p>
     */
    @Override
    public Map queryIisCountryNationalFlagByEngName(String engName) {

        IisCountryNationalFlag flag = iisCountryNationalFlagDao.findByCountryEngName(engName);

        if (null != flag && flag.getNationalFlag().length > 0) {
            String targetPath = this.getFileDirPath(null) + File.separator + engName + ".jpg";
            if (byteArrayToFile(flag.getNationalFlag(), targetPath)) {
                return ResultUtil.newOk("操作成功").setData(targetPath).toMap();
            }
        }

        return ResultUtil.newOk("操作成功").toMap();
    }


    /**
     * 根据英文名获取国旗byte[]
     *
     * @return
     */
    private byte[] getFlogBytes(String engName) {

        String flagDir = this.getFileDirPath(null) + File.separator;
        List<File> list = new ArrayList<File>(Arrays.asList(new File(flagDir).listFiles()));
        if (list.size() > 0) {
            byte[] bytes = new byte[0];
            for (File file : list) {
                if (file.isFile() && engName.equals(file.getPath().substring(file.getPath().lastIndexOf(File.separator)+1, file.getPath().lastIndexOf(".")))) {
                    bytes = fileToBytes(file.getPath());
                }
            }
            if (bytes != null && bytes.length > 0) {
                return bytes;
            }
        }
        return null;
    }

    /**
     * 获取临时文件存放路径
     *
     * @return
     */
    private Path getFileDirPath(String path) {
        // 配置文件路径
        String configPath = System.getProperty("user.dir") + File.separator + "config" + File.separator;
        // 获取配置信息
        Map<String, String> map = PropertiesFileUtil.getPropertiesFileToMapFromOutFile(configPath + "yr-awaken.properties");
        String dirPath;
        if (null == path) {
            dirPath = map.get("country.flag.save.path");
        } else {
            dirPath = path;
        }
        Path fileDirPath = Paths.get(dirPath);
        try {
            if (!Files.exists(fileDirPath)) {
                fileDirPath = Files.createDirectory(fileDirPath);
            }
        } catch (IOException e) {
            logger.error(e);
        }
        return fileDirPath;
    }

    /**
     * 根据二进制转为文件
     *
     * @return
     */
    private boolean byteArrayToFile(byte[] bytes, String targetPatn) {

        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            file = new File(targetPatn);
            if (file.exists()) {
                FileUtil.delFile(targetPatn);
            }
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    /**
     * 将文件转换成Byte数组
     *
     * @return
     */
    public static byte[] fileToBytes(String pathStr) {
        File file = new File(pathStr);
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(3096);
            byte[] b = new byte[3096];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            byte[] data = bos.toByteArray();
            bos.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
