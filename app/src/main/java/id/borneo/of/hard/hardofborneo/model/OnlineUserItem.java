package id.borneo.of.hard.hardofborneo.model;

/**
 * Created by mrx on 11/26/2017.
 */

public class OnlineUserItem {
    int imageProfile;
    String status;

    public OnlineUserItem(int imageProfile , String status) {
        this.imageProfile = imageProfile;
    }

    public int getImageProfile() {
        return imageProfile;
    }

    public void setImageProfile(int imageProfile) {
        this.imageProfile = imageProfile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
