package cn.eakay.demo.webfront.controller;

import cn.eakay.commons.base.performance.annotation.Performance;
import cn.eakay.commons.base.performance.common.Extent;
import cn.eakay.demo.biz.service.OrderService;
import cn.eakay.demo.client.dataobject.OrderDO;
import cn.eakay.demo.client.param.CreateOrderParam;
import cn.eakay.demo.client.param.QueryOption;
import cn.eakay.demo.client.param.UpdateOrderParam;
import cn.eakay.demo.client.result.SingleOrderResultDO;
import cn.eakay.demo.webfront.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.perf4j.aop.Profiled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by xugang on 16/4/7.
 * @link http://websystique.com/springmvc/spring-mvc-4-restful-web-services-crud-example-resttemplate/
 *
 */
@Slf4j
@RestController
@RequestMapping(value = "/")
public class OrderController extends BaseController {
    @Autowired
    private OrderService orderService;

    /**
     * Retrieve All orders
     *
     * @return
     */
    @RequestMapping(value = "/order", method = RequestMethod.GET)
//    @Performance(extent = Extent.Whole)
    @Profiled
    public ResponseEntity<List<OrderDO>> listAllOrders() {
        List<OrderDO> orderDOs = orderService.getAllOrders(new QueryOption() {
            {
                setQueryHistoryRefund(false);
                setQueryActInfo(true);
            }
        });
        if (CollectionUtils.isEmpty(orderDOs)) {
            return new ResponseEntity<List<OrderDO>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<OrderDO>>(orderDOs, HttpStatus.OK);
    }

    /**
     * Retrieve Single order
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<OrderDO> getOrder(@PathVariable("id") long id) {
        log.info("Fetching Order with id:{}", id);

        SingleOrderResultDO resultDO = orderService.getOrderById(id, new QueryOption() {
            {
                setQueryHistoryRefund(false);
                setQueryActInfo(true);
            }
        });
        if (!resultDO.isSuccess()) {
            log.error("Order with id:{} found error:{}", id, resultDO.getErrorCode() + resultDO.getErrorMsg());
            return new ResponseEntity<OrderDO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<OrderDO>(resultDO.getOrder(), HttpStatus.OK);
    }

    /**
     * create Order
     *
     * @param createOrderParam
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "/order/", method = RequestMethod.POST)
    public ResponseEntity<Void> createOrder(@RequestBody CreateOrderParam createOrderParam, UriComponentsBuilder ucBuilder) {
        log.info("Creating Order " + createOrderParam.getBuyerId());

        OrderDO orderDO = buildOrderDO(createOrderParam);
        SingleOrderResultDO resultDO = orderService.createOrder(orderDO);
        if (!resultDO.isSuccess()) {
            log.error("Create Order error:{}", resultDO.getErrorCode() + resultDO.getErrorMsg());
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/order/{id}").buildAndExpand(resultDO.getOrder().getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    private OrderDO buildOrderDO(CreateOrderParam createOrderParam) {
        OrderDO orderDO = new OrderDO();
        orderDO.setOrderType(createOrderParam.getOrderType());
        orderDO.setBuyerId(createOrderParam.getBuyerId());
        return orderDO;
    }

    /**
     * upate order
     * @param id
     * @param upateOrderParam
     * @return
     */
    @RequestMapping(value = "/order/{id}", method = RequestMethod.PUT)
    public ResponseEntity<OrderDO> updateOrder(@PathVariable("id") long id, @RequestBody UpdateOrderParam upateOrderParam) {
        log.info("Updating order id:{} ", id);
        SingleOrderResultDO resultDO = orderService.getOrderById(id, new QueryOption() {
            {
                setQueryHistoryRefund(false);
                setQueryActInfo(true);
            }
        });

        if (!resultDO.isSuccess()) {
            log.error("Order with id:{} found error:{}", id, resultDO.getErrorCode() + resultDO.getErrorMsg());
            return new ResponseEntity<OrderDO>(HttpStatus.NOT_FOUND);
        }

        OrderDO currentOrder = resultDO.getOrder();
        currentOrder.setOrderType(upateOrderParam.getOrderType());
        resultDO = orderService.updateOrder(currentOrder);
        if (!resultDO.isSuccess()) {
            log.error("updateOrder with id:{} error:{}", id, resultDO.getErrorCode() + resultDO.getErrorMsg());
            return new ResponseEntity<OrderDO>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<OrderDO>(currentOrder, HttpStatus.OK);
    }

    /**
     * delete order
     * @param id
     * @return
     */
    @RequestMapping(value = "/order/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<OrderDO> deleteOrder(@PathVariable("id") long id) {
        log.info("Fetching & Deleting Order with id " + id);
        SingleOrderResultDO resultDO = orderService.getOrderById(id, new QueryOption() {
            {
                setQueryHistoryRefund(false);
                setQueryActInfo(true);
            }
        });
        if (!resultDO.isSuccess()) {
            log.error("Order with id:{} found error:{}", id, resultDO.getErrorCode() + resultDO.getErrorMsg());
            return new ResponseEntity<OrderDO>(HttpStatus.NOT_FOUND);
        }
        resultDO = orderService.deleteOrderById(id);
        if (!resultDO.isSuccess()) {
            log.error("deleteOrder with id:{} error:{}", id, resultDO.getErrorCode() + resultDO.getErrorMsg());
            return new ResponseEntity<OrderDO>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<OrderDO>(HttpStatus.NO_CONTENT);
    }

    /**
     * delete all orders
     * @return
     */
    @RequestMapping(value = "/order/", method = RequestMethod.DELETE)
    public ResponseEntity<OrderDO> deleteAllOrders() {
        log.info("Deleting All Orders");
        SingleOrderResultDO resultDO = orderService.deleteAllOrders();
        if (!resultDO.isSuccess()) {
            log.error("deleteAllOrders error:{}", resultDO.getErrorCode() + resultDO.getErrorMsg());
            return new ResponseEntity<OrderDO>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<OrderDO>(HttpStatus.NO_CONTENT);
    }
}
