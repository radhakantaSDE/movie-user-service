package com.movie.app.service;

import com.movie.app.model.dto.UserDto;
import com.movie.app.model.response.BaseResponse;

public interface UserService {

    BaseResponse<String> registerUser(UserDto user);
}
