package cn.eakay.demo.client.result;

import cn.eakay.commons.base.ResultDO;
import cn.eakay.demo.client.dataobject.FileDO;
import cn.eakay.demo.client.dataobject.OrderDO;
import lombok.Data;

import java.util.Date;

/**
 * 文件操作结果数据对象
 * @author xugang
 */
@Data
public class FileOptResultDO extends ResultDO {
	private static final long serialVersionUID = 339308308497129808L;

	private FileDO fileDO;

}
