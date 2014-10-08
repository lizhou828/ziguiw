package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "config_center")
@SequenceGenerator(name = "CONFIG_CENTER_SEQ", sequenceName = "config_center_seq", allocationSize = 1)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ConfigCenter implements Serializable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONFIG_CENTER_SEQ")
	private long id;
	
	private String content;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	

}
