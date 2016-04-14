package cn.eakay.demo.biz.manager.impl;

import cn.eakay.commons.dao.persistence.exception.DAOException;
import cn.eakay.demo.biz.manager.FileManager;
import cn.eakay.demo.client.dataobject.FileDO;
import cn.eakay.demo.repository.db.mybatis.FileDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xugang on 16/4/14.
 */
@Slf4j
@Component
public class FileManagerImpl implements FileManager {
    @Autowired
    private FileDAO fileDAO;


    @Override
    public void addFileOne(FileDO fileDO) throws DAOException {
        try {
            fileDAO.insertOne(fileDO);
        } catch (Exception e) {
            log.error("addFileOne error/DB,fileDO:" + fileDO, e);
            throw new DAOException("addFileOne error", e);
        }
    }

    @Override
    public FileDO findFileOne(String groupName, String remoteFileName) throws DAOException {
        try {
            return fileDAO.queryByGroupNameAndRemoteFileName(groupName, remoteFileName);
        } catch (Exception e) {
            log.error("findFileOne error/DB,groupName={},remoteFileName={}", groupName, remoteFileName, e);
            throw new DAOException("findFileOne error", e);
        }
    }

    @Override
    public FileDO findFileById(Long id) throws DAOException {
        try {
            return fileDAO.queryById(id);
        } catch (Exception e) {
            log.error("findFileById error/DB,id={}", id, e);
            throw new DAOException("findFileById error", e);
        }
    }

    @Override
    public void deleteFileById(Long id) throws DAOException {
        try {
            fileDAO.deleteById(id);
        } catch (Exception e) {
            log.error("deleteFileById error/DB,id={}", id, e);
            throw new DAOException("deleteFileById error", e);
        }
    }
}
