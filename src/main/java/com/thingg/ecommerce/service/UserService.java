package com.thingg.ecommerce.service;

import com.thingg.ecommerce.io.UserRequest;
import com.thingg.ecommerce.io.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest request);

    String getUserRole(String email);

    List<UserResponse> read();

    void delete(String userId);
}
