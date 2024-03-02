package com.pblgllgs.socialapp.service.impl;
/*
 *
 * @author pblgl
 * Created on 01-03-2024
 *
 */

import com.pblgllgs.socialapp.exception.UserAlreadyExistsException;
import com.pblgllgs.socialapp.models.User;
import com.pblgllgs.socialapp.models.dto.AuthResponseDto;
import com.pblgllgs.socialapp.models.dto.LoginRequestDto;
import com.pblgllgs.socialapp.models.dto.UserDefaultDto;
import com.pblgllgs.socialapp.repository.UserRepository;
import com.pblgllgs.socialapp.security.JwtService;
import com.pblgllgs.socialapp.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Value("${messages.service.user.already-exists}")
    private String messageEmailAlreadyExists;
    @Value("${messages.service.user.create-success}")
    private String messageUserSuccessfullyCreated;
    @Value("${messages.service.auth.password-not-matches}")
    private String messageAuthInvalidCredentials;
    @Value("${messages.service.user.login-success}")
    private String messageUserLoginSuccess;


    private final UserRepository userRepository;
    private final CustomerUserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponseDto register(UserDefaultDto userDefaultDto) {
        User userFound = userRepository.findByEmail(userDefaultDto.getEmail());
        if (userFound != null) {
            throw new UserAlreadyExistsException(messageEmailAlreadyExists + userDefaultDto.getEmail());
        }
        User user = new User();
        BeanUtils.copyProperties(userDefaultDto, user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user.getEmail(), user.getPassword()
        );
        user.setPassword(passwordEncoder.encode(userDefaultDto.getPassword()));
        userRepository.save(user);
        return new AuthResponseDto(jwtService.generateToken(authentication), messageUserSuccessfullyCreated);
    }

    @Override
    public AuthResponseDto login(LoginRequestDto loginRequestDto) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequestDto.email());
        if (!passwordEncoder.matches(loginRequestDto.password(), userDetails.getPassword())) {
            throw new BadCredentialsException(messageAuthInvalidCredentials);
        }
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequestDto.email(),
                loginRequestDto.password(),
                userDetails.getAuthorities()
        ));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return new AuthResponseDto(jwtService.generateToken(authenticate), messageUserLoginSuccess);
    }

}
