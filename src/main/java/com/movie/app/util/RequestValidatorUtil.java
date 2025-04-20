package com.movie.app.util;

import com.movie.app.exception.custom.UserServiceOperationException;
import com.movie.app.exception.custom.UserValidationException;
import com.movie.app.model.dto.UserDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestValidatorUtil {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    public static Map<String, String> validateCreateUserRequest(UserDto user) {

        Map<String, String> params = new HashMap<>();
        if (user == null) {
            throw new UserValidationException("User data can't be empty for registration");
        }

        if (user.getEmailId() == null || user.getEmailId().isEmpty() || !isValidEmailId(user.getEmailId()))
            params.put("emailId", "EmailId data can't be empty or invalid email");

        if (user.getPassword() == null || user.getPassword().isEmpty())
            params.put("password", "password can't be empty");

        return params;
    }

    public static boolean isValidEmailId(String emailId) {

        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(emailId);
        return matcher.matches();
    }
}
