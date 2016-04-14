package cn.eakay.demo.biz.common.fastdfspool;

import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.PoolableObjectFactory;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

/**
 * PoolableTrackerClientFactory 用于管理池化对象的产生、激活、挂起、校验和销毁
 * Created by xugang on 16/4/14.
 */
public class PoolableTrackerClientFactory implements PoolableObjectFactory {

    protected volatile ObjectPool pool = null;

    public PoolableTrackerClientFactory(ObjectPool pool) {
        this.pool = pool;
        pool.setFactory(this);
    }

    @Override
    public Object makeObject() throws Exception {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection(); //use ClientGlobal and new TrackerServer and Socket
        StorageServer storageServer = null;
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        PoolableFastDFSSource sourceDO = new PoolableFastDFSSource(pool, trackerClient, trackerServer, storageClient, storageServer);

        initializeSource(sourceDO);

        return sourceDO;
    }

    protected void initializeSource(PoolableFastDFSSource source) throws Exception {
        //TODO 初始化fast DSF 设置
    }

    /**
     * 销毁
     * returnObject -》addObjectToPool 发生异常 时回调
     * @param obj
     * @throws Exception
     */
    @Override
    public void destroyObject(Object obj) throws Exception {
        if(obj instanceof PoolableFastDFSSource) {
            obj = null;
        }
    }

    /**
     * 验证
     * @param obj
     * @return
     */
    @Override
    public boolean validateObject(Object obj) {
        if(obj instanceof PoolableFastDFSSource) {
            try {
                validateConn((PoolableFastDFSSource) obj);
                return true;
            } catch(Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public void validateConn(PoolableFastDFSSource source) throws Exception {

    }

    /**
     * 挂起
     * @param obj
     * @throws Exception
     */
    @Override
    public void passivateObject(Object obj) throws Exception {

    }

    /**
     * 激活
     * @param obj
     * @throws Exception
     */
    @Override
    public void activateObject(Object obj) throws Exception {

    }

}
