package cn.eakay.demo.biz.manager.impl;


import cn.eakay.demo.biz.common.errorcode.FileErrorEnum;
import cn.eakay.demo.biz.common.exception.FileOptException;
import cn.eakay.demo.biz.common.fastdfspool.FastDFSSource;
import cn.eakay.demo.biz.common.fastdfspool.PoolableFastDFSSource;
import cn.eakay.demo.biz.manager.FastDFSFileManager;
import cn.eakay.demo.client.dataobject.FastDFSFileDO;
import cn.eakay.demo.client.result.FileUploadResultDO;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xugang on 16/4/13.
 */
@Slf4j
@Component
public class FastDFSFileManagerImpl implements FastDFSFileManager {
    public static final String PROTOCOL = "http://";
    public static final String SEPARATOR = "/";
    public static final String TRACKER_NGNIX_PORT = "";

    @Autowired
    @Setter
    private FastDFSSource fastDFSSource;

    @Override
    public FileUploadResultDO uploadFast(FastDFSFileDO file) {

        PoolableFastDFSSource poolableFastDFSSource = null;
        FileUploadResultDO resultDO = new FileUploadResultDO();

        try {
            //getFastDFSSource调用一次会addObject一次到pool
            poolableFastDFSSource = fastDFSSource.getFastDFSSource();

            StorageClient storageClient = poolableFastDFSSource.getStorageClient();
            TrackerServer trackerServer = poolableFastDFSSource.getTrackerServer();

            log.info("Upload File Name:{},File Length:", file.getName(), file.getContent().length);

            NameValuePair[] meta_list = new NameValuePair[3];
            meta_list[0] = new NameValuePair("width", file.getWidth());
            meta_list[1] = new NameValuePair("heigth", file.getHeight());
            meta_list[2] = new NameValuePair("author", file.getAuthor());

            long startTime = System.currentTimeMillis();

            String[] uploadResults = storageClient.upload_file(file.getContent(), file.getExt(), meta_list);

            log.info("upload_file time used:" + (System.currentTimeMillis() - startTime) + " ms");

            if (uploadResults == null) {
                log.error("upload file fail, error code: " + storageClient.getErrorCode());
                FileErrorEnum.FILE_UPLOAD_ERROR.fillResult(resultDO);
                return resultDO;
            }

            String groupName = uploadResults[0];
            String remoteFileName = uploadResults[1];

            resultDO.setGroupName(uploadResults[0]);
            resultDO.setRemoteFileName(uploadResults[1]);

            String fileAbsolutePath = PROTOCOL + trackerServer.getInetSocketAddress().getHostName()
                    + SEPARATOR
                    + TRACKER_NGNIX_PORT
                    + SEPARATOR
                    + groupName
                    + SEPARATOR
                    + remoteFileName;

            log.info("upload file successfully!!!  " + "group_name: " + groupName + ", remoteFileName:"
                    + " " + remoteFileName);
            if (log.isDebugEnabled()) {
                log.info("upload file remote url:", fileAbsolutePath);
            }
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                e.printStackTrace();
            }
            FileErrorEnum.FILE_UPLOAD_ERROR.fillResult(resultDO);
            log.error("occur Exception when uploadind the file: " + file.getName(), e);
        } finally {
            try {
                if (poolableFastDFSSource != null) poolableFastDFSSource.close();
            } catch (Exception e) {
                resultDO.setSuccess(false);
                resultDO.setErrorMsg("poolableFastDFSSource object pool close failed");
                log.error("poolableFastDFSSource object pool close failed,filename={}", file.getName());
            }
        }
        return resultDO;
    }

    @Override
    public FileInfo getFileFast(String groupName, String remoteFileName) throws FileOptException {

        PoolableFastDFSSource poolableFastDFSSource = null;

        try {
            poolableFastDFSSource = fastDFSSource.getFastDFSSource();
            StorageClient storageClient = poolableFastDFSSource.getStorageClient();

            FileInfo fileInfo = storageClient.get_file_info(groupName, remoteFileName);
            return fileInfo;
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileOptException(e);
        } finally {
            try {
                if (poolableFastDFSSource != null) poolableFastDFSSource.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void deleteFileFast(String groupName, String remoteFileName) throws Exception {
        PoolableFastDFSSource poolableFastDFSSource = null;

        try {
            poolableFastDFSSource = fastDFSSource.getFastDFSSource();
            StorageClient storageClient = poolableFastDFSSource.getStorageClient();

            storageClient.delete_file(groupName, remoteFileName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileOptException(e);
        } finally {
            try {
                if (poolableFastDFSSource != null) poolableFastDFSSource.close();
            } catch (Exception e) {
            }
        }
    }

}
