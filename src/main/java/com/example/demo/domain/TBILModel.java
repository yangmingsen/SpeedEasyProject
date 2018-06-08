package com.example.demo.domain;

public class TBILModel {

    private String ERN;
    private String CompanyName;

    public String getERN() {
        return ERN;
    }

    public void setERN(String ERN) {
        this.ERN = ERN;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public TBILModel(String ERN, String companyName) {
        this.ERN = ERN;
        CompanyName = companyName;
    }
}
