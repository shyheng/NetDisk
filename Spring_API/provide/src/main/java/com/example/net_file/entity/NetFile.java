package com.example.net_file.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author shyheng
 * @since 2022-05-26
 */
@TableName("net_file")
public class NetFile implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String imgGroup;

    private String imgRemtoe;

    private String size;

    private String fileName;

    private String type;

    private String date;

    private Integer uid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getImgGroup() {
        return imgGroup;
    }

    public void setImgGroup(String imgGroup) {
        this.imgGroup = imgGroup;
    }
    public String getImgRemtoe() {
        return imgRemtoe;
    }

    public void setImgRemtoe(String imgRemtoe) {
        this.imgRemtoe = imgRemtoe;
    }
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "NetFile{" +
            "id=" + id +
            ", imgGroup=" + imgGroup +
            ", imgRemtoe=" + imgRemtoe +
            ", size=" + size +
            ", fileName=" + fileName +
            ", type=" + type +
            ", date=" + date +
            ", uid=" + uid +
        "}";
    }
}
