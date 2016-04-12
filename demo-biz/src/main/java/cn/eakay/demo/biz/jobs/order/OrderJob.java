package cn.eakay.demo.biz.jobs.order;

import cn.eakay.commons.job.BaseJob;
import cn.eakay.commons.job.base.quartz.Cron;
import cn.eakay.demo.biz.service.OrderService;
import cn.eakay.demo.client.dataobject.OrderDO;
import cn.eakay.demo.client.param.QueryOption;
import lombok.extern.slf4j.Slf4j;
import org.perf4j.aop.Profiled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by xugang on 16/4/12.
 */
@Slf4j
public class OrderJob extends BaseJob {

    @Autowired
    private OrderService orderService;

    /**
     * 扫描所有未付款的订单
     * 如果是支付超时的delete
     */
    @Profiled
    @Cron(value = "0 */1 * * * ?", desc = "超时未支付的订单delete")
    @Override
    protected void work() throws Exception {
        log.info("未付款的订单超时delete任务开始");
        //TODO 先查Ids 再根据Ids更新比较快
        List<OrderDO> orderDOs = orderService.getAllOrders(new QueryOption() {
            {
                setQueryHistoryRefund(false);
                setQueryActInfo(true);
            }
        });
        if (CollectionUtils.isEmpty(orderDOs)) {
            log.info("OrderJob未扫描到未支付订单");
            return;
        }

        for (OrderDO orderDO : orderDOs) {
            log.info("delete unpay orderId:" + orderDO.getId());
            orderDO.setOrderType(OrderDO.ORDER_TYPE_OLD);
            orderService.updateOrder(orderDO);
        }
    }
}
