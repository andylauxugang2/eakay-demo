package cn.eakay.demo.biz.common.fastdfspool;

import cn.eakay.demo.biz.common.config.FastdfsClientConfig;
import org.apache.commons.pool.impl.GenericObjectPool;

/**
 * 基本的实现 带有连接池 commons-pool
 * 可使用spring创建
 *
 * Created by xugang on 16/4/13.
 */
public class BasicFastDFSSource implements FastDFSSource{

    public BasicFastDFSSource() {
    }

    @Override
    public PoolableFastDFSSource getFastDFSSource() throws Exception {
        return createFastDFSSource().getFastDFSSource();
    }

    //暂未用到
    @Deprecated
    protected FastdfsClientConfig fastdfsClientConfig;

    /**
     * 只被createFastDFSSource方法调用
     */
    protected volatile FastDFSSource fastDFSSource = null;

    /**
     * 对象池 管理 TrackerClient
     */
    protected volatile GenericObjectPool trackerClientPool = null;

    protected boolean closed;

    /**
     * 当创建池子时 初始化几多对象
     */
    protected int initialSize = 0;

    /**
     * 设置initial Size
     * @param initialSize
     */
    public synchronized void setInitialSize(int initialSize) {
        this.initialSize = initialSize;
    }

    private int maxActive = GenericObjectPool.DEFAULT_MAX_ACTIVE;
    private int maxIdle = GenericObjectPool.DEFAULT_MAX_IDLE;
    private int minIdle = GenericObjectPool.DEFAULT_MIN_IDLE;
    private long maxWait = GenericObjectPool.DEFAULT_MAX_WAIT;
    private boolean testOnBorrow = true;
    private boolean testOnReturn = false;
    private boolean testWhileIdle = false;
    private long timeBetweenEvictionRunsMillis = GenericObjectPool.DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS;
    private int numTestsPerEvictionRun = GenericObjectPool.DEFAULT_NUM_TESTS_PER_EVICTION_RUN;
    private long minEvictableIdleTimeMillis = GenericObjectPool.DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS;

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public long getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(long maxWait) {
        this.maxWait = maxWait;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public boolean isTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public boolean isTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public long getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(long timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public int getNumTestsPerEvictionRun() {
        return numTestsPerEvictionRun;
    }

    public void setNumTestsPerEvictionRun(int numTestsPerEvictionRun) {
        this.numTestsPerEvictionRun = numTestsPerEvictionRun;
    }

    public long getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(long minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    /**
     * 外部调用回收所有资源 包括pool
     * @throws Exception
     */
    public synchronized void close() throws Exception {
        closed = true;
        GenericObjectPool oldpool = trackerClientPool;
        trackerClientPool = null;
        fastDFSSource = null;
        try {
            if (oldpool != null) {
                oldpool.close();
            }
        } catch(Exception e) {
            throw e;
        }
    }

    /**
     * @see GenericObjectPool#addObject()
     *
     * @return FastDFSSource
     * @throws Exception
     */
    protected synchronized FastDFSSource createFastDFSSource() throws Exception {
        if (closed) {
            throw new IllegalStateException("Fast Source is closed");
        }

        // Return the pool if we have already created it
        if (fastDFSSource != null) {
            return fastDFSSource;
        }
        createTrackerClientPool();
        // 设置PoolableTrackerClientFactory
        createPoolableTrackerClientFactory();
        createFastDFSSourceInstance();
        try {
            for (int i = 0 ; i < initialSize ; i++) {
                //调用PoolableObjectFactory makeObject方法创建对象
                //必须为trackerClientPool创建PoolableObjectFactory
                //会调用_factory.validateObject passivateObject
                trackerClientPool.addObject();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error preloading the Fast source pool", e);
        }

        return fastDFSSource;
    }

    /**
     * 创建 PoolableTrackerClientFactory 并附加到trackerClientPool属性中
     * @throws Exception
     */
    protected void createPoolableTrackerClientFactory() throws Exception {
        PoolableTrackerClientFactory trackerClientFactory;
        try {
            trackerClientFactory = new PoolableTrackerClientFactory(trackerClientPool);
            validateTrackerClientFactory(trackerClientFactory);
        } catch (Exception e) {
            throw e;
        }
    }

    protected static void validateTrackerClientFactory(PoolableTrackerClientFactory trackerClientFactory) throws Exception {
        //TODO
    }

    protected void createFastDFSSourceInstance() throws Exception {
        PoolFastDFSSource poolFastDFSSource = new PoolFastDFSSource(trackerClientPool);
        fastDFSSource = poolFastDFSSource;
    }

    /**
     * 为FastDFSSource 创建 TrackerClient pool
     */
    protected void createTrackerClientPool() {
        // 创建 object pool 获取 active TrackerClient
        GenericObjectPool objectPool = new GenericObjectPool();

        objectPool.setMaxActive(maxActive);
        objectPool.setMaxIdle(maxIdle);
        objectPool.setMinIdle(minIdle);
        objectPool.setMaxWait(maxWait);
        objectPool.setTestOnBorrow(testOnBorrow);
        objectPool.setTestOnReturn(testOnReturn);
        objectPool.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        objectPool.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
        objectPool.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        objectPool.setTestWhileIdle(testWhileIdle);
        trackerClientPool = objectPool;
    }

}
