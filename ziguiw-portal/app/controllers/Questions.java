package controllers;

import controllers.modules.cas.SecureCAS;
import controllers.modules.cas.UnSecure;
import models.Answer;
import models.Page;
import models.Question;
import models.UserBase;
import play.mvc.With;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: Liujy
 * Date: 13-2-19
 * Time: 下午3:26
 */
@With(SecureCAS.class)
public class Questions extends Application{


    @UnSecure
    public static void list(Integer status){
        String userName = (String) DsisSecurity.connected();
        Page<Question> page = null;
        //3代表取当前登录用户所提的问题
        if(3 == status){
           if(userName == null){
               page = new Page<Question>(getPage(),getPageSize(),0,new ArrayList());
           }else {
               page = Question.findByCreateNickName(userName,getPage(),getPageSize());
           }
        } else {
            page = Question.findByStatus(status, getPage(), getPageSize());
        }
        render(page,status);
    }

    @UnSecure
    public static void show(Long id){
        Question question = Question.findById(id);
        question.clickCount =  question.clickCount + 1;
        question.save();
        List<Answer> answers = Answer.findByQuestionId(question.id);
        Answer bestAnswer = Answer.findById(question.bestAnswerId);
        List<Question> hots = Question.findHot();
        render(question, answers, bestAnswer,hots);
    }


    public static void answer(String content,Long questionId){
        String userName = (String) DsisSecurity.connected();
        UserBase userBase =  UserBase.find("byNickName", userName).first();
        Answer answer = new Answer();
        answer.content = content;
        answer.question = Question.findById(questionId);
        answer.createTime = new Date();
        answer.creatorNick = userName;
        answer.creatorId = userBase.id;
        answer.create();
        show(questionId);
    }

    public static void askquestionui(){
        List<Question> hots = Question.findHot();
        render(hots);
    }

    public static void askquestion(String title, int point, int pendingTime, String content){
        String userName = (String) DsisSecurity.connected();
        UserBase userBase =  UserBase.find("byNickName", userName).first();
        Question question = new Question();
        question.content = content;
        question.createTime = new Date();
        question.createId = userBase.id;
        question.creatorNick = userName;
        question.point = point;
        question.status = 0;
        question.title = title;
        question.pendingTime = pendingTime;
        question.create();
        list(0);
    }

}
