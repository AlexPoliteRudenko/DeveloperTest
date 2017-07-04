package net.polite.devtest.repository;

import net.polite.devtest.exceptions.UserAlreadyExistsException;
import net.polite.devtest.repository.entities.User;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertTrue;

public class UserRepositoryImplTest {

    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        userRepository = new UserRepositoryImpl();
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void addUserWithExistingUserNameThrowsUserAlreadyExistsException() throws Exception {
        User userNeo = new User("Aaa", "Aaa", "Neo", "pass");
        userRepository.createUser(userNeo);
    }

    @Test
    public void getAllMethodShouldReturnEmptyListIfDBHasNoItems() throws Exception {
        userRepository.setDb(new HashSet<>());
        assertTrue(userRepository.getAll().isEmpty());
    }

    @Test
    public void addNewUserWithUniqueUserNameShouldReturnThisUserWithId() throws Exception {
        User userUnique = new User("TestName", "TestSurname", "TestUserName", "TestPassword");
        User result = userRepository.createUser(userUnique);
        assertTrue(result.getFirstName().equals(userUnique.getFirstName()));
        assertTrue(result.getLastName().equals(userUnique.getLastName()));
        assertTrue(result.getUserName().equals(userUnique.getUserName()));
        assertTrue(result.getId() != null);
        assertTrue(userRepository.getAll().size() == 3);
    }

}