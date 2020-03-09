package shubham.com.baqat.HarshitCreateAdd.ApimodelKeyword;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import shubham.com.baqat.HarshitCreateAdd.ApiModel.Datum;

public class KeywordModel {

    @SerializedName("errorCode")
    @Expose
    private Integer errorCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<KeywordDataModel> data = null;

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

    public List<KeywordDataModel> getData() {
        return data;
    }

    public void setData(List<KeywordDataModel> data) {
        this.data = data;
    }
}
