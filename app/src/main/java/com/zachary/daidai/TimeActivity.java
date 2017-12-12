package com.zachary.daidai;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeActivity extends AppCompatActivity {
    private TextView time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        time= (TextView) findViewById(R.id.time);
        String str1 = "20171112";  //"yyyyMMdd"格式 如 20131022
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyyMMdd");
        String str2 = simpleDateFormat.format(new java.util.Date());
        Date date1 = null;
        try {
            date1 = simpleDateFormat.parse(str1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date date2 = null;
        try {
            date2 = simpleDateFormat.parse(str2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        GregorianCalendar cal1 = new GregorianCalendar();
        GregorianCalendar cal2 = new GregorianCalendar();
        cal1.setTime(date1);
        cal2.setTime(date2);
        double dayCount = (cal2.getTimeInMillis()-cal1.getTimeInMillis())/(1000*3600*24);//从间隔毫秒变成间隔天数
        time.setText((int)dayCount+"");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.share){
            openShareView();
        }
        return true;
    }

    private void openShareView() {
        UMWeb web = new UMWeb("http://120.79.49.167");
        web.setTitle("亲爱的呆呆");//标题
        web.setThumb(new UMImage(TimeActivity.this,R.mipmap.logo));//缩略图
        web.setDescription("有人告白啦！有人告白啦！有人告白啦！");//描述
        //  UMImage image = new UMImage(ShareActivity.this, R.mipmap.ic_erweima);//资源文件
        new ShareAction(TimeActivity.this).withMedia(web)
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
            Toast.makeText(TimeActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(TimeActivity.this,platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if(t!=null){
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(TimeActivity.this,platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };
}
