package com.zigui.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "space_visit_history")
@SequenceGenerator(name = "SPACE_VISIT_HISTORY_SEQ", sequenceName = "space_visit_history_seq", allocationSize = 1)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SpaceVisitHistory implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPACE_VISIT_HISTORY_SEQ")
	private long id;
	
	@OneToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
	@JoinColumn(name="from_user_id", insertable=true, updatable=true)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private UserBase formUser;
	
	@OneToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
	@JoinColumn(name="to_user_id", insertable=true, updatable=true)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private UserBase toUser;
	
	@Column(name = "visit_time")
	private Date visitTime = new Date();

	public UserBase getFormUser() {
		return formUser;
	}

	public void setFormUser(UserBase formUser) {
		this.formUser = formUser;
	}

	public UserBase getToUser() {
		return toUser;
	}

	public void setToUser(UserBase toUser) {
		this.toUser = toUser;
	}

	public Date getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "SpaceVisitHistory [id=" + id + ", formUser=" + formUser
				+ ", toUser=" + toUser + ", visitTime=" + visitTime + "]";
	}

}
