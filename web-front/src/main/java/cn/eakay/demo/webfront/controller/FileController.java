package cn.eakay.demo.webfront.controller;

import cn.eakay.demo.biz.service.FileOptService;
import cn.eakay.demo.biz.service.OrderService;
import cn.eakay.demo.client.dataobject.FastDFSFileDO;
import cn.eakay.demo.client.dataobject.FileDO;
import cn.eakay.demo.client.dataobject.OrderDO;
import cn.eakay.demo.client.param.CreateOrderParam;
import cn.eakay.demo.client.param.QueryOption;
import cn.eakay.demo.client.param.UpdateOrderParam;
import cn.eakay.demo.client.result.FileOptResultDO;
import cn.eakay.demo.client.result.SingleOrderResultDO;
import cn.eakay.demo.webfront.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.perf4j.aop.Profiled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by xugang on 16/4/7.
 *
 * @link http://websystique.com/springmvc/spring-mvc-4-restful-web-services-crud-example-resttemplate/
 */
@Slf4j
@RestController
@RequestMapping(value = "/file")
public class FileController extends BaseController {
    @Autowired
    private FileOptService fileOptService;

    /**
     * 上传文件
     * 支持批量
     *
     * @param uploadfiles
     * @return
     */
    @RequestMapping(value = "/uploads", method = RequestMethod.POST)
    public ResponseEntity<Void> uploadFileBatch(@RequestParam("uploadfiles") CommonsMultipartFile[] uploadfiles) {
        if (uploadfiles.length == 0) {
            log.error("uploads file error:{}", "param uploadfiles is empty");
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        log.info("begin uploading files " + uploadfiles.length);

        for (int i = 0; i < uploadfiles.length; i++) {
            CommonsMultipartFile multipartFile = uploadfiles[i];
            if (multipartFile == null || multipartFile.isEmpty()) {
                continue;
            }

            FastDFSFileDO fastDFSFileDO = new FastDFSFileDO();
            try {
                InputStream is = multipartFile.getInputStream();
                byte[] fileData = new byte[(int) multipartFile.getSize()];
                is.read(fileData);
                fastDFSFileDO.setName(multipartFile.getOriginalFilename());
                fastDFSFileDO.setContent(fileData);
                FileOptResultDO fileOptResultDO;
                fileOptResultDO = fileOptService.uploadFile(fastDFSFileDO);

                if (fileOptResultDO.isFailure()) {
                    log.error("uploading file failed error:{}", fileOptResultDO.getErrorCode() + fileOptResultDO.getErrorMsg());
                    return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } catch (Exception e) {
                if (log.isDebugEnabled()) {
                    e.printStackTrace();
                }
                log.error("upload file failed. Exception:", e);
                return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    /**
     * 获取文件
     *
     * @param groupName
     * @param remoteFileName
     * @return
     */
    @RequestMapping(value = "/order/{groupName}/{remoteFileName}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<FileDO> fetchingOrder(@PathVariable("groupName") String groupName,
                                                @PathVariable("remoteFileName") String remoteFileName) {

        log.info("Fetching File with groupName={},remoteFileName={}", groupName, remoteFileName);

        FileOptResultDO resultDO = fileOptService.getFile(groupName, remoteFileName);

        if (resultDO.isFailure()) {
            log.error("Fetching File failed,groupName={},remoteFileName={},error={}", new Object[]{groupName, remoteFileName, resultDO.getErrorCode() + resultDO.getErrorMsg()});
            return new ResponseEntity<FileDO>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<FileDO>(resultDO.getFileDO(), HttpStatus.OK);
    }

    /**
     * 删文件
     *
     * @param groupName
     * @param remoteFileName
     * @return
     */
    @RequestMapping(value = "/file/{id}/{groupName}/{remoteFileName}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteFile(@PathVariable("id") Long id,
                                           @PathVariable("groupName") String groupName,
                                           @PathVariable("remoteFileName") String remoteFileName) {

        log.info("Fetching & delete file with fileId={},groupName={},remoteFileName={}", new Object[]{id, groupName, remoteFileName});

        FileOptResultDO resultDO = fileOptService.deleteFile(id, groupName, remoteFileName);
        if (resultDO.isFailure()) {
            log.error("delete file failed:fileId={},groupName={},remoteFileName={},error={}", new Object[]{id, groupName, remoteFileName, resultDO.getErrorCode() + resultDO.getErrorMsg()});
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

    }
}