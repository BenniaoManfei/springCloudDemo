package com.daniel.cloud.model.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 用户实体
 * 
 * @Description: TODO
 * 
 * @author DanielDai
 *
 * @date 2017年9月8日
 *
 */
public class User implements Serializable {

	private Long id;
	
	private String username;
	
	private String name;
	
	private short age;
	
	private BigDecimal balance;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getAge() {
		return age;
	}

	public void setAge(short age) {
		this.age = age;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", name=" + name + ", age=" + age + ", balance="
				+ balance + "]";
	}
	
}
