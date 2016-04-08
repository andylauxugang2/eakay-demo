package cn.eakay.demo.client.param;


import cn.eakay.commons.base.BaseParam;
import lombok.Data;

/**
 * һ�㴫��controller�� ��������
 * ʹ��aop �� validationͳһ����
 * �������rpc�ӿڻ������ɲ�����Param��ֱ����DO����Param��Controllerʹ��
 * @author xugang
 */
@Data
public class CreateOrderParam extends BaseParam {

    public static final int NEEDSAVE = 1;
    public static final int NOT_NEEDSAVE = 0;

    Long buyerId;//���Id

    Integer orderType;
}
