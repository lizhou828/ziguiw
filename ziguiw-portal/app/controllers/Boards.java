package controllers;

import models.Board;
import models.Forum;
import models.Page;
import models.Recommend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Liujy
 * Date: 13-3-4
 * Time: 下午2:28
 */
public class Boards extends Application {

    public static void index(){
        List<Recommend> community_index_hd = Recommend.findByArea("COMMUNITY_INDEX_HD");
        List<Recommend> community_index_sqtonglingquan = Recommend.findByArea("COMMUNITY_INDEX_SQTONGLINGQUAN");
        List<Recommend> community_index_sqtongchengquan = Recommend.findByArea("COMMUNITY_INDEX_SQTONGCHENGQUAN");
        List<Recommend> community_index_sqxuexijiaoliu = Recommend.findByArea("COMMUNITY_INDEX_SQXUEXIJIAOLIU");
        List<Recommend> community_index_sqzhuanjiawenda = Recommend.findByArea("COMMUNITY_INDEX_SQZHUANJIAWENDA");
        List<Recommend> community_index_tonglingquan = Recommend.findByArea("COMMUNITY_INDEX_TONGLINGQUAN");
        List<Recommend> community_index_tongchengquan = Recommend.findByArea("COMMUNITY_INDEX_TONGCHENGQUAN");
        List<Recommend> community_index_xuexijiaoliu = Recommend.findByArea("COMMUNITY_INDEX_XUEXIJIAOLIU");
        List<Recommend> community_index_zhuanjiawenda = Recommend.findByArea("COMMUNITY_INDEX_ZHUANJIAWENDA");

        List<Forum> newForums = Forum.findNewForums();
        List<Forum> hotForums = Forum.findHotForums();

        Map<Board,List<Board>> map = new HashMap<Board,List<Board>>();
        List<Board> boards = Board.findParentBoard();
        for(Board board : boards){
            map.put(board,Board.findChildBoardByParentId(board.id));
        }

        render(map,hotForums,community_index_hd,community_index_sqtonglingquan,community_index_sqtongchengquan,
                community_index_sqxuexijiaoliu,community_index_sqzhuanjiawenda,community_index_tonglingquan,
                community_index_tongchengquan,community_index_xuexijiaoliu,community_index_zhuanjiawenda,newForums);

    }

    public static void list(Long parentBoardId){
        List<Board> boards = Board.findChildBoardByParentId(parentBoardId);
        Board board1 = null;
        if(boards.size() != 0){
            board1 = boards.get(0);
        }
        List<Forum> forums = Board.findTopByBoardIds(boards);

        Page<Forum> page = Board.findByBoardIds(boards,getPage(),getPageSize());
        render(boards,forums,page,parentBoardId,board1);
    }
}
