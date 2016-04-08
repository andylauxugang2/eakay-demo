package cn.eakay.demo.client.result;

import cn.eakay.commons.base.ResultDO;
import cn.eakay.demo.client.dataobject.OrderDO;
import lombok.Data;

import java.util.Date;

/**
 * service�ӿڴ���������
 * ��service web-front controller�õ�
 * @author xugang
 */
@Data
public class SingleOrderResultDO extends ResultDO {

	private static final long serialVersionUID = -8377627780593024439L;

	private OrderDO order;
	private boolean isHistory;//�Ƿ���ʷ����
	private Date confirmTimeoutDate;//��ȷ�ϳ�ʱʱ��

	//����DO
}
