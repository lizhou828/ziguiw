package com.arj.ziguiw.datasynch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by IntelliJ IDEA.
 * User: liumengjie
 * Date: 12-9-18
 * Time: P.M.1:59
 */
public class Main {

    private static final Log log = LogFactory.getLog(Main.class);

    public static void main(String[] args) {
        try {
            new ClassPathXmlApplicationContext(new String[]{"com/arj/ziguiw/datasynch/*.xml"});
        } catch (SystemAbortException e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
        }
    }
}
