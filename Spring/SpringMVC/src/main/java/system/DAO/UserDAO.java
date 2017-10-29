package system.DAO;

import org.springframework.stereotype.Repository;
import system.model.User;

import java.util.Arrays;
import java.util.List;

@Repository
public class UserDAO {
    private List<User> users = Arrays.asList(
            new User("admin", "admin"),
            new User("user1", "user1")
    );

    public List<User> getAllUsers() {
        return users;
    }

    public boolean containUser(User user) {
        System.out.println("Debug: " + user);
        System.out.printf("Equal: " + users.contains(user));

        for (User usr : users) {
            if (usr.getLogin().equals(user.getLogin()) &&
                    usr.getPassword().equals(user.getPassword())) return true;
        }

        return false;
    }
}
