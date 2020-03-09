
package shubham.com.baqat.HarshitCreateAdd.ApiModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Info {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("value")
    @Expose
    private String value;

    boolean isSlectedType = false;

    public boolean getisSlectedType() {
        return isSlectedType;
    }

    public void setSlectedType(boolean slectedType) {
        isSlectedType = slectedType;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
