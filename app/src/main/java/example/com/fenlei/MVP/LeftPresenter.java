package example.com.fenlei.MVP;

import android.content.Context;
import java.util.List;
import example.com.fenlei.utils.LeftCategory;

/**
 * Created by lenovo on 2018/4/20.
 */

public class LeftPresenter implements FenLeiJieKou.IPresenter<List<LeftCategory>>{
    private FenLeiJieKou.Iview iv;
    private Context context;

    public LeftPresenter() {

    }

    public LeftPresenter(Context context) {
        this.context = context;
    }

    public void attachView(FenLeiJieKou.Iview iv) {
        this.iv = iv;
    }

    public void detachView() {
        if (iv != null) {
            iv = null;
        }
    }

    public void getData() {
        // 请求M层获取网络数据
        LeftModel model = new LeftModel(context, this);
        model.getData();
    }


    @Override
    public void onReceived(List<LeftCategory> listMessageBean) {
        // 接受到M层返回的数据之后
        // 把数据返回给V层
        iv.onSuccess(listMessageBean);
    }

    @Override
    public void onError(Throwable t) {
        iv.onFailed(t);
    }
}
