package com.example.qctmanagement.api.model.request.product;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductRequest implements Serializable {
    @SerializedName("ITEMCODE")
    private String iTEMCODE;
    @SerializedName("ITEMNAME")
    private String iTEMNAME;
    @SerializedName("UNITCODE")
    private Integer uNITCODE;
    @SerializedName("DESCRIPTION")
    private String dESCRIPTION;
    @SerializedName("SDESCRIPTION")
    private String sDESCRIPTION;
    @SerializedName("QUANTITY")
    private Double qUANTITY;
    @SerializedName("PRICE")
    private Double pRICE;
    @SerializedName("SELLPRICE")
    private Double sELLPRICE;
    @SerializedName("IMAGE")
    private String iMAGE;
    @SerializedName("COLOR1")
    private String cOLOR1;
    @SerializedName("COLOR2")
    private String cOLOR2;
    @SerializedName("COLOR3")
    private String cOLOR3;
    @SerializedName("DISCOUNT")
    private Boolean dISCOUNT;
    @SerializedName("DISCOUNTPRICE")
    private Double dISCOUNTPRICE;
    @SerializedName("CATEGORYCODE")
    private Integer cATEGORYCODE;
    @SerializedName("EMPLOYEECODE")
    private String employeeCode;

    public ProductRequest() {
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
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

    public Integer getuNITCODE() {
        return uNITCODE;
    }

    public void setuNITCODE(Integer uNITCODE) {
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

    public Double getqUANTITY() {
        return qUANTITY;
    }

    public void setqUANTITY(Double qUANTITY) {
        this.qUANTITY = qUANTITY;
    }

    public Double getpRICE() {
        return pRICE;
    }

    public void setpRICE(Double pRICE) {
        this.pRICE = pRICE;
    }

    public Double getsELLPRICE() {
        return sELLPRICE;
    }

    public void setsELLPRICE(Double sELLPRICE) {
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

    public Boolean getdISCOUNT() {
        return dISCOUNT;
    }

    public void setdISCOUNT(Boolean dISCOUNT) {
        this.dISCOUNT = dISCOUNT;
    }

    public Double getdISCOUNTPRICE() {
        return dISCOUNTPRICE;
    }

    public void setdISCOUNTPRICE(Double dISCOUNTPRICE) {
        this.dISCOUNTPRICE = dISCOUNTPRICE;
    }

    public Integer getcATEGORYCODE() {
        return cATEGORYCODE;
    }

    public void setcATEGORYCODE(Integer cATEGORYCODE) {
        this.cATEGORYCODE = cATEGORYCODE;
    }
}
