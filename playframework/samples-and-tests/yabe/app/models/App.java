package models;

import play.db.jpa.JPASupport;

import javax.persistence.*;

@Entity
@Table(name = "app")
@PersistenceUnit(name = "realshop")
public class App extends JPASupport{
	
	@Id
	@Column(name = "id")
	public Integer id;
	
	@Column(name = "name")
	public String name;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mysql_id")
	public Mysql mysql;

}
