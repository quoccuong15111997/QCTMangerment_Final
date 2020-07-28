package com.example.qctmanagement.model;

/**
 * Created by Nguyen Quoc Cuong on 7/28/2020.
 */
public class WizardModel {
    private String title;
    private String description;
    private int Image;

    public WizardModel() {
    }

    public WizardModel(String title, String description, int image) {
        this.title = title;
        this.description = description;
        Image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }
}
