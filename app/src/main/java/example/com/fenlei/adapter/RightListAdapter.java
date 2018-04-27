package example.com.fenlei.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.fenlei.R;
import example.com.fenlei.utils.RightCategory;
import example.com.fenlei.utils.SecondProduct;

/**
 * Created by lenovo on 2018/4/20.
 */

public class RightListAdapter extends RecyclerView.Adapter<RightListAdapter.ViewHolder> {
    private Context context;
    private List<RightCategory> list;

    public RightListAdapter(Context context, List<RightCategory> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_right_first, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtTitle.setText(list.get(position).getName());

        // 对应的二级的RecyclerView列表
        // 数据对应的是一条data里面的list
        SecondAdapter adapter = new SecondAdapter(context, list.get(position).getList());
        holder.rvSecond.setLayoutManager(new GridLayoutManager(context, 3));
        holder.rvSecond.setAdapter(adapter);
//        holder.rvSecond;
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_right_first)
        TextView txtTitle;

        @BindView(R.id.rv_second_right)
        RecyclerView rvSecond;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    // 第二级的RecyclerView适配器
    class SecondAdapter extends RecyclerView.Adapter<SecondAdapter.SecondViewHolder> {
        private Context context;
        private List<SecondProduct> products;

        public SecondAdapter(Context context, List<SecondProduct> products) {
            this.context = context;
            this.products = products;
        }

        @NonNull
        @Override
        public SecondViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = View.inflate(context, R.layout.item_right_second, null);
            SecondViewHolder holder = new SecondViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull SecondViewHolder holder, int position) {
            Glide.with(context).load(products.get(position).getIcon()).into(holder.imgLogo);
            holder.txtSecondTitle.setText(products.get(position).getName());
        }

        @Override
        public int getItemCount() {
            if (products != null) {
                return products.size();
            }
            return 0;
        }

        class SecondViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.img_right_second_item)
            ImageView imgLogo;

            @BindView(R.id.txt_right_second_item)
            TextView txtSecondTitle;

            public SecondViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}