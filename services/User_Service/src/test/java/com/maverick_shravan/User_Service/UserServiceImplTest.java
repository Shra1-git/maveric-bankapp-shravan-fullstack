package com.maverick_shravan.User_Service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.maverick_shravan.User_Service.Service.UserServiceImpl;
import com.maverick_shravan.User_Service.UserRepo.UserRepository;
import com.maverick_shravan.User_Service.modal.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testSaveUser() {
        // Create a sample user object
        User user = new User();
        user.setFirstName("Shravan Jagu");
        user.setEmail("shravan@maveric.com");

        // Mock the save method of the userRepository
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Call the saveUser method
        User savedUser = userService.saveUser(user);

        // Verify that the save method of userRepository is called with the expected user object
        verify(userRepository, times(1)).save(user);

        // Assert that the saved user matches the expected user
        assertEquals("Shravan Jagu", savedUser.getFirstName());
        assertEquals("shravan@maveric.com", savedUser.getEmail());
    }

    @Test
    public void testGetUserByEmail() {
        // Mock user data
        String email = "john.doe@example.com";
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail(email);

        // Mock UserRepository.findByEmail method
        when(userRepository.findByEmail(email)).thenReturn(user);

        // Call the method to be tested
        User foundUser = userService.getUserByEmail(email);

        // Verify that UserRepository.findByEmail was called with the correct email
        verify(userRepository, times(1)).findByEmail(email);

        // Assert that the returned user matches the expected user data
        assertEquals(user.getId(), foundUser.getId());
        assertEquals(user.getFirstName(), foundUser.getFirstName());
        assertEquals(user.getLastName(), foundUser.getLastName());
        assertEquals(user.getEmail(), foundUser.getEmail());
    }
}
