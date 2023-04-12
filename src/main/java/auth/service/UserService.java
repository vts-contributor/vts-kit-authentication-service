package auth.service;

import auth.request.LoginFormRequest;
import auth.request.SignUpFormRequest;
import auth.response.MessageResponse;

public interface UserService {
    MessageResponse register(SignUpFormRequest signUpFormRequest);
    MessageResponse login(LoginFormRequest loginFormRequest);
}
