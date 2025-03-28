/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import org.example.entities.Train;
import org.example.entities.User;
import org.example.services.UserBookingService;
import org.example.util.UserServiceUtil;

public class App {
    public static void main(String[] args) {
        System.out.println("Running the IRCTC application...");
        Scanner sc = new Scanner(System.in);
        int option = 0;
        UserBookingService userBookingService;
        try {
            userBookingService = new UserBookingService();
        } catch (Exception e) {
            System.out.println("Error loading user data: " + e.getMessage());
            return;
        }

        while (option != 7) {
            System.out.println("Choose option");
            System.out.println("1. Sign up");
            System.out.println("2. Login");
            System.out.println("3. Fetch Bookings");
            System.out.println("4. Search Trains");
            System.out.println("5. Book a Seat");
            System.out.println("6. Cancel my Booking");
            System.out.println("7. Exit the App");

            option = sc.nextInt();
            Train trainSelected = new Train();

            switch (option) {
                case 1:
                    System.out.println("Enter the username to signup!");
                    String name = sc.next();
                    System.out.println("Enter the password to signup!");
                    String password = sc.next();
                    User user = new User(name, password, UserServiceUtil.hashPassword(password), new ArrayList<>(),
                            UUID.randomUUID().toString());
                    userBookingService.signUp(user);
                    System.out.println("User signed up successfully!");
                    break;
                case 2:
                    System.out.println("Enter the username to login!");
                    String loginName = sc.next();
                    System.out.println("Enter the password to login!");
                    String loginPassword = sc.next();
                    User userLogin = new User(loginName, loginPassword, UserServiceUtil.hashPassword(loginPassword),
                            new ArrayList<>(), UUID.randomUUID().toString());
                    try {
                        userBookingService = new UserBookingService(userLogin);
                    } catch (Exception e) {
                        return;
                    }
                    break;
                case 3:
                    System.out.println("Fetching bookings...");
                    userBookingService.fetchBookings();
                    break;
                case 4:
                    System.out.println("Type your source destination");
                    String source = sc.next();
                    System.out.println("Type your destination");
                    String destination = sc.next();
                    List<Train> trains = userBookingService.getTrains(source, destination);
                    int index = 1;
                    for (Train t : trains) {
                        System.out.println(index + " Train id : " + t.getTrainId());
                        for (Map.Entry<String, String> entry : t.getArrivalTime().entrySet()) {
                            System.out.println("Station: " + entry.getKey() + ", Arrival Time: " + entry.getValue());
                        }
                    }
                    System.out.println("Select the train by printing the index");
                    trainSelected = trains.get(sc.nextInt());
                    break;
                case 5:
                    System.out.println("Select a seat out of these");
                    List<List<Integer>> seats = userBookingService.getSeats(trainSelected);
                    for(List<Integer> row : seats) {
                        for(Integer val: row) {
                            System.out.print(val + " ");
                        }
                        System.out.println();
                    }
                    System.out.println("Select the row and column to book");
                    int row = sc.nextInt();
                    int col = sc.nextInt();
                    System.out.println("Booking the seat...");
                    Boolean booked = userBookingService.bookTrainSeat(trainSelected, row, col);
                    if(booked.equals(Boolean.TRUE)) {
                        System.out.println("Booked!");
                    } else {
                        System.out.println("Seat already booked!");
                    }
                    break;
                case 6:
                    System.out.println("Enter the ticket ID to cancel: ");
                    String ticketId = sc.next();
                    Boolean cacelled = userBookingService.cancelBooking(ticketId);
                    if(cacelled.equals(Boolean.TRUE)) {
                        System.out.println("Cancelled!");
                    } else {
                        System.out.println("Ticket not found!");
                    }
                    break;
                default: 
                    break;
            }
        }
    }
}
