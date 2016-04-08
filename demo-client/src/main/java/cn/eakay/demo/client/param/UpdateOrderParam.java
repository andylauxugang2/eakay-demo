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
public class UpdateOrderParam extends BaseParam {

    Long id;//买家Id

    Integer orderType;
}
