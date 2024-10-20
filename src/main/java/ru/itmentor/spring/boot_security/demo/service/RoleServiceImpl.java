package ru.itmentor.spring.boot_security.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.entity.RoleEntity;
import ru.itmentor.spring.boot_security.demo.repository.RoleRepository;
import java.util.Collections;
import java.util.List;
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public List<RoleEntity> findAll() {
         List<RoleEntity> roleAll = roleRepository.findAll();
         return roleAll.isEmpty() ? Collections.emptyList() : roleAll;
    }
}