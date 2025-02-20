package ticket.booking.util;

import org.mindrot.jbcrypt.BCrypt;

public class UserServiceUtil {

    // no need of creating object to access as its a static method and will
    //be available in the RAM
    //function which hashes/encrypts the plain password
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    //function to verify the plain password entered bu the user while logging and match it with the same user password
    //stored in the db
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
