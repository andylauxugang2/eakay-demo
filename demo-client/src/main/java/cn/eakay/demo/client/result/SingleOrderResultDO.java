package cn.eakay.demo.client.result;

import cn.eakay.commons.base.ResultDO;
import cn.eakay.demo.client.dataobject.OrderDO;
import lombok.Data;

import java.util.Date;

/**
 * service接口处理结果对象
 * 被service web-front controller用到
 * @author xugang
 */
@Data
public class SingleOrderResultDO extends ResultDO {

	private static final long serialVersionUID = -8377627780593024439L;

	private OrderDO order;
	private boolean isHistory;//是否历史订单
	private Date confirmTimeoutDate;//已确认超时时刻

	//其他DO
}
