package cn.eakay.demo.client.dataobject;

import cn.eakay.commons.base.BaseDO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 活动实例对象，可根据ActivityDetailDO（活动模板）自动生成
 * 
 * @author xugang
 * 
 */
@Data
public class ActivityDO extends BaseDO {

    private static final long serialVersionUID = 6436378299731283443L;

    private String name;
    private Integer activityType;

}
