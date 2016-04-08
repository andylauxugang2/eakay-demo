package cn.eakay.demo.biz.manager.impl;

import cn.eakay.commons.dao.persistence.exception.DAOException;
import cn.eakay.demo.biz.manager.OrderManager;
import cn.eakay.demo.client.dataobject.ActivityDO;
import cn.eakay.demo.client.dataobject.OrderDO;
import cn.eakay.demo.client.param.QueryOption;
import cn.eakay.demo.repository.db.mybatis.ActivityDAO;
import cn.eakay.demo.repository.db.mybatis.OrderDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * manager实现类
 * 直接方位DAO 或者 处理消息 或者处理各种回调
 */
@Slf4j
@Component
public class OrderManagerImpl implements OrderManager {
    @Autowired
    public OrderDAO orderDAO;
    @Autowired
    protected ActivityDAO activityDAO;

    //其他manager

    @Override
    public OrderDO getOrderById(Long orderId) throws DAOException {
        return null;
    }

    @Override
    public OrderDO getOrderById(Long orderId, QueryOption option) throws DAOException {
        OrderDO orderDO = orderDAO.queryOrderById(orderId);
        if (orderDO == null)
            return null;

        _commonBuildAll(orderDO, option);

        return orderDO;
    }

    @Override
    public List<OrderDO> getOrders(QueryOption option) throws DAOException {
        List<OrderDO> orderDOs = orderDAO.queryOrder();
        if (orderDOs == null || orderDOs.size() == 0)
            return null;

        for(OrderDO orderDO : orderDOs){
            _commonBuildAll(orderDO, option);
        }

        return orderDOs;
    }

    private void _commonBuildAll(OrderDO order, QueryOption option) throws DAOException {
        // 构造活动订单
        buildActOrder(order);
    }


    private void buildActOrder(OrderDO orderDO) throws DAOException {
        if (orderDO.isAct()) {
            List<ActivityDO> activityDOs = activityDAO.queryActivityDOsByOrderId(orderDO.getId());
            orderDO.setActivityDOs(activityDOs);
        }
    }
}