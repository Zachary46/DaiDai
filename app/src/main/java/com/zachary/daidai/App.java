package com.zachary.daidai;

import android.app.Application;

import com.igexin.sdk.PushManager;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by Zachary on 2017/12/9.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PushManager.getInstance().initialize(this.getApplicationContext(),DemoPushService.class);
        // com.getui.demo.DemoIntentService 为第三方自定义的推送服务事件接收类
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), DemoIntentService.class);

        UMShareAPI.get(this);
        PlatformConfig.setWeixin("wx38f8ecd6a1596924", "0a779916cfc04b030d7d81f3fb431c9e");
        PlatformConfig.setQQZone("1106601402", "kgGEfVmcHAVGha1n");

    }
}
