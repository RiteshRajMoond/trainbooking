package org.example.entities;

import java.util.List;

public class User {
    private String name;
    private String password;
    private String hashedPassword;
    private List<Ticket> tickets;
    private String userId;

    public User(String name, String password, String hashedPassword, List<Ticket> tickets, String userId) {
        this.name = name;
        this.password = password;
        this.hashedPassword = hashedPassword;
        this.tickets = tickets;
        this.userId = userId;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public String getUserId() {
        return userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void printTickets() {
        for (Ticket ticket : tickets) {
            System.out.println(ticket);
        }
    }
}
