package net.polite.devtest.controller;

import net.polite.devtest.entity.User;
import net.polite.devtest.exception.UserAlreadyExistsException;
import net.polite.devtest.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.Properties;

import static net.polite.devtest.JsonConverter.convertObjectToJsonBytes;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    private User userValid;
    private MockMvc mvc;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;


    @Before
    public void setUp() throws Exception {
        mvc = standaloneSetup(userController)
                .setHandlerExceptionResolvers(exceptionResolver())
                .build();
        userValid = new User("Some first name", "The last name", "The user name", "The password in plain text");

    }

    @Test
    public void addNewValidUserReturnStatusCreated() throws Exception {
        when(userRepository.createUser(userValid)).thenReturn(userValid);
        mvc.perform(post("/userservice/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(userValid)))
                .andExpect(status().isCreated());
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

    @Test
    public void addExistedUserThrowsNullPointerException() throws Exception {
        mvc.perform(post("/userservice/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes("{Wrong value}")))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void wrongRequestReturn404Error() throws Exception {
        mvc.perform(post("/wrong/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes("")))
                .andExpect(status().isNotFound());
    }


    private HandlerExceptionResolver exceptionResolver() {
        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();

        Properties exceptionMappings = new Properties();

        exceptionMappings.put("net.polite.devtest.exception.UserAlreadyExistsException", "error/409");
        exceptionMappings.put("java.lang.Exception", "error/error");
        exceptionMappings.put("java.lang.RuntimeException", "error/error");

        exceptionResolver.setExceptionMappings(exceptionMappings);

        Properties statusCodes = new Properties();

        statusCodes.put("error/404", "404");
        statusCodes.put("error/409", "409");
        statusCodes.put("error/error", "500");

        exceptionResolver.setStatusCodes(statusCodes);

        return exceptionResolver;
    }
}