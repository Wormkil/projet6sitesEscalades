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

public void tmp
    @Autowired
    private UserRepository userRepository;

{
        String sql = "select * from users where email = 'maVariableEmail'"
    }

    public Optional<User> getUser(final Long id) {
        return userRepository.findById(id);
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
