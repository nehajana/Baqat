package shubham.com.baqat.BlogScreen;

import java.util.ArrayList;

public class BlogActivityAbstractModel {

    private String title;

    private String message;


    public BlogActivityAbstractModel(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public BlogActivityAbstractModel() {

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
