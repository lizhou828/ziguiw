package com.zigui.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-12-20
 * Time: 下午2:49
 * To change this template use File | Settings | File Templates.
 */
public class DigestUtilsTest {

    @Test
    public void test (){
        String str=DigestUtils.md5Hex("123456");
        System.out.println(str);

    }
}
