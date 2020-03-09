package shubham.com.baqat.RestaurantsCateringScreen;

import java.util.ArrayList;

public class RestaurentCateringAbstractModel {

    private String title;

    private String message;


    public RestaurentCateringAbstractModel(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public RestaurentCateringAbstractModel() {

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
