package shubham.com.baqat.UserProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import shubham.com.baqat.HarshitCreateAdd.ApiModel.Datum;

public class LocationDataModel {

    @SerializedName("state_id")
    @Expose
    private String stateId;
    @SerializedName("state_name")
    @Expose
    private String stateName;
    @SerializedName("tbl_country_country_id")
    @Expose
    private String tblCountryCountryId;
    @SerializedName("status")
    @Expose
    private String status;

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getTblCountryCountryId() {
        return tblCountryCountryId;
    }

    public void setTblCountryCountryId(String tblCountryCountryId) {
        this.tblCountryCountryId = tblCountryCountryId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
