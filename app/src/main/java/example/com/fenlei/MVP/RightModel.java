package example.com.fenlei.MVP;

import android.content.Context;
import android.util.Log;
import java.util.List;

import example.com.fenlei.db.RightCategoryDao;
import example.com.fenlei.db.SecondProductDao;
import example.com.fenlei.utils.BaseApplication;
import example.com.fenlei.db.DBManager;
import example.com.fenlei.utils.HttpUtils;
import example.com.fenlei.utils.NetWorkUtils;
import example.com.fenlei.utils.RightCategory;
import example.com.fenlei.utils.SecondProduct;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by lenovo on 2018/4/20.
 */

public class RightModel {
    private static final String TAG = "RightModel";
    private FenLeiJieKou.IPresenter presenter;
    private Context context;

    public RightModel(Context context, FenLeiJieKou.IPresenter presenter) {
        this.presenter = presenter;
        this.context = context;
    }

    public void getData(String cid) {
        // 1. 从内存缓存中取
        List<RightCategory> rightCategories = BaseApplication.getInstance().getRightCategories(cid);

        // 2. 内存缓存中没有数据则从数据库或者网络取
        if (rightCategories == null) {
            getDataFromDBOrHttp(context, cid);
        } else {
            Log.i(TAG, "从内存缓存中取");
            presenter.onReceived(rightCategories);
        }

    }

    private void getDataFromDBOrHttp(Context context, String cid) {
        // 先数据库取数据
        RightCategoryDao rightCategoryDao = DBManager.getInstance(context).getRightCategoryDao();
        // 根据cid查询出对应的条目
        List<RightCategory> rightCategories = rightCategoryDao.queryBuilder().where(RightCategoryDao.Properties.Cid.eq(cid))
                .build().list();

        SecondProductDao secondProductDao = DBManager.getInstance(context).getSecondProductDao();
        for (RightCategory rightCategory : rightCategories) {
            // 查询出子条目中pcid=外层pcid的条目
            List<SecondProduct> list = secondProductDao.queryBuilder().where(SecondProductDao.Properties.Pcid.eq(rightCategory.getPcid()))
                    .build().list();
            if (list != null) {
                rightCategory.setList(list);
            }
        }
        Log.i(TAG, "从数据库中取");
        // 保存到内存之中一份
        if (rightCategories != null) {
            if (rightCategories.size() > 0) {
                BaseApplication.getInstance().setRightCategories(cid, rightCategories);
            }
        }

//       List<RightCategory> rightCategories就是我们model层需要获取的数据
        // 数据返回给P层
        presenter.onReceived(rightCategories);

        if (NetWorkUtils.getNetState(context)) {
            getDataFromHttp(cid);
        }

    }

    private void getDataFromHttp(final String cid) {
        // RxJava+Retrofit做网络请求
        HttpUtils.getInstance().getService()
                .getSecondCategory(cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<MessageBean<List<RightCategory>>>() {
                    @Override
                    public void onNext(MessageBean<List<RightCategory>> listMessageBean) {
                        if (listMessageBean != null) {
                            // 对应的是data字段里面的列表
                            List<RightCategory> data = listMessageBean.getData();
                            if (data != null) {
                                // 右侧列表的第一级
                                RightCategoryDao dao = DBManager.getInstance(context).getRightCategoryDao();
                                dao.insertOrReplaceInTx(data);
                                // 内层集合
                                for (RightCategory inner : data) {
                                    // 循环遍历，将每一个data里面的list字段插入到数据库
                                    List<SecondProduct> innerList = inner.getList();
                                    if (innerList != null) {
                                        SecondProductDao productDao = DBManager.getInstance(context).getSecondProductDao();
                                        // 插入内层的数据库列表
                                        productDao.insertOrReplaceInTx(innerList);
                                    }
                                }

                                // 再添加进内存缓存
                                if (data.size() > 0) {
                                    BaseApplication.getInstance().setRightCategories(cid, data);
                                }
                                Log.i(TAG, "从网络取");
                                // 获取到数据之后传给P层
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
