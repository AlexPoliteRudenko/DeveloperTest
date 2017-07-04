package net.polite.devtest.repository;

import net.polite.devtest.exceptions.UserAlreadyExistsException;
import net.polite.devtest.repository.entities.ErrorMessage;
import net.polite.devtest.repository.entities.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserRepositoryImpl implements UserRepository {
    private Set<User> db;

    public UserRepositoryImpl() {
        db = new HashSet<>();
        User user0 = new User("John", "Smith", "Agent", "MyMatrix");
        User user1 = new User("Mr", "Andersen", "Neo", "TheOne");
        user0.setId(generateId(user0));
        user1.setId(generateId(user1));
        db.add(user0);
        db.add(user1);
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(db);
    }

    @Override
    public User createUser(User user) throws UserAlreadyExistsException {
        if (db.contains(user)) {
            throw new UserAlreadyExistsException(new ErrorMessage("USER_ALREADY_EXISTS","A user with the given username already exists"));
        } else {
            user.setId(generateId(user));
            db.add(user);
            return user;
        }
    }

    private String generateId(User user) {
        return Integer.toString(user.getUserName().hashCode());
    }

    @Override
    public void setDb(Set<User> db) {
        this.db = db;
    }
}
