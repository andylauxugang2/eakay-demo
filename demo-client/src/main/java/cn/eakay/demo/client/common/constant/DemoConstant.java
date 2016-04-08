package cn.eakay.demo.client.common.constant;

/**
 * demo 常量
 * 各种类型
 * 各种值
 * client包下会用到，如DO
 *
 * @author xugang
 */
public class DemoConstant {

    /**
     * 通用
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
     * 商品类型
     */
    public static final Integer ITEM_TYPE_1 = 1;
    public static final Integer ITEM_TYPE_2 = 2;
    public static final Integer ITEM_TYPE_3 = 3;

    /**
     * 支付方式
     */
    public static final Integer PAYWAY_GUARANTEE = 2;
    public static final Integer PAYWAY_CAE = 3;

    /**
     * 找人代付参数值:选择了值为1,未选择为0
     */
    public static final Integer PEAR_PAY_YES = 1;
    public static final Integer PEAR_PAY_NO = 0;

    /**
     * AppKey
     */
    public static final String APPKEY_PC = "PC";
    public static final String APPKEY_WIRELESS = "Wireless";

    /**
     * 快速购买手机验证码
     */
    public static final String QUICK_BUY_PHONE_VERIFY_CODE = "quick_buy_phone_verify_code";

    /**
     * Json返回
     */
    public static final int IS_SUCCESS = 0;
    public static final int IS_FAIL = -1;
    public static final int IS_FAIL_PARAM = -1;
    public static final int IS_FAIL_SAVEERROR = -2;
    public static final int IS_FAIL_SYSERROR = -3;
    public static final int IS_FAIL_SESSIONERROR = -4;

    public static final String DATE_FMT = "yyyy年MM月dd日 HH:mm";
    public static final String DATE_FMT_1 = "yyyy-MM-dd HH:mm";
    public static final String DATE_FMT_2 = "HH:mm";
    public static final String DATE_FMT_3 = "MM-dd";
    public static final String DATE_FMT_4 = "MM-dd HH:mm";
    public static final Integer ITINERARY_PRICE = 1000;//10分


    /**
     * 活动类型
     */
    public static final Integer ACTIVITYTYPE_SPECIAL_PRICE_ITEM = 1; //特价商品
    public static final Integer ACTIVITYTYPE_BONUS = 2; //红包

    public static final Integer ACTIVITYTYPE_COMMON_ACTIVITY = 3; //普通活动
    public static final Integer ACTIVITYTYPE_VIP_ACTIVITY = 4; //VIP活动
    public static final Integer ACTIVITYTYPE_TICKET_FREE = 5; //免单活动
    public static final Integer ACTIVITYTYPE_NEWUSER = 6; //新人活动

    /**
     * 状态类型
     */
    public static final Integer ACTIVITY_STATUS_OFF = 0; //无效
    public static final Integer ACTIVITY_STATUS_ON = 1; //有效
    public static final Integer IS_DELETE = 1; //删除标识位

    /**
     * 结算类型
     */
    public static final Integer SETTLEMENTTYPE_ONLINE = 1; //线上结算
    public static final Integer SETTLEMENTTYPE_OFFLINE = 2; //线下结算

    /**
     * 支付宝回调相关参数
     */
    public static final String ALIPAY_OUT_ORDER_NO = "out_order_no";
    public static final String ALIPAY_ERROR_CODE = "error_code";
    public static final String ALIPAY_TRADE_DEPOSIT_STAT = "depositback_state";
    public static final String ALIPAY_IS_SUCCESS = "is_success";
    public static final String ALIPAY_DEPOSIT_TIME = "notify_time";

}
