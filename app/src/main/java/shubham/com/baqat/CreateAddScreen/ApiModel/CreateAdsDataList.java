package shubham.com.baqat.CreateAddScreen.ApiModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateAdsDataList {

    @SerializedName("package_id")
    @Expose
    private String packageId;
    @SerializedName("package_type")
    @Expose
    private String packageType;
    @SerializedName("parent_package_id")
    @Expose
    private String parentPackageId;
    @SerializedName("ads_location")
    @Expose
    private String adsLocation;
    @SerializedName("ads_volume")
    @Expose
    private String adsVolume;
    @SerializedName("ads_validity")
    @Expose
    private String adsValidity;
    @SerializedName("package_price")
    @Expose
    private String packagePrice;
    @SerializedName("currency_code")
    @Expose
    private String currencyCode;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("deleted_at")
    @Expose
    private String deletedAt;

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getParentPackageId() {
        return parentPackageId;
    }

    public void setParentPackageId(String parentPackageId) {
        this.parentPackageId = parentPackageId;
    }

    public String getAdsLocation() {
        return adsLocation;
    }

    public void setAdsLocation(String adsLocation) {
        this.adsLocation = adsLocation;
    }

    public String getAdsVolume() {
        return adsVolume;
    }

    public void setAdsVolume(String adsVolume) {
        this.adsVolume = adsVolume;
    }

    public String getAdsValidity() {
        return adsValidity;
    }

    public void setAdsValidity(String adsValidity) {
        this.adsValidity = adsValidity;
    }

    public String getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(String packagePrice) {
        this.packagePrice = packagePrice;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }

}
