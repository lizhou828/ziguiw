package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * create table ALL_SCHOOLNOTICE_MSG(
 * Msg_ID		INTEGER,
 * Msg_Title			varchar2(60),
 * Msg_Content			varchar2(4000),
 * TypeID		INTEGER,
 * filePath			varchar2(100),
 * pub_Date			DATE,
 * pub_Person			varchar2(50),
 * isFile			INTEGER,
 * XxID		varchar2(30)
 * );
 * @author bertyang
 *
 */

/**
 * 学校通知表
 * 
 * 已经无用
 *
 */
@Entity
@Table(name="ALL_SCHOOLNOTICE_MSG")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Deprecated
public class SchoolNotice implements Serializable{
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int Msg_ID;
	
	private String Msg_Title;
	
	private String Msg_Content;
	
	private int TypeID;
	
	private String filePath;
	
	private Date pub_Date;
	
	private String pub_Person;
	
	private int isFile;
	
	private String XxID;

	public int getMsg_ID() {
		return Msg_ID;
	}

	public void setMsg_ID(int msg_ID) {
		Msg_ID = msg_ID;
	}

	public String getMsg_Title() {
		return Msg_Title;
	}

	public void setMsg_Title(String msg_Title) {
		Msg_Title = msg_Title;
	}

	public String getMsg_Content() {
		return Msg_Content;
	}

	public void setMsg_Content(String msg_Content) {
		Msg_Content = msg_Content;
	}

	public int getTypeID() {
		return TypeID;
	}

	public void setTypeID(int typeID) {
		TypeID = typeID;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Date getPub_Date() {
		return pub_Date;
	}

	public void setPub_Date(Date pub_Date) {
		this.pub_Date = pub_Date;
	}

	public String getPub_Person() {
		return pub_Person;
	}

	public void setPub_Person(String pub_Person) {
		this.pub_Person = pub_Person;
	}

	public int getIsFile() {
		return isFile;
	}

	public void setIsFile(int isFile) {
		this.isFile = isFile;
	}

	public String getXxID() {
		return XxID;
	}

	public void setXxID(String xxID) {
		XxID = xxID;
	}

}
