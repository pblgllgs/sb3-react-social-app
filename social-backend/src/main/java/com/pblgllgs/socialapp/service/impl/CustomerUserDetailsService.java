package com.pblgllgs.socialapp.service.impl;
/*
 *
 * @author pblgl
 * Created on 01-03-2024
 *
 */

import com.pblgllgs.socialapp.models.User;
import com.pblgllgs.socialapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {

    @Value("${messages.service.user.not-found}")
    private String messageUserNotFound;

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User userFound = userRepository.findByEmail(email);
        if (userFound == null) {
            throw new UsernameNotFoundException(messageUserNotFound + email);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();

        return new org.springframework.security.core.userdetails.User(
                userFound.getEmail(), userFound.getPassword(), authorities);

    }
}
