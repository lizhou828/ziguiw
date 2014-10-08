package com.zigui.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-11-6
 * Time: 下午2:20
 * To change this template use File | Settings | File Templates.
 */
public class DateDemoTest {
    public static void main(String [] s) throws ParseException {
////           long to String
//        Date d=new Date(1352188740000L);
//        System.out.println(d);
//        String string=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
//        System.out.println(string);//string= 2012-11-06 15:59:00


        //“2012-11-06 15:59” String to date
//        String string="2012-11-06";
//        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
//        Date date=sdf.parse(string);
//        Long l=date.getTime();
//        System.out.println("l=" + l);   //l=1352188740000
//
//
//        //new Date() ==>  long
//       System.out.println("当前时间的long类型="+new Date ()+ "当前时间的long类型=" + new Date().getTime());
//
//        //前一天
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DATE, -1);    //得到前一天
//        Date date1 = calendar.getTime();
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(df.format(date1));

        String  d = "1352649600000";
        System.out.println(d);
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(d));
        System.out.println(date);//string= 2012-11-06 15:59:00


    }
}
