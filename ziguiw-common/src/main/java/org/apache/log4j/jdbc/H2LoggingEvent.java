package org.apache.log4j.jdbc;

import org.apache.log4j.Category;
import org.apache.log4j.Priority;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created with IntelliJ IDEA.
 * User: pengchangguo
 * Date: 13-4-24
 * Time: A.M 11:25
 */
class H2LoggingEvent extends org.apache.log4j.spi.LoggingEvent{

    public H2LoggingEvent (String fqnOfCategoryClass, Category logger, Priority level, Object message, Throwable throwable) {
        super(fqnOfCategoryClass, logger, level, message, throwable);
    }

    @Override
    public String getRenderedMessage() {
        String message = super.getRenderedMessage();
        if (message != null) {
            if (message.contains("'")) message = message.replaceAll("'", "‘");
            if (this.getThrowableInformation() != null) {
                StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                this.getThrowableInformation().getThrowable().printStackTrace(printWriter);
                message += "\n" + stringWriter.toString().replaceAll("'", "‘");
            }
        }
        return message;
    }

    @Override
    public String getThreadName() {
        String threadName = super.getThreadName();
        if (threadName != null && threadName.contains("'")) threadName = threadName.replaceAll("'", "’");
        return threadName;
    }
}