package cn.eakay.demo.client.param;


import cn.eakay.commons.base.BaseParam;
import lombok.Data;

/**
 * 一般传到controller层 就用完了
 * 使用aop 对 validation统一处理
 * 如果内网rpc接口互调，可不适用Param，直接用DO，此Param被Controller使用
 * @author xugang
 */
@Data
public class CreateOrderParam extends BaseParam {

    public static final int NEEDSAVE = 1;
    public static final int NOT_NEEDSAVE = 0;

    Long buyerId;//买家Id

    Integer orderType;
}
