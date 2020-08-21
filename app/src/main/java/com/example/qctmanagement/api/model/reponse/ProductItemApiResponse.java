package com.example.qctmanagement.api.model.reponse;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.$Gson$Types;

import java.io.Serializable;
import java.util.List;

public class ProductItemApiResponse extends BaseObservable implements Serializable {
    @SerializedName("ITEMCODE")
    public String iTEMCODE;
    @SerializedName("ITEMNAME")
    public String iTEMNAME;
    @SerializedName("UNITCODE")
    public int uNITCODE;
    @SerializedName("DESCRIPTION")
    public String dESCRIPTION;
    @SerializedName("SDESCRIPTION")
    public String sDESCRIPTION;
    @SerializedName("QUANTITY")
    public double qUANTITY;
    @SerializedName("PRICE")
    public double pRICE;
    @SerializedName("SELLPRICE")
    public double sELLPRICE;
    @SerializedName("IMAGE")
    public String iMAGE;
    @SerializedName("COLOR1")
    public String cOLOR1;
    @SerializedName("COLOR2")
    public String cOLOR2;
    @SerializedName("COLOR3")
    public String cOLOR3;
    @SerializedName("DISCOUNT")
    public boolean dISCOUNT;
    @SerializedName("DISCOUNTPRICE")
    public double dISCOUNTPRICE;
    @SerializedName("CATEGORYCODE")
    public int cATEGORYCODE;

    public ProductItemApiResponse() {
    }

    public String getiTEMCODE() {
        return iTEMCODE;
    }

    public void setiTEMCODE(String iTEMCODE) {
        this.iTEMCODE = iTEMCODE;
    }

    public String getiTEMNAME() {
        return iTEMNAME;
    }

    public void setiTEMNAME(String iTEMNAME) {
        this.iTEMNAME = iTEMNAME;
    }

    public int getuNITCODE() {
        return uNITCODE;
    }

    public void setuNITCODE(int uNITCODE) {
        this.uNITCODE = uNITCODE;
    }

    public String getdESCRIPTION() {
        return dESCRIPTION;
    }

    public void setdESCRIPTION(String dESCRIPTION) {
        this.dESCRIPTION = dESCRIPTION;
    }

    public String getsDESCRIPTION() {
        return sDESCRIPTION;
    }

    public void setsDESCRIPTION(String sDESCRIPTION) {
        this.sDESCRIPTION = sDESCRIPTION;
    }

    public double getqUANTITY() {
        return qUANTITY;
    }

    public void setqUANTITY(double qUANTITY) {
        this.qUANTITY = qUANTITY;
    }

    public double getpRICE() {
        return pRICE;
    }

    public void setpRICE(double pRICE) {
        this.pRICE = pRICE;
    }

    public double getsELLPRICE() {
        return sELLPRICE;
    }

    public void setsELLPRICE(double sELLPRICE) {
        this.sELLPRICE = sELLPRICE;
    }

    public String getiMAGE() {
        return iMAGE;
    }

    public void setiMAGE(String iMAGE) {
        this.iMAGE = iMAGE;
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

    public boolean isdISCOUNT() {
        return dISCOUNT;
    }

    public void setdISCOUNT(boolean dISCOUNT) {
        this.dISCOUNT = dISCOUNT;
    }

    public double getdISCOUNTPRICE() {
        return dISCOUNTPRICE;
    }

    public void setdISCOUNTPRICE(double dISCOUNTPRICE) {
        this.dISCOUNTPRICE = dISCOUNTPRICE;
    }

    public int getcATEGORYCODE() {
        return cATEGORYCODE;
    }

    public void setcATEGORYCODE(int cATEGORYCODE) {
        this.cATEGORYCODE = cATEGORYCODE;
    }
}
