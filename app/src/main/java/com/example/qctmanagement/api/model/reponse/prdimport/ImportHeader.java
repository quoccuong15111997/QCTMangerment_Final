package com.example.qctmanagement.api.model.reponse.prdimport;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ImportHeader implements Serializable {
    @SerializedName("IMPORTCODE")
    private Integer iMPORTCODE;
    @SerializedName("IMPDATE")
    private String iMPDATE;
    @SerializedName("EMPCODE")
    private String eMPCODE;
    @SerializedName("EMPNAME")
    private String eMPNAME;

    public ImportHeader() {
    }

    public Integer getiMPORTCODE() {
        return iMPORTCODE;
    }

    public void setiMPORTCODE(Integer iMPORTCODE) {
        this.iMPORTCODE = iMPORTCODE;
    }

    public String getiMPDATE() {
        return iMPDATE;
    }

    public void setiMPDATE(String iMPDATE) {
        this.iMPDATE = iMPDATE;
    }

    public String geteMPCODE() {
        return eMPCODE;
    }

    public void seteMPCODE(String eMPCODE) {
        this.eMPCODE = eMPCODE;
    }

    public String geteMPNAME() {
        return eMPNAME;
    }

    public void seteMPNAME(String eMPNAME) {
        this.eMPNAME = eMPNAME;
    }
}
