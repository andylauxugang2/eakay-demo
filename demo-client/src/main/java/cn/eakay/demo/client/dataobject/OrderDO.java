package cn.eakay.demo.client.dataobject;

import cn.eakay.commons.base.BaseDO;
import cn.eakay.demo.client.common.constant.DemoConstant;
import lombok.Data;
import java.util.*;

/**
 * �������ݶ���
 * DO����ȫ�ֶ��� �������Ĵ�����service ao manager handler���õ�
 *
 * @author xugang
 */
@Data
public class OrderDO extends BaseDO {

    private static final long serialVersionUID = 7302260880805552158L;

    public static final Integer ORDER_TYPE_OLD = 0;//�϶���
    public static final Integer ORDER_TYPE_NEW = 1;//�¶���

    private Integer orderType;//�������ͣ�1:�£�0:��
    private Long parentId;// ������id��
    private Long buyerId;// ����Ա�id
    private String buyerNick;// ����Ա�nick
    private String buyerAlipayId;// ���֧����id
    private String buyerAlipayEmail;// ���֧����Email
    private Integer status;// ����״̬
    private Integer payStatus;// ����֧��״̬

    private Integer delStatus;// ɾ��״̬
    private boolean isAct = false;// �Ƿ�����
    private Integer totalPrice;// �����ܼۣ��������̵���֮��ļ۸�
    private Integer payWay;// ֧������
    private String alipayTradeNo;// ֧�������׺�
    private String createAppkey = DemoConstant.APPNAME;// �µ�appKey
    private String payAppkey;// ֧��appKey
    //    private RelationDO relation;// ��ϵ��
    private String failMemo;// ����ʧ�ܱ�ע
    private Integer refer;// ������Դ
    private String scene;// ��Դ����
    //    private TrafficDO trafficDO;// ������Ϣ
    private Double score;// ����
    private Integer accessSubType;// ֧����֧������
    private Date payTime;// ���֧���ɹ�ʱ��
    private Date pay2sellerTime;// Ǯת�˸����ҵ�ʱ��
    private Date payLatestTime;// ������֧��ʱ��
    private Date processLatestTime;// ������ٴ���ʱ��
    private String memo;// ��ע
    private String outOrderNo; // �̻�������

    private List<ActivityDO> activityDOs = new ArrayList<>();

    private Map<String, String> attrMap = new HashMap<String, String>();// attributes���ݽ�����Map����

    /**
     * �������ʵ��Ӧ��֧������Ǯ ��Ʒ�ܼ� - ��Ż�
     */
    public Integer getBuyerShouldPay() {
        return this.totalPrice - getTotalOffPricesInAct();
    }

    /**
     * ��������ʵ��Ӧ�õõ�����Ǯ
     */
    public Integer getRealTotalPrice() {
        return this.totalPrice;
    }

    /**
     * ����ö�����ܹ��Ż��˶���Ǯ
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
                //�Ƴ�alue��null������
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
     * �������
     */
    public void addAttr(String key, String value) {
        this.attrMap.put(key, value);
    }

    /**
     * ����key��ȡ����ֵ
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
     * ��ȡ֧����ʱ��ʱ�䣬��λ�Ƿ�
     */
    public int getPayExpireTime() {
        return (int) Math.floor((getPayLatestTime().getTime() - (new Date()).getTime()) / 1000.0 / 60.0);
    }
}
