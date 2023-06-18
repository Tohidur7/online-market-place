//package com.onlinemarketplace.userManagementService.service;
//
//import com.onlinemarketplace.userManagementService.entity.Role;
//import com.onlinemarketplace.userManagementService.entity.User;
//import com.onlinemarketplace.userManagementService.exception.UserAlreadyExistException;
//import com.onlinemarketplace.userManagementService.repository.RoleRepository;
//import com.onlinemarketplace.userManagementService.repository.UserRepository;
//import com.onlinemarketplace.userManagementService.requestData.LoginRequestData;
//import com.onlinemarketplace.userManagementService.requestData.RegisterRequestData;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.Set;
//import java.util.UUID;
//
//@Service
//public class AuthServiceImpl implements AuthService{
//
//    private AuthenticationManager authenticationManager ;
//    private UserRepository userRepository ;
//    private RoleRepository roleRepository ;
//    private PasswordEncoder passwordEncoder ;
//
//    @Autowired
//    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
//        this.authenticationManager = authenticationManager;
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public String login(LoginRequestData loginRequestData) {
//
//        Authentication authenticate = authenticationManager.
//                authenticate(new UsernamePasswordAuthenticationToken(loginRequestData.getUsernameOrEmail(), loginRequestData.getPassword()));
//
//        SecurityContextHolder.getContext().setAuthentication(authenticate);
//
//        return "User logged in successfully.";
//    }
//
//    @Override
//    public String register(RegisterRequestData registerRequestData) {
//
//        // add check whether username is exists in database or not
//        if (userRepository.existsByUsername(registerRequestData.getUsername())) {
//            throw new UserAlreadyExistException("User already exist with the given username. please choose another username.");
//        }
//
//        // add check for email exists in the database
//        if (userRepository.existsByEmail(registerRequestData.getEmail())) {
//            throw new UserAlreadyExistException("User already exist with the given email. please provide another email.");
//        }
//
//        User user = new User() ;
//
//        String temp = "user"+ UUID.randomUUID() ;
//
//        user.setId(temp);
//        user.setName(registerRequestData.getName());
//        user.setUsername(registerRequestData.getUsername());
//        user.setEmail(registerRequestData.getEmail());
//        user.setPassword(passwordEncoder.encode(registerRequestData.getPassword()));
//
//        // for setting roles
//        Set<Role> roles = new HashSet<>();
//        Role userRole = roleRepository.findByName("ROLE_USER").get() ;
//        roles.add(userRole);
//
//        user.setRoles(roles);
//
//        userRepository.save(user);
//
//        return "YOU HAVE REGISTERED SUCCESSFULLY! PLEASE LOG IN AGAIN  WITH YOUR CREDENTIALS.";
//    }
//
//
//}
