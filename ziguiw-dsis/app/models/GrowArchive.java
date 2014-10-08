package models;

import play.data.validation.Required;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-1-25
 * Time: P.M.3:47
 */
@Entity()
@Table(name = "grow_archives")
@Form(displayName = "成长档案")
@SequenceGenerator(name = "GROW_ARCHIVES_SQE",sequenceName = "GROW_ARCHIVES_SQE",allocationSize = 1)
public class GrowArchive extends JPASupport {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "GROW_ARCHIVES_SQE")
    @Column(name = "id")
    public long id;

    @Column(name="user_id")
    @Required
    public long userId;

    @Column(name="hobby")
    @Field(displayName = "爱好")
    public String hobby;       //爱好

    @Column(name="specialty")
    @Field(displayName = "特长")
    public String specialty;        //特长

    @Column(name="ideal")
    @Field(displayName = "理想")
    public String ideal;             //理想

    @Column(name="famous")
    @Field(displayName = "兴趣")
    public String interests;

    @Column(name="parents_send_word")
    @Field(displayName = "家长寄语")
    public String parentsSendWord;     //家长寄语

    @Column(name="teacher_send_word")
    @Field(displayName = "老师寄语")
    public String teacherSendWord;     //老师寄语

    @Column(name="honor_and_works")
    @Field(displayName = "荣誉、成就")
    public String honorAndWorks;       //荣誉、成就

    @Column(name="favourite_work")
    @Field(displayName = "最喜爱的工作")
    public String favouriteWork;       //最喜爱的工作

    @Column(name="reading_ability_teacher")
    @Field(displayName = "阅读能力-师评")
    public String readingAbilityTeacher;    //阅读能力-师评

    @Column(name="reading_ability_self")
    @Field(displayName = "阅读能力-自评")
    public String readingAbilitySelf;      //阅读能力-自评

    @Column(name="reading_ability_parents")
    @Field(displayName = "阅读能力-家长评价")
    public String readingAbilityParents;    //阅读能力-家长评价

    @Column(name="expression_ability_teacher")
    @Field(displayName = "表达能力-师评")
    public String expressionAbilityTeacher;  //表达能力

    @Column(name="expression_ability_self")
    @Field(displayName = "表达能力-自评")
    public String expressionAbilitySelf;

    @Column(name="expression_ability_parents")
    @Field(displayName = "阅读能力-家长评价")
    public String expressionAbilityParents;

    @Column(name="writing_ability_teacher")
    @Field(displayName = "写作能力-师评")
    public String wtingAbilityTeacher;       //写作能力

    @Column(name="writing_ability_self")
    @Field(displayName = "写作能力-自评")
    public String writingAbilitySelf;

    @Column(name="writing_ability_parents")
    @Field(displayName = "写作能力-家长评价")
    public String writingAbilityParents;

    @Column(name="cooperation_ability_teacher")
    @Field(displayName = "团队协作能力-师评")
    public String cooperationAbilityTeacher;     //团队协作能力

    @Column(name="cooperation_ability_self")
    @Field(displayName = "团队协作能力-自评")
    public String cooperationAbilitySelf;

    @Column(name="cooperation_ability_parents")
    @Field(displayName = "团队协作能力-家长评价")
    public String cooperationAbilityParents;

    @Column(name="art_ability_teacher")
    @Field(displayName = "艺术能力-师评")
    public String artAbilityTeacher;         //艺术能力

    @Column(name="art_ability_self")
    @Field(displayName = "艺术能力-自评")
    public String artAbilitySelf;

    @Column(name="art_ability_parents")
    @Field(displayName = "艺术能力-家长评价")
    public String artAbilityParents;

    @Column(name="sport_ability_teacher")
    @Field(displayName = "运动能力-师评")
    public String sportAbilityTeacher;       //运动能力

    @Column(name="sport_ability_self")
    @Field(displayName = "运动能力-自评")
    public String sportAbilitySelf;

    @Column(name="sport_ability_parents")
    @Field(displayName = "运动能力-家长评价")
    public String sportAbilityParents;

    @Column(name="daily_ability_teacher")
    @Field(displayName = "日常表现-师评")
    public String dailyAbilityTeacher;       //日常表现

    @Column(name="daily_ability_self")
    @Field(displayName = "日常表现-自评")
    public String dailyAbilitySelf;

    @Column(name="daily_ability_parents")
    @Field(displayName = "日常表现-家长评价")
    public String dailyAbilityParents;

    @Column(name="state")
    @Field(displayName = "状态")
    public int state =1;  //0：删除    1：未删除

    @Column(name="creator_nick")
    @Field(displayName = "创建者")
    public String creatorNick;

    @Column(name="create_time")
    @Field(displayName = "创建时间")
    public Date createTime;

    @Column(name="last_modify_nick")
    @Field(displayName = "最后修改人")
    public String lastModifyNick;

    @Column(name="last_modify_time")
    @Field(displayName = "最后修改时间")
    public Date lastModifyTime;

    @Column(name="blood_type")
    @Field(displayName = "血型")
    public String bloodType;           //血型

    @Column(name="allergic_history")
    @Field(displayName = "过敏史")
    public String allergicHistory;     //过敏史

    @Column(name="past_medical_history")
    @Field(displayName = "既往史")
    public String pastMedicalHistory;  //既往史

    @Column(name="student_id")
    @Field(displayName = "学生id")
    public  int studentId;

    public static Page<GrowArchive> pageQuery (Integer xsId, Integer page, Integer pageSize){
        String hql = "from GrowArchive g where g.studentId = ? ";
        List<GrowArchive> data = find(hql,xsId).fetch(page,pageSize);
        long totalCount = count("select count(*) " + hql,xsId);
        return new Page(page, pageSize, totalCount, data);
    }

}
