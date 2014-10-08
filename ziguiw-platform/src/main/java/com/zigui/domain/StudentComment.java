package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * create table STUDENT_ERVIEW_LIBRAY(
 * rtc_id		varchar2(50),
 * INFO_CONTENT			varchar2(500),
 * zh_ID		INTEGER,
 * groupID		INTEGER,
 * Categories		INTEGER,
 * XXID		varchar2(50)
 * );
 * @author bertyang
 *
 */
@Entity
@Table(name="STUDENT_ERVIEW_LIBRAY")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StudentComment implements Serializable{
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private String rtc_id;
	private String INFO_CONTENT;
	private int zh_ID;
	private int groupID;
	private int Categories;
	private String XXID;
	
	public String getRtc_id() {
		return rtc_id;
	}
	public void setRtc_id(String rtc_id) {
		this.rtc_id = rtc_id;
	}
	public String getINFO_CONTENT() {
		return INFO_CONTENT;
	}
	public void setINFO_CONTENT(String iNFO_CONTENT) {
		INFO_CONTENT = iNFO_CONTENT;
	}
	public int getZh_ID() {
		return zh_ID;
	}
	public void setZh_ID(int zh_ID) {
		this.zh_ID = zh_ID;
	}
	public int getGroupID() {
		return groupID;
	}
	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}
	public int getCategories() {
		return Categories;
	}
	public void setCategories(int categories) {
		Categories = categories;
	}
	public String getXXID() {
		return XXID;
	}
	public void setXXID(String xXID) {
		XXID = xXID;
	}
	
	

}
