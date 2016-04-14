package cn.eakay.demo.biz.manager;

import cn.eakay.commons.dao.persistence.exception.DAOException;
import cn.eakay.demo.client.dataobject.FileDO;

/**
 * File 业务逻辑组件
 * 入库File
 * Created by xugang on 16/4/14.
 */
public interface FileManager {
    void addFileOne(FileDO fileDO) throws DAOException;

    FileDO findFileOne(String groupName, String remoteFileName) throws DAOException;

    FileDO findFileById(Long id) throws DAOException;

    void deleteFileById(Long id) throws DAOException;
}
