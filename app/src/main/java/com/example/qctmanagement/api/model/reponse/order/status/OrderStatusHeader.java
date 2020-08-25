package com.example.qctmanagement.api.model.reponse.order.status;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.annotations.SerializedName;

public class OrderStatusHeader extends BaseObservable {
    @SerializedName("ORDCODE")
    public int oRDCODE;
    @SerializedName("STTCODE")
    public int sTTCODE;
    @SerializedName("STTNAME")
    public String sTTNAME;
    @SerializedName("CRTDATE")
    public String oRDDATE;
    @SerializedName("SHIPADDRESS")
    public String sHPADDR;
    @SerializedName("SHIPCOMP")
    public String sHPCOMP;
    @SerializedName("CUSTCODE")
    public int cUSTCODE;
    @SerializedName("CUSTNAME")
    public String cUSTNAME;
    @SerializedName("PHONE")
    public String pHONE;
    @SerializedName("PAYMETHOD")
    public String pAYMETHOD;

    public OrderStatusHeader() {
    }

    public int getoRDCODE() {
        return oRDCODE;
    }

    public String getpHONE() {
        return pHONE;
    }

    public void setpHONE(String pHONE) {
        this.pHONE = pHONE;
    }

    public String getpAYMETHOD() {
        return pAYMETHOD;
    }

    public void setpAYMETHOD(String pAYMETHOD) {
        this.pAYMETHOD = pAYMETHOD;
    }

    public void setoRDCODE(int oRDCODE) {
        this.oRDCODE = oRDCODE;
    }

    public int getsTTCODE() {
        return sTTCODE;
    }

    public void setsTTCODE(int sTTCODE) {
        this.sTTCODE = sTTCODE;
    }

    @Bindable
    public String getsTTNAME() {
        return sTTNAME;
    }

    public void setsTTNAME(String sTTNAME) {
        this.sTTNAME = sTTNAME;
    }

    public String getoRDDATE() {
        return oRDDATE;
    }

    public void setoRDDATE(String oRDDATE) {
        this.oRDDATE = oRDDATE;
    }
    @Bindable
    public String getsHPADDR() {
        return sHPADDR;
    }

    public void setsHPADDR(String sHPADDR) {
        this.sHPADDR = sHPADDR;
    }
    @Bindable
    public String getsHPCOMP() {
        return sHPCOMP;
    }

    public void setsHPCOMP(String sHPCOMP) {
        this.sHPCOMP = sHPCOMP;
    }

    public int getcUSTCODE() {
        return cUSTCODE;
    }

    public void setcUSTCODE(int cUSTCODE) {
        this.cUSTCODE = cUSTCODE;
    }

    public String getcUSTNAME() {
        return cUSTNAME;
    }

    public void setcUSTNAME(String cUSTNAME) {
        this.cUSTNAME = cUSTNAME;
    }
}
