package com.wyu.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @author 李达成
 * @since 2020/11/17
 *
 */
@ApiModel
public class Notice implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    // 公告ID
    @ApiModelProperty(value = "公告ID", example = "1")
    private Integer id;

    // 公告名
    @ApiModelProperty(value = "公告标题", example = "标题1")
    private String noticeName;

    // 公告内容
    @ApiModelProperty(value = "公告内容", example = "内容1")
    private String content;

    // 发布时间
    @ApiModelProperty(value = "发布时间", example = "2020/11/18")
    private Timestamp releaseTime;

    public Notice() {
        // super();
    }

    public Notice(Integer id, String noticeName, String content, Timestamp releaseTime) {
        // super();
        this.id = id;
        this.noticeName = noticeName;
        this.content = content;
        this.releaseTime = releaseTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Notice)) {
            return false;
        }
        Notice other = (Notice) obj;
        return Objects.equals(content, other.content) && Objects.equals(id, other.id)
                && Objects.equals(noticeName, other.noticeName) && Objects.equals(releaseTime, other.releaseTime);
    }

    public String getContent() {
        return content;
    }

    public Integer getId() {
        return id;
    }

    public String getNoticeName() {
        return noticeName;
    }

    public Timestamp getReleaseTime() {
        return releaseTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, id, noticeName, releaseTime);
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNoticeName(String noticeName) {
        this.noticeName = noticeName;
    }

    public void setReleaseTime(Timestamp releaseTime) {
        this.releaseTime = releaseTime;
    }

    @Override
    public String toString() {
        return "Notice [id=" + id + ", noticeName=" + noticeName + ", content=" + content + ", releaseTime="
                + releaseTime + "]";
    }

}
