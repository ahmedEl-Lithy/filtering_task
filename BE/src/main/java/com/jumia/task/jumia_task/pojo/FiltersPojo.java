package com.jumia.task.jumia_task.pojo;

public class FiltersPojo {

    private String country;
    private String state;

    public FiltersPojo(String country, String state) {
        this.country = country;
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "FiltersPojo [country=" + country + ", state=" + state + "]";
    }

}
