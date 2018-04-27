package example.com.fenlei.MVP;

/**
 * Created by lenovo on 2018/4/20.
 */

public interface FenLeiJieKou {

    interface Iview<T> {
        void onSuccess(T t);
        void onFailed(Throwable t);
    }

    interface IPresenter<T> {
        void onReceived(T t);
        void onError(Throwable t);
    }

    interface OnRecyclerViewItemClickListener {
        void onItemClick(int position);
    }
}
