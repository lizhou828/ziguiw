package com.zigui.utils;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-11-16
 * Time: 上午9:25
 * To change this template use File | Settings | File Templates.
 */
public class PagesDemoTest {

    public static void main(String[] args){
        int totalPages;
        int rectotal=101;
        int pagesize=18;


        totalPages = rectotal%pagesize==0 ? rectotal/pagesize : rectotal/pagesize+1;

        System.out.println("totalPages="+totalPages);


    }
}
