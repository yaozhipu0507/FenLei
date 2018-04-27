package example.com.fenlei.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.greenrobot.eventbus.EventBus;

/**
 * Created by lenovo on 2018/4/20.
 */

public class NetWorkStateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("网络状态发生变化");
        // 监听到网络状态发生变化的时候，将当前的网络状态发送出去
        EventBus.getDefault().post(NetWorkUtils.getNetState(context) ? NetState.AVALIABLE : NetState.UNAVALIABLE);
    }

}
