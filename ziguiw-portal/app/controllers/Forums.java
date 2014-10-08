package controllers;

import com.arj.ziguiw.common.PointType;
import com.arj.ziguiw.common.Queues;
import com.arj.ziguiw.common.Status;
import controllers.modules.cas.SecureCAS;
import controllers.modules.cas.UnSecure;
import models.Board;
import models.Forum;
import models.Page;
import models.UserBase;
import play.mvc.With;

import java.util.*;

/**
 * User: Liujy
 * Date: 13-3-4
 * Time: 下午2:08
 */
@With(SecureCAS.class)
public class Forums extends  Application{

    @UnSecure
    public static void list(Long boardId){
        Board board = Board.findById(boardId);
        List<Forum> forums = Board.findTopByBoardId(boardId);
        Page<Forum> page = Board.findByBoardId(boardId,getPage(),getPageSize());
        render(forums,page,boardId,board);


    }

    @UnSecure
    public static void show(Long id){
        Forum forum = Forum.findById(id);
        if(forum.clickCount == null){forum.clickCount = 1;}
        else{forum.clickCount = forum.clickCount + 1;}
        forum.save();
        Board board = Board.findById(forum.board.id);
        Page<Forum> page = Forum.findReplyByFourmId(forum.id,getPage(),getPageSize());
        render(forum,page,board,id);

    }

    public static void myHomeList(Long userId){
        UserBase userBase = UserBase.findById(userId);
        Page<Forum> page = Forum.findByUserId(userId,getPage(),getPageSize());
        render(page,userId,userBase);
    }

    public static void reply(String content,Long forumId){
        Forum forum = Forum.findById(forumId);
        if(!"".equals(content.trim()) && content != null){
            String userName = (String) DsisSecurity.connected();
            UserBase userBase =  UserBase.find("byNickName", userName).first();
            Forum replyForum = new Forum();
            replyForum.content = content;
            replyForum.createTime = new Date();
            replyForum.creatorNick = userName;
            replyForum.state = Status.OK;
            replyForum.parentForum = forum;
            replyForum.creatorId = userBase.id;
            replyForum.create();
            forum.lastPostTime = replyForum.createTime;
            forum.lastPostNick = replyForum.creatorNick;
            forum.save();
        }  else{
            flash.put("error","内容不能为空!");
        }
        show(forum.id);
    }

    public static void forumUI(Long boardId){
        List<Board> boards = null;
        Board board = null;
        if(boardId != null){
            board = Board.findById(boardId);
            boards = Board.findChildBoardByParentId(board.parentBoard.id);
            if(boards == null || boards.size() == 0){
                boards = new ArrayList<Board>();
                boards.add(board);
            }
        }
        render(board, boards);
    }

    public static void saveForum(String title,String content,Long boardId){
        if(!"".equals(content.trim()) && content != null && title != null && !"".equals(title.trim())){
            String userName = (String) DsisSecurity.connected();
            UserBase userBase =  UserBase.find("byNickName", userName).first();
            Board board = Board.findById(boardId);
            Forum forum = new Forum();
            forum.title = title;
            forum.content = content;
            forum.firstImage = Forum.getFirstImage(content);
            if(content.length() > 150){
                content = content.substring(0,150);
            }
            forum.autoDescription = Forum.getPureText(content);
            forum.creatorNick = userName;
            forum.creatorId = userBase.id;
            forum.board = board;
            forum.createTime = new Date();
            forum.lastPostNick = userName;
            forum.lastPostTime = forum.createTime;
            forum.create();
            Map<String,Object> map = new HashMap<String, Object>();
            map.put(PointType.POINT_MESSAGEKEY_USERID,userBase.id);
            map.put(PointType.POINT_MESSAGEKEY_TYPE,PointType.FABIAOTIEZI);
            map.put(PointType.POINT_MESSAGEKEY_POINTCHANGE,PointType.points.get(PointType.FABIAOTIEZI));
            map.put(PointType.POINT_MESSAGEKEY_FLAG,PointType.ADD_POINT);
            jedisPoolSource.rpush(Queues.USER_POINTS, map);
        }
        else{
            flash.put("error","标题或内容不能为空!");
            forumUI(boardId);
        }
        list(boardId);
    }



}
