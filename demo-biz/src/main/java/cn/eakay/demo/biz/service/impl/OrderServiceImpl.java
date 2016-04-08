package cn.eakay.demo.biz.service.impl;

import cn.eakay.commons.base.performance.annotation.Performance;
import cn.eakay.demo.biz.common.errorcode.OrderErrorEnum;
import cn.eakay.demo.biz.manager.OrderManager;
import cn.eakay.demo.biz.service.OrderService;
import cn.eakay.demo.client.dataobject.OrderDO;
import cn.eakay.demo.client.param.QueryOption;
import cn.eakay.demo.client.result.SingleOrderResultDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 如果业务复杂需要引入AO来处理，如果不复杂直接使用manager去操作dao 或者直接使用dao也可以
 * Created by xugang on 16/4/7.
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderManager orderManager;

    @Performance(desc = "根据id获取单个订单")
    @Override
    public SingleOrderResultDO getOrderById(Long orderId, QueryOption option) {
        SingleOrderResultDO rs = new SingleOrderResultDO();

        try {
            //1.查询
            OrderDO order = orderManager.getOrderById(orderId, option);
            if (order == null) {
                OrderErrorEnum.ORDER_NOT_EXIST.fillResult(rs);
                return rs;
            }
            //2.通用查询
            rs = _commonSingleOrderQuery(order, option);
        } catch (Exception e) {
            OrderErrorEnum.ORDER_NOT_EXIST.fillResult(rs);
            log.error("查询订单失败,orderId:" + orderId, e);
            return rs;
        }
        return rs;
    }

    public List<SingleOrderResultDO> getOrdersByParentId(Long parentId, Long buyerId, QueryOption option) {
        return null;
    }

    @Override
    public List<OrderDO> getAllOrders(QueryOption option) {
        try {
            //1.查询
            List<OrderDO> orderDOs = orderManager.getOrders(option);
            return orderDOs;
        } catch (Exception e) {
            log.error("查询订单失败,option:" + option, e);
            return null;
        }
    }

    @Override
    public SingleOrderResultDO createOrder(OrderDO orderDO) {
        return new SingleOrderResultDO();
    }

    @Override
    public SingleOrderResultDO updateOrder(OrderDO currentOrder) {
        return null;
    }

    @Override
    public SingleOrderResultDO deleteOrderById(long id) {
        return new SingleOrderResultDO();
    }

    @Override
    public SingleOrderResultDO deleteAllOrders() {
        return new SingleOrderResultDO();
    }

    private SingleOrderResultDO _commonSingleOrderQuery(OrderDO order, QueryOption option) {
        SingleOrderResultDO rs = new SingleOrderResultDO();
        rs.setOrder(order);
        if (option.isQueryActInfo()) {
            //查询活动信息 rs.set 活动DO
        }
        return rs;
    }
}
