package org.example.entities;

public class Ticket {
    private String ticketId;
    private String userId;
    private String source;
    private String destination;
    private String dateOfJourney;
    private Train train;

    public Ticket() {
    }

    public Ticket(String ticketId, String userId, String source, String destination, String dateOfJourney,
            Train train) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.source = source;
        this.destination = destination;
        this.dateOfJourney = dateOfJourney;
        this.train = train;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getUserId() {
        return userId;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getDateOfJourney() {
        return dateOfJourney;
    }

    public Train getTrain() {
        return train;
    }

    public String getTicketInfo() {
        return String.format("Ticket ID: %s belongs to User %s from %s to %s on %s", ticketId, userId, source,
                destination, dateOfJourney);
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDateOfJourney(String dateOfJourney) {
        this.dateOfJourney = dateOfJourney;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

}
