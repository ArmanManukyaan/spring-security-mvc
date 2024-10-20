package ru.itmentor.spring.boot_security.demo.security;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.entity.UserEntity;
import ru.itmentor.spring.boot_security.demo.repository.UserRepository;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findFirstByUsername(username);
        if (user.isPresent()) {
            return new CurrentUser(user.get());
        } else {
            throw new UsernameNotFoundException("Invalid username or password" + username);
        }
    }
}
