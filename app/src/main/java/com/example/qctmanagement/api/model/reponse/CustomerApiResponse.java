package com.example.qctmanagement.api.model.reponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Quoc Cuong on 8/25/2020.
 */
public class CustomerApiResponse implements Serializable {
    @SerializedName("ITEMCODE")
    private int iTEMCODE;
    @SerializedName("ITEMNAME")
    private String iTEMNAME;
    @SerializedName("ADDRESS")
    private String aDDRESS;
    @SerializedName("PHONE")
    private String pHONE;
    @SerializedName("EMAIL")
    private String eMAIL;
    @SerializedName("STATUS")
    private int sTATUS;
    @SerializedName("USERNAME")
    private String uSERNAME;
    @SerializedName("PASSWORD")
    private String pASSWORD;
    @SerializedName("IMAGE")
    private String iMAGE;
    @SerializedName("CREATEDATE")
    private String cREATEDATE;

    public CustomerApiResponse() {
    }

    public int getiTEMCODE() {
        return iTEMCODE;
    }

    public void setiTEMCODE(int iTEMCODE) {
        this.iTEMCODE = iTEMCODE;
    }

    public String getiTEMNAME() {
        return iTEMNAME;
    }

    public void setiTEMNAME(String iTEMNAME) {
        this.iTEMNAME = iTEMNAME;
    }

    public String getaDDRESS() {
        return aDDRESS;
    }

    public void setaDDRESS(String aDDRESS) {
        this.aDDRESS = aDDRESS;
    }

    public String getpHONE() {
        return pHONE;
    }

    public void setpHONE(String pHONE) {
        this.pHONE = pHONE;
    }

    public String geteMAIL() {
        return eMAIL;
    }

    public void seteMAIL(String eMAIL) {
        this.eMAIL = eMAIL;
    }

    public int getsTATUS() {
        return sTATUS;
    }

    public void setsTATUS(int sTATUS) {
        this.sTATUS = sTATUS;
    }

    public String getuSERNAME() {
        return uSERNAME;
    }

    public void setuSERNAME(String uSERNAME) {
        this.uSERNAME = uSERNAME;
    }

    public String getpASSWORD() {
        return pASSWORD;
    }

    public void setpASSWORD(String pASSWORD) {
        this.pASSWORD = pASSWORD;
    }

    public String getiMAGE() {
        return iMAGE;
    }

    public void setiMAGE(String iMAGE) {
        this.iMAGE = iMAGE;
    }

    public String getcREATEDATE() {
        return cREATEDATE;
    }

    public void setcREATEDATE(String cREATEDATE) {
        this.cREATEDATE = cREATEDATE;
    }
}
