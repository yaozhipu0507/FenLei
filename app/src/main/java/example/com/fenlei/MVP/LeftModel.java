package example.com.fenlei.MVP;

import android.content.Context;
import android.util.Log;
import java.util.List;

import example.com.fenlei.db.LeftCategoryDao;
import example.com.fenlei.utils.BaseApplication;
import example.com.fenlei.utils.LeftCategory;
import example.com.fenlei.utils.HttpUtils;
import example.com.fenlei.utils.NetWorkUtils;
import example.com.fenlei.db.DBManager;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by lenovo on 2018/4/20.
 */

public class LeftModel {
    private static final String TAG = "LeftModel";
    private FenLeiJieKou.IPresenter presenter;
    private Context context;

    public LeftModel(Context context, FenLeiJieKou.IPresenter presenter) {
        this.presenter = presenter;
        this.context = context;
    }

    /**
     * 网络请求
     */
    public void getData() {
        // 1. 先看下内存之中是否存在这个数据;
        List<LeftCategory> leftCategories = BaseApplication.getInstance().getLeftCategories();
        // 内存中不存在
        if (leftCategories == null) {
            getDataFromDBOrHttp(context);
        } else {
            presenter.onReceived(leftCategories);
        }
    }

    private void getDataFromDBOrHttp(Context context) {

        // 从数据库中查询
        LeftCategoryDao leftCategoryDao = DBManager.getInstance(context).getLeftCategoryDao();
        List<LeftCategory> leftCategories = leftCategoryDao.loadAll();
        // 数据库中有数据，则从数据库中获取
        if (leftCategories != null) {
            // 存到内存中一份
            BaseApplication.getInstance().setLeftCategories(leftCategories);
            presenter.onReceived(leftCategories);
        }
        // 数据库中没有数据，则从网络加载，数据库中有数据的时候，也从网络加载最新数据
        if (NetWorkUtils.getNetState(context)) {
            getDataFromHttp();
        }

    }

    private void getDataFromHttp() {
        // Retrofit+RxJava网络请求，解析数据
        HttpUtils.getInstance().getService()
                .getFirstCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<MessageBean<List<LeftCategory>>>() {
                    @Override
                    public void onNext(MessageBean<List<LeftCategory>> listMessageBean) {
                        // 获取到数据之后，1.存到磁盘中一份
                        LeftCategoryDao dao = DBManager.getInstance(context).getLeftCategoryDao();
                        if (listMessageBean != null) {
                            List<LeftCategory> data = listMessageBean.getData();
                            if (data != null) {
                                // 有就替换，没有就插入
                                Log.i(TAG, "onNext: ");
                                // 把左侧列表数据，也就是data字段对应的列表数据插入数据库
                                dao.insertOrReplaceInTx(data);

                                // 2.还需要往内存中存一份
                                BaseApplication.getInstance().setLeftCategories(data);
                                // 将解析后的数据返回P层
                                presenter.onReceived(data);
                            }
                        }


                    }

                    @Override
                    public void onError(Throwable t) {
                        presenter.onError(t);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
