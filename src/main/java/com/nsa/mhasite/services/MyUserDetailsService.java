package com.nsa.mhasite.services;

import com.nsa.mhasite.config.MyUserPrincipal;
import com.nsa.mhasite.domain.User;
import com.nsa.mhasite.repositories.UserRepository;
import com.nsa.mhasite.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        try {
            User user = userRepository.findUserByUsername(username).get();
            List<String> userRoles = userRoleRepository.findRoleByUsername(username);
            return new MyUserPrincipal(user, userRoles);
        } catch (DataIntegrityViolationException e) {
            throw new UsernameNotFoundException(username);
        }
    }
}
