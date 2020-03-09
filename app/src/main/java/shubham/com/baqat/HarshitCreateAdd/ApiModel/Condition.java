
package shubham.com.baqat.HarshitCreateAdd.ApiModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Condition {

    @SerializedName("flag")
    @Expose
    private String flag;
    @SerializedName("enum")
    @Expose
    private String _enum;
    @SerializedName("info")
    @Expose
    private List<Info> info = null;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getEnum() {
        return _enum;
    }

    public void setEnum(String _enum) {
        this._enum = _enum;
    }

    public List<Info> getInfo() {
        return info;
    }

    public void setInfo(List<Info> info) {
        this.info = info;
    }

}
