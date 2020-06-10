package ru.ifmo.firstspring.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.ifmo.firstspring.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
