package example.com.fenlei.MVP;

import android.content.Context;
import java.util.List;
import example.com.fenlei.utils.RightCategory;

/**
 * Created by lenovo on 2018/4/20.
 */

public class RightPresenter implements FenLeiJieKou.IPresenter<List<RightCategory>> {

    private FenLeiJieKou.Iview iv;
    private Context context;

    public RightPresenter() {

    }

    public RightPresenter(Context context) {
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

    // 调用M层来获取网络数据
    public void getData(String cid) {
        RightModel model = new RightModel(context,this);
        model.getData(cid);
    }


    // 接受M层获取的数据
    @Override
    public void onReceived(List<RightCategory> listMessageBean) {
        // 把数据回调给V层
        iv.onSuccess(listMessageBean);
    }

    @Override
    public void onError(Throwable t) {
        iv.onFailed(t);
    }
}

