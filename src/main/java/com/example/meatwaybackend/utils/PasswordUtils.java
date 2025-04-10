package com.example.meatwaybackend.utils;

import com.example.meatwaybackend.handler.exception.auth.NonValidPasswordException;
import com.example.meatwaybackend.handler.exception.auth.PasswordsNotMatchException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PasswordUtils {
    private static final int MIN_PASSWORD_LENGTH = 8;

    public static void validatePassword(String password) {
        if (password == null || password.length() < MIN_PASSWORD_LENGTH) {
            throw new NonValidPasswordException();
        }
    }

    public static void validatePasswordMatch(@Size(min = 3) @NotBlank String password, @Size(min = 3) @NotBlank String confirmPassword) {
        validatePassword(password);
        if (!password.equals(confirmPassword)) {
            throw new PasswordsNotMatchException();
        }
    }
}
