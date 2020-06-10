package ru.ifmo.firstspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ifmo.firstspring.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}