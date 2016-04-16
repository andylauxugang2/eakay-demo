package cn.eakay.demo.biz.common.fastdfspool;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

import java.io.Serializable;

/**
 * 该对象实例化比较重，需要考虑放到对象池里
 * 可以实现接口统一 Pool Source 规范
 * Created by xugang on 16/4/14.
 */
@Data
@Slf4j
public class PoolableFastDFSSource implements Serializable {

    private static final long serialVersionUID = 1998673244100571562L;

    private String sourceId;

    protected ObjectPool pool;

    private volatile boolean closed;

    //资源最后被使用的时间
    private volatile long lastUsed = 0;

    TrackerClient trackerClient;
    TrackerServer trackerServer;
    StorageClient storageClient;
    StorageServer storageServer;

    public PoolableFastDFSSource(ObjectPool pool, TrackerClient trackerClient, TrackerServer trackerServer, StorageClient storageClient, StorageServer storageServer) {
        this.pool = pool;
        this.trackerClient = trackerClient;
        this.trackerServer = trackerServer;
        this.storageClient = storageClient;
        this.storageServer = storageServer;
    }

    /**
     * 将对象归还到pool
     * 归还失败 会触发 @see PoolableTrackerClientFactory#destroyObject方法 和 commons-pool allocate动作 重新分配queue
     *
     * @throws Exception
     */
    public synchronized void close() throws Exception {
        if (isClosed()) {
            log.info("fastDFS连接资源已关闭，无需归还到池");
            return;
        }
        try {
            //归还对象
            pool.returnObject(this);
        } catch (final IllegalStateException e) {
            // pool is closed, close the source
            setClosed(true);
        } catch (final RuntimeException e) {
            throw e;
        } catch (Exception e) {
            log.error("归还连接资源失败:return to pool failed", e);
            throw new RuntimeException("Cannot close source (return to pool failed)", e);
        }
    }

    protected boolean isClosed() {
        return closed;
    }

    protected void setClosed(final boolean closed) {
        //回收socket资源 fast SDF client 已经帮我们回收socket 不要重复close
        //trackerClient.getConnection().close();

        this.closed = closed;
    }

    /**
     * 验证 对象是否可以使用 是否可以 conn to 每个fast dfs server
     * _factory.validateObject(obj) 会调 obj validate
     *
     * @see BasicFastDFSSource#validateTrackerClientFactory
     * @see GenericObjectPool#addObjectToPool
     */
    public void validate() throws Exception {
        //TODO test connect to fast dfs server
    }

    /**
     * 激活操作
     */
    public void activate() {
        this.closed = false;
        lastUsed = System.currentTimeMillis();
    }

    /**
     * 钝化操作
     */
    public void passivate() {
        setClosed(true);
        //如果有额外资源 需要关闭
    }
}
