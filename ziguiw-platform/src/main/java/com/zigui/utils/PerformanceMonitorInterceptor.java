package com.zigui.utils;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PerformanceMonitorInterceptor implements MethodInterceptor {

    private final static Log logger    = LogFactory.getLog(PerformanceMonitorInterceptor.class);

    /** �Ժ����ʾ����ֵ */
    private int              threshold = 100;

    /**
     * ȱʡ�Ĺ��췽��.
     */
    public PerformanceMonitorInterceptor() {
        super();
    }

    /**
     * �жϷ������õ�ʱ���Ƿ񳬹���ֵ������ǣ����ӡ������־.
     *
     * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
     */
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String name = invocation.getMethod().getDeclaringClass().getName() + "."
                      + invocation.getMethod().getName();

        Profiler.start("Invoking method: " + name);

        try {
            return invocation.proceed();
        } finally {
            Profiler.release();
            long elapseTime = Profiler.getDuration();

            if (elapseTime > threshold) {
                StringBuilder builder = new StringBuilder();
                builder.append("方法").append(name);
                builder.append("的执行时间超过阈值").append(threshold).append("毫秒,");
                builder.append("实际执行时间为").append(elapseTime).append("毫秒.\r\n");
                builder.append(Profiler.dump());
                logger.info(builder.toString());
            } else {
                if (logger.isDebugEnabled()) {
                    StringBuilder builder = new StringBuilder();
                    builder.append("方法").append(name);
                    builder.append("实际执行时间为").append(elapseTime).append("毫秒.\r\n");
                    logger.debug(builder.toString());
                }
            }
        }

    }

    // ----- �������� ------

    /**
     * @param threshold The threshold to set.
     */
    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
}
