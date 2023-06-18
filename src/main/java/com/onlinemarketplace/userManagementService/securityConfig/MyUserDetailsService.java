//package com.onlinemarketplace.userManagementService.securityConfig;
//
//
//
//import com.onlinemarketplace.userManagementService.entity.User;
//import com.onlinemarketplace.userManagementService.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Service
//public class MyUserDetailsService implements UserDetailsService {
//
//    private UserRepository userRepository ;
//
//    @Autowired
//    public MyUserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
//
//        Optional<User> byUsernameOrEmail = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
//
//        if (byUsernameOrEmail.isEmpty()) {
//            throw new UsernameNotFoundException("User not found with username or email " + usernameOrEmail) ;
//        }
//        User user = byUsernameOrEmail.get();
//
//        Set<SimpleGrantedAuthority> authorities = user.getRoles().stream().map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
//
//        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),authorities);
//    }
//}
