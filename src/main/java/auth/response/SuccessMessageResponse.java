package auth.response;

import auth.common.AppConstants;
import org.springframework.http.HttpStatus;

public class SuccessMessageResponse extends MessageResponse {

    public SuccessMessageResponse(String message) {
        super(null, message, HttpStatus.OK.value());
    }

    public SuccessMessageResponse(Object data, String message) {
        super(data, message, HttpStatus.OK.value());
    }

    public SuccessMessageResponse() {
        super(null, AppConstants.SUCCESS, HttpStatus.OK.value());
    }

    public SuccessMessageResponse(Object data) {
        super(data, AppConstants.SUCCESS, HttpStatus.OK.value());
    }
}
