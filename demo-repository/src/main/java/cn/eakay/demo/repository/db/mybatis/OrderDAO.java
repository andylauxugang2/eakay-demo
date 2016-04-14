package cn.eakay.demo.repository.db.mybatis;

import cn.eakay.demo.client.dataobject.OrderDO;

import java.util.List;

/**
 * @description 订单dao 如果需要垮库访问，可以使用tddl DBRoute 可以继承Base下的crossdb base类
 * 可以使用commons包中的公用base repository 类
 * 可以对dao添加埋点和统计 需要继承base类
 * @created by xugang on 16/4/7.
 */
public interface OrderDAO {

    OrderDO queryOrderById(Long orderId);

    List<OrderDO> queryOrder();
}