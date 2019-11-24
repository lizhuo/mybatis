package com.tt.mybatis.po;

import java.util.Date;

/**
 * @author lizhuo
 * @Description: User
 * @date 2019-11-21 15:07
 */
public class User {

	private int id;
	private String username;
	private Date birthday;
	private String sex;
	private String address;

	@Override
	public String toString() {
		return "id=" + id + ", username:" + username + ", sex:" + sex + ", address:" + address
				+ ", birthday:" + birthday;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
