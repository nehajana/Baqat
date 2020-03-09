package shubham.com.baqat.HarshitCreateAdd.ApimodelKeyword;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KeywordDataModel {

    @SerializedName("keyword_id")
    @Expose
    private String keywordId;
    @SerializedName("keyword")
    @Expose
    private String keyword;
    @SerializedName("keyword_slug")
    @Expose
    private String keywordSlug;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public String getKeywordId() {
        return keywordId;
    }

    public void setKeywordId(String keywordId) {
        this.keywordId = keywordId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getKeywordSlug() {
        return keywordSlug;
    }

    public void setKeywordSlug(String keywordSlug) {
        this.keywordSlug = keywordSlug;
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
}
