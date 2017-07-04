package net.polite.devtest.repository;

import net.polite.devtest.entity.User;
import net.polite.devtest.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class UserRepositoryImpl implements UserRepository {

    private Set<User> db;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserRepositoryImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        db = PopulateDb.populate();
    }


    @Override
    public List<User> getAll() {
        return new ArrayList<>(db);
    }

    @Override
    public User createUser(User user) throws UserAlreadyExistsException {
        if (db.contains(user)) {
            throw new UserAlreadyExistsException("A user with the given username already exists", "USER_ALREADY_EXISTS");
        } else {
            user.setId(generateId(user));
            user.setHashedPassword(passwordEncoder.encode(user.getPlainTextPassword()));
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
