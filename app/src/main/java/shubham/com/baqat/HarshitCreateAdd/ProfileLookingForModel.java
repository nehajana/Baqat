package shubham.com.baqat.HarshitCreateAdd;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileLookingForModel {

    @SerializedName("profile_id")
    @Expose
    private String profileId;
    @SerializedName("profileName")
    @Expose
    private String profileName;

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

}
