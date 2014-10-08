package controllers;

import com.arj.ziguiw.common.Status;
import models.HomeAnswer;
import models.UserBase;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-4-12
 * Time: 下午1:59
 */
public class HomeAnswers extends Application{

        public static void create(Long userId ,Long questionId, String text){
            UserBase userBase = UserBase.findById(userId);
            HomeAnswer homeAnswer = new HomeAnswer();
            homeAnswer.questionId = questionId;
            homeAnswer.user = (UserBase) renderArgs.get("user");
            homeAnswer.text = text;
            homeAnswer.create();
            redirect("/HomeQuestions/list?userId=" + userId);
        }

        public static void delete(Long userId , Long answerId){
            HomeAnswer homeAnswer = HomeAnswer.findById(answerId);
            homeAnswer.status = Status.DELETED;
            homeAnswer.save();
            redirect("/HomeQuestions/list?userId=" + userId);
        }
}
