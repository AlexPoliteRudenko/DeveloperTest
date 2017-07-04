package net.polite.devtest.repository;

import net.polite.devtest.exceptions.UserAlreadyExistsException;
import net.polite.devtest.repository.entities.User;

import java.util.List;
import java.util.Set;


public interface UserRepository {

    public List<User> getAll();

    User createUser(User user) throws UserAlreadyExistsException;

    void setDb(Set<User> db);
}
