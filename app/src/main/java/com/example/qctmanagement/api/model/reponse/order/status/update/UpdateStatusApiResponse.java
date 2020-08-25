package com.example.qctmanagement.api.model.reponse.order.status.update;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nguyen Quoc Cuong on 8/25/2020.
 */
public class UpdateStatusApiResponse implements Serializable {
    @SerializedName("ORDCODE")
    private int oRDCODE;
    @SerializedName("STTCODE")
    private int sTTCODE;
    @SerializedName("STTNAME")
    private String sTTNAME;
    @SerializedName("ORDDATE")
    private String oRDDATE;
    @SerializedName("CUSTCODE")
    private int cUSTCODE;
    @SerializedName("CUSTNAME")
    private String cUSTNAME;
    @SerializedName("SHPADDR")
    private String sHPADDR;
    @SerializedName("SHPCOMP")
    private String sHPCOMP;
    @SerializedName("PRDCODE")
    private String pRDCODE;
    @SerializedName("PRDNAME")
    private String pRDNAME;
    @SerializedName("QUATITY")
    private double qUATITY;
    @SerializedName("COLOR")
    private String cOLOR;

    public UpdateStatusApiResponse() {
    }

    public int getoRDCODE() {
        return oRDCODE;
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

    public String getsHPADDR() {
        return sHPADDR;
    }

    public void setsHPADDR(String sHPADDR) {
        this.sHPADDR = sHPADDR;
    }

    public String getsHPCOMP() {
        return sHPCOMP;
    }

    public void setsHPCOMP(String sHPCOMP) {
        this.sHPCOMP = sHPCOMP;
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
}
