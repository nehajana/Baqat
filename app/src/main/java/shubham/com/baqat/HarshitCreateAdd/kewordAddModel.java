package shubham.com.baqat.HarshitCreateAdd;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class kewordAddModel {

  String keywordname;

    public kewordAddModel(String keywordname) {
        this.keywordname = keywordname;
    }

    public String getKeywordname() {
        return keywordname;
    }

    public void setKeywordname(String keywordname) {
        this.keywordname = keywordname;
    }
}
