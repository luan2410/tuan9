package iuh.fit.se.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidationException extends RuntimeException {
    private final String errorMessage;
    private final Map<String, Object> errors;

    public ValidationException(String message, Map<String, Object> errors) {
        super(message);
        this.errorMessage = message;
        this.errors = errors;
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }

    public Map<String, Object> getErrors() {
        return errors;
    }
}

