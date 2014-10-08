package com.zigui.domain;

import java.io.Serializable;
import java.util.List;

public class Province  implements Serializable{
	
	private long id;
	
	private String name;
	
	private List<City> citys;

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

	public List<City> getCitys() {
		return citys;
	}

	public void setCitys(List<City> citys) {
		this.citys = citys;
	}
	
	

}
