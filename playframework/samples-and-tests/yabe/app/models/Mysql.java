package models;

import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.List;

@Entity()
@Table(name = "mysql")
@Form(displayName = "数据库")
@PersistenceUnit(name = "realshop")
public class Mysql extends JPASupport{

	@Id
	@Column(name = "id")
	public Integer id;
	
	@Column(name = "name")
	@Field(displayName = "名称")
	public String name;

	@OneToMany(mappedBy = "mysql")
	public List<App> apps;
	
}
