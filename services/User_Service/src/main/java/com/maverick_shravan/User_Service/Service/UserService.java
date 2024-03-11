package com.maverick_shravan.User_Service.Service;

import com.maverick_shravan.User_Service.modal.User;

public interface UserService {

    User saveUser(User user);
    User getUserByEmail(String email);
    User getUserById(String userId);

}
