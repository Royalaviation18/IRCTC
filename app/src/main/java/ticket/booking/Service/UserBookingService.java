package ticket.booking.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.Entity.User;

import java.io.File;

public class UserBookingService {

    //global user which can be used by any function
    private User user;

    // object mapper is used to serialize and deserialize
    private static final ObjectMapper =

    private static final String USERS_PATH = "../LocalDB/users.json";

    //calling constructor just to verify the current user before performing any function
    public UserBookingService(User user1) {
        this.user = user1;
        File users = new File(USERS_PATH);
    }
}
