package cn.eakay.demo.repository.db.mybatis;

import cn.eakay.demo.client.dataobject.FileDO;
import org.apache.ibatis.annotations.Param;

/**
 * @created by xugang on 16/4/7.
 */
public interface FileDAO {
    void insertOne(FileDO fileDO);

    FileDO queryByGroupNameAndRemoteFileName(@Param("groupName") String groupName, @Param("remoteFileName") String remoteFileName);

    FileDO queryById(Long id);

    void deleteById(Long id);
}