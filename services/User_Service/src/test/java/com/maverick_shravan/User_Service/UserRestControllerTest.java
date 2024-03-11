package com.maverick_shravan.User_Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.maverick_shravan.User_Service.RestController.UserRestController;
import com.maverick_shravan.User_Service.Service.UserService;
import com.maverick_shravan.User_Service.modal.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class UserRestControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserRestController userController;

    @Test
    public void testCreateUser() {
        // Create a sample user object
        User user = new User();
        user.setFirstName("Maveric Shravan");
        user.setEmail("maveric@maver.com");

        // Mock the saveUser method of the userService
        when(userService.saveUser(any(User.class))).thenReturn(user);

        // Call the createUser method
        ResponseEntity<User> response = userController.createUser(user);

        // Verify that the saveUser method of userService is called with the expected user object
        verify(userService, times(1)).saveUser(user);

        // Assert that the response is a successful response with the correct user object
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Maveric Shravan", response.getBody().getFirstName());
        assertEquals("maveric@maver.com", response.getBody().getEmail());
    }

    @Test
    public void testGetUserByEmail_UserFound() {
        // Create a sample user
        User user = new User();
        user.setId("1"); // Setting ID as a String
        user.setFirstName("Mala");
        user.setLastName("Sheela");
        user.setEmail("male@gmail.com"); // Setting the email for the mocked user

        // Mock userService.getUserByEmail method
        when(userService.getUserByEmail("male@gmail.com")).thenReturn(user);

        // Call the getUserByEmail method
        ResponseEntity<User> responseEntity = userController.getUserByEmail("male@gmail.com");

        // Verify that userService.getUserByEmail was called with the provided email
        verify(userService, times(1)).getUserByEmail("male@gmail.com");

        // Check the response status
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Check that the returned user matches the provided user
        assertEquals(user, responseEntity.getBody());
    }
}
