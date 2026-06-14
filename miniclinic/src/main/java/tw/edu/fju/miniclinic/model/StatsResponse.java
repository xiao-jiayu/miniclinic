package tw.edu.fju.miniclinic.model;

import java.util.Map;

public class StatsResponse {
    private long totalDoctors;
    private long totalPatients;
    private long totalAppointments;

    public StatsResponse() {
    }

    // Getters and Setters
    public long getTotalDoctors() {
        return totalDoctors;
    }

    public void setTotalDoctors(long totalDoctors) {
        this.totalDoctors = totalDoctors;
    }

    public long getTotalPatients() {
        return totalPatients;
    }

    public void setTotalPatients(long totalPatients) {
        this.totalPatients = totalPatients;
    }

    public long getTotalAppointments() {
        return totalAppointments;
    }

    public void setTotalAppointments(long totalAppointments) {
        this.totalAppointments = totalAppointments;
    }
}