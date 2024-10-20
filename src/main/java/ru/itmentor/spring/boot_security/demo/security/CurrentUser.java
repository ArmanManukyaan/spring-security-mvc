package ru.itmentor.spring.boot_security.demo.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import ru.itmentor.spring.boot_security.demo.entity.UserEntity;

import java.util.stream.Collectors;

public class CurrentUser extends User {
    private final UserEntity userEntity;

    public CurrentUser(UserEntity userEntity) {
        super(userEntity.getUsername(), userEntity.getPassword(),
                userEntity.getRoles().stream().
                        map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));
        this.userEntity = userEntity;
    }

    public UserEntity getUser() {
        return userEntity;
    }

}
