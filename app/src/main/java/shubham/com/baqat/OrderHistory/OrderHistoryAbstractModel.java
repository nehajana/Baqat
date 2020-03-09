package shubham.com.baqat.OrderHistory;

import java.util.ArrayList;

public class OrderHistoryAbstractModel {

    private String title;

    private String message;


    public OrderHistoryAbstractModel(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public OrderHistoryAbstractModel() {

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
