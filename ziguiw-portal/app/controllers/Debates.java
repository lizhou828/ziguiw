package controllers;

import com.arj.ziguiw.common.Status;
import controllers.modules.cas.SecureCAS;
import controllers.modules.cas.UnSecure;
import models.Debate;
import models.DebateOpinion;
import models.Page;
import models.UserBase;
import play.mvc.With;

import java.util.Date;
import java.util.List;

/**
 * User: Liujy
 * Date: 13-2-19
 * Time: 下午3:03
 */

@With(SecureCAS.class)
public class Debates extends Application{

    @UnSecure
    public static void list(){
       Page<Debate> page = Debate.findAll(getPage(),getPageSize());
       List<Debate> hots = Debate.findHot();
       render(page,hots);
    }

    @UnSecure
    public static void show(long id){
        Debate debate = Debate.findById(id);
        List<DebateOpinion> positives = DebateOpinion.findByStatusAndDebateId(1, debate.id);
        List<DebateOpinion> negatives = DebateOpinion.findByStatusAndDebateId(2, debate.id);
        render(debate,positives,negatives,id);
    }

    public static void createOpinion(String opinion,Long debateId,int status){
       String userName = (String) DsisSecurity.connected();
       UserBase userBase =  UserBase.find("byNickName", userName).first();
       Debate debate = Debate.findById(debateId);
       if(status == 1){
           debate.positiveSupportCount = debate.positiveSupportCount + 1;
       }else{
           debate.negativeSupportCount = debate.negativeSupportCount + 1;
       }
       debate.save();
       DebateOpinion debateOpinion = new DebateOpinion();
       debateOpinion.createTime = new Date();
       debateOpinion.creatorId = userBase.id;
       debateOpinion.creatorNick = userName;
       debateOpinion.debate = debate;
       debateOpinion.opinion = opinion;
       debateOpinion.state = Status.OK;
       debateOpinion.status =status;
       debateOpinion.create();
       show(debateId);
    }
}
