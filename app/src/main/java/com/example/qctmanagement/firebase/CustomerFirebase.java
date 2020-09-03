package com.example.qctmanagement.firebase;

/**
 * Created by Nguyen Quoc Cuong on 8/28/2020.
 */
public class CustomerFirebase {
    private int custCode;
    private String custName;
    private String custUsername;
    private String urlImage;

    public CustomerFirebase(int custCode, String custName, String custUsername, String urlImage) {
        this.custCode = custCode;
        this.custName = custName;
        this.custUsername = custUsername;
        this.urlImage = urlImage;
    }

    public CustomerFirebase() {
    }

    public int getCustCode() {
        return custCode;
    }

    public void setCustCode(int custCode) {
        this.custCode = custCode;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustUsername() {
        return custUsername;
    }

    public void setCustUsername(String custUsername) {
        this.custUsername = custUsername;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
