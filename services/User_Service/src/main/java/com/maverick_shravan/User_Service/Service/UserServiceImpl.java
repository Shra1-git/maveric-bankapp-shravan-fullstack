package com.maverick_shravan.User_Service.Service;

import com.maverick_shravan.User_Service.UserRepo.UserRepository;
import com.maverick_shravan.User_Service.modal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override

    public User saveUser(User user) {
        logger.info("Saving user: {}", user);
        // Generate a UUID for the user
        long uuid = generateRandomLong();
        user.setId(String.valueOf(uuid));

        Date currentDate = new Date();
        user.setCreatedAt(currentDate);
        user.setUpdatedAt(currentDate);
        return userRepository.save(user);
    }

    @Override
    public User getUserById(String userId) {
        logger.info("Fetching user by ID: {}", userId);
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.orElse(null); // Return null if user is not found
    }
    @Override
    public User getUserByEmail(String email) {
        logger.info("Fetching user by email: {}", email);
        return userRepository.findByEmail(email);
    }

    private long generateRandomLong() {
        Random random = new Random();
        long uuid;
        do {
            uuid = random.nextLong(); // Generates a random long value
        } while (uuid <= 0); // Ensure the generated value is positive

        return uuid;
    }
}
