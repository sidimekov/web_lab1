package exception;

import java.io.IOException;

public class InvalidParametersException extends IOException {
    public InvalidParametersException(String message) {
        super(message);
    }
}
