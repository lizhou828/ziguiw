package controllers;

import models.*;

import java.util.List;

/**
 * User: Liujy
 * Date: 13-2-19
 * Time: 下午1:59
 */
public class Knowledges  extends  Application{

    public static void index(){
        List<Recommend> knowledge_index_hd = Recommend.findByArea("KNOWLEDGE_INDEX_HD");
        Recommend knowledge_index_top = null;
        List<Recommend> knowledge_index_tops = Recommend.findByArea("KNOWLEDGE_INDEX_TOP");
        if (knowledge_index_tops.size() > 0) {
            knowledge_index_top = knowledge_index_tops.get(0);
        }
        Debate debate = null;
        if (knowledge_index_top != null) {
            debate = Debate.findById(new Long(knowledge_index_top.objectId));
        }
        List<Recommend> knowledge_index_youerqi = Recommend.findByArea("KNOWLEDGE_INDEX_YOUERQI");
        List<Recommend> knowledge_index_shaoerqi = Recommend.findByArea("KNOWLEDGE_INDEX_SHAOERQI");
        List<Recommend> knowledge_index_qingnianqi = Recommend.findByArea("KNOWLEDGE_INDEX_QINGNIANQI");

        List<Question> parenting = Question.findAll(1,10).data;
        List<Knowledge> earlyChildhood = Knowledge.findByKnowledgeChannelId(1l,1,12).data;
        List<Knowledge> stageChildren = Knowledge.findByKnowledgeChannelId(2l,1,12).data;
        List<Knowledge> youngPeriod = Knowledge.findByKnowledgeChannelId(3l,1,12).data;

        List<Knowledge> earlyChildhoodHot = Knowledge.findHotByKnowledgeChannelId(1l);
        List<Knowledge> stageChildrenHot = Knowledge.findHotByKnowledgeChannelId(2l);
        List<Knowledge> youngPeriodHot = Knowledge.findHotByKnowledgeChannelId(3l);

        render(knowledge_index_hd,debate,knowledge_index_youerqi,knowledge_index_shaoerqi,
                knowledge_index_qingnianqi,parenting,earlyChildhood,stageChildren,youngPeriod,
                earlyChildhoodHot,stageChildrenHot,youngPeriodHot);
    }

    public static void list(Long id){
        Page<Knowledge> page = Knowledge.findByKnowledgeChannelId(id,getPage(),getPageSize());
        render(page,id);
    }

    public static void show(Long id){
        Knowledge kn = Knowledge.findById(id);
        Long channelId = kn.knowledgeChannel.id;
        render(kn,channelId);
    }
}
