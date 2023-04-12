package auth.response;

import auth.common.AppConstants;
import org.springframework.http.HttpStatus;

public class ErrorMessageResponse extends MessageResponse {
    public ErrorMessageResponse(String message) {
        super(null, message, HttpStatus.BAD_REQUEST.value());
    }

    public ErrorMessageResponse(Object data, String message) {
        super(data, message, HttpStatus.BAD_REQUEST.value());
    }

    public ErrorMessageResponse() {
        super(null, AppConstants.Error, HttpStatus.BAD_REQUEST.value());
    }
}
