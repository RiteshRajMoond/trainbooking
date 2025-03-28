package org.example.entities;

import java.sql.Time;
import java.util.List;
import java.util.Map;

public class Train {
    private String trainId;
    private String trainNumber;
    private List<List<Integer>> seats;
    private List<String> stations;
    private Map<String, String> arrivalTime;

    public Train() {
    }

    public Train(String trainId, String trainNumber, List<List<Integer>> seats, List<String> stations,
            Map<String, String> arrivalTime) {
        this.trainId = trainId;
        this.trainNumber = trainNumber;
        this.seats = seats;
        this.stations = stations;
        this.arrivalTime = arrivalTime;
    }

    public String getTrainInfo() {
        return String.format("Train ID: %s, Train Number: %s,", trainId, trainNumber);
    }

    public String getTrainId() {
        return trainId;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public List<List<Integer>> getSeats() {
        return seats;
    }

    public List<String> getStations() {
        return stations;
    }

    public Map<String, String> getArrivalTime() {
        return arrivalTime;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public void setSeats(List<List<Integer>> seats) {
        this.seats = seats;
    }

    public void setStations(List<String> stations) {
        this.stations = stations;
    }

    public void setArrivalTime(Map<String, String> arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
