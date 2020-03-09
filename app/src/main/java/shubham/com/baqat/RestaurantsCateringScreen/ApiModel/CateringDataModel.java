package shubham.com.baqat.RestaurantsCateringScreen.ApiModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import shubham.com.baqat.HarshitCreateAdd.ApiModel.Datum;

public class CateringDataModel {

    @SerializedName("errorCode")
    @Expose
    private Integer errorCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<CateringDataList> data = null;

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

    public List<CateringDataList> getData() {
        return data;
    }

    public void setData(List<CateringDataList> data) {
        this.data = data;
    }


}
