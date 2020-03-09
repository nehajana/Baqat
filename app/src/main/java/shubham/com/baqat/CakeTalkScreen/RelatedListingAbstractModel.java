package shubham.com.baqat.CakeTalkScreen;

public class RelatedListingAbstractModel {

    private String title;

    private String message;


    public RelatedListingAbstractModel(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public RelatedListingAbstractModel() {

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
