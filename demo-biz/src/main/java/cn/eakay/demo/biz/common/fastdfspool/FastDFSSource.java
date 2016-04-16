package cn.eakay.demo.biz.common.fastdfspool;

/**
 * fastdfs 所有链接资源 TrackerServer StorageServer
 * Created by xugang on 16/4/13.
 */
public interface FastDFSSource {

    /**
     * 从对象池里获取 包装fast dfs tracker storage socket
     * @return
     * @throws Exception
     */
    PoolableFastDFSSource getFastDFSSource() throws Exception;

}
