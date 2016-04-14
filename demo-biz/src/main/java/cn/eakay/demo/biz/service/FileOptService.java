package cn.eakay.demo.biz.service;

import cn.eakay.demo.client.dataobject.FastDFSFileDO;
import cn.eakay.demo.client.result.FileOptResultDO;

/**
 * 文件上传和下载等操作接口
 *
 * @author xugang
 */
public interface FileOptService {

    /**
     * 上传文件
     * @param fastDFSFileDO
     * @return
     */
    FileOptResultDO uploadFile(FastDFSFileDO fastDFSFileDO);

    /**
     * 查询文件
     * @param groupName
     * @param remoteFileName 全网唯一文件名
     * @return
     */
    FileOptResultDO getFile(String groupName, String remoteFileName);

    /**
     * 删除文件
     * @param id file id
     * @param groupName
     * @param remoteFileName
     * @return
     */
    FileOptResultDO deleteFile(Long id, String groupName, String remoteFileName);

}
