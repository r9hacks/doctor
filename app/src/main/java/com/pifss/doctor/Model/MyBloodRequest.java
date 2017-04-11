package com.pifss.doctor.Model;

/**
 * Created by PIFSS on 4/11/2017.
 */

public class MyBloodRequest {

    private String bloodType = "";
    private int drId = 0;
    private int quantity = 0;
    private String reason = "";
    private int requestsId = 0;
    private int status = 0;
    private String timestamp = "";

    public MyBloodRequest(String bloodType, int drId, int quantity, String reason, int requestsId, int status, String timestamp) {
        this.bloodType = bloodType;
        this.drId = drId;
        this.quantity = quantity;
        this.reason = reason;
        this.requestsId = requestsId;
        this.status = status;
        this.timestamp = timestamp;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public int getDrId() {
        return drId;
    }

    public void setDrId(int drId) {
        this.drId = drId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getRequestsId() {
        return requestsId;
    }

    public void setRequestsId(int requestsId) {
        this.requestsId = requestsId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
