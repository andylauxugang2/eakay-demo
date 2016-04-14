package cn.eakay.demo.biz.common.errorcode;

import cn.eakay.commons.base.ResultDO;
import cn.eakay.demo.biz.common.constant.ErrorMsg;
import lombok.Getter;

/**
 * File相关错误码
 * @author xugang
 */
public enum FileErrorEnum {

    FILE_EMPTY_ERROR("001", ErrorMsg.FILE_EMPTY_ERROR),
    FILE_UPLOAD_ERROR("002", ErrorMsg.FILE_UPLOAD_ERROR),
    FILE_FETCH_ERROR("003", ErrorMsg.FILE_FETCH_ERROR),
    FILE_DELETE_ERROR("004", ErrorMsg.FILE_DELETE_ERROR),
    FILE_DB_NOT_EXISTS_ERROR("010", ErrorMsg.FILE_DB_NOT_EXISTS_ERROR),
    FILE_CONTEXT_NOT_FOUND_ERROR("011", ErrorMsg.FILE_CONTEXT_NOT_FOUND_ERROR);

    public static final String namespace = "file";

    @Getter
    private String errorCode;
    @Getter
    private String errorMsg;

    private FileErrorEnum(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public boolean isEqual(ResultDO rs) {
        return this.getErrorCode().equals(rs.getErrorCode());
    }

    public void fillResult(ResultDO rs) {
        rs.setErrorCode(getErrorCode());
        rs.setErrorMsg(getErrorMsg());
    }

}
