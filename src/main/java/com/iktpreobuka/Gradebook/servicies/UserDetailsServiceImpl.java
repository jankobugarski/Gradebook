package com.iktpreobuka.Gradebook.servicies;

import com.iktpreobuka.Gradebook.dto.UserSecurity;
import com.iktpreobuka.Gradebook.entities.Roles;
import com.iktpreobuka.Gradebook.entities.User;
import com.iktpreobuka.Gradebook.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException("User not found1"));
//        //TODO get roles from user_role table
//        user.setRoles(new Roles(1L, "ADMIN", null));
//
        return new UserSecurity(user);

}}
