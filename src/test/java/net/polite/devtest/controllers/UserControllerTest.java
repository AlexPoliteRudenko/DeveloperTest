package net.polite.devtest.controllers;

import net.polite.devtest.exceptions.UserAlreadyExistsException;
import net.polite.devtest.repository.UserRepository;
import net.polite.devtest.repository.entities.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static net.polite.devtest.JsonConverter.convertObjectToJsonBytes;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    private User userValid;
//    private Set<User> db;
    private MockMvc mvc;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;


    @Before
    public void setUp() throws Exception {
        mvc = standaloneSetup(userController).build();
//
//        User user0 = new User("Hilary", "Clinton", "President", "forever");
//        User user1 = new User("Donald", "Trump", "TruePresident", "winner");
//        db = new HashSet<>();
//        db.add(user0);
//        db.add(user1);

        userValid = new User("Some first name", "The last name", "The user name", "The password in plain text");

    }

    @Test
    public void addNewValidUserReturnStatusOk() throws Exception {
        when(userRepository.createUser(userValid)).thenReturn(userValid);
        mvc.perform(post("/userservice/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(userValid)))
                .andExpect(status().isOk());
        verify(userRepository).createUser(userValid);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void addExistedUserReturnStatusConflict() throws Exception {
        when(userRepository.createUser(userValid)).thenThrow(UserAlreadyExistsException.class);
        mvc.perform(post("/userservice/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(userValid)))
                .andExpect(status().isConflict());
    }


}