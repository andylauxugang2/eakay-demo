package cn.eakay.demo.client.dataobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * FastDFSFile文件数据对象
 *
 * @author xugang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FastDFSFileDO extends FileDO {

    public static final String AUTHOR = "Eakay";
    public static final String HEIGHT = "120";
    public static final String WIDTH = "120";

    private byte[] content;
    private String ext;
    private String height = HEIGHT;
    private String width = WIDTH;
    private String author = AUTHOR;

    public FastDFSFileDO(String name, byte[] content, String ext) {
        super();
        this.setName(name);
        this.content = content;
        this.ext = ext;
    }
}
