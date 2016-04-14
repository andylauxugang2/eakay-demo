package cn.eakay.demo.biz.common.fastdfspool;

import lombok.Data;
import org.apache.commons.pool.ObjectPool;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

import java.io.Serializable;

/**
 * 该对象实例化比较重，需要考虑放到对象池里
 * Created by xugang on 16/4/14.
 */
@Data
public class PoolableFastDFSSource implements Serializable{

    private static final long serialVersionUID = 1998673244100571562L;

    protected ObjectPool pool;

    private volatile boolean closed;

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
     * @throws Exception
     */
    public synchronized void close() throws Exception {
        if (isClosed()) {
            return;
        }
        try {
            //归还对象
            pool.returnObject(this);
        } catch(final IllegalStateException e) {
            // pool is closed, close the source
            setClosed(true);
        } catch(Exception e) {
            throw new RuntimeException("Cannot close source (return to pool failed)", e);
        }
    }

    protected boolean isClosed() {
        return closed;
    }

    protected void setClosed(final boolean closed) {
        this.closed = closed;
    }
}
