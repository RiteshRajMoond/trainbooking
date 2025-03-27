package org.example.entities;

import java.sql.Time;
import java.util.List;
import java.util.Map;

public class Train {
    private String trainId;
    private int trainNumber;
    private List<List<Integer>> seats;
    private List<String> stations;
    private Map<String, Time> arrivalTime;
}
