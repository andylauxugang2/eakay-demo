package cn.eakay.demo.client.param;

import lombok.Data;

import java.io.Serializable;

/**
 * 查询选项
 * 位于 client包中
 * 属于param 外部调用接口时使用
 * 一直会传到manager，控制数据的获取
 */
@Data
public class QueryOption implements Serializable {

    private static final long serialVersionUID = 2665666246985734627L;

    /**
     * 下列信息常用，默认查询
     */
    private boolean queryRefund = true; //是否查询当前退款记录
    /**
     * 下列信息非核心内容，默认不查询*
     */
    private boolean queryHistoryRefund = false;//是否查询历史退款记录
    private boolean queryOpLog = false;//是否查询操作日志
    private boolean queryPriceLog = false;//是否查询价格变化日志
    private boolean queryActInfo = false; //是否加载活动信息
    private boolean queryItemSnapShot = false;//是否加载产品快照
    private boolean queryOrderPayInfo = false;//是否查询支付信息（包含需合并支付的金额等）
    private boolean queryUrgeInfo = false;//是否查询催单记录

}
