package example.com.fenlei.MVP;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.ArrayList;
import java.util.List;
import android.support.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import example.com.fenlei.utils.BaseApplication;
import example.com.fenlei.utils.NetState;
import example.com.fenlei.R;
import example.com.fenlei.utils.RightCategory;
import example.com.fenlei.adapter.RightListAdapter;

/**
 * Created by lenovo on 2018/4/20.
 */

public class RightFragment extends Fragment implements FenLeiJieKou.Iview<List<RightCategory>> {
    /**
     * 左侧列表的视图
     */
    @BindView(R.id.rv_right)
    RecyclerView rvRight;

    Unbinder unbinder;
    private Context mActivity;

    private RightListAdapter adapter;
    private List<RightCategory> list;
    private RightPresenter presenter;

    private Long currentCid = 1L;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_right, null);
        unbinder = ButterKnife.bind(this, view);
        // 注册EventBus
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // 列表展示适配器
        list = new ArrayList<>();
        adapter = new RightListAdapter(mActivity, list);

        rvRight.setLayoutManager(new LinearLayoutManager(mActivity));
        rvRight.setAdapter(adapter);

        rvRight.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL));

        // 右侧的P层对象
        presenter = new RightPresenter(BaseApplication.getInstance().getApplicationContext());
        presenter.attachView(this);
        // 一开始的时候默认获取第一条数据
        presenter.getData(String.valueOf(currentCid));
    }


    // 接收左侧传过来的列表cid
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveData(Long cid) {
        // 接收到左侧的cid之后做网络请求，获取数据，刷新适配器
        currentCid = cid;
        presenter.getData(String.valueOf(cid));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void netState(NetState state) {
        if (state == NetState.AVALIABLE) {
            presenter.getData(String.valueOf(currentCid));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // 取消EventBus注册
        EventBus.getDefault().unregister(this);
        unbinder.unbind();
    }

    @Override
    public void onSuccess(List<RightCategory> data) {
        // 获取到数据之后，把数据添加到原有集合中，之后刷新适配器
        if (data != null) {
            list.clear();
            list.addAll(data);
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onFailed(Throwable t) {
        list.clear();
        adapter.notifyDataSetChanged();
        Toast.makeText(mActivity, "数据获取失败:" + t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
