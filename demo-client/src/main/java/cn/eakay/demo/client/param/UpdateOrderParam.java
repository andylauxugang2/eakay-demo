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
public class UpdateOrderParam extends BaseParam {

    Long id;//���Id

    Integer orderType;
}
