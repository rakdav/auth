package com.example.auth.validations;

import com.example.auth.annotations.PasswordMatches;
import com.example.auth.payload.request.SignupRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches,Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        SignupRequest userSignupRequest=(SignupRequest) o;
        return userSignupRequest.getPassword().equals(userSignupRequest.getConfigurePassword());
    }
}
