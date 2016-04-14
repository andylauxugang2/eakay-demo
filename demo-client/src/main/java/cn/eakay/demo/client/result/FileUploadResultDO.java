package cn.eakay.demo.client.result;

import cn.eakay.commons.base.ResultDO;
import lombok.Data;

/**
 * 文件操作结果数据对象
 * @author xugang
 */
@Data
public class FileUploadResultDO extends ResultDO {
	private static final long serialVersionUID = -1587433265932391659L;
	private String groupName;
	private String remoteFileName;

}
