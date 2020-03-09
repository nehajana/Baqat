package shubham.com.baqat.HomeFragment;

import java.util.ArrayList;

public class HomeVerticleAbstractModel {

    private String title;

    private int img;


    public HomeVerticleAbstractModel(String title, int img) {
        this.title = title;
        this.img = img;
    }

    public HomeVerticleAbstractModel() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
