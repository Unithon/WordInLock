package com.example.jinwoo.edulock;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

/**
 * Created by Jinwoo on 2016-02-11.
 */

public class ScreenService extends Service {
    private ScreenReceiver mReceiver = null;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //private PackageReceiver pReceiver;

    @Override
    public void onCreate() {
        super.onCreate();

        mReceiver = new ScreenReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        registerReceiver(mReceiver, filter);

        //app이 update, create, delete되었을 때 서비스 실행해주도록 !
        /*
        pReceiver = new PackageReceiver();

        IntentFilter pFilter = new IntentFilter(Intent.ACTION_PACKAGE_ADDED);
        pFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        pFilter.addAction(Intent.ACTION_PACKAGE_REPLACED);
        pFilter.addDataScheme("package");
        registerReceiver(pReceiver, pFilter);
        */
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        //foregrond로 taskkiller에 죽지 않도록 계속 돌려줌.
        startForeground(1, new Notification());

        if (intent != null) {
            if (intent.getAction() == null) {
                if (mReceiver == null) {
                    mReceiver = new ScreenReceiver();
                    IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
                    registerReceiver(mReceiver, filter);
                }
            }
        }
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mReceiver.reenableKeyguard();

        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
        }

        //app이 update, create, delete되었을 때 서비스 실행해주도록 !
        /*
        if(pReceiver != null)
            unregisterReceiver(pReceiver);
            */
    }
}