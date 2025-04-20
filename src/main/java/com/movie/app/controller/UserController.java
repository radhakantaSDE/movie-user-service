package com.movie.app.controller;

import com.movie.app.model.dto.UserDto;
import com.movie.app.model.response.BaseResponse;
import com.movie.app.model.response.ErrorResponse;
import com.movie.app.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user-service/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @Operation(summary = "Use to register a new user", description = "Return status of operation and a message")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response",
                    content = @Content(schema = @Schema(implementation = BaseResponse.class))),
            @ApiResponse(responseCode = "404", description = "Failed to create user",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))})
    public ResponseEntity<BaseResponse<String>> registerUser(@RequestBody UserDto user) {

        log.info("User registration started for : {}", user.getEmailId());
        BaseResponse<String> response = userService.registerUser(user);
        log.info("User registration completed for : {}", user.getEmailId());
        return ResponseEntity.ok(response);
    }


}
