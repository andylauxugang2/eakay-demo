package cn.eakay.demo.biz.manager;

import cn.eakay.commons.dao.persistence.exception.DAOException;
import cn.eakay.demo.client.dataobject.OrderDO;
import cn.eakay.demo.client.param.QueryOption;

import java.util.List;

/**
 *
 * @description 订单相关的业务逻辑
 * @version 1.0
 * @author xugang
 * @update 2016-4-7
 */
public interface OrderManager {

    OrderDO getOrderById(Long orderId) throws DAOException;
    
    OrderDO getOrderById(Long orderId, QueryOption option) throws DAOException;

    List<OrderDO> getOrders(QueryOption option) throws DAOException;

}
