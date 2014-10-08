package com.zigui.domain;

import java.io.Serializable;

public class City implements Serializable{
	
	private long id;
	
	private String name;
	
	private Province province;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

}
