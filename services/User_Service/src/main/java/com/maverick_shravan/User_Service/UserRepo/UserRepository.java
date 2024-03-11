package com.maverick_shravan.User_Service.UserRepo;

import com.maverick_shravan.User_Service.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
}
