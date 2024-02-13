package validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomValidator implements ConstraintValidator<CustomConstraint, String> {
    private ValidationType validationType;
    private static final Logger log = LoggerFactory.getLogger(CustomValidator.class);
    @Override
    public void initialize(CustomConstraint constraintAnnotation) {
        this.validationType = constraintAnnotation.type();
    }
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
       if (value == null) {
           log.debug("Validation failed: value is null for type: {}", validationType);
           return false;
       }
        boolean isValid;
        isValid = switch (validationType) {
            case USERNAME -> validateUserName(value);
            case PASSWORD -> validatePassword(value);
            case EMAIL -> validateEmail(value);
            default -> false;
        };

        log.debug("Validation result for type: {}, value: {}, isValid: {}", validationType, value, isValid);
        return isValid;
    }
    private boolean validateUserName(String userName) {
        boolean isValid = userName.matches("\\w{3,20}");
        log.debug("Validating userName: {}, isValid: {}", userName, isValid);
        return isValid;
    }
    private boolean validatePassword(String password) {
        boolean isValid = password.matches("(?=.*[0-9])(?=.*[a-zA-Z]).{8,}");
        log.debug("Validating password: {}, isValid: {}", password, isValid);
        return isValid;    }
    private boolean validateEmail(String email) {
        boolean isValid = email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
        log.debug("Validating email: {}, isValid: {}", email, isValid);
        return isValid;    }
}
