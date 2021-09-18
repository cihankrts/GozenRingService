package com.cihan.gozenringservis;

public class Request {
    private String station1;
    private String station2;
    private String count;
    private String time;

    public Request() {
    }

    public Request(String station1, String station2, String count, String time) {
        this.station1 = station1;
        this.station2 = station2;
        this.count = count;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStation1() {
        return station1;
    }

    public void setStation1(String station1) {
        this.station1 = station1;
    }

    public String getStation2() {
        return station2;
    }

    public void setStation2(String station2) {
        this.station2 = station2;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
