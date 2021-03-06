package shubham.com.baqat.Singupscreen.ApiModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import shubham.com.baqat.HarshitCreateAdd.ApiModel.Datum;

public class SocialLoginModel {

    @SerializedName("errorCode")
    @Expose
    private Integer errorCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<SocialDataModel> data = null;

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

    public List<SocialDataModel> getData() {
        return data;
    }

    public void setData(List<SocialDataModel> data) {
        this.data = data;
    }

}
