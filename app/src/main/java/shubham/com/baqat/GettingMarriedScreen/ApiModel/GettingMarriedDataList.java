package shubham.com.baqat.GettingMarriedScreen.ApiModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GettingMarriedDataList {

    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("web_category_slug")
    @Expose
    private String webCategorySlug;
    @SerializedName("css_icon_class")
    @Expose
    private String cssIconClass;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("parent_category_id")
    @Expose
    private String parentCategoryId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("mobile_icon")
    @Expose
    private String mobileIcon;
    @SerializedName("web_img")
    @Expose
    private String webImg;
    @SerializedName("web_original_img")
    @Expose
    private String webOriginalImg;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("deleted_at")
    @Expose
    private String deletedAt;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getWebCategorySlug() {
        return webCategorySlug;
    }

    public void setWebCategorySlug(String webCategorySlug) {
        this.webCategorySlug = webCategorySlug;
    }

    public String getCssIconClass() {
        return cssIconClass;
    }

    public void setCssIconClass(String cssIconClass) {
        this.cssIconClass = cssIconClass;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(String parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMobileIcon() {
        return mobileIcon;
    }

    public void setMobileIcon(String mobileIcon) {
        this.mobileIcon = mobileIcon;
    }

    public String getWebImg() {
        return webImg;
    }

    public void setWebImg(String webImg) {
        this.webImg = webImg;
    }

    public String getWebOriginalImg() {
        return webOriginalImg;
    }

    public void setWebOriginalImg(String webOriginalImg) {
        this.webOriginalImg = webOriginalImg;
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
