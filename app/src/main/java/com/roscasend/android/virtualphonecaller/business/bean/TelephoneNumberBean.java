package com.roscasend.android.virtualphonecaller.business.bean;

public class TelephoneNumberBean {
    private String number;
    private boolean valid;
    private boolean called;
    private String country;

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isCalled() {
        return called;
    }

    public void setCalled(boolean called) {
        this.called = called;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "TelehoneNumberBean{" +
                "number='" + number + '\'' +
                ", valid=" + valid +
                ", called=" + called +
                ", country='" + country + '\'' +
                '}';
    }
}
