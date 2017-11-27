package com.github.phoenix.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.phoenix.R;
import com.github.phoenix.cb.ChannelInterfaceProxy;
import com.github.phoenix.service.UpdateSdkUtil;
import com.github.phoenix.utils.Constant;
import com.github.phoenix.utils.Constants;
import com.github.phoenix.utils.LogUtil;
import com.github.phoenix.utils.SPUtil;

import java.io.File;

/**
 * 模拟版本更新进度
 *
 * @author Phoenix
 * @date 2016-10-24 16:58
 */
public class MainActivity extends AppCompatActivity {
    private String TAG = this.getClass().getSimpleName();

//    private NumberProgressBar bnp;
//
//    private boolean isBindService;
//    private ServiceConnection conn = new ServiceConnection() {
//
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            UpdateService.DownloadBinder binder = (UpdateService.DownloadBinder) service;
//            UpdateService downloadService = binder.getService();
//
//            //接口回调，下载进度
//            downloadService.setOnProgressListener(new OnProgressListener() {
//                @Override
//                public void onProgress(float fraction) {
//                    LogUtil.i(TAG, "下载进度：" + (int)(fraction * 100) + "%");
//                    bnp.setProgress((int)(fraction * 100));
//
//                    //判断是否真的下载完成进行安装了，以及是否注册绑定过服务
//                    if (fraction == UpdateService.UNBIND_SERVICE && isBindService) {
//                        unbindService(conn);
//                        isBindService = false;
//                        MToast.shortToast("下载完成！");
//                    }
//                }
//            });
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        initView();
//
////        removeOldApk();
//
//        initService("http://huleshikongres-10034783.file.myqcloud.com/newfishu3d/android/apks/xinjiejibuyu20.apk");

        String json = "{\"updateVersion\":\"2.1.0\",\"updateType\":\"2\",\"packageName\":\"com.hule.fishing\",\"updateUrl\":\"http://huleshikongres-10034783.file.myqcloud.com/newfishu3d/android/apks/xinjiejibuyu20.apk\",\"updateMsg\":\"2.1.0\"}";
        // 版本更新处理
        UpdateSdkUtil.updateSdkVersion(this, "2.0.0", json, "测试下载", new ChannelInterfaceProxy.ApplicationInitCallback() {
            @Override
            public void execute() {
                Log.i(Constants.tag, "加载apk失败，参数不正确！！！！！");
            }
        });


    }

//    public void initService(String apkUrl) {
////        Intent intent = new Intent(this, UpdateService.class);
////        intent.putExtra(UpdateService.BUNDLE_KEY_DOWNLOAD_URL, apkUrl);
////        intent.putExtra(UpdateService.DOWNLOAD_APP_NAME, "fishing!!!");
//
//        Intent intent = new Intent();
//        intent.setAction("com.openapi.util.UpdateService");
//        intent.setPackage(getPackageName());
//        intent.putExtra(UpdateService.BUNDLE_KEY_DOWNLOAD_URL, apkUrl);
//        intent.putExtra(UpdateService.DOWNLOAD_APP_NAME, "fishing!!!");
//        startService(intent);
//
//        isBindService = bindService(intent, conn, BIND_AUTO_CREATE);
//    }

//    /**
//     * 初始化View
//     */
//    private void initView() {
//        bnp = (NumberProgressBar) findViewById(R.id.number_bar);
//    }

    /**
     * 删除上次更新存储在本地的apk
     */
    private void removeOldApk() {
        //获取老ＡＰＫ的存储路径
        File fileName = new File(SPUtil.getString(Constant.SP_DOWNLOAD_PATH, ""));
        LogUtil.i(TAG, "老APK的存储路径 =" + SPUtil.getString(Constant.SP_DOWNLOAD_PATH, ""));

        if (fileName != null && fileName.exists() && fileName.isFile()) {
            fileName.delete();
            LogUtil.i(TAG, "存储器内存在老APK，进行删除操作");
        }
    }
}
