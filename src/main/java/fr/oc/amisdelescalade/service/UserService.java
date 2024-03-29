package fr.oc.amisdelescalade.service;

import fr.oc.amisdelescalade.model.User;
import fr.oc.amisdelescalade.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Data
@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserById(final Long id) {
        return userRepository.findById(id);
    }
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public Optional<User> getUserByUserName(String user_name) {
        return userRepository.findByUserName(user_name);
    }

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(final Long id) {
        userRepository.deleteById(id);
    }

    public User saveUser(User user) {
        User savedUser = userRepository.save(user);
        return savedUser;
    }



}
