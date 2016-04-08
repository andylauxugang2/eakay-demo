package cn.eakay.demo.biz.service;

import cn.eakay.demo.client.dataobject.OrderDO;
import cn.eakay.demo.client.param.QueryOption;
import cn.eakay.demo.client.result.SingleOrderResultDO;
import org.springframework.core.annotation.Order;

import java.util.List;

/**
 * 订单业务接口
 * 所有相关订单的业务都放到该接口中
 * <p/>
 * 复杂业务需要调用manager
 * 如果使用rpc 系统间service调用则需要添加更细的业务逻辑组件，如AO
 * 注意：service逻辑组件不要细化太多方法，如要细化可放到manger或ao里面
 *
 * @author xugang
 */
public interface OrderService {

    /**
     * 单个订单查询(根据订单id)
     *  
     * @param orderId 订单号
     * @param option  查询选项 如果订单关联信息比较多 需要添加option选项 控制需要的查询结果
     */
    SingleOrderResultDO getOrderById(Long orderId, QueryOption option);

    /**
     * 根据父订单号查询所有关联订单
     *
     * @param parentId 父订单号
     * @param option   查询选项
     */
    List<SingleOrderResultDO> getOrdersByParentId(Long parentId, Long buyerId, QueryOption option);

    List<OrderDO> getAllOrders(QueryOption option);

    SingleOrderResultDO createOrder(OrderDO orderDO);

    SingleOrderResultDO updateOrder(OrderDO currentOrder);

    SingleOrderResultDO deleteOrderById(long id);

    SingleOrderResultDO deleteAllOrders();
}
