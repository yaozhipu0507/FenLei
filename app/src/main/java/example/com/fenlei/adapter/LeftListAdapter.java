package example.com.fenlei.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.fenlei.utils.LeftCategory;
import example.com.fenlei.MVP.FenLeiJieKou;
import example.com.fenlei.R;

/**
 * Created by lenovo on 2018/4/20.
 */

public class LeftListAdapter extends RecyclerView.Adapter<LeftListAdapter.ViewHolder> {
    private Context context;
    private List<LeftCategory> list;

    private FenLeiJieKou.OnRecyclerViewItemClickListener listener;

    public void setListener(FenLeiJieKou.OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }

    public LeftListAdapter(Context context, List<LeftCategory> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_left_list, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(context).load(list.get(position).getIcon()).into(holder.imgLogo);
        holder.txtTitle.setText(list.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_left_item)
        ImageView imgLogo;

        @BindView(R.id.txt_left_item)
        TextView txtTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}