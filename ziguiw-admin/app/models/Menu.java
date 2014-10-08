package models;

import play.db.jpa.JPASupport;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: pengchangguo
 * Date: 13-1-18
 * Time: P.M 1:37
 */

@Entity
@Table(name="A_MENUS")
public class Menu extends JPASupport{

    @Id
    @Column(name = "id", nullable = false)
    public Long id;

    @Column(name = "name", length = 255)
    public String name;

    @Column(name = "url", length = 255)
    public String url;

    @Column(name = "parent_id", nullable = false)
    public Long parentId = -1l;

    @Override
    public String toString() {
       return String.format("%s", name);
    }
}
