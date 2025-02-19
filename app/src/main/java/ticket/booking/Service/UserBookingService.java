package ticket.booking.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.Entity.User;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class UserBookingService {

    //global user which can be used by any function
    private User user;

    private List<User> userList;

    // object mapper is used to serialize and deserialize
    private ObjectMapper objectMapper = new ObjectMapper();

    //final so that no one can change the path
    private static final String USERS_PATH = "../LocalDB/users.json";

    //calling constructor just to verify the current user before performing any function
    public UserBookingService(User user1) throws IOException {
        this.user = user1;
        File users = new File(USERS_PATH);
        userList = objectMapper.readValue(users, new TypeReference<List<User>>() {
        });
    }
}
