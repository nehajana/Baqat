package shubham.com.baqat.ProfileFragment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import shubham.com.baqat.HarshitCreateAdd.ApiModel.Datum;
import shubham.com.baqat.LoginSceen.ApiModel.LoginDataModel;

public class ProfileModel {

    @SerializedName("errorCode")
    @Expose
    private Integer errorCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<ProfileDataModel> data = null;

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

    public List<ProfileDataModel> getData() {
        return data;
    }

    public void setData(List<ProfileDataModel> data) {
        this.data = data;
    }


}
