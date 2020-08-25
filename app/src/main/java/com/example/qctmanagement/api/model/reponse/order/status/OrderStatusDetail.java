package com.example.qctmanagement.api.model.reponse.order.status;

import com.google.gson.annotations.SerializedName;

public class OrderStatusDetail {
    @SerializedName("PRDCODE")
    private String pRDCODE;
    @SerializedName("PRDNAME")
    private String pRDNAME;
    @SerializedName("QUATITY")
    private double qUATITY;
    @SerializedName("COLOR")
    private String cOLOR;
    @SerializedName("PRDIMAGE")
    private String pRDIMAGE;
    @SerializedName("PRDPRICE")
    private double pRDPRICE;
    @SerializedName("PRDSALEPRICE")
    private double pRDSALEPRICE;

    public OrderStatusDetail() {
    }

    public double getpRDPRICE() {
        return pRDPRICE;
    }

    public void setpRDPRICE(double pRDPRICE) {
        this.pRDPRICE = pRDPRICE;
    }

    public double getpRDSALEPRICE() {
        return pRDSALEPRICE;
    }

    public void setpRDSALEPRICE(double pRDSALEPRICE) {
        this.pRDSALEPRICE = pRDSALEPRICE;
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

    public double getqUATITY() {
        return qUATITY;
    }

    public void setqUATITY(double qUATITY) {
        this.qUATITY = qUATITY;
    }

    public String getcOLOR() {
        return cOLOR;
    }

    public void setcOLOR(String cOLOR) {
        this.cOLOR = cOLOR;
    }

    public String getpRDIMAGE() {
        return pRDIMAGE;
    }

    public void setpRDIMAGE(String pRDIMAGE) {
        this.pRDIMAGE = pRDIMAGE;
    }
}
