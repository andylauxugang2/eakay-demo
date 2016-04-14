package cn.eakay.demo.repository.db.mybatis;

import cn.eakay.demo.client.dataobject.FileDO;

/**
 * @created by xugang on 16/4/7.
 */
public interface FileDAO {
    void insertOne(FileDO fileDO);

    FileDO queryByGroupNameAndRemoteFileName(String groupName, String remoteFileName);

    FileDO queryById(Long id);

    void deleteById(Long id);
}