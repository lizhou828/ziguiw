package controllers;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-5-10
 * Time: p.m.4:51
 * 年级名称转换工具类
 */
public class GradeNameUtils {

    public static String transform(String gradeName){
        gradeName = gradeName.trim();
        String primarySchool1 = "1年级(小)一年级（小）一年级";
        String primarySchool2 = "2年级(小)二年级（小）二年级";
        String primarySchool3 = "2年级(小)三年级（小）三年级";
        String primarySchool4 = "4年级(小)四年级（小）四年级";
        String primarySchool5 = "5年级(小)五年级（小）五年级";
        String primarySchool6 = "6年级(小)六年级（小）六年级";
        String middleSchool1 = "7年级七年级";
        String middleSchool2 = "8年级八年级";
        String middleSchool3 = "9年级九年级";
        String highSchool1 = "复读";
        if(middleSchool1.contains(gradeName)){
            gradeName = "初一";
        }else if(middleSchool2.contains(gradeName)){
            gradeName = "初二";
        }else if(middleSchool3.contains(gradeName)){
            gradeName = "初三";
        }else if(highSchool1.contains(gradeName)){
            gradeName = "高三";
        }else if(primarySchool1.contains(gradeName)){
            gradeName = "一年级";
        } else if(primarySchool2.contains(gradeName)){
            gradeName = "二年级";
        }else if(primarySchool3.contains(gradeName)){
            gradeName = "三年级";
        }else if(primarySchool4.contains(gradeName)){
            gradeName = "四年级";
        }else if(primarySchool5.contains(gradeName)){
            gradeName = "五年级";
        }else if(primarySchool6.contains(gradeName)){
            gradeName = "六年级";
        }
        return gradeName;
    }

    public static void main(String [] args){
        String source = "5年级";
        System.out.println(transform(source));
    }
}
