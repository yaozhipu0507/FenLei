package example.com.fenlei.utils;

import android.app.Application;
import android.content.IntentFilter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2018/4/20.
 */

public class BaseApplication extends Application {

    private NetWorkStateReceiver receiver;

    // 内存缓存
    private List<LeftCategory> leftCategories;
    private Map<String,List<RightCategory>> rightMap;

    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        rightMap = new HashMap<>();
        // 网络状态发生变化时候发出的广播
        IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        receiver = new NetWorkStateReceiver();
        registerReceiver(receiver, filter);
    }

    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (receiver != null) {
            unregisterReceiver(receiver);
        }
    }

    public List<RightCategory> getRightCategories(String cid) {
        // 取出cid对应的内存缓存数据
        if (rightMap.containsKey(cid)) {
            return rightMap.get(cid);
        }else{
            return null;
        }
    }

    public void setRightCategories(String cid, List<RightCategory> rightCategories) {
        // 内存缓存中存cid对应的数据
        rightMap.put(cid, rightCategories);
    }

    public List<LeftCategory> getLeftCategories() {
        return leftCategories;
    }

    public void setLeftCategories(List<LeftCategory> leftCategories) {
        this.leftCategories = leftCategories;
    }
}
