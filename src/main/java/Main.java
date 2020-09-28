import org.mindrot.jbcrypt.BCrypt;

public class Main {
    public static void main(String[] args) {
        String password = "password123";
        String hash = BCrypt.hashpw(password, BCrypt.gensalt());
        boolean passwordsMatch = BCrypt.checkpw("mypassword", hash);
        System.out.println(passwordsMatch); // false
        passwordsMatch = BCrypt.checkpw("password123", hash);
        System.out.println(passwordsMatch); // true
    }
}
