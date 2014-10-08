package org.apache.log4j.jdbc;

import org.apache.log4j.spi.LoggingEvent;

/**
 * Created with IntelliJ IDEA.
 * User: pengchangguo
 * Date: 13-4-24
 * Time: A.M 11:14
 */
public class H2JDBCAppender extends JDBCAppender{

    @Override
    protected String getLogStatement(LoggingEvent event) {
        return super.getLogStatement(new H2LoggingEvent(event.fqnOfCategoryClass,
                event.getLogger(), event.getLevel(), event.getMessage(), event.getThrowableInformation() == null ? null : event.getThrowableInformation().getThrowable()));
    }
}
