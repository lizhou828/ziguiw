package models;

import com.arj.ziguiw.common.Status;
import org.hibernate.annotations.Formula;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * User: Liujy
 * Date: 13-2-22
 * Time: 上午9:57
 */
@Entity
@Table(name = "board")
@SequenceGenerator(name = "BOARD_SEQ",
        sequenceName = "board_seq",
        allocationSize = 1)
@Form(displayName = "论坛版块")
public class Board extends JPASupport{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARD_SEQ")
    public long id;


    @ManyToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
    @JoinColumn(name="parentid")
    @Field(displayName = "父版块")
    public Board parentBoard;

    @Column(name = "boardname")
    @Field(displayName = "版块名称")
    public String boardName;

    @Column(name = "explains")
    @Field(displayName = "版块说明")
    public String explains;

    @Column(name = "bulletin")
    @Field(displayName = "公告")
    public String bulletin;

    @Column(name = "state")
    @Field(displayName = "状态")
    public int state = Status.UNVERIFIED;


    @Column(name = "creator_id")
    @Field(displayName = "创建者ID")
    public long creatorId;

    @Column(name = "creator_nick")
    @Field(displayName = "创建者昵称")
    public String creatorNick;

    @Column(name = "create_time")
    @Field(displayName = "创建时间")
    public Date createTime = new Date();


    @Formula("(select count(*) from forum f where f.boardid = id and f.isnew = 1)")
    public long mainpostnum = 0;

    @Formula("(select count(*) from forum f where f.boardid = id)")
    public long postnum = 0;

    @Override
    public String toString(){
        return boardName;
    }

    public static List<Board> findParentBoard(){
       List<Board> list = find("select b from Board b where b.id = b.parentBoard.id").fetch();
       return list;
    }
}
