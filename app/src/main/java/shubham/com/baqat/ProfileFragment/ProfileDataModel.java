package shubham.com.baqat.ProfileFragment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileDataModel {

    @SerializedName("Total_Listing")
    @Expose
    private String totalListing;
    @SerializedName("Expired_Listing")
    @Expose
    private String expiredListing;
    @SerializedName("Active_Listing")
    @Expose
    private String activeListing;
    @SerializedName("Pending_Approval")
    @Expose
    private String pendingApproval;

    public String getTotalListing() {
        return totalListing;
    }

    public void setTotalListing(String totalListing) {
        this.totalListing = totalListing;
    }

    public String getExpiredListing() {
        return expiredListing;
    }

    public void setExpiredListing(String expiredListing) {
        this.expiredListing = expiredListing;
    }

    public String getActiveListing() {
        return activeListing;
    }

    public void setActiveListing(String activeListing) {
        this.activeListing = activeListing;
    }

    public String getPendingApproval() {
        return pendingApproval;
    }

    public void setPendingApproval(String pendingApproval) {
        this.pendingApproval = pendingApproval;
    }


}
