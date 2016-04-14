package cn.eakay.demo.client.dataobject;

import cn.eakay.commons.base.BaseDO;
import lombok.Data;

import java.util.Date;

/**
 * 通用文件数据对象 可被FastDFS等实现
 *
 * @author xugang
 */
@Data
public class FileDO extends BaseDO {
    private static final long serialVersionUID = 3721716059117679633L;

    protected String sourceIpAddr;
    protected long fileSize;
    protected Date createTimestamp;
    protected int crc32;
    private String name;
    private String groupName;
    private String remoteFileName;
    private String biz;
}
