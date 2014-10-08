package controllers;

import com.arj.ziguiw.common.RecommendArea;
import models.Newse;
import models.Page;
import models.Recommend;

import java.util.List;

/**
 * User: Liujy
 * Date: 13-2-19
 * Time: 下午2:53
 */
public class Newses extends Application{

    public static void index(){
        List<Recommend> online_index_hd = Recommend.findByArea(RecommendArea.ONLINE_INDEX_HD);
        Recommend top = null;
        List<Recommend> tops =Recommend.findByArea(RecommendArea.ONLINE_INDEX_JINRITOUTIAO);
        if (tops.size() > 0) top = tops.get(0);
        List<Recommend> online_index_bianjituijian = Recommend.findByArea(RecommendArea.ONLINE_INDEX_BIANJITUIJIAN);
        List<Recommend> online_index_jiaodianhuati = Recommend.findByArea(RecommendArea.ONLINE_INDEX_JIAODIANHUATI);
        List<Recommend> online_index_remengtuijian = Recommend.findByArea(RecommendArea.ONLINE_INDEX_REMENGTUIJIAN);
        List<Recommend> online_index_xiaoyuanxinwen = Recommend.findByArea(RecommendArea.ONLINE_INDEX_XIAOYUANXINWEN);
        List<Recommend> online_index_kaoshishengxue = Recommend.findByArea(RecommendArea.ONLINE_INDEX_KAOSHISHENGXUE);
        List<Recommend> online_index_difangzixun = Recommend.findByArea(RecommendArea.ONLINE_INDEX_DIFANGZIXUN);

        List<Newse> hots = Newse.findHot();
        List<Newse> focus = Newse.findByNewsChannelId(1l,1,6).data;
        List<Newse> schoolNews = Newse.findByNewsChannelId(2l,1,6).data;
        List<Newse> testNews = Newse.findByNewsChannelId(3l,1,6).data;
        List<Newse> peopleHots = Newse.findHot();
        render(online_index_hd,top,online_index_bianjituijian,online_index_difangzixun,
                online_index_jiaodianhuati,online_index_remengtuijian,
                online_index_xiaoyuanxinwen,online_index_kaoshishengxue,
                hots,focus,schoolNews,testNews,peopleHots);
    }


    public static void list(Long id){
        Page<Newse> page = Newse.findByNewsChannelId(id,getPage(),getPageSize());
        render(page,id);
    }

    public static void show(Long id){
        Newse newse = Newse.findById(id);
        Long channelId = newse.newsChannel.id;
        render(newse,channelId);
    }
}
