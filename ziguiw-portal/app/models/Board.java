package models;

import com.arj.ziguiw.common.Status;
import org.hibernate.annotations.Formula;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: Liujy
 * Date: 13-3-4
 * Time: 上午9:57
 */
@Entity
@Table(name = "board")
@SequenceGenerator(name = "BOARD_SEQ",
        sequenceName = "board_seq",
        allocationSize = 1)
public class Board extends JPASupport{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARD_SEQ")
    public long id;


    @ManyToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
    @JoinColumn(name="parentid")
    public Board parentBoard;

    @Column(name = "boardname")
    public String boardName;

    @Column(name = "explains")
    public String explains;

    @Column(name = "bulletin")
    public String bulletin;

    @Column(name = "state")
    public int state = Status.UNVERIFIED;


    @Column(name = "creator_id")
    public long creatorId;

    @Column(name = "creator_nick")
    public String creatorNick;

    @Column(name = "create_time")
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
        List<Board> list = find("select b from Board b where b.id = b.parentBoard.id and b.state = ?",Status.OK).fetch();
        return list;
    }

    public static List<Board> findChildBoardByParentId(Long id){
        return  find("select b from Board b where" +
                " b.parentBoard.id = ? and b.state = ? and b.id != b.parentBoard.id",
                id,Status.OK).fetch();
    }

    public static List<Forum> findTopByBoardIds(List<Board> boards){
        if(boards.size() == 0){
            return new ArrayList<Forum>();
        }
        List<Long> boardIds = new ArrayList<Long>();
        for(Board board1 : boards){
            boardIds.add(board1.id);
        }
        StringBuffer sb = new StringBuffer("select f from Forum f where f.istop = ?" +
                " and f.state = ? and f.board.id in ");
        String hql = hqlWhere(boardIds,sb);
        hql = hql + "order by f.lastPostTime desc";
        return find(hql,1,Status.OK).fetch();
    }

    public static List<Forum> findTopByBoardId(Long boardId){
        return find("select f from Forum f where f.istop = ? and f.state = ? " +
                "and f.board.id = ? order by f.lastPostTime desc",1,Status.OK,boardId).fetch();
    }


    public static Page<Forum> findByBoardIds(List<Board> boards,Integer page,Integer pageSize){
        if(boards.size() == 0){
            return new Page<Forum>(page,pageSize,0,new ArrayList());
        }
        List<Long> boardIds = new ArrayList<Long>();
        for(Board board1 : boards){
            boardIds.add(board1.id);
        }
        StringBuffer sb = new StringBuffer("select f from Forum f where  f.state = ? " +
                "and f.parentForum is null and f.board.id in ");
        String hql = hqlWhere(boardIds,sb);
        hql = hql +  "order by f.lastPostTime desc";
        List<Forum> list = find(hql,Status.OK).fetch(page, pageSize);
        sb = new StringBuffer("select count(*) from Forum f where f.state = ? " +
                "and f.parentForum is null and f.board.id in");
        hql = hqlWhere(boardIds,sb);
        Long totalCount = count(hql,Status.OK);
        return new Page<Forum>(page,pageSize,totalCount,list);

    }

    public static Page<Forum> findByBoardId(Long boardId, Integer page, Integer pageSize){
        String hql = "select f from Forum f where  f.state = ?" +
                " and f.parentForum is null and f.board.id = ? order by f.lastPostTime desc";
        List<Forum> list = find(hql,Status.OK,boardId).fetch(page,pageSize);
        hql = "select count(*) from Forum f where  f.state = ? and f.parentForum is null and f.board.id = ?";
        Long totalCount = count(hql,Status.OK,boardId);
        return  new Page<Forum>(page,pageSize,totalCount,list);
    }


    public static String hqlWhere(List<Long> lists,StringBuffer stringBuffer){
        for(int i =0;i<lists.size();i++){
            if(i == 0){
                stringBuffer.append("(" + lists.get(i) + ",");
            }
            else if(i == lists.size()-1){
                stringBuffer.append(lists.get(i) + ")");
            }else{
                stringBuffer.append(lists.get(i) + ",");
            }
        }
        return stringBuffer.toString();
    }
}
