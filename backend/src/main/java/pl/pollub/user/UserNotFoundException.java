package pl.pollub.user;

import org.springframework.http.HttpStatus;
import pl.pollub.infrastructure.exception.CommonException;

public class UserNotFoundException extends CommonException {

    public UserNotFoundException(Object parameter) {
        super(parameter);
    }

    @Override
    public HttpStatus getHttpReturnStatus() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public String getMessage() {
        return "User [" + parameter + "] is not exist";
    }
}
