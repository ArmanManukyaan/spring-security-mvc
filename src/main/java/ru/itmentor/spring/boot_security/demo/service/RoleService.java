package ru.itmentor.spring.boot_security.demo.service;
import ru.itmentor.spring.boot_security.demo.entity.RoleEntity;
import java.util.List;

public interface RoleService {
  List<RoleEntity> findAll();
}