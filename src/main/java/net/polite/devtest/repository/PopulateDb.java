package net.polite.devtest.repository;

import net.polite.devtest.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

public class PopulateDb {

    public static Set<User> populate() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Set<User> db = new HashSet<>();
        User user0 = new User("John", "Smith", "Agent", "MyMatrix");
        User user1 = new User("Mr", "Andersen", "Neo", "TheOne");
        user0.setHashedPassword(passwordEncoder.encode(user0.getPlainTextPassword()));
        user0.setId(Integer.toString(user0.getUserName().hashCode()));
        user1.setId(Integer.toString(user1.getUserName().hashCode()));
        user1.setHashedPassword(passwordEncoder.encode(user1.getPlainTextPassword()));
        db.add(user0);
        db.add(user1);
        return db;
    }
}
