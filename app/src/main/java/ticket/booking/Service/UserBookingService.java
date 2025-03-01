package ticket.booking.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.Entity.Train;
import ticket.booking.Entity.User;
import ticket.booking.util.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserBookingService {

    //global user which can be used by any function
    private User user;

    private List<User> userList;

    // object mapper is used to serialize and deserialize
    //user_id -> userId mapper is used here
    private ObjectMapper objectMapper = new ObjectMapper();

    //final so that no one can change the path
    private static final String USERS_PATH = "app/src/main/java/ticket/booking/LocalDB/users.json";

    public UserBookingService() throws IOException {
        loadUsers();
    }

    public List<User> loadUsers() throws IOException {
        File users = new File(USERS_PATH);
        return objectMapper.readValue(users, new TypeReference<List<User>>() {
        });
    }

    //calling constructor just to verify the current user before performing any function
    public UserBookingService(User user1) throws IOException {
        this.user = user1;
        loadUsers();
    }

    //function for login
    public Boolean loginUser() {
        Optional<User> foundUser = userList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashPassword());
        }).findFirst();
        return foundUser.isPresent();
    }

    //function for signUp
    public Boolean signUp(User user1) {
        try {
            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        } catch (IOException ex) {
            return Boolean.FALSE;
        }
    }

    //json --> Object (User) --> Deserialize
    //Object (User) --> json --> Serialize

    //function to add the locally saved user to the file
    private void saveUserListToFile() throws IOException {
        File userFile = new File(USERS_PATH);
        //serialization
        objectMapper.writeValue(userFile, userList);
    }

    public void fetchBooking() {
        user.printTickets();
    }

    //function to cancel booking
    public Boolean cancelBooking(String ticketId) {
        if (ticketId == null || ticketId.isEmpty()) {
            System.out.println("Ticket ID cannot be null or empty");
            return Boolean.FALSE;
        }

        boolean removed = user.getTicketsBooked().removeIf(ticket -> ticket.getTicketId().equals(ticketId));

        if (removed) {
            System.out.println("Ticket with ID " + ticketId + " has been canceled.");
            return Boolean.TRUE;
        } else {
            System.out.println("No ticket found with ID " + ticketId);
            return Boolean.FALSE;
        }
    }

    //function to search trains between stations
    public List<Train> getTrains(String source,String destination){
        TrainService trainService = new TrainService();
        return trainService.searchTrains(source,destination);
    }

}
