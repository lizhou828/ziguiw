package com.zigui;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-11-26
 * Time: 下午4:32
 * To change this template use File | Settings | File Templates.
 */
public class MD5Test {
       public static void main(String [] args){
             String passwd = DigestUtils.md5Hex("15616246162");
             System.out.print(passwd);

       }
}
