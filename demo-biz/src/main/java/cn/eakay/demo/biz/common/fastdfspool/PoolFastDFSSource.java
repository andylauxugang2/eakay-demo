package cn.eakay.demo.biz.common.fastdfspool;

import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;

/**
 * ObjectPool 用于管理要被池化的对象的借出和归还，通知 PoolableObjectFactory 完成相应的工作
 *
 * @see PoolableTrackerClientFactory
 * Created by xugang on 16/4/13.
 */
public class PoolFastDFSSource implements FastDFSSource {

    protected ObjectPool pool = null;

    public PoolFastDFSSource(ObjectPool pool) {
        this.pool = pool;
    }

    /**
     * 从 pool 里获取一个对象
     * If the number of instances checked out from the pool is less than <code>maxActive,</code> a new
     * instance is created, activated and (if applicable) validated and returned to the caller
     *
     * @return
     * @see GenericObjectPool
     */
    @Override
    public PoolableFastDFSSource getFastDFSSource() throws Exception {
        try {
            PoolableFastDFSSource source = (PoolableFastDFSSource) (pool.borrowObject());
            return source;
        } catch (Exception e) {
            throw e;
        }
    }
}
