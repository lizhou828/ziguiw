package com.zigui.utils;

public final class Constant {

    /**
     * 用户角色常量
     */
    public static final int SCHOOL_TYPE = 1;

    public static final int TEACHER_TYPE = 2;

    public static final int PARENT_TYPE = 3;

    public static final int STUDENT_TYPE = 4;

    /**
     * 积分相关常量
     */
    public static final int EVERY_LOGON = 9;
    //下载资源
    public static final int DOWN_RESOURCE = 18;
    //自己上传的资源被下载后加积分
    public static final int UPLOAD_RESOURCE = 19;
    
    //购买积分
    public static final int PAYPOINTS = 20;
    //注册增加10个积分
    public static final int REGISTER = 1;
    public static final int REGISTER_POINT = 10;

    //每次登录增加一个积分
    public static final int EVERY_LOGON_1 = 8;

    //连续五天登录
    public static final int CONTINUE_LOGON = 7;

    //完善资料
    public static final int FUFILLMENT_INFO = 6;
    public static final int FUFILLMENT_INFO_POINT = 10;

    //发布问题
    public static final int QUESTION = 5;
    public static final int QUESTION_POINT = 5;

    //发表日记
    public static final int DIARY_PUBLISH = 13;
    public static final int DIARY_PUBLISH_POINT = 5;

    //选择满意答案之后，提问人
    public static final int QUESTION_SUB = 11;
    public static final int QUESTION_ADD = 12;

    //发表帖子
    public static final int PUBLISH_FORUM = 14;
    public static final int PUBLISH_FORUM_POINT = 5;

    //回复帖子
    public static final int REPLY_FORUM = 14;
    public static final int REPLY_FORUM_POINT = 2;

    public static final String USER_NO_LOGIN = "USER_NO_LOGIN";


    public static final int TEACHER_LEAVING_MESSAGE_TYPE = 3;

    public static final int TEACHER_COMMENT_MESSAGE_TYPE = 7;

    public static final int CLASS_LEAVING_MESSAGE_TYPE = 4;


    public static final String MOBILE_VALIDATE_CODE = "mobileValidateCode";


    public static final int REGISTER_NOTIFY = 1;

    public static final int COMMENT_NOTIFY = 2;

    public static final int DIARY_NOTIFY = 3;

    public static final int PHOTO_NOTIFY = 4;


    public static final int USER_AUDIT_PASS_STATE = 6;

    public static final int LOGON_TIME = 123;

    //学生的学籍状态
    public static final int STUDENT_ARCHIVE_NORMAL=1;//正常
    public static final int STUDENT_ARCHIVE_QUIT=2;//退学
    public static final int STUDENT_ARCHIVE_BREAK=3;//休学
    public static final int STUDENT_ARCHIVE_TURN=4;//转学
    public static final int STUDENT_ARCHIVE_RETURN = 5;//复学
}
