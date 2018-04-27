package example.com.fenlei.api;

import java.util.List;
import example.com.fenlei.utils.LeftCategory;
import example.com.fenlei.MVP.MessageBean;
import example.com.fenlei.utils.RightCategory;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by lenovo on 2018/4/20.
 */

public interface ApiService {

    @GET("product/getCatagory")
    Flowable<MessageBean<List<LeftCategory>>> getFirstCategory();

    @GET("product/getProductCatagory")
    Flowable<MessageBean<List<RightCategory>>> getSecondCategory(@Query("cid") String cid);
}