package example.com.fenlei.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;

/**
 * Created by lenovo on 2018/4/20.
 */

public class NetWorkUtils {

    // 获取当前网络状态
    public static boolean getNetState(Context context) {
        if (Build.VERSION.SDK_INT < 21) {
            return checkNetStateBelow21(context);
        } else {
            return checkNetStateAbove21(context);
        }
    }


    // API版本21以下时调用此方法进行检测
    //因为API21后getNetworkInfo(int networkType)方法被弃用
    public static boolean checkNetStateBelow21(Context context) {
        //步骤1：通过Context.getSystemService(Context.CONNECTIVITY_SERVICE)获得ConnectivityManager对象
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        //步骤2：获取ConnectivityManager对象对应的NetworkInfo对象
        //NetworkInfo对象包含网络连接的所有信息
        //步骤3：根据需要取出网络连接信息
        //获取WIFI连接的信息
        NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        boolean isWifiConn = networkInfo.isConnected();

        //获取移动数据连接的信息
        networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        boolean isMobileConn = networkInfo.isConnected();
        return isWifiConn || isMobileConn;
    }

    //API版本23及以上时调用此方法进行网络的检测
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static boolean checkNetStateAbove21(Context context) {
        //获得ConnectivityManager对象
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        //获取所有网络连接的信息
        Network[] networks = connMgr.getAllNetworks();
        //用于存放网络连接信息
        StringBuilder sb = new StringBuilder();
        //通过循环将网络信息逐个取出来
        for (int i = 0; i < networks.length; i++) {
            //获取ConnectivityManager对象对应的NetworkInfo对象
            NetworkInfo networkInfo = connMgr.getNetworkInfo(networks[i]);
            if (networkInfo.isConnected()) {
                return true;
            }
        }
        return false;
    }
}
