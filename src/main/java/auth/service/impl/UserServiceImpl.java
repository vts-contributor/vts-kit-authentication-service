package auth.service.impl;

import auth.common.AppConstants;
import auth.common.CommonConstants;
import auth.common.UserConstants;
import auth.common.ValidationRegex;
import auth.common.enums.RoleName;
import auth.config.JwtTokenUtils;
import auth.entity.Role;
import auth.entity.User;
import auth.repository.RoleRepository;
import auth.repository.UserRepository;
import auth.request.LoginFormRequest;
import auth.request.SignUpFormRequest;
import auth.response.ErrorMessageResponse;
import auth.response.JwtResponse;
import auth.response.MessageResponse;
import auth.response.SuccessMessageResponse;
import auth.service.UserService;
import auth.utils.BCryptUtils;
import auth.utils.exceptions.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptUtils bCryptUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    private String validationSignUpFormRequest(SignUpFormRequest signUpFormRequest) {
        if (signUpFormRequest.getEmail().equals("")) {
            return UserConstants.EMAIL_NULL;
        } else if (!signUpFormRequest.getEmail().matches(ValidationRegex.EMAIL_PATTERN)) {
            return UserConstants.INVALID_EMAIL_FORMAT;
        } else if (signUpFormRequest.getPassword().equals("")) {
            return UserConstants.PASSWORD_NULL;
        } else if (userRepository.existsByEmail(signUpFormRequest.getEmail())) {
            return UserConstants.EMAIL_EXISTS;
        } else if (roleRepository.findByName(RoleName.ROLE_USER) == null) {
            return UserConstants.ROLE_NOT_EXISTS;
        }

        return null;
    }


    @Override
    public MessageResponse registerWithUserRole(SignUpFormRequest signUpFormRequest) {
        String valid = validationSignUpFormRequest(signUpFormRequest);
        if (valid != null)
            return new ErrorMessageResponse(valid);

        User newUser = new User();
        newUser.setActive(true);
        newUser.setEmail(signUpFormRequest.getEmail());
        newUser.setPassword(bCryptUtils.bcryptEncoder(signUpFormRequest.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER);
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        newUser.setRoles(roles);
        newUser = userRepository.save(newUser);

        if (newUser == null)
            throw new CustomException(AppConstants.Error);

        return new SuccessMessageResponse();
    }

    @Override
    public MessageResponse login(LoginFormRequest loginFormRequest) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginFormRequest.getEmail());
        if (!bCryptUtils.compare(loginFormRequest.getPassword(), userDetails.getPassword())) {
            return new ErrorMessageResponse(UserConstants.INVALID_ACCOUNT);
        }
        
        final String accessToken = jwtTokenUtils.generateToken(userDetails);
        final String refreshToken = jwtTokenUtils.generateRefreshToken(userDetails);
        JwtResponse jwtResponse = new JwtResponse(accessToken, refreshToken);

        return new SuccessMessageResponse(jwtResponse, CommonConstants.LOGIN_SUCCESS);
    }
}
