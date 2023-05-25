package com.onlinemarketplace.userManagementService.service;

import com.onlinemarketplace.userManagementService.requestData.LoginRequestData;
import com.onlinemarketplace.userManagementService.requestData.RegisterRequestData;

public interface AuthService {

    String login(LoginRequestData loginRequestData);

    String register(RegisterRequestData registerRequestData);
}
