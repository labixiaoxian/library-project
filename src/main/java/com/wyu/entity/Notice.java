package com.wyu.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * 
 * @author 李达成
 * @since 2020/11/17
 *
 */
public class Notice implements Serializable {
	// 公告ID
	private Integer id;
	// 公告名
	private String noticeName;
	// 公告内容
	private String content;
	// 发布时间
	private Timestamp releaseTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNoticeName() {
		return noticeName;
	}

	public void setNoticeName(String noticeName) {
		this.noticeName = noticeName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Timestamp releaseTime) {
		this.releaseTime = releaseTime;
	}

	@Override
	public int hashCode() {
		return Objects.hash(content, id, noticeName, releaseTime);
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

	@Override
	public String toString() {
		return "Notice [id=" + id + ", noticeName=" + noticeName + ", content=" + content + ", releaseTime="
				+ releaseTime + "]";
	}

	public Notice(Integer id, String noticeName, String content, Timestamp releaseTime) {
		// super();
		this.id = id;
		this.noticeName = noticeName;
		this.content = content;
		this.releaseTime = releaseTime;
	}

	public Notice() {
		// super();
	}

}
