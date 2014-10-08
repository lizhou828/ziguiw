package com.zigui.utils;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Profiler {

    private static final ThreadLocal<Entry> entryStack = new ThreadLocal<Entry>();

    public static void start() {
        start((String) null);
    }

    public static void start(String message) {
        entryStack.set(new Entry(message, null, null));
    }

    public static void start(Message message) {
        entryStack.set(new Entry(message, null, null));
    }

    public static void reset() {
        entryStack.set(null);
    }

    public static void enter(String message) {
        Entry currentEntry = getCurrentEntry();

        if (currentEntry != null) {
            currentEntry.enterSubEntry(message);
        }
    }

    public static void enter(Message message) {
        Entry currentEntry = getCurrentEntry();

        if (currentEntry != null) {
            currentEntry.enterSubEntry(message);
        }
    }

    public static void release() {
        Entry currentEntry = getCurrentEntry();

        if (currentEntry != null) {
            currentEntry.release();
        }
    }

    public static long getDuration() {
        Entry entry = (Entry) entryStack.get();

        if (entry != null) {
            return entry.getDuration();
        } else {
            return -1;
        }
    }

    public static String dump() {
        return dump("", "");
    }

    public static String dump(String prefix) {
        return dump(prefix, prefix);
    }

    public static String dump(String prefix1, String prefix2) {
        Entry entry = (Entry) entryStack.get();

        if (entry != null) {
            return entry.toString(prefix1, prefix2);
        } else {
            return "";
        }
    }

    public static Entry getEntry() {
        return (Entry) entryStack.get();
    }

    private static Entry getCurrentEntry() {
        Entry subEntry = (Entry) entryStack.get();
        Entry entry = null;

        if (subEntry != null) {
            do {
                entry = subEntry;
                subEntry = entry.getUnreleasedEntry();
            } while (subEntry != null);
        }

        return entry;
    }

    public static final class Entry {
        private final List<Entry> subEntries = new ArrayList<Entry>(4);
        private final Object      message;
        private final Entry       parentEntry;
        private final Entry       firstEntry;
        private final long        baseTime;
        private final long        startTime;
        private long              endTime;

        private Entry(Object message, Entry parentEntry, Entry firstEntry) {
            this.message = message;
            this.startTime = System.currentTimeMillis();
            this.parentEntry = parentEntry;
            this.firstEntry = (Entry) ObjectUtils.defaultIfNull(firstEntry, this);
            this.baseTime = (firstEntry == null) ? 0 : firstEntry.startTime;
        }

        public String getMessage() {
            String messageString = null;

            if (message instanceof String) {
                messageString = (String) message;
            } else if (message instanceof Message) {
                Message messageObject = (Message) message;
                MessageLevel level = MessageLevel.BRIEF_MESSAGE;

                if (isReleased()) {
                    level = messageObject.getMessageLevel(this);
                }

                if (level == MessageLevel.DETAILED_MESSAGE) {
                    messageString = messageObject.getDetailedMessage();
                } else {
                    messageString = messageObject.getBriefMessage();
                }
            }

            return StringUtils.defaultIfEmpty(messageString, null);
        }

        public long getStartTime() {
            return (baseTime > 0) ? (startTime - baseTime) : 0;
        }

        public long getEndTime() {
            if (endTime < baseTime) {
                return -1;
            } else {
                return endTime - baseTime;
            }
        }

        public long getDuration() {
            if (endTime < startTime) {
                return -1;
            } else {
                return endTime - startTime;
            }
        }

        public long getDurationOfSelf() {
            long duration = getDuration();

            if (duration < 0) {
                return -1;
            } else if (subEntries.isEmpty()) {
                return duration;
            } else {
                for (int i = 0; i < subEntries.size(); i++) {
                    Entry subEntry = (Entry) subEntries.get(i);

                    duration -= subEntry.getDuration();
                }

                if (duration < 0) {
                    return -1;
                } else {
                    return duration;
                }
            }
        }

        public double getPecentage() {
            double parentDuration = 0;
            double duration = getDuration();

            if ((parentEntry != null) && parentEntry.isReleased()) {
                parentDuration = parentEntry.getDuration();
            }

            if ((duration > 0) && (parentDuration > 0)) {
                return duration / parentDuration;
            } else {
                return 0;
            }
        }

        public double getPecentageOfAll() {
            double firstDuration = 0;
            double duration = getDuration();

            if ((firstEntry != null) && firstEntry.isReleased()) {
                firstDuration = firstEntry.getDuration();
            }

            if ((duration > 0) && (firstDuration > 0)) {
                return duration / firstDuration;
            } else {
                return 0;
            }
        }

        public List<Entry> getSubEntries() {
            return Collections.unmodifiableList(subEntries);
        }

        private void release() {
            endTime = System.currentTimeMillis();
        }

        /**
         * �жϵ�ǰentry�Ƿ����
         *
         * @return ���entry�Ѿ������򷵻�<code>true</code>
         */
        private boolean isReleased() {
            return endTime > 0;
        }

        private void enterSubEntry(Object message) {
            Entry subEntry = new Entry(message, this, firstEntry);

            subEntries.add(subEntry);
        }

        private Entry getUnreleasedEntry() {
            Entry subEntry = null;

            if (!subEntries.isEmpty()) {
                subEntry = (Entry) subEntries.get(subEntries.size() - 1);

                if (subEntry.isReleased()) {
                    subEntry = null;
                }
            }

            return subEntry;
        }

        public String toString() {
            return toString("", "");
        }

        private String toString(String prefix1, String prefix2) {
            StringBuffer buffer = new StringBuffer();

            toString(buffer, prefix1, prefix2);

            return buffer.toString();
        }

        private void toString(StringBuffer buffer, String prefix1, String prefix2) {
            buffer.append(prefix1);

            String message = getMessage();
            long startTime = getStartTime();
            long duration = getDuration();
            long durationOfSelf = getDurationOfSelf();
            double percent = getPecentage();
            double percentOfAll = getPecentageOfAll();

            Object[] params = new Object[] { message, // {0} - entry��Ϣ 
                    new Long(startTime), // {1} - ��ʼʱ��
                    new Long(duration), // {2} - ������ʱ��
                    new Long(durationOfSelf), // {3} - ������ĵ�ʱ��
                    new Double(percent), // {4} - �ڸ�entry����ռ��ʱ�����
                    new Double(percentOfAll) // {5} - ����ʱ������ɵ�ʱ�����
            };

            StringBuffer pattern = new StringBuffer("{1,number} ");

            if (isReleased()) {
                pattern.append("[{2,number}ms");

                if ((durationOfSelf > 0) && (durationOfSelf != duration)) {
                    pattern.append(" ({3,number}ms)");
                }

                if (percent > 0) {
                    pattern.append(", {4,number,##%}");
                }

                if (percentOfAll > 0) {
                    pattern.append(", {5,number,##%}");
                }

                pattern.append("]");
            } else {
                pattern.append("[UNRELEASED]");
            }

            if (message != null) {
                pattern.append(" - {0}");
            }

            buffer.append(MessageFormat.format(pattern.toString(), params));

            for (int i = 0; i < subEntries.size(); i++) {
                Entry subEntry = (Entry) subEntries.get(i);

                buffer.append('\n');

                if (i == (subEntries.size() - 1)) {
                    subEntry.toString(buffer, prefix2 + "`---", prefix2 + "    "); // ���һ��
                } else if (i == 0) {
                    subEntry.toString(buffer, prefix2 + "+---", prefix2 + "|   "); // ��һ��
                } else {
                    subEntry.toString(buffer, prefix2 + "+---", prefix2 + "|   "); // �м���
                }
            }
        }
    }

    public interface Message {
        MessageLevel getMessageLevel(Entry entry);

        String getBriefMessage();

        String getDetailedMessage();
    }
    
    public static void main(String[] args){
    	Profiler.enter("test");
    	try {
			Thread.sleep(30000000);
		} catch (Exception e) {
		}
		finally{
			Profiler.release();
		}
    	
    }
}
