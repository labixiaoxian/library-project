package com.wyu.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by XiaoXian on 2020/11/17.
 */
public class UserInfo implements Serializable {

	private static final long serialVersionUID = 5025161701298001360L;

	private Integer id;

	private User user;

	private Integer age;

	private Integer sex;// 1为男 2为女 0未设置

	private Date birthday;

	private String nickname;

	private String email;

	private String telephone;

	private String address;

	private String personalDesc;

	private String picture;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPersonalDesc() {
		return personalDesc;
	}

	public void setPersonalDesc(String personalDesc) {
		this.personalDesc = personalDesc;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", user=" + user + ", age=" + age + ", sex=" + sex + ", birthday=" + birthday
				+ ", nickname=" + nickname + ", email=" + email + ", telephone=" + telephone + ", address=" + address
				+ ", personalDesc=" + personalDesc + ", picture=" + picture + "]";
	}

}
