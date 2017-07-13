package com.yuyoubang.utils;



import com.yuyoubang.activity.login.RigisterActivity;

import java.util.Calendar;


/**
 * @author zitian.zhang
 */
public final class QLog {

    /**
     * Send a {@link #VERBOSE} log message.
     * 
     * @param tag Used to identify the source of a log message. It usually identifies the class or activity where the
     *            log call occurs.
     * @param msg The message you would like logged.
     */
    public static int v(String tag, String format, Object... args) {
        if (BuildConfig.LOG_DEBUG) {
            if (args == null || args.length == 0) {
                return android.util.Log.v(tag, format);
            }
            return android.util.Log.v(tag, String.format(format, args));
        }
        return 0;
    }

    /**
     * Send a {@link #DEBUG} log message.
     * 
     * @param tag Used to identify the source of a log message. It usually identifies the class or activity where the
     *            log call occurs.
     * @param msg The message you would like logged.
     */
    public static int d(String tag, String format, Object... args) {
        if (BuildConfig.LOG_DEBUG) {
            if (args == null || args.length == 0) {
                return android.util.Log.d(tag, format);
            }
            
            return android.util.Log.d(tag, String.format(format, args));
        }
        return 0;
    }

    /**
     * Send an {@link #INFO} log message.
     * 
     * @param tag Used to identify the source of a log message. It usually identifies the class or activity where the
     *            log call occurs.
     * @param msg The message you would like logged.
     */
    public static int i(String tag, String format, Object... args) {
        if (BuildConfig.LOG_DEBUG) {
            if (args == null || args.length == 0) {
                return android.util.Log.i(tag, format);
            }
            return android.util.Log.i(tag, String.format(format, args));
        }
        return 0;
    }

    /**
     * Send a {@link #WARN} log message.
     * 
     * @param tag Used to identify the source of a log message. It usually identifies the class or activity where the
     *            log call occurs.
     * @param msg The message you would like logged.
     */
    public static int w(String tag, String format, Object... args) {
        if (BuildConfig.LOG_DEBUG) {
            if (args == null || args.length == 0) {
                return android.util.Log.w(tag, format);
            }
            return android.util.Log.w(tag, String.format(format, args));
        }
        return 0;
    }

    /**
     * Send a {@link #WARN} log message and log the exception.
     * 
     * @param tag Used to identify the source of a log message. It usually identifies the class or activity where the
     *            log call occurs.
     * @param tr An exception to log
     */
    public static int w(String tag, Throwable tr) {
        if (BuildConfig.LOG_DEBUG) {
            return android.util.Log.w(tag, tr);
        }
        return 0;
    }

    /**
     * Send an {@link #ERROR} log message.
     * 
     * @param tag Used to identify the source of a log message. It usually identifies the class or activity where the
     *            log call occurs.
     * @param msg The message you would like logged.
     */
    public static int e(String tag, String msg, Throwable e) {
        if (BuildConfig.LOG_DEBUG) {
            return android.util.Log.e(tag, msg, e);
        }
        return 0;
    }

    /**
     * Send an {@link #ERROR} log message.
     *  @param tag Used to identify the source of a log message. It usually identifies the class or activity where the
     *            log call occurs.
     * @param msg The message you would like logged.
     */
    /**
     * Low-level logging call.
     * 
     * @param priority The priority/type of this log message
     * @param tag Used to identify the source of a log message. It usually identifies the class or activity where the
     *            log call occurs.
     * @param msg The message you would like logged.
     * @return The number of bytes written.
     */
    public static int println(int priority, String tag, String msg) {
        if (BuildConfig.LOG_DEBUG) {
            return android.util.Log.println(priority, tag, msg);
        }
        return 0;
    }

    /**
     * 打印调用时间
     * 
     * @param cur 开始的时间
     * @return 调用时间
     */
    public static long debugDuration(long cur) {
        long sec = System.currentTimeMillis();
        StackTraceElement elem = Thread.currentThread().getStackTrace()[3];
        d("Performance", elem.getFileName() + "_" + elem.getLineNumber() + ":" + (sec - cur));
        return sec;
    }

    /**
     * 打印当前调用的位置： 文件 行号 方法
     * 
     * @param tag
     * @return
     */
    public static int printLogPos(String tag) {
        StackTraceElement elem = Thread.currentThread().getStackTrace()[3];
        return d(tag, elem.getClassName() + ":" + elem.getLineNumber() + "::" + elem.getMethodName());
    }

    /**
     * 获得当前调用位置： 类名
     * 
     * @param depth 堆栈深度
     * @return
     */
    public static String getLogPos(int depth) {
        StackTraceElement elem = Thread.currentThread().getStackTrace()[depth];
        return elem.getClassName();
    }

    /**
     * depth = 4
     * 
     * @see #getLogPos(int)
     * @return
     */
    public static String getLogPos() {
        return getLogPos(4);
    }

    /**
     * 获得当前从<code>from</code>开始的秒数
     * 
     * @param from
     * @return
     */
    public static long getSecond(Calendar from) {
        return (System.currentTimeMillis() - from.getTimeInMillis()) / 1000;
    }

    private static final Calendar _20120101 = Calendar.getInstance();
    static {
        _20120101.set(2012, 0, 0, 0, 0, 0);
    }

    /**
     * from = 20120101
     * 
     * @see #getSecond(Calendar)
     * @return
     */
    public static long getSecond() {
        return getSecond(_20120101);
    }


}
