package renginiaiFx.Utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class registerValid {

    private static final String USER_NAME_REGEX_PATERN = "^[a-zA-z0-9]{5,12}$";
    private static final String USER_PASSWORD_REGEX_PATTERN = "^[a-zA-z0-9@!?_#&%.^]{5,12}$";
    private static final String USER_EMAIL_REGEX_PATTERN = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,50}$";

    public static boolean isValidUsername(String usernameRegister) {
        Pattern pattern = Pattern.compile(USER_NAME_REGEX_PATERN);
        Matcher matcher = pattern.matcher(usernameRegister);
        return matcher.find();
    }

    public static boolean isValidPassword(String passwordRegister) {
        Pattern pattern = Pattern.compile(USER_PASSWORD_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(passwordRegister);
        return matcher.find();
    }

    public static boolean isValidEmail(String emailRegister) {
        Pattern pattern = Pattern.compile(USER_EMAIL_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(emailRegister);
        return matcher.find();
    }
}