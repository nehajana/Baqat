package shubham.com.baqat.CareerScreen;

public class BussinessCareerAbstractModel {

    private String title;

    private String message;


    public BussinessCareerAbstractModel(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public BussinessCareerAbstractModel() {

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
