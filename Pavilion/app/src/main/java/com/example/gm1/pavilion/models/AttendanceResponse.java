package com.example.gm1.pavilion.models;

import java.util.List;

public class AttendanceResponse {
    private boolean status;
    private String message;
    private List<AttendanceList> data;

    public AttendanceResponse(boolean status, String message, List<AttendanceList> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<AttendanceList> getData() {
        return data;
    }
}
