package cn.eakay.demo.biz.common.fastdfspool;

/**
 * fastdfs 所有链接资源 TrackerServer StorageServer
 * Created by xugang on 16/4/13.
 */
public interface FastDFSSource {

    PoolableFastDFSSource getFastDFSSource() throws Exception;

}
