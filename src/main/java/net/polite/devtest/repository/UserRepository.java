package net.polite.devtest.repository;

import net.polite.devtest.exception.UserAlreadyExistsException;
import net.polite.devtest.entity.User;

import java.util.List;
import java.util.Set;


public interface UserRepository {

    List<User> getAll();

    User createUser(User user) throws UserAlreadyExistsException;

    void setDb(Set<User> db);
}
