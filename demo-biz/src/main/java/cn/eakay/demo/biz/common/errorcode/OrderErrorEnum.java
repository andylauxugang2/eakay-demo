package cn.eakay.demo.biz.common.errorcode;

import cn.eakay.commons.base.ResultDO;
import cn.eakay.demo.biz.common.constant.ErrorMsg;
import lombok.Getter;

/**
 * 订单相关错误码
 * @author xugang
 */
public enum OrderErrorEnum {

    ORDER_PAY_ERROR("001", ErrorMsg.SYSTEM_ERROR),
    ORDER_NOT_EXIST("002", ErrorMsg.ORDER_NOT_EXIST);

    public static final String namespace = "order";

    @Getter
    private String errorCode;
    @Getter
    private String errorMsg;

    private OrderErrorEnum(String errorCode, String errorMsg) {
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
