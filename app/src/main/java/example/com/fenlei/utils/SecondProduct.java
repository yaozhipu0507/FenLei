package example.com.fenlei.utils;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by eric on 2018/4/18.
 */

@Entity
public class SecondProduct {
    private String icon;
    private String name;
    private int pcid;
    @Id
    private Long pscid;

    @Generated(hash = 820941372)
    public SecondProduct(String icon, String name, int pcid, Long pscid) {
        this.icon = icon;
        this.name = name;
        this.pcid = pcid;
        this.pscid = pscid;
    }

    @Generated(hash = 1664098311)
    public SecondProduct() {
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPcid() {
        return pcid;
    }

    public void setPcid(int pcid) {
        this.pcid = pcid;
    }

    public Long getPscid() {
        return pscid;
    }

    public void setPscid(Long pscid) {
        this.pscid = pscid;
    }
}
