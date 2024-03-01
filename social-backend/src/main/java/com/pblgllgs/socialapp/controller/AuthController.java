package com.pblgllgs.socialapp.controller;

import com.pblgllgs.socialapp.models.dto.AuthResponseDto;
import com.pblgllgs.socialapp.models.dto.LoginRequestDto;
import com.pblgllgs.socialapp.models.dto.UserDefaultDto;
import com.pblgllgs.socialapp.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 *
 * @author pblgl
 * Created on 01-03-2024
 *
 */
@RestController
@RequestMapping("/api/1.0/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody UserDefaultDto userDefaultDto) {
        return new ResponseEntity<>(authService.register(userDefaultDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        log.info(loginRequestDto.toString());
        return new ResponseEntity<>(authService.login(loginRequestDto), HttpStatus.OK);
    }
}
