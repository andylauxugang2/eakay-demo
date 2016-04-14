package cn.eakay.demo.biz.common.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * fast dfs 客户端配置组件
 * 默认使用classpath/fdfs_client.conf
 * 如果文件不存在 启动异常
 * Created by xugang on 16/4/13.
 */
@Slf4j
public class FastdfsClientConfig implements Serializable {

    public static final String FILE_DEFAULT_WIDTH = "120";
    public static final String FILE_DEFAULT_HEIGHT = "120";
    public static final String FILE_DEFAULT_AUTHOR = "Eakay";
    public static final String PROTOCOL = "http://";
    public static final String SEPARATOR = "/";
    public static final String TRACKER_NGNIX_PORT = "8080";
    public static final String CLIENT_CONFIG_FILE = "fdfs_client.conf";

    @Getter
    @Setter
    private volatile String configFile; //防止指令重排序优化

    /**
     * 使用默认配置文件 CLIENT_CONFIG_FILE
     */
    public FastdfsClientConfig() {
        log.info("初始化FastDFS配置...使用默认配置文件:fdfs_client.conf");
        String classPath = null;
        try {
            classPath = new File(FastdfsClientConfig.class.getResource("/").getFile()).getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("获取默认文件classPath失败:", e);
        }
        configFile = classPath + File.separator + CLIENT_CONFIG_FILE;
        if (!new File(configFile).exists()) {
            log.error("使用默认文件configFile失败:文件不存在:{}", configFile);
            throw new RuntimeException("configFile文件不存在:" + configFile);
        }
        init();
    }

    /**
     * 自动配置文件 可覆盖默认配置文件
     *
     * @param configLocation
     */
    public FastdfsClientConfig(Resource configLocation) {
        if (!ObjectUtils.isEmpty(configLocation)) {
            try {
                this.configFile = configLocation.getFile().getAbsolutePath();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        init();
    }

    /**
     * 初始化
     * 配置tracker_server tracker_http_port等 都在configFile文件中定义
     *
     * @see ClientGlobal
     */
    private void init() {
        try {
            ClientGlobal.init(configFile); //static属性初始化后 static方法直接获取配置
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("ClientGlobal init failed", e);
        }
    }
}
