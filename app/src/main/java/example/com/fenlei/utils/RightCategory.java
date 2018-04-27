package example.com.fenlei.utils;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by eric on 2018/4/18.
 */

@Entity
public class RightCategory {
    private String cid;
    private String name;
    @Transient
    private List<SecondProduct> list;
    @Id
    private Long pcid;
    @Generated(hash = 489537434)
    public RightCategory(String cid, String name, Long pcid) {
        this.cid = cid;
        this.name = name;
        this.pcid = pcid;
    }
    @Generated(hash = 1703583084)
    public RightCategory() {
    }
    public String getCid() {
        return this.cid;
    }
    public void setCid(String cid) {
        this.cid = cid;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getPcid() {
        return this.pcid;
    }
    public void setPcid(Long pcid) {
        this.pcid = pcid;
    }

    public List<SecondProduct> getList() {
        return list;
    }

    public void setList(List<SecondProduct> list) {
        this.list = list;
    }
}
