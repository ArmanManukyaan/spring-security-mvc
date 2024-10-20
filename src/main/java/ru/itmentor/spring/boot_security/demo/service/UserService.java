package ru.itmentor.spring.boot_security.demo.service;
import ru.itmentor.spring.boot_security.demo.entity.UserEntity;
import java.util.List;
import java.util.Optional;

public interface UserService {
    void save(UserEntity userEntity);
    List<UserEntity> findAll();
    Optional<UserEntity> findById(int id);
    void update(UserEntity user);
    void deleteById(int id);
    List<UserEntity> findByUsername(String username);

}