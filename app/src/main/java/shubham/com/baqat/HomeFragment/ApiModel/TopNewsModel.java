package shubham.com.baqat.HomeFragment.ApiModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopNewsModel {

    @SerializedName("errorCode")
    @Expose
    private Integer errorCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<TopNewsDataModel> data = null;

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<TopNewsDataModel> getData() {
        return data;
    }

    public void setData(List<TopNewsDataModel> data) {
        this.data = data;
    }
}
