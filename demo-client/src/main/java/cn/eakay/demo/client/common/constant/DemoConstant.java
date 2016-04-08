package cn.eakay.demo.client.common.constant;

/**
 * demo ����
 * ��������
 * ����ֵ
 * client���»��õ�����DO
 *
 * @author xugang
 */
public class DemoConstant {

    /**
     * ͨ��
     */
    public static final String APPNAME = "demo";
    public static final String MINUS_SIGN = "-";
    public static final String LEFT_BRACE = "{";
    public static final String RIGHT_BRACE = "}";
    public static final String SEPERATOR = "-";
    public static final String SINGLE_SPACE = " ";
    public static final String SPLIT = ":";
    public static final String COLON = ":";
    public static final String SEMICOLON = ";";
    public static final String COMMA = ",";
    public static final String BREAK = "\\^";
    public static final String VERTICAL_LINE = "|";
    public static final String SLASH = "/";
    public static final String ENTER = "\n";
    public static final String ALIPAY_ORDER_PREFIX = "dm";
    public static final String ALIPAY_REFUND_ORDER_PREFIX = "dm-r";
    public static final String ALIPAY_SIGN_ALGO = "DSA";
    public static final String DEFAULT_CHARSET = "GB18030";
    public static final String POST_CODE = "POST";

    public static final String SUFFIX_CSV = ".csv";
    public static final String TERMINAL_DEFAULT = "--";

    /**
     * ��Ʒ����
     */
    public static final Integer ITEM_TYPE_1 = 1;
    public static final Integer ITEM_TYPE_2 = 2;
    public static final Integer ITEM_TYPE_3 = 3;

    /**
     * ֧����ʽ
     */
    public static final Integer PAYWAY_GUARANTEE = 2;
    public static final Integer PAYWAY_CAE = 3;

    /**
     * ���˴�������ֵ:ѡ����ֵΪ1,δѡ��Ϊ0
     */
    public static final Integer PEAR_PAY_YES = 1;
    public static final Integer PEAR_PAY_NO = 0;

    /**
     * AppKey
     */
    public static final String APPKEY_PC = "PC";
    public static final String APPKEY_WIRELESS = "Wireless";

    /**
     * ���ٹ����ֻ���֤��
     */
    public static final String QUICK_BUY_PHONE_VERIFY_CODE = "quick_buy_phone_verify_code";

    /**
     * Json����
     */
    public static final int IS_SUCCESS = 0;
    public static final int IS_FAIL = -1;
    public static final int IS_FAIL_PARAM = -1;
    public static final int IS_FAIL_SAVEERROR = -2;
    public static final int IS_FAIL_SYSERROR = -3;
    public static final int IS_FAIL_SESSIONERROR = -4;

    public static final String DATE_FMT = "yyyy��MM��dd�� HH:mm";
    public static final String DATE_FMT_1 = "yyyy-MM-dd HH:mm";
    public static final String DATE_FMT_2 = "HH:mm";
    public static final String DATE_FMT_3 = "MM-dd";
    public static final String DATE_FMT_4 = "MM-dd HH:mm";
    public static final Integer ITINERARY_PRICE = 1000;//10��


    /**
     * �����
     */
    public static final Integer ACTIVITYTYPE_SPECIAL_PRICE_ITEM = 1; //�ؼ���Ʒ
    public static final Integer ACTIVITYTYPE_BONUS = 2; //���

    public static final Integer ACTIVITYTYPE_COMMON_ACTIVITY = 3; //��ͨ�
    public static final Integer ACTIVITYTYPE_VIP_ACTIVITY = 4; //VIP�
    public static final Integer ACTIVITYTYPE_TICKET_FREE = 5; //�ⵥ�
    public static final Integer ACTIVITYTYPE_NEWUSER = 6; //���˻

    /**
     * ״̬����
     */
    public static final Integer ACTIVITY_STATUS_OFF = 0; //��Ч
    public static final Integer ACTIVITY_STATUS_ON = 1; //��Ч
    public static final Integer IS_DELETE = 1; //ɾ����ʶλ

    /**
     * ��������
     */
    public static final Integer SETTLEMENTTYPE_ONLINE = 1; //���Ͻ���
    public static final Integer SETTLEMENTTYPE_OFFLINE = 2; //���½���

    /**
     * ֧�����ص���ز���
     */
    public static final String ALIPAY_OUT_ORDER_NO = "out_order_no";
    public static final String ALIPAY_ERROR_CODE = "error_code";
    public static final String ALIPAY_TRADE_DEPOSIT_STAT = "depositback_state";
    public static final String ALIPAY_IS_SUCCESS = "is_success";
    public static final String ALIPAY_DEPOSIT_TIME = "notify_time";

}
