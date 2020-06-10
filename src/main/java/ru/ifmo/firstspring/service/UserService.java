package ru.ifmo.firstspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ifmo.firstspring.entity.Role;
import ru.ifmo.firstspring.entity.User;
import ru.ifmo.firstspring.repository.RoleRepository;
import ru.ifmo.firstspring.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager entityManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder encoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Autowired
    public void setEncoder(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("User Not Found");
        }
        return user;
    }

    public boolean saveUser(User user){
        User userFromDb =
                userRepository.findByUsername(user.getUsername());
        if (userFromDb != null){
            return false;
        }

        Role role = roleRepository.findById(1).get();
        user.getRoles().add(role);
        user.setPassword(encoder.encode(user.getPassword()));
        role.getUsers().add(user);
        userRepository.save(user);
        return true;
    }
}
