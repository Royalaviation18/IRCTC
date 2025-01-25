package ticket.booking.Service;

import ticket.booking.Entity.User;

public class UserBookingService {
    private User user;

    //calling constructor just to verify the current user before performing any function
    public UserBookingService(User user1) {
        this.user = user1;
    }
}
