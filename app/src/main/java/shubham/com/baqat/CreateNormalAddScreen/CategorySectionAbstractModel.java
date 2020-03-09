package shubham.com.baqat.CreateNormalAddScreen;

import java.util.ArrayList;

public class CategorySectionAbstractModel {

    private String title;

    private String message;


    public CategorySectionAbstractModel(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public CategorySectionAbstractModel() {

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
