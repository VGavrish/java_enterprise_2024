//package com.hillel.app.validation.aspect;
//
//import jakarta.validation.ValidationException;
//import openapitools.model.UserDto;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.validation.BeanPropertyBindingResult;
//import org.springframework.validation.Errors;
//import org.springframework.validation.Validator;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.aspectj.lang.annotation.Pointcut;
//
//@Aspect
//@Component
//public class ValidationAspect {
//    @Autowired
//    private Validator validator;
//
//    @Pointcut("execution(public * com.hillel.app.API.UsersApiController.createUser(..))")
//    public void createUserPointcut() {}
//
//    @Before("createUserPointcut")
//    public void validate(JoinPoint joinPoint) {
//        Object[] args = joinPoint.getArgs();
//        for (Object arg : args) {
//            if (arg instanceof UserDto) {
//                UserDto userDto = (UserDto) arg;
//                Errors errors = new BeanPropertyBindingResult(userDto, "userDto");
//                validator.validate(userDto, errors);
//            if (errors.hasErrors()) {
//                    throw new ValidationException("Validation failed: " + errors.getAllErrors().get(0).getDefaultMessage());
//                }
//            }
//        }
//    }
//
//    @ExceptionHandler(ValidationException.class)
//    public ResponseEntity<String> handleValidationException(ValidationException e) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//    }
//}
