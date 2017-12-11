package com.zachary.daidai;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.nightonke.boommenu.BoomButtons.BoomButton;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.OnBoomListener;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

public class ActionBarActivity extends AppCompatActivity {
    private WebView webView;
    private int[] texts = new int[]{
            R.string.qq, R.string.wx, R.string.dh,
            R.string.wx, R.string.dh, R.string.wx,
            R.string.dh, R.string.wx, R.string.dh};
    private int[] text2s = new int[]{
            R.string.r1, R.string.r2, R.string.r3};
    private int[] texts2 = new int[]{
            R.string.t1, R.string.t2, R.string.t3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar);

        ActionBar mActionBar = getSupportActionBar();
        assert mActionBar != null;
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        initWebView();

        View actionBar = mInflater.inflate(R.layout.custom_actionbar, null);
        TextView mTitleTextView = (TextView) actionBar.findViewById(R.id.title_text);
        mTitleTextView.setText("呆呆");
        mActionBar.setCustomView(actionBar);
        mActionBar.setDisplayShowCustomEnabled(true);
        ((Toolbar) actionBar.getParent()).setContentInsetsAbsolute(0, 0);

        BoomMenuButton leftBmb = (BoomMenuButton) actionBar.findViewById(R.id.action_bar_left_bmb);
        BoomMenuButton rightBmb = (BoomMenuButton) actionBar.findViewById(R.id.action_bar_right_bmb);

        leftBmb.setButtonEnum(ButtonEnum.TextOutsideCircle);
        leftBmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_3_1);
        leftBmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_3_1);
        for (int i = 0; i < leftBmb.getPiecePlaceEnum().pieceNumber(); i++)
            leftBmb.addBuilder(BuilderManager.getTextOutsideCircleButtonBuilderWithDifferentPieceColor(texts[i]));

        rightBmb.setButtonEnum(ButtonEnum.Ham);
        rightBmb.setPiecePlaceEnum(PiecePlaceEnum.HAM_3);
        rightBmb.setButtonPlaceEnum(ButtonPlaceEnum.HAM_3);
        for (int i = 0; i < rightBmb.getPiecePlaceEnum().pieceNumber(); i++)
            rightBmb.addBuilder(BuilderManager.getHamButtonBuilderWithDifferentPieceColor(text2s[i],texts2[i]));


        leftBmb.setOnBoomListener(new OnBoomListener() {
            @Override
            public void onClicked(int index, BoomButton boomButton) {
                switch (index) {
                    case 0:
                        String url0 = "mqqwpa://im/chat?chat_type=wpa&uin=337047207";
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url0)));
                        break;
                    case 1:
                        Intent intent1 = new Intent();
                        ComponentName cmp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
                        intent1.setAction(Intent.ACTION_MAIN);
                        intent1.addCategory(Intent.CATEGORY_LAUNCHER);
                        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent1.setComponent(cmp);
                        startActivity(intent1);
                        break;
                    case 2:
                        if (ContextCompat.checkSelfPermission(ActionBarActivity.this,
                                Manifest.permission.CALL_PHONE)
                                != PackageManager.PERMISSION_GRANTED) {

                            ActivityCompat.requestPermissions(ActionBarActivity.this,
                                    new String[]{Manifest.permission.CALL_PHONE}, 111);
                        } else {
                            callPhone();
                        }
                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    case 5:

                        break;
                    case 6:

                        break;
                    case 7:

                        break;
                }
            }

            @Override
            public void onBackgroundClick() {

            }

            @Override
            public void onBoomWillHide() {

            }

            @Override
            public void onBoomDidHide() {

            }

            @Override
            public void onBoomWillShow() {

            }

            @Override
            public void onBoomDidShow() {

            }
        });

        rightBmb.setOnBoomListener(new OnBoomListener() {
            @Override
            public void onClicked(int index, BoomButton boomButton) {
                switch (index){
                    case 0:
                        startActivity(new Intent(ActionBarActivity.this,MainActivity.class).putExtra("url","https://weibo.com/u/3034594103"));
                        break;
                    case 1:
                        startActivity(new Intent(ActionBarActivity.this,MainActivity.class).putExtra("url","https://weibo.com/zlf337047207"));
                        break;
                    case 2:
                        startActivity(new Intent(ActionBarActivity.this,TimeActivity.class));
                        break;
                    case 3:
                       // startActivity(new Intent(ActionBarActivity.this,MainActivity.class).putExtra("url","https://h5.qzone.qq.com/mqzone/profile?starttime=1512886146481&hostuin=337047207"));
                        break;
                }
            }

            @Override
            public void onBackgroundClick() {

            }

            @Override
            public void onBoomWillHide() {

            }

            @Override
            public void onBoomDidHide() {

            }

            @Override
            public void onBoomWillShow() {

            }

            @Override
            public void onBoomDidShow() {

            }
        });
    }

    private void openShareView() {
        UMWeb web = new UMWeb("http://a2.rabbitpre.com/m/ijjNA2VfH");
        web.setTitle("二傻子初告白");//标题
        web.setThumb(new UMImage(ActionBarActivity.this,R.mipmap.logo));//缩略图
        web.setDescription("呆呆我爱你！呆呆我爱你！" +
                "呆呆我爱你！");//描述
        //  UMImage image = new UMImage(ShareActivity.this, R.mipmap.ic_erweima);//资源文件
        new ShareAction(ActionBarActivity.this).withMedia(web)
                .setDisplayList(SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.QQ,SHARE_MEDIA.QZONE)
                .setCallback(umShareListener).open();
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //分享开始的回调
        }
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(ActionBarActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(ActionBarActivity.this,platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if(t!=null){
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(ActionBarActivity.this,platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };

    public void callPhone()
    {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + "18030390558");
        intent.setData(data);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {

        if (requestCode == 111)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callPhone();
            } else {
                Toast.makeText(ActionBarActivity.this, "去设置里面打开拨打电话的权限", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void initWebView() {
        webView= (WebView) findViewById(R.id.wv);
        webView.loadUrl("http://a2.rabbitpre.com/m/ijjNA2VfH");
        WebSettings webSettings =webView.getSettings();
        webSettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        webView.setWebViewClient(new WebViewClient(){

                 @Override
                 //只要重写此方法，就能在本应用中加载网页
                 public boolean shouldOverrideUrlLoading(WebView view, String url) {
                     view.loadUrl(url);
                     return true;
                 //返回值时true的时候控制网页在WebView中去打开，如果为false调用系统浏览器或第三方浏览器
                 }
                 @Override
                 public void onReceivedError(WebView view, int errorCode,
                                             String description, String failingUrl) {

                 }

             }
        );
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    private long mExitTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
