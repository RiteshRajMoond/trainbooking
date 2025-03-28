package org.example.services;

import org.example.util.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.example.entities.*;

public class UserBookingService {

    private ObjectMapper objectMapper = new ObjectMapper();
    private User user;
    private List<User> userList;
    private final String USER_FILE_PATH = "f:/java/irctc/app/src/main/java/org/example/localDB/users.json";

    public UserBookingService(User user) throws IOException {
        this.user = user;
        loadUserListFromFile();
    }

    public UserBookingService() throws IOException {
        loadUserListFromFile();
    }

    private void loadUserListFromFile() throws IOException {
        try {
            userList = objectMapper.readValue(new File(USER_FILE_PATH), new TypeReference<List<User>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    public Boolean loginUser() {
        Optional<User> foundUser = userList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName())
                    && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedPassword());
        }).findFirst();
        return foundUser.isPresent();
    }

    public Boolean signUp(User user) {
        try {
            userList.add(user);
            saveUserListToFile();
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    public void saveUserListToFile() throws IOException {
        File userFile = new File(USER_FILE_PATH);
        objectMapper.writeValue(userFile, userList);
    }

    public void fetchBookings() {
        Optional<User> foundUser = userList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName())
                    && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedPassword());
        }).findFirst();

        if (foundUser.isPresent()) {
            foundUser.get().printTickets();
        }
    }

    public Boolean cancelBooking(String ticketId) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the ticket ID to cancel: ");
        ticketId = s.nextLine();

        if (ticketId == null || ticketId.isEmpty()) {
            System.out.println("Ticket ID cannot be null or empty.");
            return Boolean.FALSE;
        }

        String finalTicketId = ticketId;

        boolean removed = user.getTickets().removeIf(ticket -> ticket.getTicketId().equalsIgnoreCase(finalTicketId));

        if (removed) {
            System.out.println("Ticket with ID " + finalTicketId + " has been cancelled successfully.");
            return Boolean.TRUE;
        } else {
            System.out.println("Ticket with ID " + finalTicketId + " not found.");
            return Boolean.FALSE;
        }
    }

    public List<Train> getTrains(String source, String destination) {
        try {
            TrainService trainService = new TrainService();
            List<Train> trains = trainService.searchTrains(source, destination);
            return trains;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public List<List<Integer>> getSeats(Train train) {
        return train.getSeats();
    }

    public Boolean bookTrainSeat(Train train, int row, int col) {
        try {
            TrainService trainService = new TrainService();
            List<List<Integer>> seats = train.getSeats();
            if (row >= 0 && row < seats.size() && col >= 0 && col < seats.get(row).size()) {
                if (seats.get(row).get(col) == 0) {
                    seats.get(row).set(col, 1);
                    train.setSeats(seats);
                    trainService.updateTrain(train);
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }
}
