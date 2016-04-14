package cn.eakay.demo.biz.manager;

import cn.eakay.demo.biz.common.exception.FileOptException;
import cn.eakay.demo.client.dataobject.FastDFSFileDO;
import cn.eakay.demo.client.dataobject.FileDO;
import cn.eakay.demo.client.result.FileUploadResultDO;
import org.csource.fastdfs.FileInfo;

/**
 * 文件操作业务逻辑接口
 * 报错文件的上传、下载、删除
 *
 * @author xugang
 * @version 1.0
 */
public interface FastDFSFileManager {

    /**
     * 上传文件
     *
     * @param fastDFSFileDO
     * @return FileUploadResultDO 返回文件上传后的path （fiel system or http url）
     * @throws FileOptException
     */
    FileUploadResultDO uploadFast(FastDFSFileDO fastDFSFileDO);

    /**
     * 下载文件
     *
     * @param groupName      trackerServer握手后返回给我们的文件的groupName IDCARD
     * @param remoteFileName storageServer保存的文件名 M00/00/00/Zch5C1cOEP6AbA5cAArBeOlRIqM581.jpg
     * @return
     * @throws FileOptException
     */
    FileInfo getFileFast(String groupName, String remoteFileName) throws FileOptException;

    /**
     * 删除文件
     *
     * @param groupName
     * @param remoteFileName
     * @throws Exception
     */
    void deleteFileFast(String groupName, String remoteFileName) throws Exception;
}
