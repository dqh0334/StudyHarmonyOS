package com.harmonyos.myapplication.util;

import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.util.Locale;

public class LogUtils {

    private static final HiLogLabel LABEL_LOG = new HiLogLabel(3, 0xD000f00, "dqh");

    private static final String LOG_FORMAT = "%{public}s:%{public}s";

    private LogUtils() {

    }

    public static void debug(String tag, String msg) {
        HiLog.debug(LABEL_LOG, LOG_FORMAT, tag, msg);
    }

    public static void info(String tag, String msg) {
        HiLog.info(LABEL_LOG, LOG_FORMAT, tag, msg);
    }

    public static void info(String tag, final String format, Object... args) {
        String buffMsg = String.format(Locale.ROOT, format, args);
        HiLog.info(LABEL_LOG, LOG_FORMAT, tag, buffMsg);
    }

    public static void warn(String tag, String msg) {
        HiLog.warn(LABEL_LOG, LOG_FORMAT, tag, msg);
    }

    public static void error(String tag, String msg) {
        HiLog.error(LABEL_LOG, LOG_FORMAT, tag, msg);
    }

    public static void error(String tag, final String format, Object... args) {
        String buffMsg = String.format(Locale.ROOT, format, args);
        HiLog.error(LABEL_LOG, LOG_FORMAT, tag, buffMsg);
    }
}
