package auth.config;

import auth.common.UserConstants;
import auth.entity.User;
import auth.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(mail)
                .orElseThrow(() -> new UsernameNotFoundException(UserConstants.INVALID_ACCOUNT));

        return JwtDetails.build(user);
    }
}
