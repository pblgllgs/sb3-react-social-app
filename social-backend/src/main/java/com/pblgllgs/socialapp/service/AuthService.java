package com.pblgllgs.socialapp.service;

import com.pblgllgs.socialapp.models.dto.AuthResponseDto;
import com.pblgllgs.socialapp.models.dto.LoginRequestDto;
import com.pblgllgs.socialapp.models.dto.UserDefaultDto;

public interface AuthService {

    AuthResponseDto register(UserDefaultDto userDefaultDto);

    AuthResponseDto login(LoginRequestDto loginRequestDto);
}
