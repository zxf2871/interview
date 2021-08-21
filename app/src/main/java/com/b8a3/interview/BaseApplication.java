package com.b8a3.interview;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Debug;
import android.os.Process;
import android.util.Log;

import com.b8a3.core.Core;
import com.b8a3.core.CoreConfig;
import com.b8a3.core.log.LogUtil;
import com.b8a3.core.network.IOkHttpBuilderHandler;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class BaseApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Core.install(this);
        CoreConfig.Builder builder = new CoreConfig.Builder(this);
        Core.getInstance().init(builder.build(), getString(R.string.app_name));
        LogUtil.init(BuildConfig.DEBUG, "interview ");
        LogUtil.i("BaseApplication", "pic:" + Process.myPid() + " pName:" + getProcessName(this) );

    }

    public static String getProcessName(Context cxt) {
        int pid = android.os.Process.myPid();
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }

    public static String getCurrentProcessName() {
        FileInputStream in = null;
        try {
            String fn = "/proc/self/cmdline";
            in = new FileInputStream(fn);
            byte[] buffer = new byte[256];
            int len = 0;
            int b;
            while ((b = in.read()) > 0 && len < buffer.length) {
                buffer[len++] = (byte) b;
            }
            if (len > 0) {
                String s = new String(buffer, 0, len, "UTF-8");
                return s;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
