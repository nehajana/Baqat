package shubham.com.baqat.HomeSearchApi;

import java.util.ArrayList;

public class HomeSearchAbstractModel {

    private String title;

    private String message;


    public HomeSearchAbstractModel(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public HomeSearchAbstractModel() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
