package com.example.qctmanagement.api.model.reponse.prdimport;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ImportApiResponse implements Serializable {
    @SerializedName("HEADER")
    private List<ImportHeader> importHeaders;
    @SerializedName("DETAIL")
    private List<ImportDetail> importDetails;

    public ImportApiResponse() {
    }

    public List<ImportHeader> getImportHeaders() {
        return importHeaders;
    }

    public void setImportHeaders(List<ImportHeader> importHeaders) {
        this.importHeaders = importHeaders;
    }

    public List<ImportDetail> getImportDetails() {
        return importDetails;
    }

    public void setImportDetails(List<ImportDetail> importDetails) {
        this.importDetails = importDetails;
    }
}
