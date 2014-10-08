package models;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-5-31
 * Time: 下午12:04
 */
@Form(displayName = "科目得分")
public class SubjectBean {
    @Field(displayName ="科目id" )
    public Integer subId;

    @Field(displayName ="科目名称" )
    public String subName;

    @Field(displayName ="科目得分" )
    public float subScore;

    @Field(displayName ="班级平均分" )
    public float subAvg;

    @Field(displayName ="科目总分" )
    public float subTotal;
}
