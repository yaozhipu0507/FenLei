package example.com.fenlei.MVP;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import org.greenrobot.eventbus.EventBus;
import java.util.ArrayList;
import android.support.annotation.Nullable;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import example.com.fenlei.utils.BaseApplication;
import example.com.fenlei.utils.LeftCategory;
import example.com.fenlei.R;
import example.com.fenlei.adapter.LeftListAdapter;

/**
 * Created by lenovo on 2018/4/20.
 */

public class LeftFragment extends Fragment implements FenLeiJieKou.Iview<List<LeftCategory>>{
    private static final String TAG = "LeftFragment";
    /**
     * 左侧列表的视图
     */
    @BindView(R.id.rv_left)
    RecyclerView rvLeft;

    Unbinder unbinder;
    private Context mActivity;

    private LeftListAdapter adapter;
    private List<LeftCategory> list;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_left, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // RecyclerView设置适配器
        list = new ArrayList<>();
        adapter = new LeftListAdapter(mActivity, list);

        // 左侧的条目点击监听
        adapter.setListener(new FenLeiJieKou.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Long cid = list.get(position).getCid();
                // 左侧列表的cid传出去，右侧列表进行接收
                EventBus.getDefault().post(cid);
            }
        });

        rvLeft.setLayoutManager(new LinearLayoutManager(mActivity));
        rvLeft.setAdapter(adapter);

        // 左侧的Presenter
        LeftPresenter presenter = new LeftPresenter(BaseApplication.getInstance().getApplicationContext());
        presenter.attachView(this);
        // 左侧的Present调用自己的请求数据的方法来获取左侧所有的数据
        presenter.getData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onSuccess(List<LeftCategory> data) {
        // 获取到网络数据之后，把数据的列表添加到原有的列表之中，然后刷新适配器
        if (data != null) {
            list.addAll(data);
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onFailed(Throwable t) {
        Toast.makeText(mActivity, "数据获取失败:" + t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
