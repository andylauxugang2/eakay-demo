package cn.eakay.demo.server.test.manage;

import cn.eakay.demo.biz.manager.FastDFSFileManager;
import cn.eakay.demo.server.test.TestBase;
import org.csource.fastdfs.FileInfo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 测试 FileManagerImpl
 * Created by xugang on 16/4/14.
 */
public class FileManagerTest extends TestBase {
    @Autowired
    private FastDFSFileManager fileManager;

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void testGetFile() {
        FileInfo fileInfo = fileManager.getFileFast("IDCARD2", "M00/00/00/Zch5C1cPO36AP2gfAArBeOlRIqM314.jpg");
        Assert.assertNotNull(fileInfo);
        String sourceIpAddr = fileInfo.getSourceIpAddr();
        long size = fileInfo.getFileSize();
        System.out.println("ip:" + sourceIpAddr + ",size:" + size);
    }
}
