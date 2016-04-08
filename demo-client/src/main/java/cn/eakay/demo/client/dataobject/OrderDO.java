package cn.eakay.demo.client.dataobject;

import cn.eakay.commons.base.BaseDO;
import cn.eakay.demo.client.common.constant.DemoConstant;
import lombok.Data;
import java.util.*;

/**
 * 订单数据对象
 * DO属于全局对象 被后续的处理如service ao manager handler等用到
 *
 * @author xugang
 */
@Data
public class OrderDO extends BaseDO {

    private static final long serialVersionUID = 7302260880805552158L;

    public static final Integer ORDER_TYPE_OLD = 0;//老订单
    public static final Integer ORDER_TYPE_NEW = 1;//新订单

    private Integer orderType;//订单类型：1:新；0:旧
    private Long parentId;// 父订单id。
    private Long buyerId;// 买家淘宝id
    private String buyerNick;// 买家淘宝nick
    private String buyerAlipayId;// 买家支付宝id
    private String buyerAlipayEmail;// 买家支付宝Email
    private Integer status;// 订单状态
    private Integer payStatus;// 订单支付状态

    private Integer delStatus;// 删除状态
    private boolean isAct = false;// 是否活动订单
    private Integer totalPrice;// 订单总价（含代理商调价之后的价格）
    private Integer payWay;// 支付渠道
    private String alipayTradeNo;// 支付宝交易号
    private String createAppkey = DemoConstant.APPNAME;// 下单appKey
    private String payAppkey;// 支付appKey
    //    private RelationDO relation;// 联系人
    private String failMemo;// 订单失败备注
    private Integer refer;// 订单来源
    private String scene;// 来源场景
    //    private TrafficDO trafficDO;// 物流信息
    private Double score;// 评分
    private Integer accessSubType;// 支付宝支付渠道
    private Date payTime;// 买家支付成功时间
    private Date pay2sellerTime;// 钱转账给卖家的时间
    private Date payLatestTime;// 买家最迟支付时间
    private Date processLatestTime;// 卖家最迟处理时间
    private String memo;// 备注
    private String outOrderNo; // 商户订单号

    private List<ActivityDO> activityDOs = new ArrayList<>();

    private Map<String, String> attrMap = new HashMap<String, String>();// attributes内容解析成Map保存

    /**
     * 计算买家实际应该支付多少钱 商品总价 - 活动优惠
     */
    public Integer getBuyerShouldPay() {
        return this.totalPrice - getTotalOffPricesInAct();
    }

    /**
     * 计算卖家实际应该得到的总钱
     */
    public Integer getRealTotalPrice() {
        return this.totalPrice;
    }

    /**
     * 计算该订单活动总共优惠了多少钱
     */
    public Integer getTotalOffPricesInAct() {
        if (!this.isAct()) {
            return 0;
        }
        Integer totalOffInCent = 0;
        /*for (ActTicketDO actOrder : this.getActDOs()) {
            totalOffInCent += actOrder.getOffPrice();
        }*/
        return totalOffInCent;
    }

    /*public String getAttributes() {
        try {
            if (!attrMap.isEmpty()) {
                //移除alue是null的属性
                clearNullValue(attrMap);
                JSONObject obj = JSONObject.fromObject(attrMap);
                return obj.toString();
            }
        } catch (Throwable t) {

        }
        return "";
    }

    private void clearNullValue(Map<String, String> attrMap) {
        List<String> needRemove = new ArrayList<String>();
        for (String key : attrMap.keySet()) {
            if (attrMap.get(key) == null || JSONNull.getInstance().equals(attrMap.get(key))) {
                needRemove.add(key);
            }
        }
        if (needRemove.size() > 0) {
            for (String key : needRemove) {
                attrMap.remove(key);
            }
        }
    }

    public void setAttributes(String attributes) {
        try {
            if (StringUtils.isNotBlank(attributes)) {
                JSONObject json = JSONObject.fromObject(attributes);
                attrMap = (Map<String, String>) JSONObject.toBean(json, attrMap.getClass());
                clearNullValue(attrMap);
            }
        } catch (Throwable t) {

        }
    }*/

    /**
     * 添加属性
     */
    public void addAttr(String key, String value) {
        this.attrMap.put(key, value);
    }

    /**
     * 根据key获取属性值
     */
    public String getAttr(String key) {
        try {
            String v = this.attrMap.get(key);
            return v;
        } catch (Throwable t) {
            return "";
        }
    }

    /**
     * 获取支付超时的时间，单位是分
     */
    public int getPayExpireTime() {
        return (int) Math.floor((getPayLatestTime().getTime() - (new Date()).getTime()) / 1000.0 / 60.0);
    }
}
