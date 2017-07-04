package net.polite.devtest.repository;

import net.polite.devtest.entity.User;
import net.polite.devtest.exception.UserAlreadyExistsException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.Assert.assertTrue;

public class UserRepositoryImplTest {

    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userRepository = new UserRepositoryImpl(passwordEncoder);
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void addUserWithExistingUserNameThrowsUserAlreadyExistsException() throws Exception {
        User userNeo = new User("Aaa", "Aaa", "Neo", "pass");
        userRepository.createUser(userNeo);
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