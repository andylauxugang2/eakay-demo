package cn.eakay.demo.biz.test.manager;

import cn.eakay.demo.biz.common.config.FastdfsClientConfig;
import cn.eakay.demo.biz.common.fastdfspool.BasicFastDFSSource;
import cn.eakay.demo.biz.manager.impl.FastDFSFileManagerImpl;
import cn.eakay.demo.client.dataobject.FastDFSFileDO;
import cn.eakay.demo.client.result.FileUploadResultDO;
import org.csource.fastdfs.FileInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;

public class TestFastdfs {

    FastDFSFileManagerImpl fileManager;

    @Before
    public void setUp() {

        BasicFastDFSSource fastDFSSource = new BasicFastDFSSource();
        fastDFSSource.setInitialSize(10);
        fastDFSSource.setMaxActive(30);
        fastDFSSource.setMaxWait(30000);
        fastDFSSource.setMaxIdle(10);

        fileManager = new FastDFSFileManagerImpl();
        fileManager.setFastDFSSource(fastDFSSource);
    }

    @Test
    public void upload() throws Exception {
        File content = new File("/Users/xugang/Desktop/aaatest.png");

        FileInputStream fis = new FileInputStream(content);
        byte[] file_buff = null;
        if (fis != null) {
            int len = fis.available();
            file_buff = new byte[len];
            fis.read(file_buff);
        }

        FastDFSFileDO fastDFSFileDO = new FastDFSFileDO("520", file_buff, "jpg");
        fis.close();

        FileUploadResultDO resultDO = fileManager.uploadFast(fastDFSFileDO);
        Assert.assertTrue(resultDO.isSuccess());
    }

    @Test
    public void getFile() throws Exception {
        FastdfsClientConfig clientConfig = new FastdfsClientConfig();
        FileInfo fileInfo = fileManager.getFileFast("IDCARD", "M00/00/00/ezgsT1cQ-miAMw41AACZ8ASiEgA9543824");
        Assert.assertNotNull(fileInfo);
        String sourceIpAddr = fileInfo.getSourceIpAddr();
        long size = fileInfo.getFileSize();
        System.out.println("ip:" + sourceIpAddr + ",size:" + size);
    }

    @Test
    public void deleteFile() throws Exception {
        FastdfsClientConfig clientConfig = new FastdfsClientConfig();
        fileManager.deleteFileFast("IDCARD", "M00/00/00/ezgsT1cQ-miAMw41AACZ8ASiEgA9543824");
    }

    @Test
    public void getStorageServer() throws Exception {
        /*StorageServer[] ss = FastdfsManager.getInstance().getStoreStorages("group1");
        Assert.assertNotNull(ss);

        for (int k = 0; k < ss.length; k++) {
            System.err.println(k + 1 + ". " + ss[k].getInetSocketAddress().getAddress().getHostAddress() + ":" + ss[k].getInetSocketAddress().getPort());
        }*/
    }

    @Test
    public void getFetchStorages() throws Exception {
        /*ServerInfo[] servers = FastdfsManager.getInstance().getFetchStorages("group1", "M00/00/00/wKgBm1N1-CiANRLmAABygPyzdlw073.jpg");
        Assert.assertNotNull(servers);

        for (int k = 0; k < servers.length; k++) {
            System.err.println(k + 1 + ". " + servers[k].getIpAddr() + ":" + servers[k].getPort());
        }*/
    }


}
