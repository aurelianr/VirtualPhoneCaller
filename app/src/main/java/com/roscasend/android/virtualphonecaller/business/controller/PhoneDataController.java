package com.roscasend.android.virtualphonecaller.business.controller;

import com.roscasend.android.virtualphonecaller.business.bean.TelephoneNumberBean;

import java.util.ArrayList;
import java.util.List;

public class PhoneDataController {
    private static PhoneDataController ourInstance = new PhoneDataController();

    public static PhoneDataController getInstance() {
        return ourInstance;
    }

    private PhoneDataController() {
    }

    private String latestValidNumber;
    private String latestInValidNumber;
    private List<TelephoneNumberBean> telephoneNumbers = new ArrayList<TelephoneNumberBean>();

    private String currentNumber;

    public String getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(String currentNumber) {
        this.currentNumber = currentNumber;
    }

    public String getLatestValidNumber() {
        return latestValidNumber;
    }

    public void setLatestValidNumber(String latestValidNumber) {
        this.latestValidNumber = latestValidNumber;
    }

    public String getLatestInValidNumber() {
        return latestInValidNumber;
    }

    public void setLatestInValidNumber(String latestInValidNumber) {
        this.latestInValidNumber = latestInValidNumber;
    }

    public List<TelephoneNumberBean> getTelephoneNumbers() {
        return telephoneNumbers;
    }

    public void addTelephoneNumbers(TelephoneNumberBean telephoneNumbers) {
        this.telephoneNumbers.add(telephoneNumbers);
    }


}
