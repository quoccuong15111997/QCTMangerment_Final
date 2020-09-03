package com.example.qctmanagement.api.model.reponse.prdimport;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ImportDetail implements Serializable {
    @SerializedName("PRDCODE")
    private String pRDCODE;
    @SerializedName("PRDNAME")
    private String pRDNAME;
    @SerializedName("QUATITY")
    private Double qUATITY;
    @SerializedName("COLOR1")
    private String cOLOR1;
    @SerializedName("COLOR2")
    private String cOLOR2;
    @SerializedName("COLOR3")
    private String cOLOR3;

    public ImportDetail() {
    }

    public String getpRDCODE() {
        return pRDCODE;
    }

    public void setpRDCODE(String pRDCODE) {
        this.pRDCODE = pRDCODE;
    }

    public String getpRDNAME() {
        return pRDNAME;
    }

    public void setpRDNAME(String pRDNAME) {
        this.pRDNAME = pRDNAME;
    }

    public Double getqUATITY() {
        return qUATITY;
    }

    public void setqUATITY(Double qUATITY) {
        this.qUATITY = qUATITY;
    }

    public String getcOLOR1() {
        return cOLOR1;
    }

    public void setcOLOR1(String cOLOR1) {
        this.cOLOR1 = cOLOR1;
    }

    public String getcOLOR2() {
        return cOLOR2;
    }

    public void setcOLOR2(String cOLOR2) {
        this.cOLOR2 = cOLOR2;
    }

    public String getcOLOR3() {
        return cOLOR3;
    }

    public void setcOLOR3(String cOLOR3) {
        this.cOLOR3 = cOLOR3;
    }
}
