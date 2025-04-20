package com.movie.app.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.app.exception.custom.UserServiceOperationException;
import com.movie.app.exception.custom.UserValidationException;
import com.movie.app.model.dto.UserDto;
import com.movie.app.model.entity.UserEntity;
import com.movie.app.model.response.BaseResponse;
import com.movie.app.repository.UserRepository;
import com.movie.app.service.UserService;
import com.movie.app.util.RequestValidatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.movie.app.util.Constant.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public BaseResponse<String> registerUser(UserDto user) {

        try {

            Map<String, String> checkResults = RequestValidatorUtil.validateCreateUserRequest(user);
            if (!checkResults.isEmpty()) {
                throw new UserValidationException("Invalid date to create user. Please verify details error.", checkResults);
            }

            UserEntity userEntity = objectMapper.convertValue(user, UserEntity.class);
            UserEntity newUser = userRepository.save(userEntity);
            BaseResponse<String> response = BaseResponse.<String>builder().build();

            if (newUser.getId() != null) {
                response.setStatus(SUCCESS);
                response.setMessage("User registered successfully!");
            }else {
                response.setStatus(FAILED);
                response.setMessage("Failed to register user");
            }
            return response;
        } catch (UserValidationException ex) {
            log.error("User creation failed in validation {}", ex.getErrorMessage());
            throw ex;
        }
        catch (Exception ex) {
            log.error("Exception in user registration");
            throw new UserServiceOperationException("Failed to register user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
