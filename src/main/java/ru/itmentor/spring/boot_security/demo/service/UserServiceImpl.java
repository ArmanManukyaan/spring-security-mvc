package ru.itmentor.spring.boot_security.demo.service;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.entity.RoleEntity;
import ru.itmentor.spring.boot_security.demo.entity.UserEntity;
import ru.itmentor.spring.boot_security.demo.repository.RoleRepository;
import ru.itmentor.spring.boot_security.demo.repository.UserRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public void save(UserEntity userEntity) {
        final Optional<RoleEntity> optionalRole = roleRepository.findByName("USER");
        optionalRole.ifPresent(role -> userEntity.setRoles(Collections.singletonList(role)));
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userRepository.save(userEntity);
    }

    @Override
    public List<UserEntity> findAll() {
        List<UserEntity> listUser = userRepository.findAll();
        return listUser.isEmpty() ? Collections.emptyList() : listUser;
    }

    @Override
    public Optional<UserEntity> findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public void update(UserEntity user) {
        Optional<UserEntity> userById = userRepository.findById(user.getId());
        userById.ifPresent(u -> {
            u.setUsername(user.getUsername());
            u.setAge(user.getAge());
            u.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(u);
        });
    }

    @Override
    public void deleteById(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }

    @Override
    public List<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);

    }
}