package ru.itmentor.spring.boot_security.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmentor.spring.boot_security.demo.entity.UserEntity;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    List<UserEntity> findByUsername(String username);
    Optional<UserEntity> findFirstByUsername(String username);

}