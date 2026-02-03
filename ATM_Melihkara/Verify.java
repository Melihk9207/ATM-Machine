public class Verify {

    static String Login = "melih";
    static String Password = "1234";

    boolean check(String login, String password) {
        return login.equals(Login) && password.equals(Password);
    }
}