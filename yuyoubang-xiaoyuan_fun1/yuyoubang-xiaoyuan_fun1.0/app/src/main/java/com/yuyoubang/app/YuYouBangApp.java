package com.yuyoubang.app;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.multidex.MultiDex;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.yuyoubang.activity.mine.SettingActivity;
import com.yuyoubang.fragment.message.DemoHelper;
import com.yuyoubang.fragment.message.DemoModel;
import com.yuyoubang.fragment.message.chat.EaseUI;
import com.yuyoubang.network.HttpClient;
import com.yuyoubang.utils.ContextUtil;
import com.yuyoubang.utils.PreferenceUtils;
import com.yuyoubang.utils.ScreenUtil;
import com.yuyoubang.widget.photo.PhotoHelper;

import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 * date: Created hongchen on 16/11/05.
 */
public class YuYouBangApp extends Application {

    private static Context mApplicationContext;
    private static final String LOGIN = "login";
    boolean settingMsgInfo;
    private DemoModel demoModel;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationContext = this;
        demoModel = new DemoModel(this);
        ContextUtil.init(this);
        ScreenUtil.init(this);
        initUmengShare();
        HttpClient.initOkHttpClient();
        initImageLoaderCache();
        PhotoHelper.init(this);
        initChat();
        DemoHelper.getInstance().init(this);
        settingMsgInfo = PreferenceUtils.getPrefBoolean(this, SettingActivity.SETTING_msg, true);
        if (settingMsgInfo) {
            //消息推送打开
            demoModel.setSettingMsgNotification(true);
            demoModel.setSettingMsgSound(true);
            demoModel.setSettingMsgVibrate(true);
        } else {
            //消息推送关闭
            demoModel.setSettingMsgNotification(false);
            demoModel.setSettingMsgSound(false);
            demoModel.setSettingMsgVibrate(false);
        }
    }

    /**
     * 初始化平台分享
     */
    void initUmengShare() {
        //微信
        PlatformConfig.setWeixin(GlobalParams.WEIXIN_APP_KEY, GlobalParams.WEIXIN_APP_SECRET);
        //新浪微博
        PlatformConfig.setSinaWeibo(GlobalParams.SINA_APP_KEY, GlobalParams.SINA_APP_SECRET);
        Config.REDIRECT_URL = "http://sns.whalecloud.com/sina2/callback";
        //QQ和QQzone
        PlatformConfig.setQQZone(GlobalParams.QQZONE_APP_KEY, GlobalParams.QQZONE_APP_SECRET);
    }

    /**
     * 初始化imgloader
     */
    public void initImageLoaderCache() {
        File cacheDir = StorageUtils.getOwnCacheDirectory(
                getApplicationContext(), "ImageLoader/Cache");

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext())
                .memoryCacheExtraOptions(720, 1280)
                // default = device screen dimensions
                .diskCacheExtraOptions(720, 1280, null)
                .memoryCache(new WeakMemoryCache())
//                .memoryCacheSize(2 * 1024 * 1024)
//                .memoryCacheSizePercentage(13)
                // default
                .diskCache(new UnlimitedDiskCache(cacheDir))
                // default
//                .diskCacheSize(50 * 1024 * 1024)
//                .diskCacheFileCount(100)
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
                // default
                .imageDownloader(
                        new BaseImageDownloader(getApplicationContext())) // default
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
                .build();
        ImageLoader.getInstance().init(config);
    }

    // 获取ApplicationContext
    public static Context getContext() {
        return mApplicationContext;
    }


    private void initChat() {
        EMOptions options = new EMOptions();
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(true);
        options.setUseHttps(true);
        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);
        // 如果APP启用了远程的service，此application:onCreate会被调用2次
        // 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
        // 默认的APP会在以包名为默认的process name下运行，如果查到的process name不是APP的process name就立即返回
        if (processAppName == null || !processAppName.equalsIgnoreCase(this.getPackageName())) {

            // 则此application::onCreate 是被service 调用的，直接返回
            return;
        }
        //初始化
        EMClient.getInstance().init(this, options);
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);
        //初始化UI资源
        EaseUI.getInstance().init(this, options);

    }

    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
    }

    public static void logOut() {
        PreferenceUtils.putsessionId("");
//        PreferenceUtils.clearPreference(getContext());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
