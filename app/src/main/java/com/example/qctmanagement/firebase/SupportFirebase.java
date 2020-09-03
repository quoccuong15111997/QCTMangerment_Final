package com.example.qctmanagement.firebase;

public class SupportFirebase {
    private String title;
    private long number;

    public SupportFirebase(String title, int number) {
        this.title = title;
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public SupportFirebase() {
    }
}
