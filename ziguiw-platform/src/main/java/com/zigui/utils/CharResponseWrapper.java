package com.zigui.utils;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-10-22
 * Time: 上午10:09
 */
public class CharResponseWrapper extends HttpServletResponseWrapper{
    private CharArrayWriter charArrayWriter = new CharArrayWriter();

    public CharResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(charArrayWriter);
    }

    public CharArrayWriter getCharArrayWriter() {
        return charArrayWriter;
    }


}
