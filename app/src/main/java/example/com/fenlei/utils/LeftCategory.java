package example.com.fenlei.utils;

import com.google.gson.annotations.SerializedName;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by eric on 2018/4/18.
 */

@Entity
public class LeftCategory {
    @Id
    private Long cid;
    @SerializedName("createtime")
    private String createTime;
    private String icon;
    @SerializedName("ishome")
    private int isHome;
    private String name;

    @Generated(hash = 1247440683)
    public LeftCategory(Long cid, String createTime, String icon, int isHome,
            String name) {
        this.cid = cid;
        this.createTime = createTime;
        this.icon = icon;
        this.isHome = isHome;
        this.name = name;
    }

    @Generated(hash = 275471825)
    public LeftCategory() {
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getIsHome() {
        return isHome;
    }

    public void setIsHome(int isHome) {
        this.isHome = isHome;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
