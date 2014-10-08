package controllers;

import com.arj.ziguiw.common.Status;
import models.HomeAnswer;
import models.HomeQuestion;
import models.Page;
import models.UserBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-4-12
 * Time: 上午11:42
 */
public class HomeQuestions extends Application{

        public static void list(Long userId){
            Page<HomeQuestion> page = HomeQuestion.findByUserId(userId , getPage() , 10);
            UserBase userBase = UserBase.findById(userId);
            Map<HomeQuestion,List<HomeAnswer>> map = new HashMap<HomeQuestion,List<HomeAnswer>>();
            for (HomeQuestion homeQuestion : page.data){
                 map.put(homeQuestion, HomeAnswer.findByQuestionId(homeQuestion.id));
            }
            render(map , userBase ,page);
        }

        public static void create(long userId , String text){
            UserBase userBase = UserBase.findById(userId);
            String error = "";
            if(text != null){
               HomeQuestion homeQuestion = new HomeQuestion();
               homeQuestion.user = userBase;
               homeQuestion.text = text;
               homeQuestion.create();
               list(userId);
            }
            error = "类容不能为空";
            flash.put("error",error);
            list(userId);
        }

        public static void delete(long userId, long questionId){
            HomeQuestion homeQuestion = HomeQuestion.findById(questionId);
            homeQuestion.status = Status.DELETED;
            homeQuestion.save();
            list(userId);
        }
}
